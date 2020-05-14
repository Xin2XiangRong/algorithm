package algorithm.cs.leetcode;

import org.junit.Test;

import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

/**
 * 7进制转10进制
 * @author chaishuai
 * @date 2019/6/11
 */
public class parse7to10 {

    public int parseInt(String src, int num) {
        int sum = 0;
        int weight = 1;
        for (int i=src.length(); i > 0; i--) {
            int a = src.charAt(i-1)-'0';
            sum += src.charAt(i-1)-'0' * weight;
            weight *= num;
        }
        return sum;
    }

    @Test
    public void test() {
        String a = "12345";
        String s = String.valueOf(a.charAt(1));
        Integer.parseInt(s,2);


        System.out.println(parseInt("1", 7));
    }
}
