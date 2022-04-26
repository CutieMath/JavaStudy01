package com.cutieMath02;

public class Employee {
    private int baseSalary;
    private int hourlyRage;

    // Note: Abstraction principle
    // Hide unnecessary methods from other classes

    // Constructor
    public Employee(int baseSalary, int hourlyRage){
        setBaseSalary(baseSalary);
        setHourlyRage(hourlyRage);
    }

    // Setter
    private void setBaseSalary(int baseSalary) {
        if (baseSalary <= 0){
            throw new IllegalArgumentException("Base salary cannot be zero or less.");
        }
        this.baseSalary = baseSalary;
    }
    private void setHourlyRage(int hourlyRage) {
        if (hourlyRage <= 0){
            throw new IllegalArgumentException("Hourly rate cannot be zero or less.");
        }
        this.hourlyRage = hourlyRage;
    }

    // Getter
    private int getBaseSalary() {
        return baseSalary;
    }
    private int getHourlyRage() {
        return hourlyRage;
    }

    // Methods
    public int calculateWage(int extraHours){
        return baseSalary + hourlyRage * extraHours;
    }


}
