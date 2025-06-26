package com.jogli.bootcamp.java8.collectors;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collector Examples in Java 8
 * 
 * Collectors provide various ways to accumulate stream elements
 * into collections, strings, or other summary results.
 */
public class CollectorExamples {
    
    public static void runExamples() {
        example1_BasicCollection();
        example2_GroupingOperations();
        example3_PartitioningOperations();
        example4_AggregationOperations();
        example5_CustomCollectors();
    }
    
    /**
     * Example 1: Basic collection operations
     * Demonstrates collecting to lists, sets, and maps
     */
    public static void example1_BasicCollection() {
        System.out.println("1. BASIC COLLECTION OPERATIONS");
        System.out.println("------------------------------");
        
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        
        // Collect to List
        List<String> upperCaseList = words.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("Uppercase list: " + upperCaseList);
        
        // Collect to Set
        Set<Integer> lengthSet = words.stream()
            .map(String::length)
            .collect(Collectors.toSet());
        System.out.println("Length set: " + lengthSet);
        
        // Collect to specific collection type
        LinkedList<String> linkedList = words.stream()
            .filter(word -> word.length() > 5)
            .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("Long words in LinkedList: " + linkedList);
        
        // Collect to Map (word -> length)
        Map<String, Integer> wordLengthMap = words.stream()
            .collect(Collectors.toMap(
                Function.identity(),  // key mapper
                String::length        // value mapper
            ));
        System.out.println("Word length map: " + wordLengthMap);
        
        // Collect to Map with duplicate key handling
        List<String> wordsWithDuplicateLength = Arrays.asList("cat", "dog", "bird", "fish");
        Map<Integer, String> lengthToWordMap = wordsWithDuplicateLength.stream()
            .collect(Collectors.toMap(
                String::length,
                Function.identity(),
                (existing, replacement) -> existing + ", " + replacement
            ));
        System.out.println("Length to word map: " + lengthToWordMap);
        
        // Joining strings
        String joinedWords = words.stream()
            .collect(Collectors.joining(", "));
        System.out.println("Joined words: " + joinedWords);
        
        String joinedWithPrefixSuffix = words.stream()
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Joined with brackets: " + joinedWithPrefixSuffix);
        
        System.out.println();
    }
    
    /**
     * Example 2: Grouping operations
     * Demonstrates groupingBy collector
     */
    public static void example2_GroupingOperations() {
        System.out.println("2. GROUPING OPERATIONS");
        System.out.println("----------------------");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineering", 75000),
            new Person("Bob", 30, "Marketing", 65000),
            new Person("Charlie", 35, "Engineering", 85000),
            new Person("Diana", 28, "Marketing", 70000),
            new Person("Eve", 32, "Engineering", 80000),
            new Person("Frank", 29, "Sales", 60000)
        );
        
        System.out.println("All people:");
        people.forEach(System.out::println);
        
        // Group by department
        Map<String, List<Person>> byDepartment = people.stream()
            .collect(Collectors.groupingBy(Person::getDepartment));
        
        System.out.println("\nGrouped by department:");
        byDepartment.forEach((dept, persons) -> {
            System.out.println("  " + dept + ": " + persons.size() + " people");
            persons.forEach(person -> System.out.println("    " + person));
        });
        
        // Group by age range
        Map<String, List<Person>> byAgeRange = people.stream()
            .collect(Collectors.groupingBy(person -> {
                if (person.getAge() < 30) return "Under 30";
                else if (person.getAge() < 35) return "30-34";
                else return "35+";
            }));
        
        System.out.println("\nGrouped by age range:");
        byAgeRange.forEach((range, persons) -> {
            System.out.println("  " + range + ": " + persons.size() + " people");
        });
        
