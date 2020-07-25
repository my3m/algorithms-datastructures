package com.interviewcake.arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class FindInOrderedSet {

    public static boolean contains(int[] a, int n) {

        // check if an integer is present in the array
        int l = 0, r = a.length - 1;
        while(l < r) {
            int m = l + (r-l)/2;
            if(a[m] < n) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return l >= 0 && l < a.length && a[l] == n;
    }

    // tests

    @Test
    public void emptyArrayTest() {
        final boolean result = contains(new int[] {}, 1);
        assertFalse(result);
    }

    @Test
    public void oneItemArrayNumberPresentTest() {
        final boolean result = contains(new int[] { 1 }, 1);
        assertTrue(result);
    }

    @Test
    public void oneItemArrayNumberAbsentTest() {
        final boolean result = contains(new int[] { 1 }, 2);
        assertFalse(result);
    }

    @Test
    public void smallArrayNumberPresentTest() {
        final boolean result = contains(new int[] { 2, 4, 6 }, 4);
        assertTrue(result);
    }

    @Test
    public void smallArrayNumberAbsentTest() {
        final boolean result = contains(new int[] { 2, 4, 6 }, 5);
        assertFalse(result);
    }

    @Test
    public void largeArrayNumberPresentTest() {
        final boolean result = contains(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 8);
        assertTrue(result);
    }

    @Test
    public void largeArrayNumberAbsentTest() {
        final boolean result = contains(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 0);
        assertFalse(result);
    }

    @Test
    public void largeArrayNumberFirstTest() {
        final boolean result = contains(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 1);
        assertTrue(result);
    }

    @Test
    public void largeArrayNumberLastTest() {
        final boolean result = contains(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 10);
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FindInOrderedSet.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}