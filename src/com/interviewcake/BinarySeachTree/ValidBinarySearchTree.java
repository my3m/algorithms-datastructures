package com.interviewcake.BinarySeachTree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.interviewcake.datastructures.BinaryTreeNode;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class ValidBinarySearchTree {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ValidBinarySearchTree.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

    public boolean isBinarySearchTree(BinaryTreeNode root) {
        return isBinarySearchTree(root, null, null);
    }

    boolean isBinarySearchTree(BinaryTreeNode root, Integer left, Integer right) {
        if(root == null)
            return true;
        if(left != null && root.value <= left || right != null && root.value >= right)
            return false;
        return isBinarySearchTree(root.left, left, root.value) &&
        isBinarySearchTree(root.right, root.value, right);
    }

    @Test
    public void Test_When_Empty() {
        assertEquals(true, isBinarySearchTree(null, null, null));
    }

    @Test
    public void Test_When_Only_Root() {
        BinaryTreeNode root = new BinaryTreeNode(10);
        assertEquals(true, isBinarySearchTree(root, null, null));
    }

    @Test
    public void Test_Root_Case1() {
        BinaryTreeNode root = new BinaryTreeNode(10);
        BinaryTreeNode a = root.insertLeft(7);
        a.insertLeft(7);
        a.insertRight(8);
        root.insertRight(20);
        assertEquals(false, isBinarySearchTree(root, null, null));
    }

    @Test
    public void Test_Root_Case2() {
        BinaryTreeNode root = new BinaryTreeNode(10);
        BinaryTreeNode a = root.insertLeft(7);
        a.insertLeft(5);
        a.insertRight(8);
        root.insertRight(20);
        assertEquals(true, isBinarySearchTree(root, null, null));
    }

    @Test
    public void Test_Root_Case3() {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.insertLeft(9).
        insertLeft(8).
        insertLeft(7).
        insertLeft(6).
        insertLeft(5).
        insertLeft(4).
        insertLeft(3).
        insertLeft(2).
        insertLeft(1).
        insertLeft(0);
        assertEquals(true, isBinarySearchTree(root, null, null));
    }
    public void Test_Root_Case4() {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.insertLeft(9).
        insertLeft(8).
        insertLeft(7).
        insertLeft(6).
        insertLeft(5).
        insertLeft(4).
        insertLeft(3).
        insertLeft(2).
        insertLeft(1).
        insertRight(2);
        assertEquals(false, isBinarySearchTree(root, null, null));
    }

    @Test
    public void validFullTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        final boolean result = isBinarySearchTree(root);
        assertTrue(result);
    }

    @Test
    public void bothSubtreesValidTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(20);
        a.insertRight(60);
        final BinaryTreeNode b = root.insertRight(80);
        b.insertLeft(70);
        b.insertRight(90);
        final boolean result = isBinarySearchTree(root);
        assertFalse(result);
    }

    @Test
    public void descendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertLeft(40).insertLeft(30).insertLeft(20).insertLeft(10);
        final boolean result = isBinarySearchTree(root);
        assertTrue(result);
    }

    @Test
    public void outOfOrderLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertRight(70).insertRight(60).insertRight(80);
        final boolean result = isBinarySearchTree(root);
        assertFalse(result);
    }

    @Test
    public void oneNodeTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final boolean result = isBinarySearchTree(root);
        assertTrue(result);
    }
}