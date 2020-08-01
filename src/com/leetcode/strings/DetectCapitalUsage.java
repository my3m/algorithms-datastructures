package com.leetcode.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class DetectCapitalUsage {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DetectCapitalUsage.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public boolean isValidCapitalUsage(String word) {
        if(word.length() == 0)
            return true;
        boolean isFirstCapital = Character.isUpperCase(word.charAt(0)),
                    continueCapital = false;
        for(int i = 1; i < word.length(); i++) {
            if(i == 1 && Character.isUpperCase(word.charAt(1))) {
                if(!isFirstCapital) return false;
                continueCapital = true;
            }
            else if(continueCapital && !Character.isUpperCase(word.charAt(i))){
                return false;
            }
            else if(!continueCapital && Character.isUpperCase(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void Test() {
        assertEquals(true, isValidCapitalUsage("USA"));
        assertEquals(true, isValidCapitalUsage("Apple"));
        assertEquals(true, isValidCapitalUsage("California"));
        assertEquals(true, isValidCapitalUsage("apple"));
        assertEquals(false, isValidCapitalUsage("flagG"));
        assertEquals(false, isValidCapitalUsage("CaliforniA"));
        assertEquals(false, isValidCapitalUsage("FlagG"));
    }
}