package firenze.project.tiny.spring.di;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FDIContainer {
    private Map<Key<?>, Value> map;
    private List<Key> keyLock = new ArrayList<>();

    public FDIContainer(ContainerConfig config) {
        map = config.toMap();
    }

    public <T> T get(Class<T> clazz) {
        Key<T> key = Key.get(clazz);
        return inject(key);
    }

    private <T> T inject(Key<T> key) {
        if (keyLock.contains(key)) throw new RuntimeException("key is using!");
        keyLock.add(key);
        Object obj = constructorInject(map.get(key));
        fieldInject(obj);
        keyLock.remove(key);
        return (T) obj;
    }

    private Object constructorInject(Value value) {
        Constructor<?>[] constructors = value.getType().getConstructors();
        return Stream.of(constructors)
                .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                .findFirst()
                .map(constructor -> {
                    try {
                        return constructor.newInstance(Arrays.stream(constructor.getParameterTypes()).map(this::get).toArray());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("constructor inject failed");
                    }
                }).orElse(value.getObj());
    }

    private void fieldInject(Object obj) {
        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Stream.of(declaredFields)
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(obj, this.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
