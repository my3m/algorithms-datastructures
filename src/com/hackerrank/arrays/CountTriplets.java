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

    @Test
    public void Test1() {
        assertEquals(4, countTriplets(new int[] { 1, 5, 5, 25, 125 }, 5));
    }
}