        // Group and count
        Map<String, Long> departmentCounts = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.counting()
            ));
        System.out.println("\nDepartment counts: " + departmentCounts);
        
        // Group and calculate average salary
        Map<String, Double> avgSalaryByDept = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.averagingDouble(Person::getSalary)
            ));
        System.out.println("Average salary by department: " + avgSalaryByDept);
        
        // Multi-level grouping
        Map<String, Map<String, List<Person>>> multiLevel = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.groupingBy(person -> 
                    person.getAge() < 30 ? "Young" : "Experienced"
                )
            ));
        
        System.out.println("\nMulti-level grouping:");
        multiLevel.forEach((dept, ageGroups) -> {
            System.out.println("  " + dept + ":");
            ageGroups.forEach((ageGroup, persons) -> {
                System.out.println("    " + ageGroup + ": " + persons.size() + " people");
            });
        });
        
        System.out.println();
    }
    
    /**
     * Example 3: Partitioning operations
     * Demonstrates partitioningBy collector
     */
    public static void example3_PartitioningOperations() {
        System.out.println("3. PARTITIONING OPERATIONS");
        System.out.println("--------------------------");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Partition by even/odd
        Map<Boolean, List<Integer>> evenOddPartition = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        
        System.out.println("Numbers: " + numbers);
        System.out.println("Even numbers: " + evenOddPartition.get(true));
        System.out.println("Odd numbers: " + evenOddPartition.get(false));
        
        // Partition with downstream collector
        Map<Boolean, Long> evenOddCounts = numbers.stream()
            .collect(Collectors.partitioningBy(
                n -> n % 2 == 0,
                Collectors.counting()
            ));
        System.out.println("Even count: " + evenOddCounts.get(true));
        System.out.println("Odd count: " + evenOddCounts.get(false));
        
        // Partition people by high salary
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineering", 75000),
            new Person("Bob", 30, "Marketing", 65000),
            new Person("Charlie", 35, "Engineering", 85000),
            new Person("Diana", 28, "Marketing", 70000)
        );
        
        Map<Boolean, List<Person>> salaryPartition = people.stream()
            .collect(Collectors.partitioningBy(person -> person.getSalary() > 70000));
        
        System.out.println("\nHigh salary (>70k): " + salaryPartition.get(true).size() + " people");
        System.out.println("Regular salary (<=70k): " + salaryPartition.get(false).size() + " people");
        
        // Partition with statistics
        Map<Boolean, DoubleSummaryStatistics> salaryStats = people.stream()
            .collect(Collectors.partitioningBy(
                person -> person.getSalary() > 70000,
                Collectors.summarizingDouble(Person::getSalary)
            ));
        
        System.out.println("High salary stats: " + salaryStats.get(true));
        System.out.println("Regular salary stats: " + salaryStats.get(false));
        
        System.out.println();
    }
    
    /**
     * Example 4: Aggregation operations
     * Demonstrates various aggregation collectors
     */
    public static void example4_AggregationOperations() {
        System.out.println("4. AGGREGATION OPERATIONS");
        System.out.println("-------------------------");
        
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200.0, 5),
            new Product("Mouse", "Electronics", 25.0, 50),
            new Product("Keyboard", "Electronics", 75.0, 30),
            new Product("Desk", "Furniture", 300.0, 10),
            new Product("Chair", "Furniture", 150.0, 20)
        );
        
        System.out.println("Products:");
        products.forEach(System.out::println);
        
        // Sum of prices
        double totalValue = products.stream()
            .collect(Collectors.summingDouble(Product::getPrice));
        System.out.println("\nTotal value: $" + totalValue);
        
        // Average price
        double avgPrice = products.stream()
            .collect(Collectors.averagingDouble(Product::getPrice));
        System.out.println("Average price: $" + String.format("%.2f", avgPrice));
        
        // Count by category
        Map<String, Long> categoryCount = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.counting()
            ));
        System.out.println("Count by category: " + categoryCount);
        
        // Max and min price products
        Optional<Product> mostExpensive = products.stream()
            .collect(Collectors.maxBy(Comparator.comparing(Product::getPrice)));
        Optional<Product> cheapest = products.stream()
            .collect(Collectors.minBy(Comparator.comparing(Product::getPrice)));
        
        mostExpensive.ifPresent(p -> System.out.println("Most expensive: " + p));
        cheapest.ifPresent(p -> System.out.println("Cheapest: " + p));
        
        // Summary statistics
        DoubleSummaryStatistics priceStats = products.stream()
            .collect(Collectors.summarizingDouble(Product::getPrice));
        System.out.println("Price statistics: " + priceStats);
        
        IntSummaryStatistics stockStats = products.stream()
            .collect(Collectors.summarizingInt(Product::getStock));
        System.out.println("Stock statistics: " + stockStats);
        
        // Mapping collector
        Set<String> categories = products.stream()
            .collect(Collectors.mapping(
                Product::getCategory,
                Collectors.toSet()
            ));
        System.out.println("Categories: " + categories);
        
        // Reducing collector
        Optional<Double> totalPriceReduced = products.stream()
            .map(Product::getPrice)
            .collect(Collectors.reducing(Double::sum));
        System.out.println("Total price (using reducing): $" + totalPriceReduced.orElse(0.0));
        
        System.out.println();
    }
    
    /**
     * Example 5: Custom collectors
     * Demonstrates creating custom collectors
     */
    public static void example5_CustomCollectors() {
        System.out.println("5. CUSTOM COLLECTORS");
        System.out.println("--------------------");
        
        List<String> words = Arrays.asList("hello", "world", "java", "streams", "collectors");
        
        // Custom collector using Collector.of()
        String result = words.stream()
            .collect(java.util.stream.Collector.of(
                StringBuilder::new,                    // supplier
                (sb, s) -> sb.append(s).append(" "),  // accumulator
                StringBuilder::append,                 // combiner
                StringBuilder::toString               // finisher
            ));
        System.out.println("Custom collector result: " + result.trim());
        
        // Custom collector for statistics
        WordStatistics stats = words.stream()
            .collect(java.util.stream.Collector.of(
                WordStatistics::new,
                WordStatistics::accept,
                WordStatistics::combine,
                Function.identity()
            ));
        System.out.println("Word statistics: " + stats);
        
        // Using collectingAndThen
        String longestWord = words.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparing(String::length)),
                opt -> opt.orElse("No words")
            ));
        System.out.println("Longest word: " + longestWord);
        
        // Custom immutable list collector
        List<String> immutableUppercase = words.stream()
            .map(String::toUpperCase)
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                Collections::unmodifiableList
            ));
        System.out.println("Immutable uppercase list: " + immutableUppercase);
        
        System.out.println();
    }
    
    // Helper classes
    static class Person {
        private String name;
        private int age;
        private String department;
        private double salary;
        
        public Person(String name, int age, String department, double salary) {
            this.name = name;
            this.age = age;
            this.department = department;
            this.salary = salary;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
        
        @Override
        public String toString() {
            return name + " (" + age + ") - " + department + " - $" + salary;
        }
    }
    
    static class Product {
        private String name;
        private String category;
        private double price;
        private int stock;
        
        public Product(String name, String category, double price, int stock) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.stock = stock;
        }
        
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
        public int getStock() { return stock; }
        
        @Override
        public String toString() {
            return name + " (" + category + ") - $" + price + " (Stock: " + stock + ")";
        }
    }
    
    static class WordStatistics {
        private int count = 0;
        private int totalLength = 0;
        private int maxLength = 0;
        private int minLength = Integer.MAX_VALUE;
        
        public void accept(String word) {
            count++;
            totalLength += word.length();
            maxLength = Math.max(maxLength, word.length());
            minLength = Math.min(minLength, word.length());
        }
        
        public WordStatistics combine(WordStatistics other) {
            WordStatistics combined = new WordStatistics();
            combined.count = this.count + other.count;
            combined.totalLength = this.totalLength + other.totalLength;
            combined.maxLength = Math.max(this.maxLength, other.maxLength);
            combined.minLength = Math.min(this.minLength, other.minLength);
            return combined;
        }
        
        @Override
        public String toString() {
            double avgLength = count > 0 ? (double) totalLength / count : 0;
            return String.format("WordStats{count=%d, avgLength=%.1f, maxLength=%d, minLength=%d}", 
                count, avgLength, maxLength, minLength == Integer.MAX_VALUE ? 0 : minLength);
        }
    }
} 