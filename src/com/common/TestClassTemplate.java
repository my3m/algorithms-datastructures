package com.common;

import java.util.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestClassTemplate {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestClassTemplate.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public void execute() {

    }

    @Test
    public void Test1() {

    }

    @Test
    public void Test2() {
        
    }

    @Test
    public void Test3() {
        
    }
}