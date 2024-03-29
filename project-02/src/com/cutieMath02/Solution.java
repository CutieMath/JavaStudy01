package com.cutieMath02;

import java.util.*;
import java.util.stream.IntStream;

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

    // 12/05/22 https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations/
    public int minOperations(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        //  not possible case
        if (Math.min(len1, len2) * 6 < Math.max(len1, len2))
            return -1;

        // possible case
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();
        // 1. Switch position of the two so that the algo only need to cater for one situation
        if (sum1 > sum2)
            return minOperations(nums2, nums1);
        if (sum1 == sum2)
            return 0;
        // 2. Sort
        Arrays.sort(nums1);
        // This is just to sort in reverse
        nums2 = IntStream.of(nums2).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();
        // 3. Start the algo
        int res = 0;
        int i = 0;
        int j = 0;
        while (sum1 != sum2){
            int diff = sum2 - sum1;
            int d1 = (i == len1) ? 0 : 6 - nums1[i];
            int d2 = (j == len2) ? 0 : nums2[j] - 1;
            int d = Math.min(diff, Math.max(d1, d2)); // use the min to check if the answer is equal
            if (d1 >= d2){
                sum1 += d;
                i ++;
            } else {
                sum2 -= d;
                j ++;
            }
            res ++;
        }
        return res;
    }

    // 18/05 https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/
    public int[] maxSubsequence(int[] nums, int k) {
        // Create a minHeap priority queue
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[0] - n2[0]); // https://stackoverflow.com/questions/6065710/how-does-javas-priorityqueue-differ-from-a-min-heap
        for (int i = 0; i < nums.length; i ++){
            pq.offer(new int[]{nums[i], i}); // store value and index in priority queue
            if(pq.size() > k){
                pq.poll();
            }
        }

        // add into result
        Set<Integer> indx = new HashSet<>(); // create an index set to store the results
        while (!pq.isEmpty()){
            int[] top = pq.poll();
            indx.add(top[1]);
        }
        int[] res = new int[k];
        int resIndx = 0;
        for (int i = 0; i < nums.length; i ++){
            if(indx.contains(i)){
                res[resIndx] = nums[i];
                resIndx ++;
            }
        }
        return res;
    }

    // 24/05 https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    public int findMin(int[] nums) {
        int res = nums[0];
        int l = 0;
        int r = nums.length - 1;

        while (l <= r){
            // 1. If the array is already sorted
            if (nums[l] < nums[r]){
                res = Math.min(res, nums[l]);
                break;
            }

            // 2.
            int m = (l + r) / 2;
            res = Math.min(res, nums[m]);
            if (nums[m] >= nums[l]){
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }


    // 07/06/22 https://leetcode.com/problems/search-in-rotated-sorted-array/
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        // 4,5,6,7,8,0,1,2

        while (l <= r){
            int mid = (l + r) / 2;
            if (target == nums[mid]){
                return mid;
            }

            // Search left portion
            if(nums[l] <= nums[mid]){
                if(target > nums[mid] || target < nums[l]){
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }

            // Search right portion
            } else {
                if(target < nums[mid] || target > nums[r]){
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }

    // 08/06/22 https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
    public boolean searchII(int[] nums, int target) {
        // Sorted => Binary search
        // normal:  4 5 6 0 0 2 3
        // special: 3 1 2 3 3 3 3

        int low = 0;
        int high = nums.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if(nums[mid] == target){
                return true;
            }

            // Special case
            if(nums[low] == nums[mid] && nums[high] == nums[mid]){
                low ++;
                high --;

            // Smaller array on the left
            } else if(nums[low] <= nums[mid]){
                if(nums[mid] > target && nums[low] <= target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            // Smaller array on the right
            } else {
                if(nums[mid] < target && nums[high] >= target){
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    // 09/06/22 https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    public int[] searchRange(int[] nums, int target) {
        int left = this.binSearch(nums, target, true);
        int right = this.binSearch(nums, target, false);
        int[] res = {left, right};
        return res;
    }

    private int binSearch(int[] nums, int target, boolean leftBias){
        int l = 0;
        int r = nums.length - 1;
        int i = -1;
        while (l <= r){
            int mid = (l + r) / 2;

            // Search right
            if(target > nums[mid]){
                l = mid + 1;

            // Search left
            } else if (target < nums[mid]){
                r = mid - 1;

            // Add into result
            } else {
                i = mid;

                // Add extra step to continue the binSearch for dups
                if (leftBias){
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return i;
    }

    // 14/06/22
    public int divide(int dividend, int divisor) {
        // edge case
        if(dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        // check neg/pos sign and change all to negative
        boolean negSign = ((dividend < 0) == (divisor < 0));
        dividend = (dividend > 0) ? -dividend: dividend;
        divisor = (divisor > 0) ? -divisor: divisor;

        // put into helper function
        int res = helper(dividend, divisor);
        return negSign ? res : -res;
    }

    private int helper (int dividend, int divisor){
        if (dividend > divisor) // -2 / -5
            return 0;

        int _sum = divisor;
        int m = 1;

        while ((Integer.MIN_VALUE - _sum < _sum) && (_sum * 2 > dividend)) {
            _sum *= 2;
            m *= 2;
        }

        return m + helper(dividend - _sum, divisor);
    }

    // 15/06/22 https://leetcode.com/problems/count-good-numbers/
    public int countGoodNumbers(long n) {

        // 1. find the result
        // odd = 4   (2, 3, 5, 7)
        // even = 5  (0, 2, 4, 6, 8)

        // 5 4 5 4 => combination
        // 0 1 2 3 => position

        // 5 x 4 x 5 x 4 => 5 ^ n/2 x 4 ^ n/2 x 1 (if n is even)
        //               => 5 ^ n/2 x 4 ^ n/2 x 5 (If n is odd)

        // (5 x 4) ^ n/2
        // 20 ^ n/2

        // 2. use fast way to find the result
        // 2 ^ 8 = 4 ^ 4 = 16 ^ 2 => in every iteration, increase the base

        int MOD = 1_000_000_007;
        long res = (n % 2 == 0) ? 1 : 5;
        long x = 20;

        // use the binary way to calculate the result
        for (long i = n / 2; i > 0; i /= 2){
            if ( i % 2 != 0){
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return (int) res;
    }

    //16/06/22 https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
    public int shipWithinDays(int[] weights, int days) {
        int l = Arrays.stream(weights).max().getAsInt();
        int r = IntStream.of(weights).sum();

        while (l < r){
            int mid = (l + r) / 2;
            if (canShip(mid, weights, days)){
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean canShip(int candidate, int[] weights, int days){
        int currWeight = 0;
        int daysTaken = 1;

        for (int i = 0; i < weights.length; i ++){
            currWeight += weights[i];
            if (currWeight > candidate){
                daysTaken += 1;
                currWeight = weights[i];
            }
        }

        return daysTaken <= days;
    }

    // 21/06/22 https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1;
        int r = Arrays.stream(nums).max().getAsInt();
        while (l <= r) {
            int mid = (int)Math.floor((l + r) / 2);
            if (metCriteria(mid, nums, threshold)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
    private boolean metCriteria(int mid, int[] nums, int threshold){
        int _sum = 0;
        for(int i = 0; i < nums.length; i ++){
            _sum += Math.ceil(nums[i] / mid);
        }
        return _sum > threshold;
    }

    // 23/06/22 https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/
    int max = 0;
    public int findBestValue(int[] arr, int target) {
        int len = arr.length;
        for (int i = 0; i < len; i ++)
            max = Math.max(max, arr[i]);

        int l = 0;
        int r = max;
        while(l < r){
             int mid = l + (r - l) / 2;
            if(check(arr, mid, target) <= check(arr, mid + 1, target))
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
    private int check(int[] arr, int value, int target){
        int sum = 0;
        for (int e : arr){
            if (e > value)
                sum += value;
            else
                sum += e;
        }
        return Math.abs(sum - target);
    }

    public int minDays(int[] bloomDay, int m, int k) {
        // edge
        if(m * k > bloomDay.length)
            return -1;

        // binary search starts
        int left = 1;
        int right = 1;
        int mid;
        // get the lower and upper bound
        for (int i: bloomDay){
            left = Math.min(left, i);
            right = Math.max(right, i);
        }
        while (left < right) {
            mid = left + (right - left) / 2;
            if (canMake(mid, m, k, bloomDay)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    private boolean canMake(int val, int bouquet, int numOfflowers, int[] bloom){
        int count = 0;
        for (int i:bloom){
            if (i <= val){
                count ++;
                if(count == numOfflowers){
                    bouquet --;
                    count = 0;
                    if (bouquet == 0)
                        return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    // 29/06/22
    public int rangeSum(int[] nums, int n, int left, int right) {
        int MOD = 1_000_000_007;
        int nn = n*(n + 1) / 2;
        long[] prefixes = new long[nn];
        int k = 0;
        for (int i = 0; i < n; i++) {
            long currSum = 0;
            for (int j = i; j < n; j++) {
                currSum += nums[j];
                prefixes[k++] = currSum;
            }
        }
        Arrays.sort(prefixes);
        long result = 0;
        while (left <= right) {
            result += prefixes[left - 1];
            left++;
        }
        return (int)(result % MOD);
    }

    // 05/07/22 1552 https://leetcode.com/problems/magnetic-force-between-two-balls/
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int res = 0;
        int head = 0;
        int tail = position[position.length - 1];
        while(head <= tail){
            int mid = head + (tail - head) / 2;
            if(metGoodCondition(m, position, mid)){
                res = mid;
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        return res;
    }

    private boolean metGoodCondition(int numOfBalls, int[] position, int max){
        int count = 1;
        int last = position[0];
        for(int i = 0; i < position.length; i ++ ){
            if(position[i] - last >= max){
                last = position[i];
                count ++;
            }
        }
        return count >= numOfBalls;
    }

    // 06/07 https://github.com/doocs/leetcode/blob/main/solution/1800-1899/1891.Cutting%20Ribbons/README_EN.md
    public int maxLength(int[] ribbons, int k) {
        int l = 1;
        int r = Arrays.stream(ribbons).max().getAsInt();

        while(l <= r){
            int mid = (l + r) / 2;
            if (canCut(ribbons, k, mid)){
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private boolean canCut(int[] ribbons, int k, int ribbonLength){
        int cut = 0;
        for (int i = 0; i < ribbons.length; i ++){
            cut += ribbons[i] / ribbonLength;
        }
        return cut >= k;
    }

    // 12/07 https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
    public int specialArray(int[] nums) {
        int r = nums.length;
        int l = 1;
        while(l <= r){
            int mid = r - (r-l) / 2;
            int count = 0;
            for (int x : nums) {
                if (x >= mid)
                    count++;
            }
            if(count == mid)
                return mid;
            else if (count > mid)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }

    // 13/07 1648 https://leetcode.com/problems/sell-diminishing-valued-colored-balls/
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        long res = 0;
        int n = inventory.length-1;
        long count = 1;
        while(orders > 0){
            if(n > 0 && inventory[n] - inventory[n-1] > 0 && orders >= count * (inventory[n] - inventory[n-1])){
                res += count * _sum(inventory[n], inventory[n-1]);
                orders -= count * (inventory[n] - inventory[n-1]);
            }else if(n == 0 || inventory[n] - inventory[n-1] > 0){
                long a = orders / count;
                res += count * _sum(inventory[n], inventory[n]-a);
                long b = orders % count;
                res += b * (inventory[n]-a);
                orders = 0;
            }
            res %= 1000000007;
            n --;
            count ++;
        }
        return (int)res;
    }

    private long _sum(long n, long x){
        return (n * (n+1))/2 - (x * (x+1))/2;
    }


    // 14/07 1802 https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/
    public int maxValue(int n, int index, int maxSum) {
        long r = n - index - 1;
        long l = index;
        long hi = maxSum;
        long lo = 1;
        long res = 0;

        while (lo <= hi){
            long mid = (hi - lo) / 2 + lo;
            long _sum = mid;
            long ls = 0;
            long rs = 0;
            long m = mid - 1;

             if(r <= m) {
                 rs = m * (m + 1)/2 - (m-r) * (m - r + 1) / 2;
             } else {
                 rs = m * (m + 1)/2 + 1 * (r - m);
             }

             if(l <= m){
                 ls = m * (m + 1) / 2 - (m - l) * (m - l + 1) / 2;
             } else {
                 ls = m * (m + 1) / 2 + 1 * (l - m);
             }

             _sum += ls + rs;
             if(_sum <= maxSum){
                 res = mid;
                 lo = mid + 1;
             } else {
                 hi = mid - 1;
             }
        }
        return (int)res;
    }


    // 19/07 1891 https://medium.com/@miniChang8/leetcode-cutting-ribbons-96ef52c31cfb
    public int cutRibbons(int[] ribbons, int k) {
        // Identify low and high boundaries
        int l = 1;
        int r = Arrays.stream(ribbons).max().getAsInt();

        while (l <= r) {
            int mid = (l + r) / 2;
            if (canCut2(ribbons, k, mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private boolean canCut2(int[] ribbons, int k, int ribbonLength){
        int cut = 0;
        for (int i = 0; i < ribbons.length; i ++){
            cut += ribbons[i] / ribbonLength;
        }
        return cut >= k;
    }

    // 21/07 2064 https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/
    public int minimizedMaximum(int n, int[] quantities) {
        int l = 1;
        int r = Integer.MAX_VALUE / 2;
        while (l < r){
            int mid = l + (r - l) / 2;
            if(good(quantities, n, mid)){
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean good(int[] quantities, int n, int limit){
        int count = 0;

        for (int i = 0; i < quantities.length; i ++){
            if(quantities[i] % limit == 0){
                count += quantities[i] / limit;
            } else {
                count += quantities[i] / limit + 1;
            }
        }
        return count <= n;
    }

    // 26/07 2226 https://leetcode.com/problems/maximum-candies-allocated-to-k-children/
    public int maximumCandies(int[] candies, long k) {
        int l = 1;
        int r = Arrays.stream(candies).max().getAsInt();
        while (l < r){
            int mid = l + (r - l) / 2;
            if(meetsCondition(candies, k, mid)){
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return meetsCondition(candies, k, l) ? l : l - 1;
    }

    private boolean meetsCondition(int[] candies, long k, int pileSize){
        if(pileSize == 0){
            return true;
        }
        long totalPiles = 0;
        for(int candy: candies){
            totalPiles += candy / pileSize;
            if(totalPiles >= k){
                return true;
            }
        }
        return false;
    }

    // 02/08/22 1918 https://leetcode.ca/2021-08-01-1918-Kth-Smallest-Subarray-Sum/
    public int kthSmallestSubarraySum(int[] nums, int k) {
        int min = Integer.MAX_VALUE, sum = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }
        int low = min, high = sum;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int count = countSubarrays(nums, mid);
            if (count < k)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    public int countSubarrays(int[] nums, int threshold) {
        int count = 0;
        int sum = 0;
        int length = nums.length;
        int left = 0, right = 0;
        while (right < length) {
            sum += nums[right];
            while (sum > threshold) {
                sum -= nums[left];
                left++;
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }

    // 03/08/22 49 https://leetcode.com/problems/group-anagrams/
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> groupedAnagrams = new ArrayList<>();
        HashMap<String, List<String>> myMap = new HashMap<>();
        for (String current:strs){
            char[] characters = current.toCharArray();
            Arrays.sort(characters);
            String sorted = new String(characters);
            if(!myMap.containsKey(sorted)){
                myMap.put(sorted, new ArrayList<>());
            }
            myMap.get(sorted).add(current);
        }
        groupedAnagrams.addAll(myMap.values());
        return groupedAnagrams;
    }

    // 04/08/22 166 https://leetcode.com/problems/fraction-to-recurring-decimal/
    public String fractionToDecimal(int numerator, int denominator) {
        // Take care of the edge
        if(numerator == 0) return "0";

        // Determine neg/pos
        StringBuilder sb = new StringBuilder();
        if(numerator < 0 && denominator > 0 || numerator >  0 && denominator < 0){
            sb.append("-");
        }

        // Calc the first position
        long divisor = Math.abs((long) numerator);
        long dividend = Math.abs((long) denominator);
        long remainder = divisor % dividend;
        sb.append(divisor / dividend);
        if(remainder == 0){
            return sb.toString();
        }

        // Calc the decimals
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0){
            if(map.containsKey(remainder)){
                sb.insert(map.get(remainder), "(");
                sb.append(")");
                break;
            }
            map.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / dividend);
            remainder %= dividend;
        }
        return sb.toString();
    }


    // 10/08/22 204 https://leetcode.com/problems/count-primes/
    public int countPrimes(int n) {
        boolean[] primes = new boolean[n];
        System.out.println(primes.length);
        for (int i = 2; i * i < primes.length; i ++){
            if(!primes[i]){
                for (int j = i; j * i < primes.length; j ++){
                    primes[i * j] = true;
                }
            }
        }
        int res = 0;
        for (int i = 2; i < primes.length; i ++){
            if (!primes[i]){
                res ++;
            }
        }
        return res;
    }

    //16/08/22 409 https://leetcode.com/problems/longest-palindrome/
    public int longestPalindrome(String s) {
        int[] char_counts = new int[128];
        for (char c : s.toCharArray()){
            char_counts[c] ++;
        }

        int res = 0;
        for (Integer char_count: char_counts){
            res += char_count / 2 * 2; // integer division
            if( res % 2 == 0 && char_count % 2 == 1){
                res += 1;
            }
        }

        return res;
    }

    //17/08/22 447 https://leetcode.com/problems/number-of-boomerangs/
    public int numberOfBoomerangs(int[][] points) {
        HashMap<Integer, Integer> distances = new HashMap<>();
        int res = 0;
        for (int i = 0; i < points.length; i ++){
            for (int j = 0; j < points.length; j ++){
                if (i == j){
                    continue;
                }
                int distance = ((points[i][0] - points[j][0]) * (points[i][0] - points[j][0]))
                        + ((points[i][1] - points[j][1]) * (points[i][1] - points[j][1]));
                distances.put(distance, distances.getOrDefault(distance, 0) + 1);
            }
            for (Integer distance : distances.values()){
                res += distance * (distance - 1);
            }
            distances.clear();
        }
        return res;
    }


    // 18/08/22 438 https://leetcode.com/problems/find-all-anagrams-in-a-string/
    public List<Integer> findAnagrams(String s, String p) {
        int[] charCount = new int[26];
        for(int i = 0; i < p.length(); i++) charCount[p.charAt(i) - 'a']++;
        List<Integer> res = new ArrayList<>();

        // The left and right end represent the end of a window.
        // toVisit gives # elements remaining to be visited in the window, till we slide the window.
        int left = 0, right = 0, toVisit = p.length();
        while(right < s.length()){
            // If char at right end of window is present in p(charCount)
            if(charCount[s.charAt(right) - 'a'] >= 1) {
                toVisit--;
            }
            charCount[s.charAt(right) - 'a']--; // Reduce count of char at right end.
            right++; // Increment right end
            if(toVisit == 0) res.add(left);

            // Completely scan the window
            if(right - left == p.length()){
                if(charCount[s.charAt(left) - 'a'] >= 0){
                    toVisit++;
                }
                charCount[s.charAt(left) - 'a']++;
                left++;
            }
        }
        return res;
    }

    // 23/08/22 594 https://leetcode.com/problems/longest-harmonious-subsequence/
    public int findLHS(int[] nums) {
        int res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i : nums){
            count.put(i, count.getOrDefault(i,0) + 1);
        }
        for (int i : count.keySet()) {
            if (count.containsKey(i + 1)){
                res = Math.max(res, count.get(i) + count.get(i + 1));
            }
        }
        return res;
    }

    // 25/08/22 532 https://leetcode.com/problems/k-diff-pairs-in-an-array/submissions/
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int res = 0;
        for (int i: map.keySet()){
            if ((k > 0 && map.containsKey(i + k)) || (k == 0 && map.get(i) > 1))
                res ++;
        }

        return res;

    }


    // 26/08/22 890 https://leetcode.com/problems/find-and-replace-pattern/
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (check(word, pattern)) res.add(word);
        }
        return res;
    }

    private boolean check(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (a.indexOf(a.charAt(i)) != b.indexOf(b.charAt(i))) return false;
        }
        return true;
    }


    // 13/08/22 https://leetcode.com/problems/number-of-ways-to-split-a-string/
    int MOD = (int)Math.pow(10, 9) + 7;
    public int numWays(String s) {
        // If sum % 3 != 0, there will be no results
        int numOnes = (int)s.chars()
                .filter(c -> c == '1')
                .count();
        if(numOnes % 3 != 0) {
            return 0;
        }

        if(numOnes == 0){
            long len = s.length();
            return (int) ((int)(len - 1) * (len - 2) / 2 % MOD);
        }

        // Compute results
        int onePerGroup = numOnes / 3;
        int counter = 0;
        long firstBlock = 0, secondBlock = 0;
        for (char letter : s.toCharArray()){
            if (letter == "1".charAt(0)){
                counter ++;
            }
            if(counter == onePerGroup){
                firstBlock ++ ;
            } else if ( counter == 2 * onePerGroup){
                secondBlock ++;
            }

        }
        return (int)(firstBlock * secondBlock % MOD);

    }



}
