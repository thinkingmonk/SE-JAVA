package org.fullstack.operations;

public class Addition extends Operation {
    public Addition() {
        symbol = "+";
    }

    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}
