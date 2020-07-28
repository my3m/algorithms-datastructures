package com.interviewcake.BinarySeachTree;

import static org.junit.Assert.assertEquals;

import com.interviewcake.datastructures.BinaryTreeNode;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class SecondLargestItemInBST {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SecondLargestItemInBST.class);
        if (result.wasSuccessful()){
            System.out.println("All tests passed.");
        }
    }

    public int findSecondLargest(BinaryTreeNode root) {
        if(root == null || root.left == null && root.right == null)
            throw new IllegalArgumentException();
        return findSecondLargest(root, null, true);
    }
    
    public int findSecondLargest(BinaryTreeNode root, BinaryTreeNode parent, boolean findAbove) {
        if(root == null)
            return -1;
        if(root.right != null) {
            return findSecondLargest(root.right, root, findAbove);
        }
        else {
            if (!findAbove) {
                return root.value;
            }
            else {
                if(root.left != null) {
                    return findSecondLargest(root.left, root, false);
                }
                else {
                    return parent != null ? parent.value : -1;
                }
            }
        }
    }

    @Test
    public void findSecondLargestTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        final int actual = findSecondLargest(root);
        final int expected = 70;
        assertEquals(expected, actual);
    }

    @Test
    public void largestHasLeftChildTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        root.insertRight(70).insertLeft(60);
        final int actual = findSecondLargest(root);
        final int expected = 60;
        assertEquals(expected, actual);
    }

    @Test
    public void largestHasLeftSubtreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70).insertLeft(60);
        b.insertLeft(55).insertRight(58);
        b.insertRight(65);
        final int actual = findSecondLargest(root);
        final int expected = 65;
        assertEquals(expected, actual);
    }

    @Test
    public void secondLargestIsRootNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        root.insertRight(70);
        final int actual = findSecondLargest(root);
        final int expected = 50;
        assertEquals(expected, actual);
    }

    @Test
    public void descendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertLeft(40).insertLeft(30).insertLeft(20);
        final int actual = findSecondLargest(root);
        final int expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    public void ascendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertRight(60).insertRight(70).insertRight(80);
        final int actual = findSecondLargest(root);
        final int expected = 70;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithTreeThatHasOneNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        findSecondLargest(root);
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyTreeTest() {
        findSecondLargest(null);
    }    
}