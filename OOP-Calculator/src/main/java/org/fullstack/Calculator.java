package org.fullstack;

import org.fullstack.operations.Addition;
import org.fullstack.operations.Division;
import org.fullstack.operations.Multiplication;
import org.fullstack.operations.Operation;
import org.fullstack.operations.Subtraction;

public class Calculator {
    private final Operation[] operations;

    public Calculator() {
        operations = new Operation[] {
            new Addition(),
            new Subtraction(),
            new Multiplication(),
            new Division()
        };
    }

    public double compute(double a, double b, String operator) {
        for (Operation op : operations) {
            if (op.getSymbol().equals(operator)) {
                return op.calculate(a, b);
            }
        }
        throw new IllegalArgumentException("Invalid operator: " + operator);
    }
}
