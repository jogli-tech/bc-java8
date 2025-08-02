package com.jogli.bootcamp.java8.BatallaCriaturas;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Asegúrate de que tu clase Criatura esté en el mismo paquete o importada
        // correctamente.
        Criatura dragon = new Criatura("Dragon de Fuego", 100, 30, 15, "Fuego");
        Criatura golem = new Criatura("Golem de Tierra", 120, 20, 25, "Tierra");
        Criatura elfo = new Criatura("Elfo Arquero", 70, 40, 10, "Planta");
        Criatura orco = new Criatura("Orco Guerrero", 90, 25, 20, "Agua");

        List<Criatura> concursantes = new ArrayList<>();
        concursantes.add(dragon);
        concursantes.add(golem);
        concursantes.add(elfo);
        concursantes.add(orco);

        Batalla miBatalla = new Batalla(concursantes);
        miBatalla.iniciarBatalla();
    }
}