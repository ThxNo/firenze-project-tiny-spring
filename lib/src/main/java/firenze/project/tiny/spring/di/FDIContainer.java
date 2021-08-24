package firenze.project.tiny.spring.di;


import java.util.Map;

public class FDIContainer {
    private Map<Key<?>, Value> map;

    public FDIContainer(ContainerConfig config) {
        map = config.toMap();
    }

    public <T> T get(Class<T> clazz) {
        return map.get(Key.get(clazz)).getObj();
    }
}
