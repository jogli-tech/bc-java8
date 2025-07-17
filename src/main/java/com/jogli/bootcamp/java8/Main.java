package com.jogli.bootcamp.java8;


import com.jogli.bootcamp.java8.poo.Figure;
import com.jogli.bootcamp.java8.poo.Square;
import com.jogli.bootcamp.java8.poo.Triangle;

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

    public static void main(String[] args) {

        final var sq1 = new Square();
        sq1.setName("Sq1");
        sq1.setH(3f);
        sq1.setL(5f);

        final var sq2 = new Square();
        sq2.setH(6f);
        sq2.setL(5f);

        final var t1 = new Triangle();
        t1.setName("Triangle1");
        t1.setH(4f);
        t1.setL(4f);

        final List<Figure> fgs = List.of(sq1, sq2, t1);
        fgs.stream()
                .forEach(f -> System.out.println("Area for :" + f.getName() +
                        " is : " + f.calculateArea()));
    }

} 