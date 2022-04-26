package com.cutieMath02;

public class Main {
    public static void main(String[] args){
        int baseSalary = 50_000;
        int extraHours = 10;
        int hourlyRate = 20;
        int wage = calcWage(baseSalary, extraHours, hourlyRate);
        System.out.println(wage);
    }
    public static int calcWage(int baseSalary, int extraHours, int hourlyRate) {
        return baseSalary + extraHours * hourlyRate;
    }


}
