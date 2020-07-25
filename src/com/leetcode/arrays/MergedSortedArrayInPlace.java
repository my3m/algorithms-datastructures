package com.leetcode.arrays;

public class MergedSortedArrayInPlace {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = n + m - 1;
        
        //4,5,6,0,0,0
        //1,2,3
        //4,5,6
        while(i >= 0 && j >= 0) {
            if(nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            }
            else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        //7,8,9
        //1,2,2,
        //7,8,9
        while(j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}