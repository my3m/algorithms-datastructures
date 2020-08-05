package com.leetcode.linkedlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import com.leetcode.datastructures.ListNode;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class IntersectionLinkedLists {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(IntersectionLinkedLists.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {      
        ListNode currentA = headA, currentB = headB;     
        int lenA = 0;
        while(currentA != null) {
            currentA = currentA.next;
            lenA++;
        }   
        int lenB = 0;
        while(currentB != null) {
            currentB = currentB.next;
            lenB++;
        }          
        while(lenA > lenB) {
            headA = headA.next;
            lenA--;
        }      
        while(lenB > lenA) {
            headB = headB.next;
            lenB--;
        }
        while(headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    @Test
    public void Test1() {
        ListNode tail = new ListNode(new int[] { 7, 3, 1, 3, 6});
        ListNode a = new ListNode(new int[] { 1,6,2,7});
        a.next = tail;
        ListNode b = new ListNode(new int[] { 1,1,1,19,5});
        b.next = tail;
        assertEquals(tail, getIntersectionNode(a, b));
    }
}