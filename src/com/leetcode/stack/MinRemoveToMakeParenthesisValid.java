package com.leetcode.stack;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MinRemoveToMakeParenthesisValid {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MinRemoveToMakeParenthesisValid.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public String minRemoveToMakeValid(String s) {
        LinkedList<Integer> x = new LinkedList<>();
        Set<Integer> invalid = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                x.push(i);
            }
            else if(s.charAt(i) == ')') {
                if(x.size() == 0) {
                    invalid.add(i);
                }
                else {
                    x.pop();
                }
            }
        }
        while(x.size() > 0)
            invalid.add(x.pop());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(invalid.contains(i))
                continue;
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    @Test
    public void Test1() {
        assertEquals("lee(t(c)o)de", minRemoveToMakeValid("lee(t(c)o)de)"));
    }

    @Test
    public void Test2() {
        assertEquals("ab(c)d", minRemoveToMakeValid("a)b(c)d"));
    }

    @Test
    public void Test3() {
        assertEquals("", minRemoveToMakeValid("))(("));
    }
}