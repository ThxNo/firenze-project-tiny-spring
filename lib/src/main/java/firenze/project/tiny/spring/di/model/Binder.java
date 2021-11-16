package firenze.project.tiny.spring.di.model;

public class Binder<T> {
    private final Key<T> key;
    private final Value value;

    public Binder(Key<T> key) {
        this.key = key;
        this.value = new Value();
    }

    public Key<T> getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public Binder<T> to(Class<?> clazz) {
        value.setType(clazz);
        return this;
    }

    public Binder<T> named(String name) {
        key.setName(name);
        return this;
    }
}
