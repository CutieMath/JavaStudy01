package com.cutiemath;

import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);
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
    public static double readNumber(String prompt){
        // Method overloading
        return scanner.nextDouble();
    }
}
