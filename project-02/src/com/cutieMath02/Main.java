package com.cutieMath02;

public class Main {
    public static void main(String[] args){
        Employee employee = new Employee();
        employee.setBaseSalary(50_000);
        employee.setHourlyRage(20);
        int extraHours = 10;
        int wage = employee.calculateWage(extraHours);
        System.out.println(wage);
    }

}
