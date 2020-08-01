package com.leetcode.binarysearchtree;

import java.util.*;

import com.leetcode.datastructures.TreeNode;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GreaterSumBST {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GreaterSumBST.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    /* Solution 1*/
    public TreeNode bstToGst(TreeNode root) {
        TreeNode originalRoot = root;
        Map<Integer, Integer> cache = new HashMap<>();
        List<Integer> ps = new ArrayList<>();
        
        LinkedList<TreeNode> s = new LinkedList<>();
        int prev = 0, total = 0, totalSum = 0;
        while(root != null || s.size() > 0) {
            if(root != null) {
                s.push(root);
                root = root.left;
            }
            else {
                TreeNode treeNode = s.pop();
                totalSum += treeNode.val;
                total += prev;
                ps.add(total);
                prev = treeNode.val;
                cache.put(treeNode.val, total);
                root = treeNode.right;
            }
        }
        
        //System.out.println(ps.toString());
        //System.out.println(cache);
        return constructTree(originalRoot, totalSum, cache);
    }

    TreeNode constructTree(TreeNode root, int totalSum, Map<Integer, Integer> cache) {
        if(root == null)
            return null;
        TreeNode newRoot = new TreeNode(totalSum-cache.get(root.val));
        newRoot.left = constructTree(root.left, totalSum, cache);
        newRoot.right = constructTree(root.right, totalSum, cache);
        return newRoot;
    }


    /* Solution 2 */
    public TreeNode bstToGst2(TreeNode root) {
        rightTraversal(root, 0);
        return root;
    }
    
    public int rightTraversal(TreeNode root, int sum) {
        if(root == null)
            return sum;
        int right = rightTraversal(root.right, sum);
        root.val = right + root.val;
        int left = rightTraversal(root.left, root.val);
        return left;
    }

    @Test
    public void Test1() {

    }

    @Test
    public void Test2() {
        
    }

    @Test
    public void Test3() {
        
    }
}