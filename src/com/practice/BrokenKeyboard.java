package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrokenKeyboard {

    class TrieNode {
        public Map<Character, TrieNode> children;
        public boolean isWord;
        public String word;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isWord = false;
        }

        @Override
        public String toString() {
            return this.children.toString();
        }
    }

    TrieNode root;

    public BrokenKeyboard() {
        this.root = new TrieNode();
    }

    void addWord(String word) {
        TrieNode root = this.root;
        for(int i = 0; i < word.length(); i++) {
            char chr = word.charAt(i);
            if(!root.children.containsKey(chr)) {
                root.children.put(chr, new TrieNode());
            }
            root = root.children.get(chr);
        }
        root.isWord = true;
        root.word = word;
    }

    public void addWords(String[] words) {
        for(String word : words)
            addWord(word);
    }

    public List<String> findWords(String text, Character chr) {
        return findWords(text, this.root, 0, chr);
    }

    /*
        Example Input: String: "can s r n " Dictionary: ["can", "canes", "serene", "rene", "sam"]
        Expected Output: ["can serene", "canes rene"]

        1. Presence of a space could mean "space" or "e", perform recursion
        2. If we form a word, and there is a space
    */
    public List<String> findWords(String text, TrieNode context, int index, Character chr) {        
        if(index == text.length()) {
            if(context.isWord)
                return Collections.singletonList(text);
            return new ArrayList<String>();
        }
        List<String> words = new ArrayList<>();
        char localchr = text.charAt(index);
        if(localchr == ' ') {
            for(Character key : context.children.keySet()) {
                if(key == chr) {
                    String substring = text.substring(0, index) + chr + text.substring(index + 1);
                    List<String> branchWords =  findWords(substring, context.children.get(chr), index + 1, chr);
                    for(String word : branchWords) {
                        words.add(word);
                    }
                }
            }
            if(context.isWord) {
                List<String> spaceWords = findWords(text, this.root, index + 1, chr);
                for(String word : spaceWords)
                    words.add(word);
            }
        }
        else {
            if(!context.children.containsKey(localchr))
                return new ArrayList<>();
            return findWords(text, context.children.get(localchr), index + 1, chr);
        }
        return words;
    }

    public static void main(String[] args) {
        String[] words = new String[] { "can", "canes", "serene", "rene", "sam" };
        BrokenKeyboard brokenKeyboard = new BrokenKeyboard();
        brokenKeyboard.addWords(words);

        System.out.println(brokenKeyboard.root);
        List<String> possibleWords = brokenKeyboard.findWords("can s r n ", 'e');
        System.out.println(possibleWords.toString());
    }
}