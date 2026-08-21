package org.fullstack.operations;

public class Division extends Operation {
    public Division() {
        symbol = "/";
    }

    @Override
    public double calculate(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }
}
