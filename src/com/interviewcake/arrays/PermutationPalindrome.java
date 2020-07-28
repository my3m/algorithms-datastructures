package com.interviewcake.arrays;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class PermutationPalindrome {

    public boolean hasPalindromePermutation(String str) {
        int[] counts = new int[26];
        for(char chr : str.toCharArray()) {            
            counts[chr-'a']++;
        }

        boolean hasOdd = false;
        for(int i = 0; i < counts.length; i++) {
            if(counts[i] % 2 != 0) {
                if(hasOdd)
                    return false;
                hasOdd = true;
            }
        }
        return true;
    }

      // tests

      @Test
      public void permutationWithOddNumberOfCharsTest() {
          final boolean result = hasPalindromePermutation("aabcbcd");
          assertTrue(result);
      }
  
      @Test
      public void permutationWithEvenNumberOfCharsTest() {
          final boolean result = hasPalindromePermutation("aabccbdd");
          assertTrue(result);
      }
  
      @Test
      public void noPermutationWithOddNumberOfChars() {
          final boolean result = hasPalindromePermutation("aabcd");
          assertFalse(result);
      }
  
      @Test
      public void noPermutationWithEvenNumberOfCharsTest() {
          final boolean result = hasPalindromePermutation("aabbcd");
          assertFalse(result);
      }
  
      @Test
      public void emptyStringTest() {
          final boolean result = hasPalindromePermutation("");
          assertTrue(result);
      }
  
      @Test
      public void oneCharacterStringTest() {
          final boolean result = hasPalindromePermutation("a");
          assertTrue(result);
      }
  
      public static void main(String[] args) {
          Result result = JUnitCore.runClasses(PermutationPalindrome.class);
          for (Failure failure : result.getFailures()) {
              System.out.println(failure.toString());
          }
          if (result.wasSuccessful()) {
              System.out.println("All tests passed.");
          }
      }
}