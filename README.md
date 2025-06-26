# Java 8 Features Examples

A comprehensive collection of Java 8 features organized into thematic packages. This project demonstrates the most important capabilities introduced in Java 8, including lambdas, streams, optionals, collectors, and functional interfaces.

## 📁 Project Structure

```
src/main/java/com/jogli/bootcamp/java8/
├── Main.java                          # Main class that runs all examples
├── lambdas/
│   └── LambdaExamples.java           # Lambda expressions and method references
├── streams/
│   └── StreamExamples.java           # Stream operations and processing
├── optionals/
│   └── OptionalExamples.java         # Optional for null handling
├── collectors/
│   └── CollectorExamples.java        # Collection and aggregation operations
└── functional/
    └── FunctionalExamples.java       # Functional interfaces and composition
```

## 🚀 Features Demonstrated

### 1. **Lambda Expressions** (`lambdas/LambdaExamples.java`)
- Basic lambda syntax and expressions
- Lambdas with parameters and type inference
- Usage in collections (forEach, removeIf, replaceAll)
- Method references (static, instance, constructor)
- Custom functional interfaces with default methods

### 2. **Stream Operations** (`streams/StreamExamples.java`)
- Stream creation from collections, arrays, and generators
- Filtering and mapping operations
- Sorting and limiting with Comparators
- Mathematical operations (sum, average, reduce)
- Parallel stream processing for performance

### 3. **Optional Handling** (`optionals/OptionalExamples.java`)
- Creating Optional instances safely
- Safe access methods (ifPresent, orElse, orElseGet)
- Transformation operations (map, flatMap, filter)
- Combining multiple Optionals
- Real-world use cases (repository pattern, configuration)

### 4. **Collectors** (`collectors/CollectorExamples.java`)
- Basic collection to List, Set, Map
- Grouping operations with groupingBy
- Partitioning with partitioningBy
- Aggregation operations (counting, averaging, summarizing)
- Custom collectors and collectingAndThen

### 5. **Functional Interfaces** (`functional/FunctionalExamples.java`)
- Built-in interfaces (Function, Predicate, Consumer, Supplier)
- Advanced interfaces (BiFunction, specialized primitives)
- Function composition (andThen, compose)
- Predicate operations (and, or, negate)
- Consumer chaining and Supplier patterns

## 🛠️ How to Run

### Prerequisites
- Java 8 or higher
- Gradle (included via Gradle Wrapper)

### Execution Commands

```bash
# Compile the project
./gradlew build

# Run all examples
./gradlew run

# Run specific example class (alternative)
./gradlew run --args="lambdas"     # Run only lambda examples
./gradlew run --args="streams"     # Run only stream examples
./gradlew run --args="optionals"   # Run only optional examples
./gradlew run --args="collectors"  # Run only collector examples
./gradlew run --args="functional"  # Run only functional examples
```

### Expected Output Structure

```
=== JAVA 8 FEATURES EXAMPLES ===

🔸 LAMBDA EXAMPLES
==================
1. BASIC LAMBDAS
----------------
Hello from lambda!
Message: Hello world!
...

🔸 STREAM EXAMPLES
==================
1. BASIC STREAM OPERATIONS
--------------------------
Original words: [java, stream, lambda, functional, programming]
...

🔸 OPTIONAL EXAMPLES
====================
1. CREATING OPTIONALS
---------------------
Optional with value: Optional[Hello World]
...

🔸 COLLECTOR EXAMPLES
=====================
1. BASIC COLLECTION OPERATIONS
------------------------------
Uppercase list: [APPLE, BANANA, CHERRY, DATE, ELDERBERRY]
...

🔸 FUNCTIONAL INTERFACE EXAMPLES
=================================
1. BASIC FUNCTIONAL INTERFACES
------------------------------
String length of 'Hello': 5
...

✅ All examples have been executed successfully!
```

## 📚 Key Learning Concepts

### Lambda Expressions
- **Syntax**: `(parameters) -> expression` or `(parameters) -> { statements; }`
- **Type Inference**: Compiler can infer parameter types
- **Method References**: Shorthand for lambdas (`Class::method`)
- **Functional Interfaces**: Interfaces with exactly one abstract method

### Stream API
- **Declarative**: Describe what you want, not how to do it
- **Lazy Evaluation**: Intermediate operations are not executed until terminal operation
- **Parallel Processing**: Easy parallelization with `.parallelStream()`
- **Immutable**: Original collections remain unchanged

### Optional Class
- **Null Safety**: Explicit handling of potentially null values
- **Fluent API**: Chain operations safely
- **Functional Style**: Use map, filter, flatMap for transformations
- **Best Practices**: Avoid Optional in fields, parameters, and collections

### Collectors
- **Mutable Reduction**: Accumulate elements into mutable containers
- **Built-in Collectors**: toList(), groupingBy(), partitioningBy(), etc.
- **Custom Collectors**: Create your own using Collector.of()
- **Performance**: Often more efficient than manual collection building

### Functional Interfaces
- **Single Abstract Method**: Core principle of functional interfaces
- **Built-in Types**: Function, Predicate, Consumer, Supplier, etc.
- **Composition**: Combine simple functions into complex operations
- **Type Safety**: Compile-time checking of functional compositions

## 🎯 Best Practices Demonstrated

1. **Prefer method references** over lambda expressions when possible
2. **Use appropriate collectors** instead of manual stream processing
3. **Chain operations fluently** for readable code
4. **Handle Optional values safely** without `.get()`
5. **Compose functions** for reusable and testable code
6. **Use parallel streams** judiciously for CPU-intensive operations
7. **Leverage type inference** to reduce verbosity

## 🔧 Build Configuration

This project uses Gradle with the following key configurations:

```gradle
sourceCompatibility = '1.8'
targetCompatibility = '1.8'

application {
    mainClass = 'com.jogli.bootcamp.java8.Main'
}
```

## 📖 Additional Resources

- [Oracle Java 8 Documentation](https://docs.oracle.com/javase/8/docs/)
- [Java 8 Stream API Guide](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
- [Functional Interfaces in Java 8](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

---

**Note**: All code examples include comprehensive English comments and follow Java best practices for readability and maintainability.