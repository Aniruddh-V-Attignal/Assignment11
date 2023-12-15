package com.example.demo;

import java.util.Objects;

public class Rational implements Comparable<Rational> {
    private final int numerator;
    private final int denominator;

    public static final Rational ZERO = new Rational(0);
    public static final Rational ONE = new Rational(1);
    public static final Rational HALF = new Rational(1, 2);
    public static final Rational TWO = new Rational(2);

    public Rational(int numerator) {
        this(numerator, 1);
    }

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        if (numerator == Integer.MIN_VALUE && denominator == -1) {
            throw new ArithmeticException("Rational cannot be represented");
        }

        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    private Rational(long numerator, long denominator) {
        this((int) numerator, (int) denominator);
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public static Rational fromString(String str) {
        String[] parts = str.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid format for Rational: " + str);
        }

        try {
            int num = Integer.parseInt(parts[0]);
            int denom = Integer.parseInt(parts[1]);
            return new Rational(num, denom);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numbers in Rational: " + str);
        }
    }

    public double asDouble() {
        return (double) numerator / denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Rational)) return false;
        Rational rational = (Rational) obj;
        return numerator == rational.numerator && denominator == rational.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public int compareTo(Rational other) {
        long thisNum = (long) numerator * other.denominator;
        long otherNum = (long) other.numerator * denominator;
        return Long.compare(thisNum, otherNum);
    }

    public Rational add(Rational rational) {
        Objects.requireNonNull(rational);
        long num = (long) numerator * rational.denominator + (long) rational.numerator * denominator;
        long denom = (long) denominator * rational.denominator;
        return new Rational(num, denom);
    }

    public Rational inv() {
        if (numerator == 0) {
            throw new ArithmeticException("Cannot compute inverse of zero");
        }
        return new Rational(denominator, numerator);
    }

    public Rational mul(Rational other) {
        return new Rational(numerator * other.numerator, denominator * other.denominator);
    }

    public Rational neg() {
        if (numerator == Integer.MIN_VALUE) {
            throw new ArithmeticException("Cannot negate: result outside representable range");
        }
        return new Rational(-numerator, denominator);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }
}
