package com.jogli.bootcamp.java8.poo.animals;

public class Animal {

    private String species;
    private String name;
    private float age;
    private float energy;
    private float hungry;


    public Animal(String species, String name, float age) {
        this.species = species;
        this.name = name;
        this.age = age;
        this.energy = 100.0f;
        this.hungry = 0.0f;
    }


    public String sound() {
        this.energy = Math.max(0, this.energy - 5);
        return "El animal hace un sonido genérico.";
    }

    public String run() {
        if (this.energy >= 10) {
            this.energy -= 10;
            this.hungry += 10;
            return this.name + " corre rápidamente.";
        } else {
            return this.name + " está demasiado cansado para correr.";
        }
    }

    public void eat() {
        this.hungry = Math.max(0, this.hungry - 20);
        this.energy = Math.min(100, this.energy + 15);
        System.out.println(this.name + " está comiendo. Hambre: " + this.hungry + ", Energía: " + this.energy);
    }

    public void dormir() {
        this.energy = Math.min(100, this.energy + 30);
        System.out.println(this.name + " está durmiendo. Energía: " + this.energy);
    }


    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName.trim();
        } else {
            System.out.println("Error: El nombre no puede ser nulo o vacío.");
        }
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String newSpecies) {
        if (newSpecies != null && !newSpecies.trim().isEmpty()) {
            this.species = newSpecies.trim();
        } else {
            System.out.println("Error: La especie no puede ser nula o vacía.");
        }
    }

    public float getAge() {
        return this.age;
    }

    public void setAge(float newAge) {
        if (newAge >= 0) {
            this.age = newAge;
        } else {
            System.out.println("Error: La edad no puede ser negativa.");
        }
    }

    public float getEnergy() {
        return this.energy;
    }

    public void setEnergy(float newEnergy) {
        if (newEnergy >= 0 && newEnergy <= 100) {
            this.energy = newEnergy;
        } else {
            System.out.println("Error: La energía debe estar entre 0 y 100.");
        }
    }

    public float getHungry() {
        return this.hungry;
    }

    public void setHungry(float newHungry) {
        if (newHungry >= 0 && newHungry <= 100) {
            this.hungry = newHungry;
        } else {
            System.out.println("Error: El hambre debe estar entre 0 y 100.");
        }
    }

    @Override
    public String toString() {
        return "Animal [Especie=" + species + ", Nombre=" + name + ", Edad=" + age +
                ", Energía=" + String.format("%.1f", energy) + ", Hambre=" + String.format("%.1f", hungry) + "]";
    }
}