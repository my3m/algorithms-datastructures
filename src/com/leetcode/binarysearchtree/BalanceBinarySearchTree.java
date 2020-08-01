package com.leetcode.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.leetcode.datastructures.TreeNode;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class BalanceBinarySearchTree {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BalanceBinarySearchTree.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public TreeNode balanceBST(TreeNode root) {
        //[1,2,3,4]
        List<Integer> sortedList = new ArrayList<>();
        LinkedList<TreeNode> s = new LinkedList<>();
        while(root != null || s.size() > 0) {
            if(root!= null) {
                s.push(root);
                root = root.left;
            }
            else {
                TreeNode treeNode = s.pop();
                sortedList.add(treeNode.val);
                root = treeNode.right;
            }
        }
        return contructTreeNode(sortedList, 0, sortedList.size() - 1);
    }
    
    TreeNode contructTreeNode(List<Integer> values, int l, int r) {
        if(l > r) return null;
        int m = l + (r-l)/2;
        TreeNode treeNode = new TreeNode(values.get(m));
        treeNode.left = contructTreeNode(values, l, m - 1);
        treeNode.right = contructTreeNode(values, m + 1, r);
        return treeNode;
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