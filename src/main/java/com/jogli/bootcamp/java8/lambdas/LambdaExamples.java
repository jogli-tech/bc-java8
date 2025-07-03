package com.jogli.bootcamp.java8.lambdas;

import java.util.*;
import java.util.function.*;

/**
 * Lambda Examples in Java 8
 * 
 * Lambdas are expressions that implement functional interfaces
 * in a concise and readable way.
 */
public class LambdaExamples {
    
    public static void runExamples() {
        example1_BasicLambdas();
        example2_LambdasWithParameters();
        example3_LambdasInCollections();
        example4_MethodReferences();
        example5_FunctionalInterfaces();
    }
    
    /**
     * Example 1: Basic lambdas
     * Demonstrates basic lambda syntax
     */
    public static void example1_BasicLambdas() {
        System.out.println("1. BASIC LAMBDAS");
        System.out.println("----------------");
        
        // Lambda without parameters
        Runnable runnable = () -> System.out.println("Hello from lambda!");
        runnable.run();
        
        // Lambda with one parameter
        Consumer<String> consumer = (String message) -> System.out.println("Message: " + message);
        consumer.accept("Hello world!");
        
        // Lambda with type inference
        Consumer<String> consumer2 = message -> System.out.println("Inferred message: " + message);
        consumer2.accept("Inferred types!");
        
        // Lambda with multiple parameters
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println("Sum: " + sum.apply(5, 3));
        
        // Lambda with code block
        BiFunction<Integer, Integer, Integer> multiplication = (a, b) -> {
            int result = a * b;
            System.out.println("Multiplying " + a + " * " + b + " = " + result);
            return result;
        };
        multiplication.apply(4, 6);
        System.out.println();
    }
    
    /**
     * Example 2: Lambdas with parameters
     * Demonstrates different ways to use parameters in lambdas
     */
    public static void example2_LambdasWithParameters() {
        System.out.println("2. LAMBDAS WITH PARAMETERS");
        System.out.println("--------------------------");
        
        // Predicate: evaluates a condition
        Predicate<String> isEmpty = str -> str.isEmpty();
        Predicate<String> isLong = str -> str.length() > 5;
        
        List<String> words = Arrays.asList("", "hello", "programming", "java", "lambda");
        
        System.out.println("Original words: " + words);
        System.out.println("Is 'hello' empty? " + isEmpty.test("hello"));
        System.out.println("Is 'programming' long? " + isLong.test("programming"));
        
        // Combine predicates
        Predicate<String> notEmptyAndLong = isEmpty.negate().and(isLong);
        List<String> filtered = new ArrayList<>();
        for (String word : words) {
            if (notEmptyAndLong.test(word)) {
                filtered.add(word);
            }
        }
        System.out.println("Non-empty and long words: " + filtered);
        
        // Function: transforms a value
        Function<String, Integer> length = String::length;
        Function<String, String> uppercase = String::toUpperCase;
        
        String text = "hello world";
        System.out.println("Original text: " + text);
        System.out.println("Length: " + length.apply(text));
        System.out.println("Uppercase: " + uppercase.apply(text));
        System.out.println();
    }
    
    /**
     * Example 3: Lambdas in collections
     * Demonstrates using lambdas with lists and maps
     */
    public static void example3_LambdasInCollections() {
        System.out.println("3. LAMBDAS IN COLLECTIONS");
        System.out.println("-------------------------");
        
        List<String> names = Arrays.asList("Anna", "Carlos", "Beatriz", "David");
        
        // forEach with lambda
        System.out.println("Original names:");
        names.forEach(name -> System.out.println("  - " + name));
        
        // removeIf with lambda
        List<String> namesCopy = new ArrayList<>(names);
        namesCopy.removeIf(name -> name.startsWith("A"));
        System.out.println("Names without 'A': " + namesCopy);
        
        // replaceAll with lambda
        List<String> modifiedNames = new ArrayList<>(names);
        modifiedNames.replaceAll(String::toLowerCase);
        System.out.println("Names in lowercase: " + modifiedNames);
        
        // Map with lambdas
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Anna", 25);
        ages.put("Carlos", 30);
        ages.put("Beatriz", 22);
        
        // forEach in map
        System.out.println("Ages:");
        ages.forEach((name, age) -> 
            System.out.println("  " + name + " is " + age + " years old"));
        
        // computeIfAbsent
        ages.computeIfAbsent("David", name -> 28);
        System.out.println("Ages after adding David: " + ages);
        System.out.println();
    }
    
    /**
     * Example 4: Method References
     * Demonstrates method references as a concise form of lambdas
     */
    public static void example4_MethodReferences() {
        System.out.println("4. METHOD REFERENCES");
        System.out.println("--------------------");
        
        List<String> words = Arrays.asList("house", "car", "tree", "book");
        
        // Static method reference
        words.forEach(System.out::println);
        
        // Instance method reference
        List<String> uppercaseList = new ArrayList<>();
        words.forEach(uppercaseList::add);
        System.out.println("Uppercase list: " + uppercaseList);
        
        // Constructor reference
        List<String> wordsList = words.stream()
            .map(String::new)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("New instances: " + wordsList);
        
        // Instance method reference of arbitrary class
        List<Integer> lengths = words.stream()
            .map(String::length)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("Lengths: " + lengths);
        
        // Custom static method reference
        List<String> formattedWords = words.stream()
            .map(LambdaExamples::formatWord)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("Formatted words: " + formattedWords);
        System.out.println();
    }
    
    /**
     * Example 5: Custom functional interfaces
     * Demonstrates how to create and use custom functional interfaces
     */
    public static void example5_FunctionalInterfaces() {
        System.out.println("5. CUSTOM FUNCTIONAL INTERFACES");
        System.out.println("-------------------------------");
        
        // Custom functional interface
        Calculator sum = (a, b) -> a + b;
        Calculator multiplication = (a, b) -> a * b;
        Calculator power = (a, b) -> (int) Math.pow(a, b);
        
        System.out.println("5 + 3 = " + sum.calculate(5, 3));
        System.out.println("4 * 6 = " + multiplication.calculate(4, 6));
        System.out.println("2 ^ 8 = " + power.calculate(2, 8));
        
        // Functional interface with default method
        Validator<String> longValidator = str -> str.length() > 5;
        Validator<String> vowelValidator = str -> str.matches(".*[aeiou].*");
        
        String text = "programming";
        System.out.println("Text: " + text);
        System.out.println("Is long? " + longValidator.validate(text));
        System.out.println("Has vowels? " + vowelValidator.validate(text));
        System.out.println("Is valid? " + longValidator.and(vowelValidator).validate(text));
        System.out.println();
    }
    
    // Helper method for method references
    public static String formatWord(String word) {
        return "[" + word.toUpperCase() + "]";
    }
    
    // Custom functional interface
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }
    
    // Functional interface with default method
    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T t);
        
        default Validator<T> and(Validator<T> other) {
            return t -> this.validate(t) && other.validate(t);
        }
    }
} 