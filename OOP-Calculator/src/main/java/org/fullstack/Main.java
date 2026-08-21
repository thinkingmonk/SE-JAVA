package org.fullstack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.print("First number: ");
        double a = sc.nextDouble();

        System.out.print("Second number: ");
        double b = sc.nextDouble();

        System.out.print("Operator (+, -, *, /): ");
        String operator = sc.next();

        try {
            double result = calculator.compute(a, b, operator);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
