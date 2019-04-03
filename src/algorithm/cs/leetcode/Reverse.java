package algorithm.cs.leetcode;

import org.junit.Test;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * @author chaishuai
 * @date 2019/3/29
 */
public class Reverse {
    public int reverse(int x) {
        int c = x;
        //几位数
        String s = String.valueOf(x).replace("-","");
        int j = s.length();
        int res = 0;
        int i = 1;
        //设置为double类型，是因为转化完可能会int溢出
        double temp = 0;
        for (;;i++){
            //找到余数，然后往前放
            temp = c % 10 * Math.pow(10, --j);
            if (temp > Math.pow(2,31)-1 || temp < -Math.pow(2,31)) {
                return 0;
            }
            //临时值记录大小，为的是判断有没有int溢出
            double a = res + temp;
            if (a > Math.pow(2,31)-1 || a < -Math.pow(2,31)) {
                return 0;
            }
            res =(int) a;
            c = c /10;
            //每一个位置上的数都处理完，退出
            if (j<0) {
                break;
            }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(reverse(
                1534239999));
    }
}
