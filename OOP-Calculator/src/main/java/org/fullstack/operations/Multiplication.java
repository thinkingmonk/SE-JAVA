package org.fullstack.operations;

public class Multiplication extends Operation {
    public Multiplication() {
        symbol = "*";
    }

    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}
