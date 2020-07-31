package com.leetcode.datastructures;


public class ListNode {
    public int key;
    public int val;
    public ListNode next, prev;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public ListNode(int[] vals) {
        ListNode root = this;
        ListNode curr = root;
        for(int val : vals) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    //1->2->3->4
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(val);
        ListNode current = this;
        while(current.next != null) {
            sb.append("->").append(current.next.val);
            current = current.next;
        }
        return sb.toString();
    }


    public boolean equals(Object other) {
        if(!(other instanceof ListNode)) 
            return false;
        ListNode curr = this;
        ListNode curr2 = (ListNode)other;
        while(curr != null && curr2 != null && 
                curr.val == curr2.val) {
            curr = curr.next;
            curr2 = curr2.next;
        }
        return curr == null && curr2 == null;
    }

    public ListNode addNext(int val) {
        this.next = new ListNode(val);
        return this.next;
    }
}