package com.interviewcake.arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class HighestProductOfThree {
    public int highestProductOf3(int[] numbers) {

        if(numbers.length < 3)
            throw new IllegalArgumentException();

        //6, 1, 3, 5, 7, 8, 2
        //[-12,-7,-4]

        //-5, -1, -3, -2
        //-2,-2,1
        //[1,MIN,MIN]
        //[-2,-2,MAX]

        int[] positive = new int[3];
        int[] negative = new int[3];
        Arrays.fill(positive, Integer.MIN_VALUE);
        Arrays.fill(negative, Integer.MAX_VALUE);

        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] > positive[0]) {
                positive[2] = positive[1];
                positive[1] = positive[0];
                positive[0] = numbers[i];
            }
            else if(numbers[i] > positive[1]) {
                positive[2] = positive[1];
                positive[1] = numbers[i];
            }
            else if(numbers[i] > positive[2]) {
                positive[2] = numbers[i];
            }
            if(numbers[i] < negative[0]) {
                negative[2] = negative[1];
                negative[1] = negative[0];
                negative[0] = numbers[i];
            }
            else if(numbers[i] < negative[1]) {
                negative[2] = negative[1];
                negative[1] = numbers[i];
            }
            else if(numbers[i] < negative[2]) {
                negative[2] = numbers[i];
            }
        }
        System.out.println(Arrays.toString(positive));
        System.out.println(Arrays.toString(negative));
        return Math.max(positive[0]*positive[1]*positive[2], negative[0]*negative[1]*positive[0]);
    }
       // tests

       @Test
       public void shortArrayTest() {
           final int actual = highestProductOf3(new int[] {1, 2, 3, 4});
           final int expected = 24;
           assertEquals(expected, actual);
       }
   
       @Test
       public void longerArrayTest() {
           final int actual = highestProductOf3(new int[] {6, 1, 3, 5, 7, 8, 2});
           final int expected = 336;
           assertEquals(expected, actual);
       }
   
       @Test
       public void arrayHasOneNegativeTest() {
           final int actual = highestProductOf3(new int[] {-5, 4, 8, 2, 3});
           final int expected = 96;
           assertEquals(expected, actual);
       }
   
       @Test
       public void arrayHasTwoNegativesTest() {
           final int actual = highestProductOf3(new int[] {-10, 1, 3, 2, -10});
           final int expected = 300;
           assertEquals(expected, actual);
       }
   
       @Test
       public void arrayIsAllNegativesTest() {
           final int actual = highestProductOf3(new int[] {-5, -1, -3, -2});
           final int expected = -6;
           assertEquals(expected, actual);
       }

       @Test
       public void arrayHasTwoNegatives() {
           final int actual = highestProductOf3(new int[] {-2,-2,1});
           final int expected = 4;
           assertEquals(expected, actual);
       }

       @Test
       public void arrayHasAllOnes() {
           final int actual = highestProductOf3(new int[] {1,1,1});
           final int expected = 1;
           assertEquals(expected, actual);
       }
   
       @Test(expected = Exception.class)
       public void exceptionWithEmptyArrayTest() {
           highestProductOf3(new int[] {});
       }
   
       @Test(expected = Exception.class)
       public void exceptionWithOneNumberTest() {
           highestProductOf3(new int[] {1});
       }
   
       @Test(expected = Exception.class)
       public void exceptionWithTwoNumbersTest() {
           highestProductOf3(new int[] {1, 1});
       }
   
       public static void main(String[] args) {
           Result result = JUnitCore.runClasses(HighestProductOfThree.class);
           for (Failure failure : result.getFailures()) {
               System.out.println(failure.toString());
           }
           if (result.wasSuccessful()) {
               System.out.println("All tests passed.");
           }
       }
}