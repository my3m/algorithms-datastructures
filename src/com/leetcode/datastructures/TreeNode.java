package com.leetcode.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    public int val;
    public int depth;
    public TreeNode left, right;

    public TreeNode() {

    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int depth, int val) {
        this.depth = depth;
        this.val = val;
    }

    public static TreeNode construct(int[] val, int rootidx) {
        TreeNode root = new TreeNode(0, val[rootidx]);        
        root.left = construct(val, 1, 0, rootidx - 1);
        root.right = construct(val, 1, rootidx + 1, val.length - 1);
        return root;
    }

    static TreeNode construct(int[] val, int d, int l, int r) {
        if(l > r) return null;
        int middle = l + (r-l)/2;
        TreeNode root = new TreeNode(d, val[middle]);        
        root.left = construct(val, d + 1, l, middle - 1);
        root.right = construct(val, d + 1, middle + 1, r);
        return root;
    }

    public void print() {
        TreeNode root = this;
        System.out.println(printhelper(root, 6));
    }

    String printhelper(TreeNode root, int indent) {
        LinkedList<TreeNode> s = new LinkedList<>();
        List<String> lines = new ArrayList<>();
        s.push(root);

        while(s.size() > 0) {
            TreeNode treeNode = s.pop();
            if(lines.size() == treeNode.depth)
                lines.add("");
            lines.set(treeNode.depth, lines.get(treeNode.depth) + "     " + treeNode.val);
            if(treeNode.right != null)
                s.push(treeNode.right);
            if(treeNode.left != null)
                s.push(treeNode.left);                
        }
        StringBuilder sb = new StringBuilder();
        while(lines.size() > 0) {
            sb.append(lines.remove(0))
                .append("\r\n");
        }
        return sb.toString();
    }
}