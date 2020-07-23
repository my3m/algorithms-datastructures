package com.hackerrank.sorting;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MarkAndToys {
    static int maximumToys(int[] prices, int k) {
        Arrays.sort(prices);
        int index = 0;
        //1 12 5 111 200 1000 10
        //1  5  10 12 111 200 1000
        //49 44 34 22
        while(k >= prices[index]) {
            k -= prices[index++];
        }
        return index;
    }

    @Test
    public void Test1() {
        assertEquals(4, maximumToys(new int[] { 1, 12, 5, 111, 200, 1000, 10 }, 50));
    }
}
