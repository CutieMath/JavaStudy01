package com.cutieMath02;

public class Main {
    public static void main(String[] args){
        Employee employee = new Employee(80_000, 40);
        int extraHours = 10;
        int wage = employee.calculateWage(extraHours);
        System.out.println(wage);
    }
}
