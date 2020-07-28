package com.interviewcake.arrays;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MakingChange {
    /*
        Assumption: Is denominations array sorted??

        1,2,2 is same as 2,1,2 (double counting)
            To prevent this greedily, select maximum number of quantity of coin before moving to next
            index

        coints= {1,2,4}
        amount= 4
            coin (1), we can use 1,2,3,4 (1) coins
            coin (2), we can use 1,2 (2) coins
            coin (4), we can use 1 (4) coins
    */
    /*
        int changePossibilities(int amount, int[] denominations) {

        if(amount < 0)
            return 0;
        else if(amount == 0)
            return 1;
        int totalWays = 0;
        
        for(int denom : denominations) {
            totalWays += changePossibilities(amount - denom, denominations);
        }
        return totalWays;
    }
    */

    int changePossibilities(int amount, int[] denominations) {
        //System.out.println(Arrays.toString(denominations));
        Arrays.sort(denominations);
        return changeHelper(0, amount, denominations);
    }

    int changeHelper(int index, int amount, int[] denominations) {
        if(amount == 0)
            return 1;
        else if(amount < 0 || index >= denominations.length)
            return 0;
        int totalWays = 0;
        int coin = denominations[index];
        if(amount >= coin) {
            int quantity = amount / coin;
            for(int i = 1; i <= quantity; i++) {   
                //1:5, 2:5, 3:5
                totalWays += changeHelper(index + 1, amount - (coin*i), denominations);
            }
            totalWays += changeHelper(index + 1, amount, denominations);
        }        
        return totalWays;
    }

    @Test
    public void sampleInputTest() {
        final int actual = changePossibilities(4, new int[] {1, 2, 3});
        final int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void oneWayToMakeZeroCentsTest() {
        final int actual = changePossibilities(0, new int[] {1, 2});
        final int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void noWaysIfNoCoinsTest() {
        final int actual = changePossibilities(1, new int[] {});
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void bigCoinValueTest() {
        final int actual = changePossibilities(5, new int[] {25, 50});
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void bigTargetAmountTest() {
        final int actual = changePossibilities(50, new int[] {5, 10});
        final int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    public void changeForOneDollarTest() {
        final int actual = changePossibilities(100, new int[] {1, 5, 10, 25, 50});
        final int expected = 292;
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MakingChange.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}