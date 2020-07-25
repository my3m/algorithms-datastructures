package com.leetcode.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class BuySellStock1 {
    public int getProfit(int[] prices) {
        //[7,1,5,3,6,4]
        //buy 1, sell 5, profit=4
        //buy 3 sell 6 profit=3

        if(prices.length == 0)
            return 0;

        int minPrice = prices[0];
        int maxProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            if(prices[i] > minPrice) {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
            else {
                minPrice = Math.min(minPrice, prices[i]);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BuySellStock1.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

    @Test
    public void Test1() {
        int[] prices = new int[] {
            7,1,5,3,6,4
        };
        assertEquals(5, getProfit(prices));
    }
}