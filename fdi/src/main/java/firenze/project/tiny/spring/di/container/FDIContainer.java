package firenze.project.tiny.spring.di.container;


import firenze.project.tiny.spring.di.annotations.Inject;
import firenze.project.tiny.spring.di.annotations.Named;
import firenze.project.tiny.spring.di.model.Key;
import firenze.project.tiny.spring.di.model.Value;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FDIContainer {
    private final Map<Key<?>, Value> map;
    private final List<Key<?>> keyLock = new ArrayList<>();

    public FDIContainer(ContainerConfig config) {
        map = config.toMap();
    }

    public <T> T get(Class<T> clazz) {
        Key<T> key = Key.get(clazz);
        Value value = map.get(key);
        return value.objExisted() ? value.getObj() : inject(key);
    }
    public <T> T get(String name, Class<T> clazz) {
        Key<T> key = Key.get(name, clazz);
        Value value = map.get(key);
        return value.objExisted() ? value.getObj() : inject(key);
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
        Object obj = Stream.of(constructors)
                .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                .findFirst()
                .map(constructor -> {
                    try {
                        return constructor.newInstance(getArgs(constructor));
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("constructor inject failed");
                    }
                }).orElse(value.getObj());
        value.setObj(obj);
        return obj;
    }

    private Object[] getArgs(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        return Arrays.stream(parameters).map(parameter -> parameter.isAnnotationPresent(Named.class) ?
                this.get(parameter.getAnnotation(Named.class).value(), parameter.getType()) :
                this.get(parameter.getType())).toArray();
    }

    private void fieldInject(Object obj) {
        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Stream.of(declaredFields)
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(obj, field.isAnnotationPresent(Named.class) ? this.get(field.getAnnotation(Named.class).value(), field.getType()) : this.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
