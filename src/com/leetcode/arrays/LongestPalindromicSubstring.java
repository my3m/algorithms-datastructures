package com.leetcode.arrays;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(LongestPalindromicSubstring.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

    public String longestPalindrome(String s) {
        /*
                babad
        */
        if(s.length() <= 1)
            return s;
        char[] arr = s.toCharArray();
        String result = new String(Arrays.copyOf(arr, 1));
        for(int i = 0; i < arr.length - 1; i++) {
            //check for both even, odd palindrome
            int[] even = getPalindromicBounds(arr, i, i + 1);
            int[] odd = getPalindromicBounds(arr, i - 1, i + 1);
            if(even.length > 0) {
                String palindrome = s.substring(even[0], ++even[1]);
                if(palindrome.length() > result.length())
                    result = palindrome;
            }
            if(odd.length > 0) {
                String palindrome = s.substring(odd[0], ++odd[1]);
                if(palindrome.length() > result.length())
                    result = palindrome;
            }
            
        }
        //System.out.println(result);
        return result;
    }

    public int[] getPalindromicBounds(char[] arr, int l, int r) {
        if(l < 0 || r >= arr.length || arr[l] != arr[r])
            return new int[0];
        while(l > 0 && r < arr.length - 1 && arr[l-1] == arr[r+1]) {
            l = l - 1;
            r = r + 1;
        }
        return new int[] { l, r };
    }

    @Test
    public void Test1() {
        final String input = "babad";
        assertEquals("bab", longestPalindrome(input));
    }
    @Test
    public void Test2() {
        final String input = "racecar";
        assertEquals("racecar", longestPalindrome(input));
    }

    @Test
    public void Test3() {
        final String input = "ac";
        assertEquals("a", longestPalindrome(input));
    }

    public boolean isPalindrome(int l, int r, String s) {
        char[] arr = s.toCharArray();
        while(l < r) {
            if(arr[l] != arr[r])
                return false;
            l++;
            r--;
        }
        return true;
    }
}