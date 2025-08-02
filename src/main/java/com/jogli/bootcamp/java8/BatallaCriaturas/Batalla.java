package com.jogli.bootcamp.java8.BatallaCriaturas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Batalla {
    private List<Criatura> participantes;
    private Random random;

    public Batalla(List<Criatura> participantes) {
        this.participantes = new ArrayList<>(participantes);
        this.random = new Random();
    }

    public void iniciarBatalla() {
        if (participantes.size() < 4) {
            System.out.println("Se necesitan al menos cuatro criaturas para iniciar una batalla.");
            return;
        }

        System.out.println("--- ¡La batalla comienza! ---");
        int turno = 1;

        while (contarCriaturasVivas() > 1) {
            System.out.println("\n--- Turno " + turno + " ---");

            List<Criatura> criaturasActivasEnEsteTurno = obtenerCriaturasVivas();

            for (Criatura atacante : criaturasActivasEnEsteTurno) {
                if (atacante.getSalud() <= 0) {
                    continue;
                }

                List<Criatura> posiblesObjetivos = obtenerCriaturasVivas().stream()
                        .filter(objetivo -> !objetivo.equals(atacante))
                        .toList();

                if (posiblesObjetivos.isEmpty()) {
                    break;
                }

                Criatura objetivo = posiblesObjetivos.get(random.nextInt(posiblesObjetivos.size()));

                atacante.atacar(objetivo);

                if (contarCriaturasVivas() <= 1) {
                    break;
                }
            }
            turno++;
        }

        Criatura ganador = obtenerGanador();
        if (ganador != null) {
            System.out.println("\n--- ¡Batalla Terminada! ---");
            System.out.println("¡" + ganador.getNombre() + " es el campeón con " + ganador.getSalud() + " de salud restante!");
        } else {
            System.out.println("\n--- Batalla Terminada sin un ganador claro (posiblemente un empate o todas derrotadas) ---");
        }
    }

    private List<Criatura> obtenerCriaturasVivas() {
        return participantes.stream()
                .filter(criatura -> criatura.getSalud() > 0)
                .collect(Collectors.toList());
    }

    private int contarCriaturasVivas() {
        return obtenerCriaturasVivas().size();
    }

    private Criatura obtenerGanador() {
        List<Criatura> vivas = obtenerCriaturasVivas();
        if (vivas.size() == 1) {
            return vivas.getFirst();
        }
        return null;
    }
}