package org.fullstack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        System.out.print("Enter name (one word): ");
        String word = sc.next();

        sc.nextLine();
        System.out.print("Enter full line: ");
        String line = sc.nextLine();

        System.out.println("Age: " + age);
        System.out.println("Line: " + line);

        sc.close();
    }
}