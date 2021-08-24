/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package firenze.project.tiny.spring;

import com.thoughtworks.fusheng.integration.junit5.FuShengTest;
import firenze.project.tiny.spring.di.ContainerConfig;
import firenze.project.tiny.spring.di.FDIContainer;
import firenze.project.tiny.spring.example.Car;
import firenze.project.tiny.spring.example.Engine;
import firenze.project.tiny.spring.example.MockContainerConfig;

@FuShengTest
public class FDISpeTest {
    private FDIContainer container;
    public void initContainer() {
        ContainerConfig containerConfig = new MockContainerConfig();
        container = new FDIContainer(containerConfig);
    }

    public String getEngineName() {
        return container.get(Engine.class).getName();
    }

    public String getCarName() {
        return container.get(Car.class).getName();
    }

    public String getCarEngineName() {
        return container.get(Car.class).getEngine().getName();
    }
}
