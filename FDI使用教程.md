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

## 使用@Inject定义Class依赖关系

```java
public class Car {
  @Inject
	private Engine engine;
		
	public getEngine() { return engine; }
}
```

从Container中获取注入的Car，此时Container中获取的Car实例的engine名称便是AEngine

```java
Engine car = container.get(Car.class);
assertThat(car.getEngine().class).isEqualTo(AEngine.class);
```

