package firenze.project.tiny.spring.example;

import firenze.project.tiny.spring.di.ContainerConfig;

public class MockContainerConfig extends ContainerConfig {
    @Override
    public void init() {
        bind(Engine.class).to(AEngine.class);
        bind(Car.class).to(FieldInjectedCar.class);
        bind(ConstructorInjectedCar.class).to(ConstructorInjectedCar.class);
    }
}
