package com.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinStepsToOne {

    /*

        Given an integer as an input, num, return the fewest operations, or steps, needed to arrive at 1, when you
        can only perform 3 operations:

        Divide by 3, if num is divisible by 3
        Divide by 2, if num is divisible by 2
        Subtract 1

        For example: given an input of 5, there are many paths to arrive at 1. You can subtract 1 five times to
        arrive at 1. But that isn't the shortest path. The largest step you can take first is to subtract 1, which gets
        you to 4. Then you can divide by 2, which gets you to 2. Finally, you subtract 1, which gets you to 1. So in
        total, we performed 3 operations: 5 => 4 => 2 => 1

        Your goal is to return the minimum number of operations from the input integer to 1.
        If num is 1, then return 0, since you don't need to perform any operations to arrive at 1.
    */

    public static void main(String[] args) {
        int result = minimumStepsToOne(1000000);
        System.out.println(result);
    }

    static Map<Integer, Integer> cache = new HashMap<>();
    public static int minimumStepsToOne(int num) {
        int[] dp = new int[1000001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for(int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i], dp[i-1] + 1);
            if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
            if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
        }
        return dp[dp.length - 1];
    }
}