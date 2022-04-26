package com.cutieMath02;

public class Employee {
    private int baseSalary;
    private int hourlyRage;

    // Setter
    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }
    public void setHourlyRage(int hourlyRage) {
        this.hourlyRage = hourlyRage;
    }

    // Methods
    public int calculateWage(int extraHours){
        return baseSalary + hourlyRage * extraHours;
    }


}
