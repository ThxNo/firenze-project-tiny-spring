package firenze.project.tiny.spring.example;

import firenze.project.tiny.spring.di.Inject;

public class FieldInjectedCar implements Car {
    @Inject
    private Engine engine;

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public String getName() {
        return "FieldInjectedCar";
    }
}
