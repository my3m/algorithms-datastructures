package com.interviewcake.arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class FindRotationPoint {

    public static int findRotationPoint(String[] words) {
            //[5,8,9,10,11,1,2,3]
        int l = 0, r = words.length - 1;
        while(l <= r) {
            int m = l + (r-l)/2;
            if(m > 0 && words[m-1].compareTo(words[m]) >= 0) {
                return m;
            }
            else {
                if(words[l].compareTo(words[m]) <= 0) {
                    //left side is sorted
                    l = m + 1;
                }
                else {
                    //right side is sorted
                    r = m - 1;
                }
            }
        }
        return 0;
    }

    // tests

    @Test
    public void smallArrayTest() {
        final int actual = findRotationPoint(new String[] { "cape", "cake" });
        final int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void mediumArrayTest() {
        final int actual = findRotationPoint(new String[] { "grape", "orange", "plum", "radish", "apple" });
        final int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void largeArrayTest() {
        final int actual = findRotationPoint(new String[] { "ptolemaic", "retrograde", "supplant", "undulate",
                "xenoepist", "asymptote", "babka", "banoffee", "engender", "karpatka", "othellolagkage" });
        final int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void possiblyMissingEdgeCaseTest() {
        // are we missing any edge cases?
        final int actual = findRotationPoint(new String[] { "apple", "banana", "orange", "raspberry" });
        final int expected = 0;
        assertEquals(expected, actual);
    }

    
    @Test
    public void possiblyMissingEdgeCaseTest2() {
        // are we missing any edge cases?
        final int actual = findRotationPoint(new String[] { "banana", "orange", "raspberry", "apple" });
        final int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void possiblyMissingEdgeCaseTest3() {
        // are we missing any edge cases?
        final int actual = findRotationPoint(new String[] { "banana" });
        final int expected = 0;
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FindRotationPoint.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}