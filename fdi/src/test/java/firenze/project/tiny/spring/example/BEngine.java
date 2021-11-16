package firenze.project.tiny.spring.example;

import firenze.project.tiny.spring.di.annotations.Named;

@Named("BEngine")
public class BEngine implements Engine {
    @Override
    public String getName() {
        return "BEngine";
    }
}
