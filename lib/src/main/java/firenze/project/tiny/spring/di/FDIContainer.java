package firenze.project.tiny.spring.di;


import java.lang.reflect.Field;
import java.util.ArrayList;
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
        T obj = map.get(key).getObj();
        inject(key, obj);
        return obj;
    }

    private void inject(Key key, Object obj) {
        if(keyLock.contains(key)) throw new RuntimeException("key is using!");
        keyLock.add(key);
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
        keyLock.remove(key);
    }
}
