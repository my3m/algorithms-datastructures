package com.hackerrank.arrays;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MinimumMeetingsRooms {
    public int getMinimumMeetingRooms(int[][] meetings) {
        return getMinimumMeetingRoomsUsingArrays(meetings);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MinimumMeetingsRooms.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

    public int getMinimumMeetingRoomsUsingArrays(int[][] meetings) {
        //keep start time and end time in two different arrays
        //use two-ptr
        //if start < end, then we can increment i, else decrement j

        int[] start = new int[meetings.length], end = new int[meetings.length];
        for(int i = 0; i < meetings.length; i++) {
            start[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int i = 0, j = 0, current = 0, max = 0;
        while(i < start.length && j < end.length) {
            if(start[i] < end[j]) {
                current++;
                i++;
            }
            else {
                j++;
                current--;
            }
            max = Math.max(max, current);
        }
        return max;
    }

    public int getMinimumMeetingRoomsUsingPriorityQueue(int[][] meetings) {
        //We can sort by start time
        //then put the meetings into a priority queue (sorted by end time least)
        //as soon as we come across a start time >= meetings[i]
        Arrays.sort(meetings, (a,b)-> a[0]-b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b)-> a[1]-b[1]
        );
        int max = 0;
        for(int i = 0; i < meetings.length; i++) {
            while(pq.size() > 0 && meetings[i][0] >= pq.peek()[1]) {
                pq.poll();
            }
            pq.offer(meetings[i]);
            max = Math.max(max, pq.size());
        }
        return max;
    }

    @Test
    public void Test1() {
        int[][] meetings = new int[][] {
            {0,30}, {5,10}, {15,20}
        };
        assertEquals(2, getMinimumMeetingRooms(meetings));
    }

    @Test
    public void Test2() {
        int[][] meetings = new int[][] {
            {9,10},{4,9},{4,17}
        };
        //[4,9],[4,17],[9,10]
        /*

            [9,10]
            [4,9]
            [4,17]
        */
        assertEquals(2, getMinimumMeetingRooms(meetings));
    }

    @Test
    public void Test3() {
        //[[2,11],[6,16],[11,16]]
        int[][] meetings = new int[][] {
            {2,11},{6,16},{11,16}
        };
        //[2,11],[6,16],[11,16]
        /*
            [11,16]   
            [6,16]            
        */
        assertEquals(2, getMinimumMeetingRooms(meetings));
    }

    //[[6,17],[8,9],[11,12],[6,9]]
    @Test
    public void Test4() {
        int[][] meetings = new int[][] {
            {6,17},{8,9},{11,12},{6,9}
        };
        //[6,9],[6,17],[8,9],[11,12]
        /*
            [11,12]
            [6,17]
        */
        assertEquals(3, getMinimumMeetingRooms(meetings));
    }

    @Test
    public void Test5() {
        //[[2,15],[36,45],[9,29],[16,23],[4,9]]
        int[][] meetings = new int[][] {
            {2,15},{36,45},{9,29},{16,23},{4,9}
        };
        //[2,15],[4,9],[9,29],[16,23],[36,45]
        /*
            [36,45]
        */
        assertEquals(2, getMinimumMeetingRooms(meetings));
    }
}