package com.jogli.bootcamp.java8.labo1;

import java.util.stream.Stream;

public class NameProcessor {

    public static void processNames() {
        getNames()
                .filter(name -> startWithVocal(name))
                .forEach(name -> System.out.println("Name: " + name));
    }

    public static boolean startWithVocal(String name) {
        return !(name.startsWith("a") ||
                name.startsWith("e") ||
                name.startsWith("i") ||
                name.startsWith("o") ||
                name.startsWith("u"));
    }

    public static Stream<String> getNames() {
        return Stream.of("ana", "maria", "pedro", "berna", "carlos", "zil", "sofi", "mate", "esneider", "ulma", "ivan");
    }

}
