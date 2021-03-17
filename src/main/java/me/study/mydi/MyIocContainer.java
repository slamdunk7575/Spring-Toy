package me.study.mydi;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class MyIocContainer {

    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);

        Stream.of(classType.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(MyAutowired.class))
                .forEach(field -> {
                    try {
                        Object fieldInstance = createInstance(field.getType());
                        field.setAccessible(true);
                        field.set(instance, fieldInstance);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        return instance;
    }

    public static <T> T createInstance(final Class<T> classType) {
        try {
            return classType.getConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
