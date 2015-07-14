package com.raulavila.mutationtesting;

import static com.raulavila.mutationtesting.Calculator.Mode.*;

public class Calculator {

    private Audit audit;

    public Calculator(Audit audit) {
        this.audit = audit;
    }

    public long add(int operand1, int operand2, Mode mode) {
        audit.register(String.format("%d + %d (%s)", operand1, operand2, mode));

        if (mode == ABSOLUTE) {
            operand1 = Math.abs(operand1);
            operand2 = Math.abs(operand2);
        }

        return operand1 + operand2;
    }

    public static enum Mode {ABSOLUTE, STRAIGHT;}
}