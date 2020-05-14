package algorithm.cs.leetcode;

import org.junit.Test;

/**
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * Input:{2, 3, 1, 0, 2, 5}
 * Output:2
 *
 * 要求时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。
 * 对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。
 * 以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，但是第 2 个位置上已经有一个 2 的值了，因此可以知道 2 重复
 * @author chaishuai
 * @date 2019/6/11
 */
public class DuplicateNum {

    @Test
    public void swapTest() {
        int[] nums = {5, 2, 1, 0, 3, 2};
        int swap = swap(nums, nums.length);
        System.out.println(swap);
    }

    public int swap(int[] nums, int len) {
        for (int i=0; i<len; ) {
            int temp = nums[i];
            if (temp != i) {
                //在交换位置之前，这个数的索引位置上已经有了和它自己等的值，则重复了
                if (temp == nums[temp]) {
                    return temp;
                }
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
            //交换位置以后，如果此位置上的值已经等于它的索引，则可以比较下一个了
            if (nums[i] == i) {
                i++;
            }
        }
        return 0;
    }
}
