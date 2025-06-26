package com.jogli.bootcamp.java8.optionals;

import java.util.*;
import java.util.function.Supplier;

/**
 * Optional Examples in Java 8
 * 
 * Optional is a container that may or may not contain a non-null value.
 * It helps to avoid NullPointerException and makes null handling explicit.
 */
public class OptionalExamples {
    
    public static void runExamples() {
        example1_CreatingOptionals();
        example2_SafeAccessMethods();
        example3_TransformationMethods();
        example4_CombiningOptionals();
        example5_PracticalUseCases();
    }
    
    /**
     * Example 1: Creating optionals
     * Demonstrates different ways to create Optional instances
     */
    public static void example1_CreatingOptionals() {
        System.out.println("1. CREATING OPTIONALS");
        System.out.println("---------------------");
        
        // Optional with value
        Optional<String> optionalWithValue = Optional.of("Hello World");
        System.out.println("Optional with value: " + optionalWithValue);
        
        // Optional with null (throws exception)
        try {
            Optional<String> optionalWithNull = Optional.of(null);
        } catch (NullPointerException e) {
            System.out.println("Optional.of(null) throws NullPointerException");
        }
        
        // Optional that can be null
        Optional<String> optionalNullable = Optional.ofNullable(null);
        System.out.println("Optional.ofNullable(null): " + optionalNullable);
        
        Optional<String> optionalNullableWithValue = Optional.ofNullable("Hello");
        System.out.println("Optional.ofNullable(\"Hello\"): " + optionalNullableWithValue);
        
        // Empty optional
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("Empty optional: " + emptyOptional);
        
        // Check if present
        System.out.println("Is optionalWithValue present? " + optionalWithValue.isPresent());
        System.out.println("Is emptyOptional present? " + emptyOptional.isPresent());
        System.out.println("Is emptyOptional empty? " + emptyOptional.isEmpty());
        
        System.out.println();
    }
    
