package com.cutieMath02;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        int[] nums = {2,1,3,3};
        int k = 2;
        Solution solution = new Solution();
        int[] res = solution.maxSubsequence(nums, k);
        System.out.println(Arrays.toString(res));

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
