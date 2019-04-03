package algorithm.cs.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chaishuai
 * @date 2019/3/27
 */
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double res;
        int c1 = nums1.length;
        int c2 = nums2.length;
        int sum = c1 + c2;
        Integer[] integers = new Integer[sum];
        for (int i=0;i<c1;i++) {
            integers[i] = nums1[i];
        }
        for (int j=0;j<c2;j++) {
            integers[c1+j] = nums2[j];
        }
        List<Integer> integerList = Arrays.asList(integers);
        Collections.sort(integerList);
        if ((sum) %2 == 0) {
            res = ((double)integerList.get(sum/2-1) + (double)integerList.get(sum/2))/2;
        } else {
            res = integerList.get(sum/2);
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
