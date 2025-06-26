package com.jogli.bootcamp.java8.streams;

import java.util.*;
import java.util.stream.*;

/**
 * Stream Examples in Java 8
 * 
 * Streams provide a functional approach to processing collections
 * of objects in a declarative way.
 */
public class StreamExamples {
    
    public static void runExamples() {
        example1_BasicOperations();
        example2_FilteringAndMapping();
        example3_SortingAndLimiting();
        example4_MathematicalOperations();
        example5_ParallelStreams();
    }
    
    /**
     * Example 1: Basic stream operations
     * Demonstrates creation and basic operations with streams
     */
    public static void example1_BasicOperations() {
        System.out.println("1. BASIC STREAM OPERATIONS");
        System.out.println("--------------------------");
        
        // Creating streams
        List<String> words = Arrays.asList("java", "stream", "lambda", "functional", "programming");
        
        // Stream from collection
        System.out.println("Original words: " + words);
        
        // forEach
        System.out.println("Words in uppercase:");
        words.stream()
            .map(String::toUpperCase)
            .forEach(word -> System.out.println("  " + word));
        
        // Stream from array
        String[] array = {"apple", "banana", "orange"};
        System.out.println("Fruits from array:");
        Arrays.stream(array)
            .forEach(fruit -> System.out.println("  " + fruit));
        
        // Stream of specific values
        System.out.println("Numbers 1 to 5:");
        Stream.of(1, 2, 3, 4, 5)
            .forEach(number -> System.out.println("  " + number));
        
        // Infinite stream (limited)
        System.out.println("First 5 even numbers:");
        Stream.iterate(0, n -> n + 2)
            .limit(5)
            .forEach(number -> System.out.println("  " + number));
        
        System.out.println();
    }
    
    /**
     * Example 2: Filtering and mapping
     * Demonstrates filter and map operations
     */
    public static void example2_FilteringAndMapping() {
        System.out.println("2. FILTERING AND MAPPING");
        System.out.println("------------------------");
        
        List<Person> people = Arrays.asList(
            new Person("Anna", 25, "Engineer"),
            new Person("Carlos", 30, "Doctor"),
            new Person("Beatriz", 22, "Teacher"),
            new Person("David", 35, "Engineer"),
            new Person("Elena", 28, "Doctor")
        );
        
        System.out.println("All people:");
        people.forEach(person -> System.out.println("  " + person));
        
        // Filter by age
        System.out.println("\nPeople over 25:");
        people.stream()
            .filter(person -> person.getAge() > 25)
            .forEach(person -> System.out.println("  " + person));
        
        // Filter by profession
        System.out.println("\nEngineers:");
        people.stream()
            .filter(person -> "Engineer".equals(person.getProfession()))
            .forEach(person -> System.out.println("  " + person));
        
        // Map to names
        System.out.println("\nNames only:");
        List<String> names = people.stream()
            .map(Person::getName)
            .collect(Collectors.toList());
        names.forEach(name -> System.out.println("  " + name));
        
        // Map to ages
        System.out.println("\nAges only:");
        List<Integer> ages = people.stream()
            .map(Person::getAge)
            .collect(Collectors.toList());
        System.out.println("  " + ages);
        
        // Complex mapping
        System.out.println("\nFormatted information:");
        people.stream()
            .map(person -> person.getName() + " (" + person.getAge() + ") - " + person.getProfession())
            .forEach(info -> System.out.println("  " + info));
        
        System.out.println();
    }
    
    /**
     * Example 3: Sorting and limiting
     * Demonstrates sorted, limit, skip operations
     */
    public static void example3_SortingAndLimiting() {
        System.out.println("3. SORTING AND LIMITING");
        System.out.println("-----------------------");
        
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6);
        System.out.println("Original numbers: " + numbers);
        
        // Natural sorting
        System.out.println("Sorted ascending:");
        numbers.stream()
            .sorted()
            .forEach(number -> System.out.print(number + " "));
        System.out.println();
        
        // Reverse sorting
        System.out.println("Sorted descending:");
        numbers.stream()
            .sorted(Collections.reverseOrder())
            .forEach(number -> System.out.print(number + " "));
        System.out.println();
        
        // Limit
        System.out.println("First 3 numbers:");
        numbers.stream()
            .limit(3)
            .forEach(number -> System.out.print(number + " "));
        System.out.println();
        
