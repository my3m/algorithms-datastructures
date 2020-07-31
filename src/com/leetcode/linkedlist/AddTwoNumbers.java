package com.leetcode.linkedlist;

import static org.junit.Assert.assertEquals;

import com.leetcode.datastructures.ListNode;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AddTwoNumbers {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AddTwoNumbers.class);
        if(result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode result = head;
        /*

            (3471 + 243)

            1->7->4->3
            3->4->2

            4->1->7->3
            
        */
        int carry = 0;
        while(l1 != null || l2 != null) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            result.next = new ListNode(sum % 10);
            carry = sum / 10;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
            result = result.next;
        }
        //what if you have a tail at the end
        if(carry > 0) {
            result.next = new ListNode(carry);
        }

        return head.next;
    }

    @Test
    public void Test1() {
        final ListNode a = new ListNode(new int[] { 1, 7, 4, 3});
        final ListNode b = new ListNode(new int[] { 3, 4, 2});
        final ListNode exp = new ListNode(new int[] { 4, 1, 7, 3});
        assertEquals(exp, addTwoNumbers(a,b));
    }

    @Test
    public void Test2() {
        final ListNode a = new ListNode(new int[] { 9, 1});
        final ListNode b = new ListNode(new int[] { 9, 1});
        final ListNode exp = new ListNode(new int[] { 8, 3 });
        assertEquals(exp, addTwoNumbers(a,b));
    }

    @Test
    public void Test3() {
        final ListNode a = new ListNode(new int[] { 3, 9});
        final ListNode b = new ListNode(new int[] { 9 });
        final ListNode exp = new ListNode(new int[] { 2, 0, 1 });
        assertEquals(exp, addTwoNumbers(a,b));
    }

    @Test
    public void Test4() {
        final ListNode a = new ListNode();
        final ListNode b = new ListNode();
        final ListNode exp = new ListNode();
        assertEquals(exp, addTwoNumbers(a,b));
    }
}