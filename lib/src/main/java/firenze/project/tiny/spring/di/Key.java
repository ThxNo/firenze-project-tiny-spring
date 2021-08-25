package firenze.project.tiny.spring.di;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Key<T> {
    private Class<T> clazz;
    private String name;

    public Key(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Key(String name, Class<T> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public static <T> Key<T> get(Class<T> clazz) {
        return new Key<>(clazz);
    }

    public static <T> Key<T> get(String name, Class<T> clazz) {
        return new Key<>(name, clazz);
    }

    public void setName(String name) {
        this.name = name;
    }
}