    /**
     * Example 2: Safe access methods
     * Demonstrates safe ways to access Optional values
     */
    public static void example2_SafeAccessMethods() {
        System.out.println("2. SAFE ACCESS METHODS");
        System.out.println("----------------------");
        
        Optional<String> presentOptional = Optional.of("Java 8");
        Optional<String> emptyOptional = Optional.empty();
        
        // ifPresent - execute action if value is present
        System.out.println("Using ifPresent:");
        presentOptional.ifPresent(value -> System.out.println("  Value: " + value));
        emptyOptional.ifPresent(value -> System.out.println("  This won't print"));
        
        // ifPresentOrElse (Java 9+, but we can simulate)
        System.out.println("Using ifPresent with else logic:");
        if (presentOptional.isPresent()) {
            System.out.println("  Present value: " + presentOptional.get());
        } else {
            System.out.println("  No value present");
        }
        
        if (emptyOptional.isPresent()) {
            System.out.println("  Present value: " + emptyOptional.get());
        } else {
            System.out.println("  No value present");
        }
        
        // orElse - provide default value
        String value1 = presentOptional.orElse("default");
        String value2 = emptyOptional.orElse("default");
        System.out.println("orElse with present optional: " + value1);
        System.out.println("orElse with empty optional: " + value2);
        
        // orElseGet - provide default value using supplier
        String value3 = presentOptional.orElseGet(() -> "generated default");
        String value4 = emptyOptional.orElseGet(() -> "generated default");
        System.out.println("orElseGet with present optional: " + value3);
        System.out.println("orElseGet with empty optional: " + value4);
        
        // orElseThrow - throw exception if empty
        try {
            String value5 = presentOptional.orElseThrow(() -> new RuntimeException("No value"));
            System.out.println("orElseThrow with present optional: " + value5);
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        try {
            String value6 = emptyOptional.orElseThrow(() -> new RuntimeException("No value"));
            System.out.println("This won't print");
        } catch (RuntimeException e) {
            System.out.println("orElseThrow with empty optional - Exception: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Example 3: Transformation methods
     * Demonstrates map, flatMap, and filter operations
     */
    public static void example3_TransformationMethods() {
        System.out.println("3. TRANSFORMATION METHODS");
        System.out.println("-------------------------");
        
        Optional<String> optionalName = Optional.of("john doe");
        Optional<String> emptyOptional = Optional.empty();
        
        // map - transform the value if present
        Optional<String> upperCaseName = optionalName.map(String::toUpperCase);
        Optional<String> emptyUpperCase = emptyOptional.map(String::toUpperCase);
        
        System.out.println("Original: " + optionalName);
        System.out.println("Uppercase: " + upperCaseName);
        System.out.println("Empty uppercase: " + emptyUpperCase);
        
        // Chain map operations
        Optional<Integer> nameLength = optionalName
            .map(String::trim)
            .map(String::toUpperCase)
            .map(String::length);
        System.out.println("Name length after transformations: " + nameLength);
        
        // filter - keep value only if it matches predicate
        Optional<String> longName = optionalName.filter(name -> name.length() > 5);
        Optional<String> shortName = optionalName.filter(name -> name.length() <= 5);
        
        System.out.println("Long name (>5 chars): " + longName);
        System.out.println("Short name (<=5 chars): " + shortName);
        
        // flatMap - flatten nested optionals
        Optional<String> address = getAddressStatic("john doe");
        System.out.println("Address: " + address);
        
        Optional<String> addressFromEmpty = getAddressStatic("");
        System.out.println("Address from empty: " + addressFromEmpty);
        
        // Complex chain with flatMap
        Optional<String> result = optionalName
            .filter(name -> name.length() > 3)
            .map(String::toUpperCase)
            .flatMap(OptionalExamples::getAddressOptionalStatic);
        System.out.println("Complex chain result: " + result);
        
        System.out.println();
    }
    
    /**
     * Example 4: Combining optionals
     * Demonstrates how to work with multiple optionals
     */
    public static void example4_CombiningOptionals() {
        System.out.println("4. COMBINING OPTIONALS");
        System.out.println("----------------------");
        
        Optional<String> firstName = Optional.of("John");
        Optional<String> lastName = Optional.of("Doe");
        Optional<String> emptyName = Optional.empty();
        
        // Combine two optionals
        Optional<String> fullName = firstName.flatMap(first -> 
            lastName.map(last -> first + " " + last));
        System.out.println("Full name: " + fullName);
        
        // Combine with empty optional
        Optional<String> partialName = firstName.flatMap(first -> 
            emptyName.map(last -> first + " " + last));
        System.out.println("Partial name: " + partialName);
        
        // Alternative approach using helper method
        Optional<String> combinedName = combineNamesStatic(firstName, lastName);
        System.out.println("Combined name: " + combinedName);
        
        Optional<String> combinedWithEmpty = combineNamesStatic(firstName, emptyName);
        System.out.println("Combined with empty: " + combinedWithEmpty);
        
        // Multiple optionals with default values
        String result = firstName.orElse("Anonymous") + " " + lastName.orElse("User");
        System.out.println("With defaults: " + result);
        
        System.out.println();
    }
    
    /**
     * Example 5: Practical use cases
     * Demonstrates real-world scenarios for Optional usage
     */
    public static void example5_PracticalUseCases() {
        System.out.println("5. PRACTICAL USE CASES");
        System.out.println("----------------------");
        
        // Repository pattern with Optional
        UserRepository repository = new UserRepository();
        
        Optional<User> user1 = repository.findById(1);
        Optional<User> user2 = repository.findById(999);
        
        System.out.println("User 1: " + user1);
        System.out.println("User 2: " + user2);
        
        // Process user if present
        user1.ifPresent(user -> {
            System.out.println("Processing user: " + user.getName());
            System.out.println("User email: " + user.getEmail().orElse("No email"));
        });
        
        // Chain operations for user processing
        String userInfo = user1
            .map(User::getName)
            .map(name -> "User: " + name)
            .orElse("User not found");
        System.out.println(userInfo);
        
        // Email validation chain
        String emailResult = user1
            .flatMap(User::getEmail)
            .filter(email -> email.contains("@"))
            .map(email -> "Valid email: " + email)
            .orElse("Invalid or missing email");
        System.out.println(emailResult);
        
        // Configuration with Optional
        ConfigurationService config = new ConfigurationService();
        
        String dbUrl = config.getProperty("database.url")
            .orElse("jdbc:h2:mem:testdb");
        System.out.println("Database URL: " + dbUrl);
        
        int timeout = config.getProperty("timeout")
            .map(Integer::parseInt)
            .orElse(30);
        System.out.println("Timeout: " + timeout + " seconds");
        
        System.out.println();
    }
    
    // Helper methods
    private static Optional<String> getAddressStatic(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of("123 Main St, " + name + "'s City");
    }
    
    private static Optional<String> getAddressOptionalStatic(String name) {
        if (name.length() > 6) {
            return Optional.of("Premium Address for " + name);
        }
        return Optional.empty();
    }
    
    private static Optional<String> combineNamesStatic(Optional<String> first, Optional<String> last) {
        return first.flatMap(f -> last.map(l -> f + " " + l));
    }
    
    // Helper classes for examples
    static class User {
        private String name;
        private Optional<String> email;
        
        public User(String name, String email) {
            this.name = name;
            this.email = Optional.ofNullable(email);
        }
        
        public String getName() { return name; }
        public Optional<String> getEmail() { return email; }
        
        @Override
        public String toString() {
            return "User{name='" + name + "', email=" + email + "}";
        }
    }
    
    static class UserRepository {
        private Map<Integer, User> users = new HashMap<>();
        
        public UserRepository() {
            users.put(1, new User("John Doe", "john@example.com"));
            users.put(2, new User("Jane Smith", null));
            users.put(3, new User("Bob Johnson", "bob@example.com"));
        }
        
        public Optional<User> findById(int id) {
            return Optional.ofNullable(users.get(id));
        }
    }
    
    static class ConfigurationService {
        private Map<String, String> properties = new HashMap<>();
        
        public ConfigurationService() {
            properties.put("database.url", "jdbc:mysql://localhost:3306/mydb");
            properties.put("debug", "true");
            // timeout property is intentionally missing
        }
        
        public Optional<String> getProperty(String key) {
            return Optional.ofNullable(properties.get(key));
        }
    }
} 