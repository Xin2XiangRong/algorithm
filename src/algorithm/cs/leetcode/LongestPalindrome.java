package algorithm.cs.leetcode;

/**
 * @author chaishuai
 * @date 2019/3/27
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        int len = s.length();
        //for ()
        return null;
    }
    public boolean isPalindrome(String s) {
        for (int i=0;i<s.length()/2;i++) {
            if (s.charAt(i) != s.charAt(s.length()-i-1)) {
                return false;
            }
        }
        return true;
    }
}
