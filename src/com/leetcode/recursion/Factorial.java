package com.leetcode.recursion;

public class Factorial {

    public static void main(String[] args) {
        System.out.println(getFactorial(4));
    }

    public static int getFactorial(int n) {
        if(n == 1)
            return 1;
        return n * getFactorial(n-1);
    }    
}