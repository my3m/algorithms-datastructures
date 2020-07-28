package com.interviewcake.datastructures;

public class BinaryTreeNode {
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public Integer value;

    public BinaryTreeNode() {}

    public BinaryTreeNode(int val) {
        this.value = val;
    }

    public BinaryTreeNode insertLeft(int value) {
        this.left = new BinaryTreeNode(value);
        return this.left;
    }

    public BinaryTreeNode insertRight(int value) {
        this.right = new BinaryTreeNode(value);
        return this.right;
    }
}