package org.java;

public class Dog extends Animal {
    String breed;

    public Dog(String name, String breed) {
        super(name); // parent constructor
        this.breed = breed;
    }

    void bark() {
        System.out.println(name + " says Woof!");
    }
}
