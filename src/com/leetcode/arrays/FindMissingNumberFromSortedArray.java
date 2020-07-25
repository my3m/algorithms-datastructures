package com.leetcode.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindMissingNumberFromSortedArray {
    int findMissingNumber(int[] arr, int k) {
        //arr[1] = 7, arr[0] = 4
        //5,6
        //4,5,6,7,8,9
        //2-0+1=>3
        //1-0+1=>2
        //4,5,6,7
               
        //4,7,9,10
        int l = 0, r = arr.length - 1;
        while(l < r) {
          int m = l + (r-l)/2;
          int should = arr[m]-arr[0]+1;
          int have = m + 1;
          int missing = should - have;
          if(missing < k) {
            l = m + 1;
          }
          else {
            r = m;
          }
        }
        //happy case, l=4,7,[9],10
        if(getMissing(arr, l) >= k && getMissing(arr,l-1) < k) {
          return arr[l-1] + k - getMissing(arr, l-1);
        }
        else {
           return arr[l] + k - getMissing(arr, l);
        }
      }
    
      int getMissing(int[] arr, int index) {
        int should = arr[index] - arr[0] + 1;
        int have = index - 0 + 1;
        int missing = should - have;
        return missing;
      }  

      @Test
      public void Test1() {
          assertEquals(8, findMissingNumber(new int[] { 4, 7, 9, 10}, 3));
      }
      @Test
      public void Test2() {
        //5,6,8,11,12,13,14
        assertEquals(14, findMissingNumber(new int[] { 4, 7, 9, 10}, 7));
    }
}