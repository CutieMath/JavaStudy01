package com.cutieMath02;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        String s1 = "abcd";
        String s2 = "qionrqabcd";
        Solution solution = new Solution();
        boolean res = solution.checkInclusion(s1, s2);
        System.out.println(res);

        // Note:
        // Static: belongs to a class
        // Instance: belongs to an object
//        Employee employee = new Employee(80_000, 40);
//        Employee employee1 = new Employee(80_000);
//        int extraHours = 10;
//        int wage = employee.calculateWage(extraHours);
//        int wage1 = employee1.calculateWage(extraHours);
//        System.out.println(wage);
//        System.out.println(wage1);
//        Employee.printNumberOfEmployees();
    }

}
