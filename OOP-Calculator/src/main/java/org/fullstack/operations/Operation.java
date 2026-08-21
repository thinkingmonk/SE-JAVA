package org.fullstack.operations;

public abstract class Operation {
    protected String symbol;

    public abstract double calculate(double a, double b);

    public String getSymbol() {
        return symbol;
    }
}
