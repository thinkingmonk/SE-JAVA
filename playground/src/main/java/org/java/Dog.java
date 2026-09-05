package org.java;

public class Dog extends Animal {
    String breed;
    String color;

    public Dog(String name, String breed, String color) {
        super(name); // parent constructor
        this.color = color;
        this.breed = breed;
    }

    public Dog(String name, String breed) {
        super(name); // parent constructor
        this.breed = breed;
    }

    void findColor() {
        System.out.println(name + " is of " + color + " color");
    }

    void bark() {
        System.out.println(name + " says Woof!");
    }

    @Override
    void eat() {
        System.out.println(name+ " loves eating treats!");
    }



    @Override
    public String toString() {
        return "{name='" + name + "', breed='" + breed + "'}";
    }
}
