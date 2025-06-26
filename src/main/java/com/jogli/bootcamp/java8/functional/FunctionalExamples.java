package com.jogli.bootcamp.java8.functional;

import java.util.*;
import java.util.function.*;

/**
 * Functional Interface Examples in Java 8
 * 
 * Demonstrates the built-in functional interfaces and
 * advanced functional programming concepts.
 */
public class FunctionalExamples {
    
    public static void runExamples() {
        example1_BasicFunctionalInterfaces();
        example2_AdvancedFunctionalInterfaces();
        example3_FunctionComposition();
        example4_PredicateOperations();
        example5_ConsumerAndSupplier();
    }
    
    /**
     * Example 1: Basic functional interfaces
     * Demonstrates Function, Predicate, Consumer, and Supplier
     */
    public static void example1_BasicFunctionalInterfaces() {
        System.out.println("1. BASIC FUNCTIONAL INTERFACES");
        System.out.println("------------------------------");
        
        // Function<T, R> - takes input T and returns R
        Function<String, Integer> stringLength = String::length;
        Function<Integer, String> intToString = Object::toString;
        Function<String, String> toUpperCase = String::toUpperCase;
        
        System.out.println("String length of 'Hello': " + stringLength.apply("Hello"));
        System.out.println("Integer 42 to string: " + intToString.apply(42));
        System.out.println("Uppercase 'world': " + toUpperCase.apply("world"));
        
        // Predicate<T> - takes input T and returns boolean
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isLong = s -> s.length() > 5;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        
        System.out.println("Is '' empty? " + isEmpty.test(""));
        System.out.println("Is 'programming' long? " + isLong.test("programming"));
        System.out.println("Is 4 even? " + isEven.test(4));
        
        // Consumer<T> - takes input T and returns void
        Consumer<String> printUpperCase = s -> System.out.println("  " + s.toUpperCase());
        Consumer<Integer> printSquare = n -> System.out.println("  " + n + "² = " + (n * n));
        
        System.out.println("Consumer examples:");
        printUpperCase.accept("hello");
        printSquare.accept(5);
        
        // Supplier<T> - takes no input and returns T
        Supplier<String> randomString = () -> "Random: " + Math.random();
        Supplier<List<String>> listSupplier = ArrayList::new;
        Supplier<Date> currentDate = Date::new;
        
        System.out.println("Supplier examples:");
        System.out.println("  " + randomString.get());
        System.out.println("  New list: " + listSupplier.get());
        System.out.println("  Current date: " + currentDate.get());
        
        System.out.println();
    }
    
    /**
     * Example 2: Advanced functional interfaces
     * Demonstrates BiFunction, BiPredicate, BiConsumer, and specialized interfaces
     */
    public static void example2_AdvancedFunctionalInterfaces() {
        System.out.println("2. ADVANCED FUNCTIONAL INTERFACES");
        System.out.println("---------------------------------");
        
        // BiFunction<T, U, R> - takes two inputs and returns R
        BiFunction<String, String, String> concatenate = (a, b) -> a + " " + b;
        BiFunction<Integer, Integer, Integer> add = Integer::sum;
        BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);
        
        System.out.println("Concatenate 'Hello' and 'World': " + concatenate.apply("Hello", "World"));
        System.out.println("Add 5 + 3: " + add.apply(5, 3));
        System.out.println("Repeat 'Hi' 3 times: " + repeat.apply("Hi", 3));
        
        // BiPredicate<T, U> - takes two inputs and returns boolean
        BiPredicate<String, String> startsWith = String::startsWith;
        BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
        
        System.out.println("Does 'Hello' start with 'He'? " + startsWith.test("Hello", "He"));
        System.out.println("Is 10 > 5? " + isGreater.test(10, 5));
        
        // BiConsumer<T, U> - takes two inputs and returns void
        BiConsumer<String, Integer> printWithIndex = (s, i) -> 
            System.out.println("  [" + i + "] " + s);
        
        System.out.println("BiConsumer example:");
        printWithIndex.accept("First", 1);
        printWithIndex.accept("Second", 2);
        
        // Specialized interfaces for primitives
        IntFunction<String> intToHex = Integer::toHexString;
        IntPredicate isPositive = n -> n > 0;
        IntConsumer printInt = n -> System.out.println("  Integer: " + n);
        IntSupplier randomInt = () -> (int) (Math.random() * 100);
        
