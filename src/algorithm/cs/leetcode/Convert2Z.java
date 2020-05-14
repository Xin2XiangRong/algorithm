package algorithm.cs.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

 L   C   I   R
 E T O E S I I G
 E   D   H   N
 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

 请你实现这个将字符串进行指定行数变换的函数：

 string convert(string s, int numRows);
 示例 1:

 输入: s = "LEETCODEISHIRING", numRows = 3
 输出: "LCIRETOESIIGEDHN"
 * @author chaishuai
 * @date 2019/3/29
 */
public class Convert2Z {
    String result = "";
    protected String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        //使用list中的一项保存一行，按个读取string，一行一行的放，通过flag判断什么时候往下什么时候往上
        List<StringBuilder> res = new ArrayList<>();
        int j = Math.min(numRows, s.length());
        for (int i=0;i<j;i++) {
            res.add(new StringBuilder());
        }
        int m=0;
        boolean flag = true; //判断向下还是向上
        for (int i=0;i<s.length();i++) {
            res.get(m).append(s.charAt(i));
            //方向转换
            if (flag && m+1 == j) {
                flag = false;
            }
            if (!flag && m == 0) {
                flag = true;
            }
            m += (flag) ? 1 : -1;

        }
        res.forEach( c -> {
            result += c.toString();
        });
        return result;
    }

    @Test
    public void test() {
        System.out.println(convert("AB", 1));
    }
}
