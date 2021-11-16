package firenze.project.tiny.spring.di.model;

import java.lang.reflect.InvocationTargetException;

public class Value {
    private Class<?> type;
    private Object obj;

    public <T> T getObj() {
        if (obj == null) {
            try {
                obj = type.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return (T) obj;
    }

    public boolean objExisted() {
        return obj != null;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
