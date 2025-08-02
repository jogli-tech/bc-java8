package com.jogli.bootcamp.java8.poo;

import com.jogli.bootcamp.java8.poo.animals.Animal;
import com.jogli.bootcamp.java8.poo.animals.Perro;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("---bAnimal ---");
        Animal leon = new Animal("Felino", "Simba", 7.5f);
        System.out.println(leon);
        System.out.println(leon.sound());
        System.out.println(leon.run());
        leon.eat();
        leon.dormir();
        System.out.println(leon);
        System.out.println("---------------------------------\n");

        System.out.println("--- Perro ---");
        Perro miPerro = new Perro("Max", 3.0f);
        System.out.println(miPerro);
        System.out.println(miPerro.sound());
        miPerro.eat();
        System.out.println(miPerro.run());
        System.out.println(miPerro);
        System.out.println("---------------------------------\n");

        System.out.println("--- Polimorfismo ---");
        List<Animal> misAnimales = new ArrayList<>();
        misAnimales.add(new Animal("Ave", "Piolín", 1.0f));
        misAnimales.add(new Perro("Laika", 5.0f));

        misAnimales.forEach(animal -> {
            System.out.println( "---"+ animal.getName() + " ---");
            System.out.println(animal);
            System.out.println(animal.sound());
            animal.eat();
        });
        System.out.println("---------------------------------\n");
    }
}