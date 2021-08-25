# FDI使用教程

## A Simple Example

定义需要注入的Class

```java
public class AEngine implements Engine {
	@Override
	public String getName() { return "AEngine"; }
}
```

将需要注入的Class添加进Container中进行管理

```java
ContainerConfig config = new ContainerConfig() {
  @Override
            public void configure() {
              bind(Engine.class).to(AEngine.class);
            }
};

FDIContainer container = new FDIContainer(config);
```

从Container中获取注入的Class，此时从Container中获取的engine名称便是AEngine

```java
Engine engine = container.get(Engine.class);
assertThat(engine.getName()).isEqualTo("AEngine");
```

## 使用@Inject实现Class依赖注入

@Inject实现依赖注入有两种方式，构造器注入和field注入

### Field注入

```java
public class FieldInjectedCar implements Car {
  @Inject
	private Engine engine;
		
	public getEngine() { return engine; }
}
```

从Container中获取注入的Car，此时Container中获取的Car实例的engine名称便是AEngine

```java
Engine car = container.get(FieldInjectedCar.class);
assertThat(car.getEngine().class).isEqualTo(AEngine.class);
```

### 构造器注入

```java
public class ConstructorInjectedCar implements Car {
	private Engine engine;
	@Inject
  public ConstructorInjectedCar(Engine engine) { this.engine = engine; }
	public getEngine() { return engine; }
}
```

从Container中获取注入的Car，此时Container中获取的Car实例的engine名称便是AEngine

```java
Engine car = container.get(FieldInjectedCar.class);
assertThat(car.getEngine().class).isEqualTo(AEngine.class);
```

## 使用@Named指定注入的实例对象

```java
public class AEngine implements Engine {
	@Override
	public String getName() { return "AEngine"; }
}
```

```java
public class BEngine implements Engine {
	@Override
	public String getName() { return "BEngine"; }
}
```

将其注入到Container中后，获取Engine的实例

```java
public class Car {
  @Inject
	private @Named("BEngine") Engine engine;
		
	public getEngine() { return engine; }
}
```

从Container中获取注入的Car，此时Container中获取的Car实例的engine名称便是BEngine

```java
Engine car = container.get(Car.class);
assertThat(car.getEngine().class).isEqualTo(BEngine.class);
```
