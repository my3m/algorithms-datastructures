package com.hackerrank.arrays;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MinimumNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        //[[10,16], [2,8], [1,6], [7,12]]
        //shoot an arrow @ 6, will take down [2,8] & [1,6]
        //shoot an arrow @ 11 will take town [10,16] & [7,12]

        //[1,6],[2,8],[7,12],[10,16]
        
        /*                  #########
                        ######
                ###########
            ##########
        */


        /*
            if we sort the array in increasing end time,
            then greedily increase counter, if next slot does not overlap
        */

        if(points.length <= 1)
            return points.length;

        Arrays.sort(points,(a,b)->a[1]-b[1]);
        int[] prev = points[0];
        int overlapping = 1;
        for(int i = 1; i < points.length; i++) {
            if(prev[1] >= points[i][0]) {
                //overlapping++;
            }
            else {
                prev = points[i];
                overlapping++;
            }
        }    
        return overlapping;  
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MinimumNumberOfArrowsToBurstBalloons.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

    @Test
    public void Test1() {
        assertEquals(2, findMinArrowShots(new int[][] {
            {10,16},
            {2,8},
            {1,6},
            {7,12}
        }));
    }

    @Test
    public void Test2() {
        //[0,6],[0,9],[2,8],[3,8],[6,8],[2,9],[3,9],[9,10],[7,12],
        //first shoot at point 6
        //then shoot at point 9
        assertEquals(2, findMinArrowShots(new int[][] {
            {3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}
        }));
    }

    @Test
    public void Test3() {
        //[0,6],[0,9],[2,8],[3,8],[6,8],[2,9],[3,9],[9,10],[7,12],
        //first shoot at point 2
        //then shoot at point 4
        assertEquals(2, findMinArrowShots(new int[][] {
            {1,2},{2,3},{3,4},{4,5}
        }));
    }

    @Test
    public void Test4() {
        //[0,6],[0,9],[2,8],[3,8],[6,8],[2,9],[3,9],[9,10],[7,12],
        //first shoot at point 2
        //then shoot at point 4
        assertEquals(2, findMinArrowShots(new int[][] {
            {1,2},{2,3},{3,4},{4,5}
        }));
    }

    @Test
    public void Test5() {
        assertEquals(0, findMinArrowShots(new int[0][]));
    }

    @Test
    public void Test6() {
        assertEquals(1, findMinArrowShots(new int[][] {
            {1,20}
        }));
    }
}