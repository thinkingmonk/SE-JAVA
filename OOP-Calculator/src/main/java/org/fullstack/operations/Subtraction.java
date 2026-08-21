package org.fullstack.operations;

public class Subtraction extends Operation {
    public Subtraction() {
        symbol = "-";
    }

    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}
