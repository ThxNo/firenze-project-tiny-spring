package firenze.project.tiny.spring.example;

import firenze.project.tiny.spring.di.ContainerConfig;

public class MockContainerConfig extends ContainerConfig {
    @Override
    public void init() {
        bind(Engine.class).to(AEngine.class);
        bind(Engine.class).named("BEngine").to(BEngine.class);
        bind(FieldInjectedCar.class).to(FieldInjectedCar.class);
        bind(Car.class).to(NamedEngineCar.class);
        bind(ConstructorInjectedCar.class).to(ConstructorInjectedCar.class);
    }
}
