package com.hackerrank.arrays;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DegreeOfAnArray {

    public int findDegreeSlow(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        int maxFreq = -1;
        for(int x : arr) {
          freq.put(x, freq.getOrDefault(x, 0) + 1);
          if(maxFreq == -1 || freq.get(x) > maxFreq)
            maxFreq = freq.get(x);
        }
        List<Integer> nums = new ArrayList<>();
        for(int key : freq.keySet()) {
          if(freq.get(key) == maxFreq)
            nums.add(key);
        }
        
        //find the minimum window sub array which contains
        //[1,2,2,3,1]
        //[1,2]
        //1:[1,2,2,3,1]
        //2:[1,2,2], [2,2]
        int minimumSubarray = arr.length;
        for(int x : nums) {
          int j = 0;
          Map<Integer, Integer> currFreq = new HashMap<>();
          for(int i = 0; i < arr.length; i++) {
            currFreq.put(arr[i], currFreq.getOrDefault(arr[i], 0) + 1);
            while(currFreq.getOrDefault(x, 0) == maxFreq) {
              minimumSubarray = Math.min(minimumSubarray, i - j + 1);
              currFreq.put(arr[j], currFreq.get(arr[j]) - 1);
              j++;
            }
          }
        }
        return minimumSubarray;
      }

    public int findDegree(int[] arr) {
        /*
         * [1,2,2,3,4,5,1]
         */
        Map<Integer, List<Integer>> freq = new HashMap<>();
        int maxFreq = -1;
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];
            freq.putIfAbsent(x, new ArrayList<>());
            freq.get(x).add(i);
            if (maxFreq == -1 || freq.get(x).size() > maxFreq) {
                maxFreq = freq.get(x).size();
            }
        }
        int shortestSubArray = arr.length;
        for (int key : freq.keySet()) {
            if (freq.get(key).size() == maxFreq) {
                List<Integer> indices = freq.get(key);
                int first = indices.get(0), last = indices.get(indices.size() - 1);
                shortestSubArray = Math.min(shortestSubArray, last - first + 1);
            }
        }
        return shortestSubArray;
    }

    @Test
    public void Test1() {
        assertEquals(2, findDegree(new int[] { 1, 2, 2, 3, 4, 5, 1 }));
    }

    @Test
    public void Test2() {
        assertEquals(7, findDegree(new int[] { 1, 2, 2, 3, 4, 5, 1, 2 }));
    }

}