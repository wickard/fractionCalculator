package com.fractionCalulator;

import java.util.Arrays;
import java.util.Scanner;

public class FractionCalculator {

    public static  void main(String[] args){
        while(true){
            System.out.println("-----------------------------------------");
            String answer           ="";
            String operation        = getOperation();
            if (operation.equals("q") || operation.equals("Q")) return;
            Fraction frac1          = getFraction();
            Fraction frac2          = getFraction();
            switch(operation) {
                case "+": answer = frac1.add(frac2).toString();
                    break;
                case "-": answer = frac1.subtract(frac2).toString();
                    break;
                case "*": answer = frac1.multiply(frac2).toString();
                    break;
                case "/": answer = frac1.divide(frac2).toString();
                    break;
                case "=": answer = String.valueOf(frac1.equals(frac2));
                    break;
            }
            System.out.println(frac1 + " " + operation + " " + frac2 + " " + "is " + answer);
        }
    }

    private static String getOperation() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an operation (+, -, /, *, =, or Q to quit): ");
        String[] operations = {"+", "-", "*", "/", "=", "Q", "q" };
        while (true) {
            String operation = input.next();
            for (int i = 0; i < operations.length; i++) {
                if (operations[i].equals(operation)) return operation;
            }
            System.out.print("Invalid input (+, -, /, *, =, or Q to quit): ");
        }
    }

    private static boolean isValidFraction(String fraction){
        int numerator;
        int denominator;
        if (fraction.contains("/")){
            String [] split = fraction.split("/");
            try{
                numerator = Integer.parseInt(split[0]);
                denominator = Integer.parseInt(split[1]);
            } catch(NumberFormatException e) {
                return false;
            }
            if (denominator <= 0) return false;
        }
        else {
            try{
                numerator = Integer.parseInt(fraction);
            } catch(NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private static Fraction getFraction() {
        Scanner input = new Scanner(System.in);
        int numerator;
        int denominator;
        System.out.print("Please enter a fractioin (a/b) or ineger (a): ");
        String userInput = input.next();
        while (!isValidFraction(userInput)) {
            System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is greater than 0: ");
            userInput = input.next();
        }
        if (userInput.contains("/")) {
            String[] split = userInput.split("/");
            numerator = Integer.parseInt(split[0]);
            denominator = Integer.parseInt(split[1]);
            return new Fraction(numerator, denominator);
        }
        else {
            numerator = Integer.parseInt(userInput);
            return new Fraction(numerator);
        }
    }
}
