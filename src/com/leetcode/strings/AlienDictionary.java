package com.leetcode.strings;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AlienDictionary {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AlienDictionary.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public boolean verifyDictionary(String[] words, String order) {
        /*
            order="hlabcdefgijkmnopqrstuvwxyz"
            words=["hello", "leetcode"]
        */

        Map<Character, Integer> chars = new HashMap<>();
        for(int i = 0; i < order.length(); i++)
            chars.put(order.charAt(i), i);

        for(int i = 1; i < words.length; i++ ) {
            String prev = words[i-1], curr = words[i];
            int index = 0, len = Math.min(prev.length(), curr.length());
            //"appleg", "applex"
            boolean sameChars = true;
            for(; index < len; index++) {
                if(prev.charAt(index) != curr.charAt(index)) {
                    sameChars = false;
                    if(chars.get(prev.charAt(index)) > chars.get(curr.charAt(index)))
                        return false;
                    break;
                }
            }
            //index == len, if there are same chars
            if(sameChars && prev.length() > curr.length())
                return false;
            //if we reach here, then check if curr.length() > prev.length()
        }
        return true;
    }

    @Test
    public void Test1() {
        final String[] words = new String[] { "hello","leetcode" };
        final String order = "hlabcdefgijkmnopqrstuvwxyz";
        assertEquals(true, verifyDictionary(words, order));
    }

    @Test
    public void Test2() {
        final String[] words = new String[] { "word","world","row" };
        final String order = "worldabcefghijkmnpqstuvxyz";
        assertEquals(false, verifyDictionary(words, order));
    }

    @Test
    public void Test3() {
        final String[] words = new String[] { "apple","app" };
        final String order = "abcdefghijklmnopqrstuvwxyz";
        assertEquals(false, verifyDictionary(words, order));
    }

    @Test
    public void Test4() {
        final String[] words = new String[] { "kuvp","q" };
        final String order = "ngxlkthsjuoqcpavbfdermiywz";
        assertEquals(true, verifyDictionary(words, order));
    }
}