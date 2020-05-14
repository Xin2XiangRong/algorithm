package algorithm.cs.leetcode;

import org.junit.Test;

/**
 * @Copyright: www.hikvision.com Inc. All rights reserved.
 * @ClassName: FindTheNum
 * @Description: 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
Consider the following matrix:
[
[1,   4,  7, 11, 15],
[2,   5,  8, 12, 19],
[3,   6,  9, 16, 22],
[10, 13, 14, 17, 24],
[18, 21, 23, 26, 30]
]
Given target = 5, return true.
Given target = 20, return false.
解题思路

要求时间复杂度 O(M + N)，空间复杂度 O(1)。其中 M 为行数，N 为 列数。

该二维数组中的一个数，小于它的数一定在其左边，大于它的数一定在其下边。
因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间，当前元素的查找区间为左下角的所有元素。
 * @author: chaishuai
 * @date: 2020/1/10 10:36
 * @version: V1.0.0
 */
public class FindTheNum {
    @Test
    public void testFindTheNum() {
        int[][] nums = {{1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        boolean b = findTheNum(nums, 20);
        System.out.println(b);
    }
    public boolean findTheNum(int[][] nums, int n) {
        //行
        int row = nums.length;
        //列
        int col = nums[0].length;
        //for (int i=0,j=col;i<row ||j>0;) {
        for (int i=0,j=col;i<row && j>0;) {
            if (nums[i][j-1] > n) {
                j--;
                //防止因为i<row进入了循环，但是已经比较到了列的最后值，比如n为0时
                /*if (j==0) {
                    return false;
                }*/
            } else if (nums[i][j-1] < n) {
                i++;
                /*if (i==row) {
                    return false;
                }*/
            } else {
                return true;
            }
        }
        return false;
    }
}
