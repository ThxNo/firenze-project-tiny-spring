package firenze.project.tiny.spring.example;

import firenze.project.tiny.spring.di.annotations.Inject;
import firenze.project.tiny.spring.di.annotations.Named;

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
