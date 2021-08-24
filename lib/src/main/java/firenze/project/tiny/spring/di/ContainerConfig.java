package firenze.project.tiny.spring.di;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ContainerConfig {
    private final List<Binder<?>> binders = new ArrayList<>();
    public abstract void init();

    public ContainerConfig() {
        init();
    }

    public <T> Binder bind(Class<T> clazz) {
        Binder<T> binder = new Binder<>(Key.get(clazz));
        binders.add(binder);
        return binder;
    }

    public Map<Key<?>, Value> toMap() {
        return binders.stream().collect(Collectors.toMap(Binder::getKey, Binder::getValue));
    }
}
