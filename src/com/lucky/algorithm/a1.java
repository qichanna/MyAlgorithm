package com.lucky.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class a1 {

    // https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
    
    public static void main(String[] args){

        System.out.println(""+lengthOfLongestSubstring2("pwwkew"));
//        System.out.println(""+lengthOfLongestSubstring("aab"));
//        System.out.println(""+lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(""+lengthOfLongestSubstring("tmmzuxt"));
    }

    /**
     *   每次移动右边窗口，有没字符添加至集合，有字符，左窗口前移至相同字符处，然后把之前的都删除掉
      */
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || "".equals(s))return 0;
        if(" ".equals(s)) return 1;

        Map<Character,Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            right=i;

            if(window.get(c) == null){
                window.put(c,i);
                len = Math.max(len,right-left+1);
            }else {
                len = Math.max(len,right-1-left);
                int position = window.get(c);
                for (int j = left; j <= position; j++) {
                    window.remove(s.charAt(j));
                }
                window.put(c,i);
                left = position+1;
                System.out.println("left: " + left + " right " + right + " len " + len);
            }
        }
        return len;
    }

    /**
     *
     * 除了第一次外，每次删除一个左窗口的元素，右边
     * 窗口前进至非重复的最后一个
     *
     */
    public static int lengthOfLongestSubstring2(String s) {
        if(s == null || "".equals(s))return 0;
        if(" ".equals(s)) return 1;//pwwkew

        Set<Character> window = new HashSet<>();
        int right = -1;
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i != 0){
                window.remove(s.charAt(i-1));
            }
            while (right+1<s.length() && !window.contains(s.charAt(right+1))){
                window.add(s.charAt(right+1));
                right++;
            }
            len = Math.max(len,right-i+1);
        }
        return len;
    }
}
