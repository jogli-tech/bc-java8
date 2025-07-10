package com.jogli.bootcamp.java8.poo;

import com.jogli.bootcamp.java8.poo.animals.Animal;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Animal cat = new Animal();
        Animal dog = new Animal();

        final var animals = List.of(
                cat, dog
        );

        animals
                .stream()
                .forEach(a -> System.out.println("Methods : " + cat.run() + " - " + cat.sound()));
    }
}
