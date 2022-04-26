package com.cutieMath02;

public class Main {
    public static void main(String[] args){
        Employee employee = new Employee(80_000, 40);
        Employee employee1 = new Employee(80_000);
        int extraHours = 10;
        int wage = employee.calculateWage(extraHours);
        int wage1 = employee1.calculateWage(extraHours);
        System.out.println(wage);
        System.out.println(wage1);
    }
}
