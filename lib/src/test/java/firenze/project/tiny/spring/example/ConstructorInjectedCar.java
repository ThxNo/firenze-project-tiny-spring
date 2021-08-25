package firenze.project.tiny.spring.example;

import firenze.project.tiny.spring.di.Inject;

public class ConstructorInjectedCar implements Car {
    private Engine engine;

    public ConstructorInjectedCar() {
    }
    @Inject
    public ConstructorInjectedCar(Engine engine) {
        this.engine = engine;
    }

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public String getName() {
        return "ConstructorInjectedCar";
    }
}
