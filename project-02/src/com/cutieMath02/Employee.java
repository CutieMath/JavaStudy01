package com.cutieMath02;

public class Employee {
    private int baseSalary;
    private int hourlyRage;
    public static int numberOfEmployees; // static fields

    // Note: Abstraction principle
    // Hide unnecessary methods from other classes

    // Static method
    public static void printNumberOfEmployees(){
        System.out.println(numberOfEmployees);
    }

    // Constructors
    public Employee(int baseSalary){
        // Constructor overloading
        this(baseSalary, 0);
    }
    public Employee(int baseSalary, int hourlyRage){
        setBaseSalary(baseSalary);
        setHourlyRage(hourlyRage);
        numberOfEmployees ++;
    }

    // Setter
    private void setBaseSalary(int baseSalary) {
        if (baseSalary <= 0){
            throw new IllegalArgumentException("Base salary cannot be zero or less.");
        }
        this.baseSalary = baseSalary;
    }
    private void setHourlyRage(int hourlyRage) {
        if (hourlyRage < 0){
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
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
    public int calculateWage(){
        // Method overloading
        // Don't overuse
        return calculateWage(0);
    }


}
