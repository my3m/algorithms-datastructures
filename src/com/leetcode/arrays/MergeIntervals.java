package com.leetcode.arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

//TODO: solve using graph merge connected components
public class MergeIntervals {
    public int[][] mergeIntervals(int[][] meetings) {
        //[7,12],[11,13],[15,20],[17,22]
        //1. sort by start time
        if(meetings.length <= 1)
            return meetings;
        Arrays.sort(meetings, (a,b)-> a[0]-b[0]);
        List<int[]> mergedMeetings = new ArrayList<>();
        mergedMeetings.add(meetings[0]);
        for(int i = 0; i < meetings.length; i++) {
            int[] last = mergedMeetings.get(mergedMeetings.size() - 1);
            int[] current = meetings[i];
            //overlap condition
            if(last[1] >= current[0]) {
                last[1] = Math.max(last[1], current[1]);
            } else {
                mergedMeetings.add(current);
            }
        }
        int[][] result = new int[mergedMeetings.size()][2];
        for(int i = 0; i < mergedMeetings.size();i++) {
            result[i] = mergedMeetings.get(i);
        }
        return result;        
    }

    @Test
    public void Test1() {
        int[][] meetings = new int[][] {
            {11,13},
            {15,20},
            {17,22},
            {7,12}
        };
        int[][] exp = new int[][] 
        {
            {7,13},
            {15,22}
        };
        assertMeetingEquals(exp, mergeIntervals(meetings));
    }

    @Test
    public void Test2() {
        int[][] meetings = new int[][] {
            {11,13},
            {17,22},
        };
        int[][] exp = new int[][] 
        {
            {11,13},
            {17,22}
        };
        assertMeetingEquals(exp, mergeIntervals(meetings));
    }

    @Test
    public void Test3() {
        int[][] meetings = new int[][] {
            {11,20},
            {12,14},
            {14,15},
            {15,16},
            {17,20}
        };
        int[][] exp = new int[][] 
        {
            {11,20}
        };
        assertMeetingEquals(exp, mergeIntervals(meetings));
    }

    @Test
    public void Test4() {
        int[][] meetings = new int[][] {
            {10, 20},
            {11,17},
            {12,22},
            {1,100}
        };
        int[][] exp = new int[][] 
        {
            {1,100}
        };
        assertMeetingEquals(exp, mergeIntervals(meetings));
    }

    void assertMeetingEquals(int[][] exp, int[][] actual) {
        assertEquals(exp.length, actual.length);
        for(int i = 0; i < actual.length; i++) {
            assertArrayEquals(exp[i], actual[i]);
        }        
    }
}