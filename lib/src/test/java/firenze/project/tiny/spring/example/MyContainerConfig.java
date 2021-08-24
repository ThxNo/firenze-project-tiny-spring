package firenze.project.tiny.spring.example;

import firenze.project.tiny.spring.di.ContainerConfig;

public class MyContainerConfig extends ContainerConfig {
    @Override
    public void init() {
        bind(Engine.class).to(AEngine.class);
    }
}
