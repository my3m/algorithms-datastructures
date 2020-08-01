package com.leetcode.strings;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(LongestCommonPrefix.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public String getLongestCommonPrefix(String[] words) {
        /*
            flower, aosjifoia
        */
        if(words.length == 0)
            return "";
        else if(words.length == 1)
            return words[0];
        String prefix = words[0];
        for(int i = 1; i < words.length; i++) {
            if(prefix.length() == 0 || words[i].length() == 0)
                return "";
            if(prefix.length() > 0 && prefix.charAt(0) != words[i].charAt(0))
                return "";
            while(prefix.length() > 0 && words[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            if(prefix.length() == 0)
                break;
        }
        return prefix;
    }

    @Test
    public void Test1() {
        assertEquals("flow", getLongestCommonPrefix(new String[] { "flower", "flow" }));
        assertEquals("", getLongestCommonPrefix(new String[] { "flower", "" }));
        assertEquals("", getLongestCommonPrefix(new String[] { "flower", "apojfpoqaf" }));
    }

    @Test
    public void Test2() {
        assertEquals("flo", getLongestCommonPrefix(new String[] { "florida" ,"flower", "flow", "flo" }));
        assertEquals("", getLongestCommonPrefix(new String[] { "dog","racecar","car" }));
    }

    @Test
    public void Test3() {
        
    }
}