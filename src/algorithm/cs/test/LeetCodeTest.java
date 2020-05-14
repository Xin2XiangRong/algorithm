package algorithm.cs.test;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.FutureTask;

/**
 * @Copyright: www.hikvision.com Inc. All rights reserved.
 * @ClassName: LeetCodeTest
 * @Description: TODO
 * @author: chaishuai
 * @date: 2020/4/8 11:20
 * @version: V1.0.0
 */
public class LeetCodeTest {
    @Test
    public void singleNumXOR() {
        int[] nums = new int[]{4,1,2,1,2};
        //借助于XOR（异或），任何数与0异或后结果都是自身
        int ans = 0;
        for (int n : nums) {
            ans = ans ^ n;
        }

        System.out.println(ans);
    }

    //通过两个下标指向实现
    @Test
    public void testTwoPointer() {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int i=0, j = 1;
        while (j <nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                //新的数组
                nums[++i] = nums[j++];
            }
        }
        System.out.println(i+1);
    }

    @Test
    public void testRotateArrayThreeTimes() {
        int[] nums = new int[]{-1,-100,3,99};
        int k = 2;
        if (nums == null || nums.length <=0 || k == nums.length) {
            return;
        }
        //整体反转
        rotateArray(nums, 0, nums.length - 1);
        //前半部分反转
        rotateArray(nums, 0, k-1);
        //后半部分反转
        rotateArray(nums, k, nums.length - 1);
        System.out.println(nums);
    }
    private void rotateArray(int[] nums, int i, int j) {
        while (i < j) {
            //交换对象
            nums[i] = nums[i] + nums[j];
            nums[j] = nums[i] - nums[j];
            nums[i] = nums[i] - nums[j];
            i++;
            j--;
        }
    }

    @Test
    public void testTwoSum() {
        int[] nums = {3,2,4};
        int target = 6;
        Map myMap = new HashMap();
        for (int i=0; i < nums.length; i++) {
            if (myMap.containsKey(target-nums[i])) {
                System.out.println(myMap.get(target-nums[i]) + "," + i);
            } else {
                myMap.put(nums[i], i);
            }
        }
    }

    @Test
    public void testContainsDuplicate() {
        int[] nums = {1,2,3,1,2,3};
        int k=2;
        Set mySet = new HashSet();
        for (int i=0;i<nums.length;i++) {
            if (i>k) {
                mySet.remove(nums[i-k-1]);
            }
            if (!mySet.add(nums[i])) {
                System.out.println(true);
            }
        }
    }

    @Test
    public void testReverse() {
        int original = 1230;
        int res = 0;
        while (original != 0) {
            //利用ans=ans*10+x%10进行翻转
            res = res*10 + original%10;
            original /= 10;
        }
        System.out.println(res);
    }

    @Test
    public void testIsAnagram() {
        String s="anagram";
        String t = "nagaram";
        int[] temp = new int[26];
        for (int i=0;i<s.length();i++)
            temp[s.charAt(i)-'a']++;
        for (int j=0;j<s.length();j++)
            temp[t.charAt(j)-'a']--;
        for (int a : temp) {
            if (a != 0)
                System.out.println(false);
        }
    }

    @Test
    public void test() {

        String i1 = new String("aaa");
        String i2 =  "aaa";
        System.out.println(i1 == i2);
    }

}
