package com.leetcode.binarysearchtree;

import static org.junit.Assert.assertEquals;

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

    public boolean containsTarget(TreeNode root1, TreeNode root2, int target) {
        if(root1 == null || root2 == null)
            return false;
        int total = root1.val + root2.val;
        if(total == target)
            return true;
        return total < target ? (containsTarget(root1.right, root2, target) || containsTarget(root1, root2.right, target)) :
                (containsTarget(root1.left, root2, target) || containsTarget(root1, root2.left, target));
    }

    public boolean containsTarget2(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> seenSet = new HashSet<>();
        LinkedList<TreeNode> s = new LinkedList<>();
        s.push(root1);
        while(s.size() > 0) {
            TreeNode treeNode = s.pop();
            seenSet.add(treeNode.val);
            if(treeNode.right != null)
                s.push(treeNode.right);
            if(treeNode.left != null)
                s.push(treeNode.left);
        }
        s = new LinkedList<>();
        s.push(root2);
        while(s.size() > 0) {
            TreeNode treeNode = s.pop();
            if(seenSet.contains(target - treeNode.val))
                return true;
            if(treeNode.right != null)
                s.push(treeNode.right);
            if(treeNode.left != null)
                s.push(treeNode.left);
        }
        return false;
    }

    @Test
    public void Test1() {
        TreeNode root = TreeNode.construct(new int[] { 0,1,2,5,7 }, 3);
        TreeNode root2 = TreeNode.construct(new int[] { -10, 0, 10 }, 1);
        final boolean exp = false;
        assertEquals(exp, containsTarget2(root, root2, 18));
    }   

    @Test
    public void Test2() {
        TreeNode root = TreeNode.construct(new int[] { 0,1,2,5,7 }, 3);
        TreeNode root2 = TreeNode.construct(new int[] { -10, 0, 10 }, 1);
        final boolean exp = true;
        assertEquals(exp, containsTarget2(root, root2, -5));
    }

    @Test
    public void Test3() {
        TreeNode root = TreeNode.construct(new int[] { 0,1,2,5,7 }, 3);
        TreeNode root2 = TreeNode.construct(new int[] { -10, 0, 10 }, 1);
        final boolean exp = true;
        assertEquals(exp, containsTarget2(root, root2, 12));
    }
}