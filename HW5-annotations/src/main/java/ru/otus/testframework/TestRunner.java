package ru.otus.testframework;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Elena on 12.07.2017.
 */
public class TestRunner {
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(TestRunner.class.getName());

    public static void run(Class<?> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        final List<Method> beforeMethods = new ArrayList<>();
        final List<Method> testMethods = new ArrayList<>();
        final List<Method> afterMethods = new ArrayList<>();

        for (final Method method : clazz.getMethods()) {
            for (final Annotation annotation : method.getAnnotations()) {
                if (annotation.annotationType().equals(Before.class)) {
                    beforeMethods.add(method);
                } else if (annotation.annotationType().equals(Test.class)) {
                    testMethods.add(method);
                } else if (annotation.annotationType().equals(After.class)) {
                    afterMethods.add(method);
                }
            }
        }

        if (testMethods.isEmpty()) {
            log.info("There are no test methods in the " + clazz.getName() + " class.");
            return;
        }

        final Object clazzInstance = clazz.newInstance();

        for (final Method testMethod : testMethods) {
            for (final Method beforeMethod : beforeMethods) {
                beforeMethod.invoke(clazzInstance);
            }
            testMethod.invoke(clazzInstance);
            for (final Method afterMethod : afterMethods) {
                afterMethod.invoke(clazzInstance);
            }
        }
    }

    public static void run(Class<?>[] classes) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        for (final Class<?> clazz : classes) {
            run(clazz);
        }
    }

    public static void run(String packageName) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        final Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        final List<Class<?>> classes = reflections.getSubTypesOf(Object.class)
                .stream()
                .filter(clazz -> Object.class.equals(clazz.getSuperclass()))
                .collect(Collectors.toList());

        run(classes.toArray(new Class<?>[classes.size()]));
    }
}
