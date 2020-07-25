package com.hackerrank.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindMaximumSubarray {
    public int maxSubArray(int[] arr) {
        if(arr.length == 0)
          return 0;
        //-2,1,-3,4,-1,2,1,-5,4
        //int maximumSubArray = Integer.MIN_VALUE;
        int continious = arr[0], maximumSubArray = arr[0];
        for(int i = 1; i < arr.length; i++) {
          continious = Math.max(arr[i], arr[i] + continious);
          maximumSubArray = Math.max(continious, maximumSubArray);
        }
        return maximumSubArray;
    }

    @Test
    public void Test1() {
        assertEquals(6, maxSubArray(new int[] { -2,1,-3,4,-1,2,1,-5,4}));
    }
}