package firenze.project.tiny.spring.di.container;

import firenze.project.tiny.spring.di.model.Binder;
import firenze.project.tiny.spring.di.model.Key;
import firenze.project.tiny.spring.di.model.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ContainerConfig {
    private final List<Binder<?>> binders = new ArrayList<>();
    public abstract void init();

    public <T> Binder<T> bind(Class<T> clazz) {
        Binder<T> binder = new Binder<>(Key.get(clazz));
        binders.add(binder);
        return binder;
    }

    public Map<Key<?>, Value> toMap() {
        return binders.stream().collect(Collectors.groupingBy(Binder::getKey))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        it -> it.getValue().stream().map(Binder::getValue).findFirst().get()));
    }
}
