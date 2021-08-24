package firenze.project.tiny.spring.di;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Key<T> {
    private Class<T> clazz;

    public Key(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> Key<T> get(Class<T> clazz) {
        return new Key<>(clazz);
    }
}
