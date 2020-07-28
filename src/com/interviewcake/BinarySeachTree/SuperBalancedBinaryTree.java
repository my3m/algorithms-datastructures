package com.interviewcake.BinarySeachTree;

import com.interviewcake.datastructures.BinaryTreeNode;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SuperBalancedBinaryTree {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SuperBalancedBinaryTree.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

    public boolean isBalanced(BinaryTreeNode root) {
        //return true if the difference b/w depths of any two leaf nodes is no greater than one
        /*
             8
            /  \
           5    3
          / \    
         2   4
              \
               5 


        */

        int[] minmax = isBalancedHelper(root, 0);
        return minmax[0] >= 0 && minmax[1] >= 0;

    }


    public int[] isBalancedHelper(BinaryTreeNode root, int d) {
        if(root == null)
            return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE};
        else if(root.left == null && root.right == null) {
            return new int[] { d, d};
        }
        int[] left = isBalancedHelper(root.left, d + 1), right = isBalancedHelper(root.right, d + 1);
        if(left[0] == -1 || right[0] == -1)
            return new int[] { -1, -1 };
        int leftMin = Math.min(left[0], right[0]), rightMax = Math.max(left[1], right[1]);
        if(leftMin != Integer.MAX_VALUE && rightMax != Integer.MIN_VALUE && Math.abs(leftMin - rightMax) > 1)
            return new int[] { -1, -1 };
        return new int[] { leftMin, rightMax};
    }



      // tests

      @Test
      public void fullTreeTest() {
          final BinaryTreeNode root = new BinaryTreeNode(5);
          final BinaryTreeNode a = root.insertLeft(8);
          final BinaryTreeNode b = root.insertRight(6);
          a.insertLeft(1);
          a.insertRight(2);
          b.insertLeft(3);
          b.insertRight(4);
          final boolean result = isBalanced(root);
          assertTrue(result);
      }
  
      @Test
      public void bothLeavesAtTheSameDepthTest() {
          final BinaryTreeNode root = new BinaryTreeNode(3);
          root.insertLeft(4).insertLeft(1);
          root.insertRight(2).insertRight(9);
          final boolean result = isBalanced(root);
          assertTrue(result);
      }
  
      @Test
      public void leafHeightsDifferByOneTest() {
          final BinaryTreeNode root = new BinaryTreeNode(6);
          root.insertLeft(1);
          root.insertRight(0).insertRight(7);
          final boolean result = isBalanced(root);
          assertTrue(result);
      }
  
      @Test
      public void leafHeightsDifferByTwoTest() {
          final BinaryTreeNode root = new BinaryTreeNode(6);
          root.insertLeft(1);
          root.insertRight(0).insertRight(7).insertRight(8);
          final boolean result = isBalanced(root);
          assertFalse(result);
      }
      
      
  /*
  
      1
     / \
    5   9
       / \
      8   5
     /
    7
  
  
  */
  
      @Test
      public void bothSubTreesSuperbalancedTest() {
          final BinaryTreeNode root = new BinaryTreeNode(1);
          root.insertLeft(5);
          final BinaryTreeNode b = root.insertRight(9);
          b.insertLeft(8).insertLeft(7);
          b.insertRight(5);
          final boolean result = isBalanced(root);
          assertFalse(result);
      }
  
  
      /*
      
          1
         / \
        2   4
       / \   \
      3   7   5
           \   \
            8   6
                 \
                  9
      */
  
      @Test
      public void bothSubTreesSuperbalancedTwoTest() {
          final BinaryTreeNode root = new BinaryTreeNode(1);
          final BinaryTreeNode a = root.insertLeft(2);
          a.insertLeft(3);
          a.insertRight(7).insertRight(8);
          root.insertRight(4).insertRight(5).insertRight(6).insertRight(9);
          final boolean result = isBalanced(root);
          assertFalse(result);
      }
      
  
      @Test
      public void threeLeavesAtDifferentLevelsTest() {
          final BinaryTreeNode root = new BinaryTreeNode(1);
          final BinaryTreeNode a = root.insertLeft(2);
          final BinaryTreeNode b = a.insertLeft(3);
          a.insertRight(4);
          b.insertLeft(5);
          b.insertRight(6);
          root.insertRight(7).insertRight(8).insertRight(9).insertRight(10);
          final boolean result = isBalanced(root);
          assertFalse(result);          
      }
  
      @Test
      public void onlyOneNodeTest() {
          final BinaryTreeNode root = new BinaryTreeNode(1);
          final boolean result = isBalanced(root);
          assertTrue(result);
      }
  
      @Test
      public void treeIsLinkedListTest() {
          final BinaryTreeNode root = new BinaryTreeNode(1);
          root.insertRight(2).insertRight(3).insertRight(4);
          final boolean result = isBalanced(root);
          assertTrue(result);
      }


}