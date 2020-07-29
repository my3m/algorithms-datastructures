package com.interviewcake.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class CustomDataStructure {
    static class TempTracker {

        // fill in the TempTracker class methods below
        int totalSum = 0;
        int freq = 0;
        int maxTemperature = -1;
        int minTemperature = -1;
        int modeTemperature = -1;
        //Map<Integer, Integer> tempFreq = new HashMap<>();
        int[] occurences = new int[111];

        // records a new temperature
        public void insert(int temperature) {
            /* We actually don't need a hashmap since the temperature is bound 0-110*/
            // tempFreq.put(temperature, tempFreq.getOrDefault(temperature, 0) + 1);
            occurences[temperature]++;
            if(maxTemperature == -1 || temperature > maxTemperature)
                maxTemperature = temperature;
            if(minTemperature == -1 || temperature < minTemperature)
                minTemperature = temperature;
            if(modeTemperature == -1 || occurences[temperature] > occurences[modeTemperature])
                modeTemperature = temperature;
            totalSum += temperature;
            freq += 1;
        }

        // returns the highest temp we've seen so far
        public int getMax() {
            if(maxTemperature == Integer.MIN_VALUE)
                return -1;
            return maxTemperature;
        }

        // returns the lowest temp we've seen so far
        public int getMin() {
            if(maxTemperature == Integer.MAX_VALUE)
                return -1;
            return minTemperature;
        }

        // returns the mean of all temps we've seen so far
        //sum / instances
        public double getMean() {
            if(freq == 0)
                return 0.0;
            return totalSum * 1.0 / freq;
        }

        // return a mode of all temps we've seen so far
        // most frequent temperature
        public int getMode() {
            if(modeTemperature == -1)
                return 0;
            return modeTemperature;
        }
    }




    // tests

    @Test
    public void temperatureTrackerTest() {
        final double precision = 1e-6;

        final TempTracker t = new TempTracker();

        t.insert(50);
        assertEquals("step 1: max:", 50, t.getMax());
        assertEquals("step 1: min:", 50, t.getMin());
        assertEquals("step 1: mean:", 50.0, t.getMean(), precision);
        assertEquals("step 3: mode:", 50, t.getMode());

        t.insert(80);
        assertEquals("step 2: max:", 80, t.getMax());
        assertEquals("step 2: min:", 50, t.getMin());
        assertEquals("step 2: mean:", 65.0, t.getMean(), precision);
        assertTrue("step 2: mode:", t.getMode() == 50 || t.getMode() == 80);

        t.insert(80);
        assertEquals("step 3: max:", 80, t.getMax());
        assertEquals("step 3: min:", 50, t.getMin());
        assertEquals("step 3: mean:", 70.0, t.getMean(), precision);
        assertEquals("step 3: mode:", 80, t.getMode());

        t.insert(30);
        assertEquals("step 4: max:", 80, t.getMax());
        assertEquals("step 4: min:", 30, t.getMin());
        assertEquals("step 4: mean:", 60.0, t.getMean(), precision);
        assertEquals("step 4: mode:", 80, t.getMode());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CustomDataStructure.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}