package com.cutieMath02;

import java.util.*;

public class Solution {

    // 02-05-22
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

    // 03-05-22
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

    // 04-05-22
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int res = 0;
        res = helper(answerKey, res, k, 'T');
        res = helper(answerKey, res, k, 'F');
        return res;
    }
    private int helper(String answerKey, int res, int k, char answer){
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

    // 04-05-22 https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        Set<Character> charSet = new HashSet<>();
        int l = 0;
        int res = 0;
        for(int r = 0; r < s.length(); r ++){
            while(charSet.contains(s.charAt(r))){
                charSet.remove(s.charAt(l)); // remove the left char
                l ++;
            }
            charSet.add(s.charAt(r)); // add in right char
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    // 09-05-22 https://leetcode.com/problems/permutation-in-string/
    public boolean checkInclusion(String s1, String s2) {
        // [0, 0, 0, 0, 0 ...]
        //  a  b  c  d  e ...

        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2)
            return false;

        // Use array to represent the map
        int[] countMap = new int[26];

        // 1. Count the length of s1 then check if contains permutation
        for (int i = 0; i < len1; i ++){
            countMap[s1.charAt(i) - 'a']++;
            countMap[s2.charAt(i) - 'a']--;
            System.out.println(Arrays.toString(countMap));
        }
        if(containsPermutation(countMap))
            return true;

        // 2. Count the rest of s2 to check permutation
        for(int i = len1; i < len2; i ++){
            countMap[s2.charAt(i) - 'a'] --;
            countMap[s2.charAt(i - len1) - 'a'] ++;
            System.out.println("Run rest" + Arrays.toString(countMap));
            if(containsPermutation(countMap))
                return true;
        }
        return false;
    }
    private boolean containsPermutation(int[] countMap){
        for(int i = 0; i < 26; i ++){
            if(countMap[i] != 0)
                return false;
        }
        return true;
    }

    // 11/05/22 https://leetcode.com/problems/interval-list-intersections/
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new LinkedList<>();
        int len1 = firstList.length;
        int len2 = secondList.length;
        int pointer1 = 0;
        int pointer2 = 0;
        while (pointer1 < len1 && pointer2 < len2){
            int s1 = firstList[pointer1][0]; // starting of firstList
            int e1 = firstList[pointer1][1]; // ending of firstList
            int s2 = secondList[pointer2][0]; // Starting of secondList
            int e2 = secondList[pointer2][1]; // Ending of secondList
            // Add intersection
            if(s1 <= s2 && s2 <= e1 || s2 <= s1 && s1 <= e2){
                int s = Math.max(s1, s2);
                int e = Math.min(e1, e2);
                int[] cur = {s, e};
                res.add(cur);
            }
            if(e2 > e1){
                pointer1 ++;
            } else {
                pointer2 ++;
            }
        }
        return res.toArray(new int[res.size()][2]);
    }


}
