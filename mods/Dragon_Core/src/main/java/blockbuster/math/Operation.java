/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math;

import java.util.HashSet;
import java.util.Set;

public enum Operation {
    ADD("+", 1){

        @Override
        public double calculate(double a2, double b2) {
            return a2 + b2;
        }
    }
    ,
    SUB("-", 1){

        @Override
        public double calculate(double a2, double b2) {
            return a2 - b2;
        }
    }
    ,
    MUL("*", 2){

        @Override
        public double calculate(double a2, double b2) {
            return a2 * b2;
        }
    }
    ,
    DIV("/", 2){

        @Override
        public double calculate(double a2, double b2) {
            return a2 / (b2 == 0.0 ? 1.0 : b2);
        }
    }
    ,
    MOD("%", 2){

        @Override
        public double calculate(double a2, double b2) {
            return a2 % b2;
        }
    }
    ,
    POW("^", 3){

        @Override
        public double calculate(double a2, double b2) {
            return Math.pow(a2, b2);
        }
    }
    ,
    AND("&&", -3){

        @Override
        public double calculate(double a2, double b2) {
            return 7.isTrue(a2) && 7.isTrue(b2) ? 1.0 : 0.0;
        }
    }
    ,
    OR("||", -3){

        @Override
        public double calculate(double a2, double b2) {
            return 8.isTrue(a2) || 8.isTrue(b2) ? 1.0 : 0.0;
        }
    }
    ,
    SHIFT_LEFT("<<", 0){

        @Override
        public double calculate(double a2, double b2) {
            return (int)a2 << (int)b2;
        }
    }
    ,
    SHIFT_RIGHT(">>", 0){

        @Override
        public double calculate(double a2, double b2) {
            return (int)a2 >> (int)b2;
        }
    }
    ,
    BIT_AND("&", -1){

        @Override
        public double calculate(double a2, double b2) {
            return (int)a2 & (int)b2;
        }
    }
    ,
    BIT_OR("|", -1){

        @Override
        public double calculate(double a2, double b2) {
            return (int)a2 | (int)b2;
        }
    }
    ,
    BIT_XOR("^^", -1){

        @Override
        public double calculate(double a2, double b2) {
            return (int)a2 ^ (int)b2;
        }
    }
    ,
    LESS("<", -2){

        @Override
        public double calculate(double a2, double b2) {
            return a2 < b2 ? 1.0 : 0.0;
        }
    }
    ,
    LESS_THAN("<=", -2){

        @Override
        public double calculate(double a2, double b2) {
            return a2 < b2 || 15.equals(a2, b2) ? 1.0 : 0.0;
        }
    }
    ,
    GREATER_THAN(">=", -2){

        @Override
        public double calculate(double a2, double b2) {
            return a2 > b2 || 16.equals(a2, b2) ? 1.0 : 0.0;
        }
    }
    ,
    GREATER(">", -2){

        @Override
        public double calculate(double a2, double b2) {
            return a2 > b2 ? 1.0 : 0.0;
        }
    }
    ,
    EQUALS("==", -2){

        @Override
        public double calculate(double a2, double b2) {
            return 18.equals(a2, b2) ? 1.0 : 0.0;
        }
    }
    ,
    NOT_EQUALS("!=", -2){

        @Override
        public double calculate(double a2, double b2) {
            return !19.equals(a2, b2) ? 1.0 : 0.0;
        }
    };

    public static final Set<String> OPERATORS;
    public final String sign;
    public final int value;

    public static boolean equals(double a2, double b2) {
        return Math.abs(a2 - b2) < 1.0E-5;
    }

    public static boolean isTrue(double value) {
        return !Operation.equals(value, 0.0);
    }

    private Operation(String sign, int value) {
        this.sign = sign;
        this.value = value;
    }

    public abstract double calculate(double var1, double var3);

    static {
        OPERATORS = new HashSet<String>();
        for (Operation op2 : Operation.values()) {
            OPERATORS.add(op2.sign);
        }
    }
}

