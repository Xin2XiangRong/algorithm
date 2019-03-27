package algorithm.cs.leetcode;

import org.junit.Test;

/**
 * @author chaishuai
 * @date 2019/3/26
 */
public class AddTwoNums {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = null;
        ListNode header = null;
        //ListNode temp;
        ListNode tl1 = l1;
        ListNode tl2 = l2;
        boolean flag = false; //是否进位
        int c = 0;
       while (tl1 != null || tl2 != null || flag) {
           ListNode temp;
           int x = 0,y = 0,z;
           if (tl1 != null) {
               x = tl1.val;
           }
           if (tl2 != null) {
               y = tl2.val;
           }
           if (flag) {
               z = x + y + 1;
           } else {
               z = x + y;
           }
           if (z < 10) {
               temp = new ListNode(z);
               flag = false;

           } else {
               temp = new ListNode(z - 10);
               flag = true;
           }
           //相当于初始化链表
           if (c == 0) {
               res = temp;
               header = res;
               c++;
           } else {
               //从头开始，找到下一个要插入的位置
               while (header.next != null) {
                   header = header.next;
               }
               header.next = temp;
           }
           if (tl1 != null) {
               tl1 = tl1.next;
           }
           if (tl2 != null) {
               tl2 = tl2.next;
           }
       }
        return res;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        //l1.next = new ListNode(4);
        //l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        //l2.next.next = new ListNode(4);

        ListNode res = addTwoNumbers(l1, l2);
        System.out.println(res);
    }


    private class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
