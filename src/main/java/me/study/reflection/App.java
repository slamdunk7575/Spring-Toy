package me.study.reflection;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {

        // 1. 타입(Coffee)을 가지고 가져오는 방법
        Class<Coffee> coffeeClass1 = Coffee.class;

        // 2. 이미 있는 인스턴스를 가지고 가져오는 방법
        Coffee coffee = new Coffee();
        Class<? extends Coffee> coffeeClass2 = coffee.getClass();

        // 3. FQCN 을 이용하여 가져오는 방법
        Class<?> coffeeClass3 = Class.forName("me.study.reflection.Coffee");


        // 필드 출력
        Arrays.stream(coffeeClass1.getDeclaredFields())
                .forEach(System.out::println);


        // 필드, 필드값 출력
        Arrays.stream(coffeeClass1.getDeclaredFields()).forEach(field -> {
            try {
                field.setAccessible(true);
                System.out.printf("%s %s\n", field, field.get(coffee));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });


        // 메소드 출려
        /*Arrays.stream(coffeeClass1.getMethods())
                .forEach(System.out::println);*/


        // 생성자 출력
        /*Arrays.stream(coffeeClass1.getConstructors())
                .forEach(System.out::println);*/


        // 부모 클래스 접근
        /*Class<? super MyCoffee> superClass = MyCoffee.class.getSuperclass();
        System.out.println(superClass);*/


        // 인터페이스 접근
        /*Arrays.stream(MyCoffee.class.getInterfaces())
                .forEach(System.out::println);*/


        // 여러가지 메소드
        /*Arrays.stream(coffeeClass1.getDeclaredFields()).forEach(field -> {
                int modifiers = field.getModifiers();
                System.out.println(field);
                System.out.println(Modifier.isPrivate(modifiers));
                System.out.println(Modifier.isStatic(modifiers));
        });*/

    }
}