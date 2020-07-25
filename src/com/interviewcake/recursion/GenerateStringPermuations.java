package com.interviewcake.recursion;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;;

public class GenerateStringPermuations {

    Set<String> getPermutations(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        List<String> result = generatePermuationsHelper(chars, 0, new char[str.length()], new boolean[str.length()]);
        //System.out.println(result.toString());
        Set<String> answer = new HashSet<>();
        for(String s : result) {
            if(answer.contains(s)) throw new IllegalArgumentException("duplicate string");
            answer.add(s);
        }
        return answer;
    }

    List<String> generatePermuationsHelper(char[] chars, int index, char[] permutation, boolean[] visited) {
        List<String> result = new ArrayList<>();
        if(index == permutation.length) {
            result.add(new String(permutation));
            return result;
        }
        else {   
            for(int i = 0; i < chars.length; i++) {
                if(visited[i])
                    continue;    
                if(i > 0 && chars[i-1] == chars[i] && visited[i-1])
                    continue;
                visited[i] = true;
                permutation[index] = chars[i];
                List<String> resultAns = generatePermuationsHelper(chars, index + 1, permutation, visited);
                for(String ans : resultAns)
                    result.add(ans);
                visited[i] = false;    
            }
        }
        return result;
    }

    @Test
    public void appleStringTest() {
        getPermutations("aabc");
    }

     //tests
     
     @Test
     public void emptyStringTest() {
         final Set<String> expected = new HashSet<>(Arrays.asList(""));
         final Set<String> actual = getPermutations("");
         assertEquals(expected, actual);
     }
 
     @Test
     public void oneCharacterStringTest() {
         final Set<String> expected = new HashSet<>(Arrays.asList("a"));
         final Set<String> actual = getPermutations("a");
         assertEquals(expected, actual);
     }
 
     @Test
     public void twoCharacterStringTest() {
         final Set<String> expected = new HashSet<>(Arrays.asList("ab", "ba"));
         final Set<String> actual = getPermutations("ab");
         assertEquals(expected, actual);
     }
     
     @Test
     public void characterDuplicateTest() {
         final Set<String> expected = new HashSet<>(Arrays.asList("aaa"));
         final Set<String> actual = getPermutations("aaa");
         assertEquals(expected, actual);
     }    
     
     @Test
     public void characterDuplicateTest2() {
         final Set<String> expected = new HashSet<>(Arrays.asList("aabb", "bbaa", "abab", "baba", "abba", "baab"));
         final Set<String> actual = getPermutations("aabb");
         assertEquals(expected, actual);
     }        
 
     @Test
     public void threeCharacterStringTest() {
         final Set<String> expected = new HashSet<>(Arrays.asList("abc", "acb", "bac", "bca",
                                                                  "cab", "cba"));
         final Set<String> actual = getPermutations("abc");
         assertEquals(expected, actual);
     }
 
     public static void main(String[] args) {
         Result result = JUnitCore.runClasses(GenerateStringPermuations.class);
         for (Failure failure : result.getFailures()) {
             System.out.println(failure.toString());
         }
         if (result.wasSuccessful()) {
             System.out.println("All tests passed.");
         }
     }
    
}