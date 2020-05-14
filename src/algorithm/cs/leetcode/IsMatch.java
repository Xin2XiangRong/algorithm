package algorithm.cs.leetcode;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。

 '.' 匹配任意单个字符。
 '*' 匹配零个或多个前面的元素。
 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。

 说明:

 s 可能为空，且只包含从 a-z 的小写字母。
 p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * @author chaishuai
 * @date 2019/4/8
 */
public class IsMatch {
    public boolean isMatch(String s, String p) {
        boolean res = false;
        for (int i=0; i<p.length();i++) {
            int j = i;
            if (p.charAt(i) == s.charAt(j)) {
                continue;
            }
            if (p.charAt(i) == '.') {
                continue;
            }
            if (p.charAt(i) == '*') {

            }
        }
        return res;
    }
}
