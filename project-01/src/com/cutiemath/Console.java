package com.cutiemath;

import java.util.Scanner;

public class Console {

    private static Scanner scanner = new Scanner(System.in);

    //    public static double calculateMortgage(int principle, float interestRate, short period) {
    //        double mortgageRaw = principle * ((interestRate * Math.pow((1 + interestRate), period)) / (Math.pow((1 + interestRate), period) - 1));
    //        return mortgageRaw;
    //    }

    public static double readNumer(String prompt){
        return scanner.nextDouble();
    }
    public static double readNumber(String prompt, double min, double max){
        double value;
        System.out.print(prompt);
        value = scanner.nextDouble();
        while(value < min || value > max){
            System.out.println("Please enter value between " + min + " and " + max + ".");
            System.out.print(prompt);
            value = scanner.nextDouble();
        }
        return value;
    }
}
