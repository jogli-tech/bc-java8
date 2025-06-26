package com.jogli.bootcamp.java8;


import java.util.List;

/**
 * Main class that executes all Java 8 examples
 * <p>
 * This class demonstrates the most important features of Java 8:
 * - Lambdas and functional expressions
 * - Streams and flow operations
 * - Optionals for null value handling
 * - Collectors for grouping and transformation
 * - Functional interfaces
 */
public class Main {

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        final var persons = List.of(
                new Person("maria", 12),
                new Person("pedro", 19),
                new Person("sol", 21),
                new Person("pipe", 6)
        );

        persons.stream()
                .filter(person -> isOld(person))
                .map(person -> buildName(person))
                .forEach(name -> printName(name));
    }

    private static boolean isOld(final Person person) {
        return person.age >= 18;
    }

    private static void printName(final String name) {
        System.out.println("Name : " + name);
    }

    private static String buildName(final Person person) {
        System.out.println("Building Name for - " + person.getName());
        return person.getName().toUpperCase();
    }


} 