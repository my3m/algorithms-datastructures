package com.leetcode.binarysearchtree;

import java.util.*;
import com.leetcode.datastructures.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TwoSumBst {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TwoSumBst.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if(root1 == null || root2 == null)
            return false;
        int total = root1.val + root2.val;
        if(total == target)
            return true;
        return total < target ? (twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target)) :
                (twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target));
    }

    @Test
    public void Test1() {
        TreeNode root = TreeNode.construct(new int[] { 0,1,2,5,7 }, 3);
        root.print();
    }   

    @Test
    public void Test2() {
        
    }

    @Test
    public void Test3() {
        
    }
}