        System.out.println("Specialized interfaces:");
        System.out.println("  15 in hex: " + intToHex.apply(15));
        System.out.println("  Is -3 positive? " + isPositive.test(-3));
        printInt.accept(42);
        System.out.println("  Random int: " + randomInt.getAsInt());
        
        // BinaryOperator and UnaryOperator
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        UnaryOperator<String> addPrefix = s -> "PREFIX_" + s;
        
        System.out.println("Binary operator 4 * 7: " + multiply.apply(4, 7));
        System.out.println("Unary operator on 'test': " + addPrefix.apply("test"));
        
        System.out.println();
    }
    
    /**
     * Example 3: Function composition
     * Demonstrates andThen and compose methods
     */
    public static void example3_FunctionComposition() {
        System.out.println("3. FUNCTION COMPOSITION");
        System.out.println("-----------------------");
        
        // Basic functions
        Function<String, String> addPrefix = s -> "Mr. " + s;
        Function<String, String> addSuffix = s -> s + " Jr.";
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, Integer> getLength = String::length;
        
        // andThen - execute this function first, then the next
        Function<String, String> addPrefixThenSuffix = addPrefix.andThen(addSuffix);
        Function<String, String> fullTransform = addPrefix.andThen(addSuffix).andThen(toUpperCase);
        
        System.out.println("Original: John");
        System.out.println("Add prefix then suffix: " + addPrefixThenSuffix.apply("John"));
        System.out.println("Full transform: " + fullTransform.apply("John"));
        
        // compose - execute the parameter function first, then this function
        Function<String, String> suffixThenPrefix = addPrefix.compose(addSuffix);
        System.out.println("Suffix then prefix: " + suffixThenPrefix.apply("John"));
        
        // Complex composition chain
        Function<String, Integer> complexChain = addPrefix
            .andThen(addSuffix)
            .andThen(toUpperCase)
            .andThen(getLength);
        
        System.out.println("Complex chain result (length): " + complexChain.apply("John"));
        
        // Mathematical function composition
        Function<Double, Double> multiplyByTwo = x -> x * 2;
        Function<Double, Double> addTen = x -> x + 10;
        Function<Double, Double> square = x -> x * x;
        
        Function<Double, Double> mathChain = multiplyByTwo
            .andThen(addTen)
            .andThen(square);
        
        System.out.println("Math chain (5 * 2 + 10)²: " + mathChain.apply(5.0));
        
        // Using Function.identity()
        Function<String, String> identity = Function.identity();
        System.out.println("Identity function: " + identity.apply("unchanged"));
        
        System.out.println();
    }
    
    /**
     * Example 4: Predicate operations
     * Demonstrates and, or, negate operations
     */
    public static void example4_PredicateOperations() {
        System.out.println("4. PREDICATE OPERATIONS");
        System.out.println("-----------------------");
        
        // Basic predicates
        Predicate<String> isLong = s -> s.length() > 5;
        Predicate<String> containsA = s -> s.contains("a");
        Predicate<String> startsWithJ = s -> s.startsWith("J");
        
        List<String> words = Arrays.asList("Java", "Python", "JavaScript", "Go", "Scala");
        
        System.out.println("Words: " + words);
        
        // Individual predicates
        System.out.println("Long words (>5 chars):");
        words.stream().filter(isLong).forEach(w -> System.out.println("  " + w));
        
        System.out.println("Words containing 'a':");
        words.stream().filter(containsA).forEach(w -> System.out.println("  " + w));
        
        // AND operation
        Predicate<String> longAndContainsA = isLong.and(containsA);
        System.out.println("Long words containing 'a':");
        words.stream().filter(longAndContainsA).forEach(w -> System.out.println("  " + w));
        
        // OR operation
        Predicate<String> longOrStartsWithJ = isLong.or(startsWithJ);
        System.out.println("Long words OR starting with 'J':");
        words.stream().filter(longOrStartsWithJ).forEach(w -> System.out.println("  " + w));
        
        // NEGATE operation
        Predicate<String> notLong = isLong.negate();
        System.out.println("Short words (<=5 chars):");
        words.stream().filter(notLong).forEach(w -> System.out.println("  " + w));
        
        // Complex combinations
        Predicate<String> complex = isLong.and(containsA).or(startsWithJ.negate());
        System.out.println("Complex predicate (long AND contains 'a') OR (NOT starts with 'J'):");
        words.stream().filter(complex).forEach(w -> System.out.println("  " + w));
        
        // Predicate for numbers
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isLarge = n -> n > 10;
        
        List<Integer> numbers = Arrays.asList(-5, 2, 8, 15, -12, 20);
        System.out.println("Numbers: " + numbers);
        
        Predicate<Integer> evenAndPositive = isEven.and(isPositive);
        System.out.println("Even and positive numbers:");
        numbers.stream().filter(evenAndPositive).forEach(n -> System.out.println("  " + n));
        
        System.out.println();
    }
    
    /**
     * Example 5: Consumer and Supplier patterns
     * Demonstrates advanced usage of Consumer and Supplier
     */
    public static void example5_ConsumerAndSupplier() {
        System.out.println("5. CONSUMER AND SUPPLIER PATTERNS");
        System.out.println("----------------------------------");
        
        // Consumer chaining
        Consumer<String> printOriginal = s -> System.out.println("Original: " + s);
        Consumer<String> printUppercase = s -> System.out.println("Uppercase: " + s.toUpperCase());
        Consumer<String> printLength = s -> System.out.println("Length: " + s.length());
        
        Consumer<String> chainedConsumer = printOriginal
            .andThen(printUppercase)
            .andThen(printLength);
        
        System.out.println("Chained consumer example:");
        chainedConsumer.accept("Hello World");
        
        // BiConsumer example
        BiConsumer<String, Integer> printWithCount = (s, count) -> {
            for (int i = 0; i < count; i++) {
                System.out.println("  [" + (i + 1) + "] " + s);
            }
        };
        
        System.out.println("BiConsumer example:");
        printWithCount.accept("Test", 3);
        
        // Supplier factory pattern
        Supplier<User> userSupplier = () -> new User("DefaultUser", "default@example.com");
        Supplier<List<String>> listSupplier = ArrayList::new;
        Supplier<String> timestampSupplier = () -> "Timestamp: " + System.currentTimeMillis();
        
        System.out.println("Supplier examples:");
        System.out.println("  " + userSupplier.get());
        System.out.println("  " + listSupplier.get());
        System.out.println("  " + timestampSupplier.get());
        
        // Lazy evaluation with Supplier
        System.out.println("Lazy evaluation example:");
        processWithLazyLogging("Important message", () -> expensiveOperation());
        processWithLazyLogging("", () -> expensiveOperation());
        
        // Consumer for side effects
        List<String> results = new ArrayList<>();
        Consumer<String> addToResults = results::add;
        Consumer<String> printAndAdd = s -> {
            System.out.println("Processing: " + s);
            addToResults.accept(s);
        };
        
        System.out.println("Consumer with side effects:");
        Arrays.asList("item1", "item2", "item3").forEach(printAndAdd);
        System.out.println("Results collected: " + results);
        
        // Functional error handling
        Consumer<String> safeConsumer = createSafeConsumer(s -> {
            if (s.equals("error")) {
                throw new RuntimeException("Simulated error");
            }
            System.out.println("Processed: " + s);
        });
        
        System.out.println("Safe consumer example:");
        Arrays.asList("good", "error", "also good").forEach(safeConsumer);
        
        System.out.println();
    }
    
    // Helper methods
    private static void processWithLazyLogging(String message, Supplier<String> logSupplier) {
        if (!message.isEmpty()) {
            System.out.println("Processing: " + message);
            System.out.println("Log: " + logSupplier.get());
        } else {
            System.out.println("Empty message, skipping expensive logging");
        }
    }
    
    private static String expensiveOperation() {
        System.out.println("  Executing expensive operation...");
        return "Expensive result";
    }
    
    private static <T> Consumer<T> createSafeConsumer(Consumer<T> consumer) {
        return item -> {
            try {
                consumer.accept(item);
            } catch (Exception e) {
                System.out.println("Error processing item: " + e.getMessage());
            }
        };
    }
    
    // Helper class
    static class User {
        private String name;
        private String email;
        
        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
        
        @Override
        public String toString() {
            return "User{name='" + name + "', email='" + email + "'}";
        }
    }
} 