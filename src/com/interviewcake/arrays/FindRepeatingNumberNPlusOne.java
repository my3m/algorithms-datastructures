package com.interviewcake.arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;


public class FindRepeatingNumberNPlusOne {
    
    /*
        I have a list of n+1 numbers. 
            Every number in the range 1..n appears once except for one number that appears twice.
        Write a function for finding the number that appears twice.
    */
    public int findRepeat(int[] numbers) {

        // find the number that appears twice
        
        //[3,5,2,4,1,3]
        //3->2->5->1->3(repeat)
                        //->2->5->1->3

        //3->2->5->1->3
        //3->5->3->5->3
        //reset slow to 3
        //3->5-
        //3->2
        
        //Arrays.sort(numbers);
        int l = 1, r = numbers.length - 1;
        while(l < r) {
            int m = l + (r-l)/2;
            int count = getCount(m, numbers);
            if (count <= m) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return l;
    }

    int getCount(int number, int[] numbers) {
        int counter = 0;
        for(int x : numbers)
            if(x <= number)
                counter++;
        return counter;
    }

    public int findRepeatingNumberCycleDetection(int[] numbers) {
        int slow = numbers[0], fast = numbers[0];
        do 
        {
            slow = numbers[slow];
            fast = numbers[numbers[fast]];
        }
        while(slow != fast);

        slow = numbers[0];
        while(slow != fast) {
            slow = numbers[slow];
            fast = numbers[fast];
        }
        return slow;
    }

    // tests

    @Test
    public void shortArrayTest() {
        final int[] numbers = {1, 2, 1};
        final int expected = 1;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void mediumArrayTest() {
        final int[] numbers = {4, 1, 3, 4, 2};
        final int expected = 4;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void longArrayTest() {
        final int[] numbers = {1, 5, 9, 7, 2, 6, 3, 8, 2, 4};
        final int expected = 2;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FindRepeatingNumberNPlusOne.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}