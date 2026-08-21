package org.java;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Dog d = new Dog("Bruno", "Labrador");
//        Dog d = new Dog("Bruno", "Labrador", "Brown");
        d.eat();
        d.bark();
//        d.findColor();
        System.out.println("Dog's info: " + d);
    }
}