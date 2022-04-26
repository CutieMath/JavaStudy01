package com.cutieMath02;

public class Employee {
    private int baseSalary;
    private int hourlyRage;
    private int extraHours;

    public int calculateWage(){
        return baseSalary + hourlyRage * extraHours;
    }


}
