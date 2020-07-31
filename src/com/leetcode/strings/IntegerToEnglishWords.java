package com.leetcode.strings;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class IntegerToEnglishWords {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(IntegerToEnglishWords.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    final String[] belowTwenty = new String[] { "Zero", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Tweleve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    final String[] belowHundreds = new String[] { "", "", "Twenty", "Thirty", "Fourty", "Fifty",
                    "Sixty", "Seventy", "Eighty", "Ninenty"};

    
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        return numberToWordsHelper(num).trim();
    }
    
    
    /*
        1. "One Hudnred " (has a space)
        2. "29" "29 < 20+9"
    */
    String numberToWordsHelper(int num) {
        if(num == 0)
            return "";
        //100-999
        //1000-1,000,000
        //1,000,000 - 1,000,000,000
        StringBuilder sb = new StringBuilder();
        if(num >= 1000000000) {
            int unit = num/1000000000;
            sb.append(numberToWordsHelper(unit))
            .append(" Billion ")
            .append(numberToWordsHelper(num % 1000000000));
        }
        else if(num >= 1000000) {
            int unit = num/1000000;
            sb.append(numberToWordsHelper(unit))
            .append(" Million ")
            .append(numberToWordsHelper(num % 1000000));
        }
        else if(num >= 1000) {
            int unit = num/1000;
            sb.append(numberToWordsHelper(unit))
            .append(" Thousand ")
            .append(numberToWordsHelper(num%1000));
        }
        else if(num >= 100) {
            int unit = num/100;
            sb.append(numberToWordsHelper(unit))
            .append(" Hundred ")
            .append(numberToWordsHelper(num%100));
        }
        else if(num >= 20) {
            for(int i = 9; i > 1; i--) {
                int num2 = i*10;
                if(num >= num2 && num < num2+10) {
                    sb.append(belowHundreds[i]);
                    int remainder = num % num2;
                    //250,000
                    //unit:250, r =0 after "Fifty"
                    if(remainder > 0) {
                        sb.append(" ").append(numberToWordsHelper(remainder));
                    }
                    break;
                }
            }
        }
        else if(num < 20) {
            sb.append(belowTwenty[num]);
        }
        return sb.toString();
    }

    @Test
    public void Test1() {
        final int value = 2381;
        final String exp = "Two Thousand Three Hundred Eighty One";
        String actual = numberToWords(value);
        assertEquals(exp, actual);
    }

    @Test
    public void Test2() {
        final int value = 9746;
        final String exp = "Nine Thousand Seven Hundred Fourty Six";
        String actual = numberToWords(value);
        assertEquals(exp, actual);
    }

    @Test
    public void Test3() {
        final int value = 0;
        final String exp = "Zero";
        String actual = numberToWords(value);
        assertEquals(exp, actual);
    }

    @Test
    public void Test4() {
        final int value = 174;
        final String exp = "One Hundred Seventy Four";
        String actual = numberToWords(value);
        assertEquals(exp, actual);
    }

    @Test
    public void Test5() {
        final int value = 1250000;
        final String exp = "One Million Two Hundred Fifty Thousand";
        String actual = numberToWords(value);
        assertEquals(exp, actual);
    }

    @Test
    public void Test6() {
        final int value = 1234567;
        final String exp = "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven";
        String actual = numberToWords(value);
        assertEquals(exp, actual);
    }

    @Test
    public void Test7() {
        final int value = 29;
        final String exp = "Twenty Nine";
        String actual = numberToWords(value);
        assertEquals(exp, actual);
    }
}