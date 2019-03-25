package algorithm.cs.leetcode;

import java.util.*;

public class TwoNums {
    public static int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        int j = nums.length;
        for (int i=0;i<j; i++) {
            for (int m=i+1;m<j;m++) {
                int x = nums[i];
                int y = nums[m];
                if (x + y == target) {
                    result[0]=i;
                    result[1]=m;
                    break;
                }
            }
            if (result[1]!=0) {
                break;
            }
        }
        return result;
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i=0; i<nums.length;i++) {
            if (map.containsKey(target-nums[i])) {
                result[0]=i;
                result[1]=map.get(target-nums[i]);
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        for (int i=0; i<2;i++) {
            System.out.println(twoSum2(nums,target)[i]);
        }
    }
}
