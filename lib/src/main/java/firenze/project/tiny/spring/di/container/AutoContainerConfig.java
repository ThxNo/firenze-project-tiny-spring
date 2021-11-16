package firenze.project.tiny.spring.di.container;

import firenze.project.tiny.spring.di.annotations.Named;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class AutoContainerConfig extends ContainerConfig {
    private final String rootPackageName;

    public AutoContainerConfig(String rootPackageName) {
        this.rootPackageName = rootPackageName;
        init();
    }

    public AutoContainerConfig(Class<?> rootClass) {
        this(rootClass.getPackageName());
    }

    @Override
    public void init() {
        Reflections reflections = new Reflections(rootPackageName);
        Set<String> classes = reflections.getAllTypes();
        classes.stream()
                .filter(className -> className.startsWith(rootPackageName))
                .forEach(this::register);
    }

    @SneakyThrows
    private void register(String clazzName) {
        Class<?> clazz = Class.forName(clazzName);
        if (Modifier.isAbstract(clazz.getModifiers()) || clazz.isInterface()) {
            return;
        }
        Queue<Class<?>> targets = new LinkedList<>();
        targets.add(clazz);
        while (!targets.isEmpty()) {
            Class<?> target = targets.poll();
            if (clazz.isAnnotationPresent(Named.class)) {
                bind(target).named(clazz.getAnnotation(Named.class).value()).to(clazz);
            } else {
                bind(target).to(clazz);
            }
            if (Objects.nonNull(target.getSuperclass()) && !Objects.equals(target.getSuperclass(), Object.class)) {
                targets.add(target.getSuperclass());
            }
            targets.addAll(Arrays.asList(target.getInterfaces()));
        }
    }

}
