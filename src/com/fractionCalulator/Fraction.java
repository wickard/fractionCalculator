package com.fractionCalulator;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator){
        if (denominator == 0) throw new IllegalArgumentException("denominator cannot equal 0.");
        if (numerator > 0 && denominator < 0) {
            numerator -=  numerator  * 2;
            denominator += denominator * 2;
        }
        if (numerator < 0 && denominator < 0) {
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }


    public Fraction (int numerator){
        this(numerator, 1);
    }

    public Fraction (){
        this(0, 1);
    }

    public static int gcd(int numerator, int denominator){
        while (numerator != 0 && denominator != 0){
            int  remainder = numerator % denominator;
            numerator = denominator;
            denominator = remainder;
        }
        return Math.abs(numerator);
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public String toString() {
        this.toLowestTerms();
        if(this.denominator != 1)return Integer.toString(numerator) + '/' + Integer.toString(denominator);
        return Integer.toString(numerator);
    }

    public void toLowestTerms(){
        int gcd = gcd(this.numerator, this.denominator);
        if (gcd != 0){
            numerator = numerator / gcd;
            denominator = denominator / gcd;
        }
    }

    public boolean equals (Object other) {
        if (!(other instanceof Fraction)) throw new IllegalArgumentException("Argument must ba a com.fractionCalulator.Fraction");
        Fraction otherFrac = (Fraction) other;
        this.toLowestTerms();
        otherFrac.toLowestTerms();
        return this.numerator == otherFrac.numerator && this.denominator == otherFrac.denominator;
    }

    public double toDouble() {
        double doubleDen = (double)this.denominator;
        double doubleNum = (double)this.numerator;
        return doubleNum / doubleDen;
    }

    public Fraction add(Fraction other) {
        if (this.denominator != other.denominator) {
            int numerator   = this.numerator * other.denominator;
            int otherNum    = other.numerator * this.denominator;
            int denominator = this.denominator * other.denominator;
            return new Fraction(numerator + otherNum, denominator);
        }
        return new Fraction(this.numerator + other.numerator, this.denominator);
    }

    public Fraction subtract(Fraction other) {
        if (this.denominator != other.denominator) {
            int numerator   = this.numerator * other.denominator;
            int otherNum    = other.numerator * this.denominator;
            int denominator = this.denominator * other.denominator;
            return new Fraction(numerator - otherNum, denominator);
        }
        return new Fraction(this.numerator - other.numerator, this.denominator);
    }

    public Fraction multiply(Fraction other){
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    public Fraction divide(Fraction other){
        return new Fraction (this.numerator * other.denominator, this.denominator * other.numerator);
    }


}
