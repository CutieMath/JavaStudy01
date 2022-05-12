package com.cutieMath02;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        int[] nums1 = {5,6,4,3,1,2};
        int[] nums2 = {6,3,3,1,4,5,3,4,1,3,4};
        Solution solution = new Solution();
        int res = solution.minOperations(nums1, nums2);
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
