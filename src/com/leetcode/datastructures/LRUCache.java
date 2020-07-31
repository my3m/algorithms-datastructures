package com.leetcode.datastructures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    
    final Map<Integer, ListNode> cache = new HashMap<>();
    final ListNode head = new ListNode(-1, -1);
    final ListNode tail = new ListNode(-1, -1);
    final int capacity;
    int counter;
    
    
    public LRUCache(int capacity) {
        head.next = tail;
        tail.prev = head;
        this.counter = 0;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!cache.containsKey(key))
            return -1;
        ListNode getNode = cache.get(key);
        remove(getNode);
        add(getNode);
        return getNode.val;
    }
    
    public void add(ListNode l1) {
        ListNode next = head.next;
        l1.next = next;
        next.prev = l1;
        l1.prev = head;
        head.next = l1;
    }
    
    public void remove(ListNode l1) {
        ListNode prev = l1.prev, next = l1.next;
        prev.next = next;
        next.prev = prev;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            ListNode node = cache.get(key);
            remove(node);
            node.val = value;            
            add(node);
        }
        else {
            ListNode newNode = new ListNode(key, value);
            if(counter == capacity) {
                cache.remove(tail.prev.key);
                //System.out.println(cache);
                remove(tail.prev);
                counter--;
            }
            add(newNode);
            cache.put(key, newNode);
            counter++;
        }
    }
}