package com.jogli.bootcamp.java8;

import com.jogli.bootcamp.java8.lambdas.LambdaExamples;
import com.jogli.bootcamp.java8.streams.StreamExamples;
import com.jogli.bootcamp.java8.optionals.OptionalExamples;
import com.jogli.bootcamp.java8.collectors.CollectorExamples;
import com.jogli.bootcamp.java8.functional.FunctionalExamples;

/**
 * Main class that executes all Java 8 examples
 * 
 * This class demonstrates the most important features of Java 8:
 * - Lambdas and functional expressions
 * - Streams and flow operations
 * - Optionals for null value handling
 * - Collectors for grouping and transformation
 * - Functional interfaces
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== JAVA 8 FEATURES EXAMPLES ===\n");
        
        // Execute lambda examples
        System.out.println("🔸 LAMBDA EXAMPLES");
        System.out.println("==================");
        LambdaExamples.runExamples();
        System.out.println();
        
        // Execute stream examples
        System.out.println("🔸 STREAM EXAMPLES");
        System.out.println("==================");
        StreamExamples.runExamples();
        System.out.println();
        
        // Execute optional examples
        System.out.println("🔸 OPTIONAL EXAMPLES");
        System.out.println("====================");
        OptionalExamples.runExamples();
        System.out.println();
        
        // Execute collector examples
        System.out.println("🔸 COLLECTOR EXAMPLES");
        System.out.println("=====================");
        CollectorExamples.runExamples();
        System.out.println();
        
        // Execute functional interface examples
        System.out.println("🔸 FUNCTIONAL INTERFACE EXAMPLES");
        System.out.println("=================================");
        FunctionalExamples.runExamples();
        System.out.println();
        
        System.out.println("✅ All examples have been executed successfully!");
    }
} 