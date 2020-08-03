package com.leetcode.recursion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RegularExpressionMatching {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RegularExpressionMatching.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public boolean isMatch(String s, String p) {
        /*
         * open= (
         */
        return isMatchHelper(s, p, 0, 0);
    }

    private boolean isMatchHelper(String s, String p, int i, int j) {
        if (j == p.length()) {
            if (i == s.length())
                return true;
            else
                return false;
        }
        // Case 1. Handle *
        // Case 2 Handle "."
        boolean result = false;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            if (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')) {
                result = isMatchHelper(s, p, i + 1, j) || isMatchHelper(s, p, i, j + 2);
            } else {
                result = isMatchHelper(s, p, i, j + 2);
            }
        } else if ((i < s.length() && p.charAt(j) == s.charAt(i)) || p.charAt(j) == '.') {
            result = isMatchHelper(s, p, i + 1, j + 1);
        }
        return result;
    }

    @Test
    public void Test1() {
        assertEquals(true, isMatch("aa", "aa"));
        assertEquals(true, isMatch("aa", "a*"));
        assertEquals(true, isMatch("aa", "a."));
        assertEquals(true, isMatch("aa", ".."));
        assertEquals(false, isMatch("aaa", ".."));
    }

    @Test
    public void Test2() {
        assertEquals(true, isMatch("aa", ".*"));
    }

    @Test
    public void Test3() {
        assertEquals(true, isMatch("aab", "c*a*b*"));
    }

    @Test
    public void Test4() {
        assertEquals(false, isMatch("mississippi", "mis*is*p*."));
    }

    @Test
    public void Test5() {
        assertEquals(true, isMatch("aaaaa", ".*"));
    }
}