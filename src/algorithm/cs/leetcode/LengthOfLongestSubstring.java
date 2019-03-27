package algorithm.cs.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 示例 1:
 输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 示例 2:
 输入: "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:
 输入: "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @author chaishuai
 * @date 2019/3/27
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        //转化为字符数组
        char[] a = s.toCharArray();
        int sum = a.length;
        int res = 0;
        int temp = 0;
        Map<Character, Integer> characterSet = new HashMap<>();
        for (int j=0;j<sum;j++) {
            if (!characterSet.containsKey(a[j])) {
                characterSet.put(a[j], j);
                temp++;
            } else {
                //如果出现了重复的字符，则定位到被重复字符的后一个，重新开始
                temp = 1;
                j = characterSet.get(a[j])+1;
                characterSet.clear();
                characterSet.put(a[j],j);
                continue;
            }
            //记录最大的个数
            if (temp > res) {
                res = temp;
            }
        }
        return res;
    }

    //题解  优化的滑动窗口
    public int lengthOfLongestSubstring1(String s) {
        int res=0, n=s.length();
        Map<Character, Integer> characterMap = new HashMap<>();
        for (int i=0,j=0;i<n;i++) {
            if (characterMap.containsKey(s.charAt(i))) {
                //记录重复字符串出现的那个位置
                j = Math.max(characterMap.get(s.charAt(i)), j);
            }
            res = Math.max(res, i-j+1);
            characterMap.put(s.charAt(i), i+1);
        }
        return res;
    }
    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring1("dvdf"));
    }
}
