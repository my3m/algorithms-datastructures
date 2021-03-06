package com.hackerrank.arrays;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/*
    Find triplets in arr, such that arr[i] < arr[j] < arr[k] && i < j < k
*/
public class CountTriplets {
    public static int countTriplets(final int[] arr, final int r) {
        final Map<Integer, Integer> right = new HashMap<>();
        for (final int x : arr) {
            right.putIfAbsent(x, 0);
            right.put(x, right.get(x) + 1);
        }

        final Map<Integer, Integer> left = new HashMap<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            final int x = arr[i];
            if (right.containsKey(x)) {
                right.put(x, right.get(x) - 1);
            }

            if (left.containsKey(x / r) && x % r == 0 && right.containsKey(x * r)) {
                final int numLeft = left.get(x / r), numRight = right.get(x * r);
                count += numLeft * numRight;

            }

            left.putIfAbsent(x, 0);
            left.put(x, left.get(x) + 1);
        }
        System.out.println(count);
        return count;
    }

    public static void countTriplets2(int[] arr, int r) {
        int ans = 0;
        for(int i = 0; i < arr.length; i++) {
          ans += countTripletsHelper(arr, r, i, 0);
        }
        //ans+= countTripletsHelper(arr, r, 1, 0);
        System.out.println(ans);
      }
      
      
      public static int countTripletsHelper(int[] arr, int r, 
                                            int index, int n) {
        if(n == 2) {
          return 1;
        }
        int ans = 0;
        for(int i = index; i < arr.length; i++) {
          if(arr[index]*r == arr[i]) {
            ans += countTripletsHelper(arr, r, i, n + 1);
          }
        }
        return ans;
      }

    @Test
    public void Test1() {
        assertEquals(4, countTriplets(new int[] { 1, 5, 5, 25, 125 }, 5));
    }

    
    @Test
    public void Test2() {
       countTriplets2(new int[] { 1, 1, 1, 1 }, 1);
    }
}