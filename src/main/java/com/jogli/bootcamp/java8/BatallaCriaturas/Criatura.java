package com.jogli.bootcamp.java8.BatallaCriaturas;

public class Criatura {
    private String nombre;
    private int salud;
    private int ataque;
    private int defensa;
    private String tipo;

    public Criatura(String nombre, int salud, int ataque, int defensa, String tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setSalud(int salud) {
        this.salud = Math.max(0, salud);
    }

    public void atacar(Criatura objetivo) {
        if (this.salud <= 0) {
            System.out.println(this.nombre + " no puede atacar porque está derrotado.");
            return;
        }
        if (objetivo.getSalud() <= 0) {
            System.out.println(objetivo.getNombre() + " ya está derrotado. " + this.nombre + " no puede atacarlo.");
            return;
        }

        int danoCausado = this.ataque - objetivo.getDefensa();
        if (danoCausado <= 0) {
            danoCausado = 1;
        }

        objetivo.setSalud(objetivo.getSalud() - danoCausado);

        System.out.println(this.nombre + " ataca a " + objetivo.getNombre() + "!");
        System.out.println(objetivo.getNombre() + " recibe " + danoCausado + " puntos de daño.");
        System.out.println(objetivo.getNombre() + " ahora tiene " + objetivo.getSalud() + " de salud.");

        if (objetivo.getSalud() <= 0) {
            System.out.println(objetivo.getNombre() + " ha sido derrotado!");
        }
    }

    @Override
    public String toString() {
        return "Criatura{" +
                "nombre='" + nombre + '\'' +
                ", salud=" + salud +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
