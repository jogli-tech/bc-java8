package com.jogli.bootcamp.java8.poo.animals;

public class Perro extends Animal {

    public Perro(String name, float age) {
        super("Mamifero", name, age);
    }

    @Override
    public String sound() {
        float newEnergy = this.getEnergy() - 3;
        if (newEnergy < 0) {
            this.setEnergy(0);
        } else {
            this.setEnergy(newEnergy);
        }
        return this.getName() + " dice guau";
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println(this.getName() + " está comiendo ");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}