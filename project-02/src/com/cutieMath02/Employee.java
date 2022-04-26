package com.cutieMath02;

public class Employee {
    private int baseSalary;
    private int hourlyRage;

    // Setter
    public void setBaseSalary(int baseSalary) {
        if (baseSalary <= 0){
            throw new IllegalArgumentException("Base salary cannot be zero or less.");
        }
        this.baseSalary = baseSalary;
    }
    public void setHourlyRage(int hourlyRage) {
        if (hourlyRage <= 0){
            throw new IllegalArgumentException("Hourly rate cannot be zero or less.");
        }
        this.hourlyRage = hourlyRage;
    }

    // Getter
    public int getBaseSalary() {
        return baseSalary;
    }
    public int getHourlyRage() {
        return hourlyRage;
    }

    // Methods
    public int calculateWage(int extraHours){
        return baseSalary + hourlyRage * extraHours;
    }


}
