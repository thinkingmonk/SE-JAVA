package org.java;

public class Dog extends Animal {
    String breed;
    String color;

    public Dog(String name, String breed) {
        super(name); // parent constructor
        this.breed = breed;
    }

    // Method Overloading
//    public Dog(String name, String breed, String color) {
//        super(name); // parent constructor
//        this.color = color;
//        this.breed = breed;
//    }

    void bark() {
        System.out.println(name + " says Woof!");
    }

//    void findColor() {
//        System.out.println(name + " is of " + color + " color");
//    }

//    @Override
//    public String toString() {
//        return "{name='" + name + "', breed='" + breed + "'}";
//    }
}
