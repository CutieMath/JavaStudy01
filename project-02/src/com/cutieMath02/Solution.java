package com.cutieMath02;

import java.util.Arrays;

public class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        // cus:    {1,0,1,2,1,1,7,5}; // get the max satisfaction of cus first
        // grumpy: {0,1,0,1,0,1,0,1};
        //                i
        int totalSatisfaction = 0;
        for (int i = 0; i < customers.length; i++)
            if (grumpy[i] == 0)
                totalSatisfaction += customers[i];

        int res = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 1)
                totalSatisfaction += customers[i];
            if (i >= minutes && grumpy[i-minutes] == 1)
                totalSatisfaction -= customers[i-minutes];
            res = Math.max(res, totalSatisfaction);
        }
        return res;
    }
    public int maxFrequency(int[] nums, int k) {
        // [1, 1, 1, 2, 2, 4]
        //     l     r
        // 1.
        Arrays.sort(nums);
        // 2. Sliding window
        int l = 0;
        int r = 0;
        int res = 0;
        long total = 0; // 5
        while (r < nums.length){
            total += nums[r];
            // While the window is not valid
            // nums[r] * (r - l + 1) => Total sum of current window if K is used entirely // 8
            // total + k => True sum of current window
            while (nums[r] * (r - l + 1) > total + k){
                total -= nums[l];
                l ++;
            }
            // While the window is valid
            res = Math.max(res, r - l + 1);
            r ++;
        }
        return res;
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
        int res = 0;
        res = helper(answerKey, res, k, 'T');
        res = helper(answerKey, res, k, 'F');
        return res;
    }
    public int helper(String answerKey, int res, int k, char answer){
        char oppoAnswer = (answer == 'T') ? 'F' : 'T';
        int r = 0;
        int flip = 0;
        for (int l = 0; l < answerKey.length(); l ++){
            // If right pointer:
            // - within length
            // - equal to the set answer || k is enough
            while (r < answerKey.length() && (answerKey.charAt(r) == answer || flip < k)){
                if (answerKey.charAt(r) == oppoAnswer)
                    flip ++;
                r ++;
            }
            res = Math.max(res, r - l);
            if (answerKey.charAt(l) == oppoAnswer)
                flip --;
        }
        return res;
    }
}
