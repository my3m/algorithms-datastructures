package com.leetcode.recursion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GenerateParenthesis {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GenerateParenthesis.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public List<String> generateParenthesis(int n) {
        /*
            open=
                (
        */
        return generateParenthesis(n, 0, 0, 0);
    }

    Map<Integer, Map<Integer, Map<Integer, List<String>>>> cache = new HashMap<>();
    public List<String> generateParenthesis(int n, int balance, int open, int closed) {
       if(cache.containsKey(balance) && cache.get(balance).containsKey(open) &&
           cache.get(balance).get(open).containsKey(closed))
           return cache.get(balance).get(open).get(closed);
       if(closed == n) {
           return Collections.singletonList("");
       }
       else {
           List<String> newResponse = new ArrayList<>();
           if(balance > 0) {
               List<String> result = generateParenthesis(n, balance - 1, open, closed + 1);
               for(String s : result) {
                   newResponse.add(new StringBuilder(")").append(s).toString());
               }
           }
           if(open < n) {
               List<String> result = generateParenthesis(n, balance + 1, open + 1, closed);
               for(String s : result) {
                   newResponse.add(new StringBuilder("(").append(s).toString());
               }
           }
           cache.putIfAbsent(balance, new HashMap<>());
           cache.get(balance).putIfAbsent(open, new HashMap<>());
           cache.get(balance).get(open).putIfAbsent(closed, newResponse);
           return newResponse;
       }
   }

    @Test
    public void Test1() {
        generateParenthesis(2);
        final List<String> exp = new ArrayList<>();
        exp.add("(())");
        exp.add("()()");
        assertTrue(areListsEqual(exp, generateParenthesis(2)));
    }

    @Test
    public void Test2() {
        final List<String> exp = new ArrayList<>();
        exp.add("((()))");
        exp.add("(()())");
        exp.add("(())()");
        exp.add("()(())");
        exp.add("()()()");
        assertTrue(areListsEqual(exp, generateParenthesis(3)));
    }

    @Test
    public void Test3() {
        
    }

    public boolean areListsEqual(List<String> a, List<String> b) {
        if(a.size() != b.size())
            return false;
        for(String s : a) {
            if(!b.contains(s))
                return false;
        }
        return true;
    }
}