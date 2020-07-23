package com.hackerrank.arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShareCommonSubstring {
    static String twoStrings(String s1, String s2) {
        int[] counts1 = new int[26];
        for(char chr : s1.toCharArray()) {
            counts1[chr-'a']++;
        }
        for(char chr : s2.toCharArray()) {
            if(counts1[chr-'a'] > 0)
                return "YES";
        }
        return "NO";
    }

    @Test
    public void Test1() {
        assertEquals("YES", twoStrings("helloworld", "ello"));
    }

    @Test
    public void Test2() {
        assertEquals("YES", twoStrings("racecar", "race"));
    }
}
