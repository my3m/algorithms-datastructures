package com.hackerrank.strings;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class LongestSubstringWithKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k == 0)
            return 0;
        Map<Character, Integer> counts = new HashMap<>();
        String longest = "";
        for(int i = 0, j = 0; i < s.length(); i++) {
          char chr = s.charAt(i);
          counts.put(chr, counts.getOrDefault(chr, 0) + 1);
          //block of code, prevents any duplicate char
          //appkle
          while(j < i && counts.size() > k) {
            counts.put(s.charAt(j), counts.get(s.charAt(j)) - 1);
            if(counts.get(s.charAt(j)) == 0)
                counts.remove(s.charAt(j));
            j++;
          }
          int len = i - j + 1;
          if(longest.length() == 0 || len > longest.length()) {
            longest = s.substring(j, i + 1);
          }
        }
        return longest.length();
    }

    @Test
    public void Test1() {
        assertEquals(0, lengthOfLongestSubstringKDistinct("a", 0));
    }

    @Test
    public void Test2() {
        assertEquals(3, lengthOfLongestSubstringKDistinct("eceba", 2));
    }
}