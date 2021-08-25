package firenze.project.tiny.spring.example;

import firenze.project.tiny.spring.di.Inject;
import firenze.project.tiny.spring.di.Named;

public class NamedEngineCar implements Car {
    @Inject
    @Named("BEngine")
    private Engine engine;

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public String getName() {
        return "NamedEngineCar";
    }
}