        // Skip
        System.out.println("Skip first 3 numbers:");
        numbers.stream()
            .skip(3)
            .forEach(number -> System.out.print(number + " "));
        System.out.println();
        
        // Combine operations
        System.out.println("Top 3 largest numbers:");
        numbers.stream()
            .sorted(Collections.reverseOrder())
            .limit(3)
            .forEach(number -> System.out.print(number + " "));
        System.out.println();
        
        // Sorting objects
        List<Person> people = Arrays.asList(
            new Person("Anna", 25, "Engineer"),
            new Person("Carlos", 30, "Doctor"),
            new Person("Beatriz", 22, "Teacher")
        );
        
        System.out.println("\nPeople sorted by age:");
        people.stream()
            .sorted(Comparator.comparing(Person::getAge))
            .forEach(person -> System.out.println("  " + person));
        
        System.out.println("People sorted by name:");
        people.stream()
            .sorted(Comparator.comparing(Person::getName))
            .forEach(person -> System.out.println("  " + person));
        
        System.out.println();
    }
    
    /**
     * Example 4: Mathematical operations
     * Demonstrates reduce, sum, average operations
     */
    public static void example4_MathematicalOperations() {
        System.out.println("4. MATHEMATICAL OPERATIONS");
        System.out.println("---------------------------");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Numbers: " + numbers);
        
        // Sum
        int sum = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Sum: " + sum);
        
        // Average
        OptionalDouble average = numbers.stream()
            .mapToInt(Integer::intValue)
            .average();
        System.out.println("Average: " + average.orElse(0.0));
        
        // Max and Min
        Optional<Integer> max = numbers.stream().max(Integer::compare);
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        System.out.println("Maximum: " + max.orElse(0));
        System.out.println("Minimum: " + min.orElse(0));
        
        // Count
        long count = numbers.stream()
            .filter(n -> n % 2 == 0)
            .count();
        System.out.println("Even numbers count: " + count);
        
        // Reduce - product
        Optional<Integer> product = numbers.stream()
            .reduce((a, b) -> a * b);
        System.out.println("Product: " + product.orElse(0));
        
        // Reduce with initial value
        int sumWithReduce = numbers.stream()
            .reduce(0, Integer::sum);
        System.out.println("Sum with reduce: " + sumWithReduce);
        
        // Statistics
        IntSummaryStatistics stats = numbers.stream()
            .mapToInt(Integer::intValue)
            .summaryStatistics();
        System.out.println("Statistics: " + stats);
        
        System.out.println();
    }
    
    /**
     * Example 5: Parallel streams
     * Demonstrates parallel processing with streams
     */
    public static void example5_ParallelStreams() {
        System.out.println("5. PARALLEL STREAMS");
        System.out.println("-------------------");
        
        List<Integer> largeList = IntStream.rangeClosed(1, 1000000)
            .boxed()
            .collect(Collectors.toList());
        
        System.out.println("Processing " + largeList.size() + " elements...");
        
        // Sequential processing
        long startTime = System.currentTimeMillis();
        long sequentialSum = largeList.stream()
            .mapToLong(Integer::longValue)
            .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        
        // Parallel processing
        startTime = System.currentTimeMillis();
        long parallelSum = largeList.parallelStream()
            .mapToLong(Integer::longValue)
            .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Sequential sum: " + sequentialSum + " (Time: " + sequentialTime + "ms)");
        System.out.println("Parallel sum: " + parallelSum + " (Time: " + parallelTime + "ms)");
        System.out.println("Speedup: " + (double) sequentialTime / parallelTime + "x");
        
        // Parallel filtering and mapping
        System.out.println("\nParallel filtering example:");
        List<Integer> evenSquares = IntStream.rangeClosed(1, 100)
            .parallel()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .boxed()
            .collect(Collectors.toList());
        
        System.out.println("First 10 even squares: " + 
            evenSquares.stream().limit(10).collect(Collectors.toList()));
        
        // Check if parallel
        boolean isParallel = largeList.parallelStream().isParallel();
        System.out.println("Is parallel stream? " + isParallel);
        
        System.out.println();
    }
    
    // Helper class for examples
    static class Person {
        private String name;
        private int age;
        private String profession;
        
        public Person(String name, int age, String profession) {
            this.name = name;
            this.age = age;
            this.profession = profession;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getProfession() { return profession; }
        
        @Override
        public String toString() {
            return name + " (" + age + ") - " + profession;
        }
    }
} 