package firenze.project.tiny.spring.di;

import java.lang.reflect.InvocationTargetException;

public class Value {
    private Class<?> type;
    private Object obj;

    public <T> T getObj() {
        if (obj == null) {
            try {
                obj = type.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return (T) obj;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
