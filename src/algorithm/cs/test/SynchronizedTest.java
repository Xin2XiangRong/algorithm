package algorithm.cs.test;

import com.alibaba.fastjson.JSONObject;
import com.sun.corba.se.impl.orbutil.closure.Future;
import org.junit.Test;

import java.beans.Beans;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Copyright: www.hikvision.com Inc. All rights reserved.
 * @ClassName: SynchronizedTest
 * @Description: TODO
 * @author: chaishuai
 * @date: 2020/4/2 14:55
 * @version: V1.0.0
 */
public class SynchronizedTest {

    private ThreadLocal<String> threadLocal;

    public static synchronized void method1() {
        System.out.println("method1 start");
        try {
            System.out.println("method1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method01 end");
    }
    public static synchronized void method2() {
        System.out.println("method2 start");
        try {
            System.out.println("method2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method02 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest test = new SynchronizedTest();
        final SynchronizedTest test1 = new SynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test1.method2();
            }
        }).start();
    }

    @Test
    public void test(){
        /*list.add("1");//Only String
        list.add("2");
        System.out.println(list);*/
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println(JSONObject.toJSONString(merge(intervals)));

    }

    public static int[][] merge(int[][] intervals) {
        //以左端点进行排序
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, (a, b)-> a[0]-b[0]);
        int i=0;
        while (i<intervals.length){
            int left = intervals[i][0];
            int right = intervals[i][1];
            //如有重叠，找到最后的右端点
            while (i<intervals.length-1 && intervals[i+1][0]<= right) {
                right = Math.max(right, intervals[++i][1]);
            }
            result.add(new int[]{left, right});
            //下一个区间
            i++;
        }
        return result.toArray(new int[0][]);
    }

    public static int maxSubArray(int[] nums) {
        int result=0;
        //int[] tempNums = new int[nums.length];
        //tempNums[0] = nums[0];
        for (int i=1;i<nums.length;i++) {
            if (nums[i-1] > 0) {
                //nums记录包含原数组值的最大值
                nums[i] += nums[i-1];
            }
            result = Math.max(result, nums[i]);
        }
        return result;
    }
    @Test
    public void test42() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

    public int climbStairs(int n) {
        int result = 0;
        if (n==1) {
            result = 1;
        } else if (n==2) {
            result = 2;
        } else {
            //递归
            result = climbStairs(n-1)+climbStairs(n-2);
        }
        return result;
    }

    public int climbStairs001(int n) {
        if (n==1) {
            return 1;
        }
        int[] result = new int[n+1];
        result[0]=0;
        result[1]=1;
        result[2]=2;
        for (int i=3; i<n+1;i++) {
            result[i]=result[i-1]+result[i-2];
        }
        return result[n];
    }

    @Test
    public void test002() {
        System.out.println(climbStairs001(3));
    }

    public int maxProfit(int[] prices) {
        //暴力法
        int r=0;
        int m = prices.length;
        for (int i=0;i<m;i++){
            for (int j=i+1;j<m;j++) {
                r = Math.max(r, prices[j]-prices[i]);
            }
        }
        return r;
    }

    public int maxProfit002(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int n=0; n < prices.length;n++) {
            if (prices[n] < min) {
                min = prices[n];
            }
            //max = Math.max(max, prices[n]-min);
            if (max<prices[n]-min) {
                max = prices[n]-min;
            }
        }
        return max;
    }

    @Test
    public void test003() {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit002(prices));
    }

    public int rob(int[] nums) {
        int m = nums.length;
        if (m==0) {
            return 0;
        }
        int[] r = new int[m+1];
        //相当于f(1)=?
        r[0]=0;
        r[1]=nums[0];
        for(int i=2;i<=m;i++) {
            //f(n)=MAX(f(n-1),f(n-2)+Nn)
            r[i] = Math.max(r[i-1], r[i-2]+nums[i-1]);
        }
        return r[m];
    }

    public int rob002(int[] nums) {
        int max = 0;
        //之前的一个值
        int pre = 0;
        for (int n : nums) {
            int temp = max;
            max = Math.max(pre+n, max);
            pre = temp;
        }
        HashMap map=new HashMap();
        map.put(null, "");
        return max;
    }

    @Test
    public void test004() {
        int[] nums = {2,7,9,3,1};
        System.out.println(rob002(nums));
    }

    public boolean canJump(int[] nums) {
        int l = nums.length;
        //rightPoint记录可以走到的最远点
        int rightPoint = 0;
        for (int i=0;i<l;i++) {
            if (i <= rightPoint) {
                rightPoint = Math.max(rightPoint, i + nums[i]);
                if (rightPoint >= l-1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test005() {
        int[] nums = {};
        System.out.println(canJump(nums));
    }

    public boolean isAnagram(String s, String t) {
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : charsS) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        for (char c : charsT) {
            if (map.containsKey(c) && map.get(c)>0) {
                if (map.get(c)-1 == 0) {
                    map.remove(c);
                } else {
                    map.put(c, map.get(c)-1);
                }
            } else {
                return false;
            }
        }
        return map.isEmpty();
    }

    public boolean isAnagram002(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for (int i=0;i<s.length();i++) {
            chars[s.charAt(i) - 'a']++;
            chars[t.charAt(i) - 'a']--;
        }
        for (int c : chars) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram003(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for (int i=0;i<s.length();i++) {
            chars[s.charAt(i) - 'a']++;
        }
        for (int i=0;i<t.length();i++) {
            chars[t.charAt(i) - 'a']--;
            if (chars[t.charAt(i) - 'a'] <0) {
                return false;
            }
        }
        return true;
    }
    public boolean isAnagram004(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        Arrays.sort(sChar);
        Arrays.sort(tChar);
        return Arrays.equals(sChar, tChar);
    }
    @Test
    public void test006() {
        String s = "rat";
        String t = "tar";
        System.out.println(isAnagram004(s, t));
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int res = 0;
        int cnt = 1;
        int[] odds = new int[nums.length+1];
        odds[0] = -1;
        //新建一个数组保存奇数个数和此个数时对应的下标位置
        for (int i=0;i<nums.length;i++) {
            if (nums[i]%2 != 0) {
                odds[cnt++] = i;
            }
        }
        odds[cnt] = nums.length;
        for (int m=1; m+k<=cnt;m++) {
            res += (odds[m]-odds[m-1]) * (odds[m + k]-odds[m - 1 + k]);
        }
        return res;
    }

    @Test
    public void test007() {
        int[] nums = {2,2,2,1,2,2,1,2,2,2};
        System.out.println(numberOfSubarrays(nums, 2));
    }

    public boolean isValid(String s) {
        boolean res = false;
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() && (c==')' || c=='}' || c==']')) {
                return res;
            } else {
                if (!stack.isEmpty()) {
                    char pop = stack.pop();
                    if ((c==')' && pop=='(') ||( (c=='}' && pop=='{') ) ||((c==']' && pop=='[')) ) {
                        continue;
                    } else {
                        stack.push(pop);
                        stack.push(c);
                    }
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid002(String s) {
        boolean res = false;
        //存储括号符号
        Map<Character, Character> myMap = new HashMap<>();
        myMap.put(')', '(');
        myMap.put(']', '[');
        myMap.put('}', '{');
        Stack<Character> myStack = new Stack<>();
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            //判断是不是后半部分
            if (myMap.containsKey(c)) {
                //从stack中取出
                char cc = myStack.isEmpty() ? '#' : myStack.pop();
                //如果是后半部分符号肯定就得配对，立马相互消了
                if (myMap.get(c) != cc) {
                    return res;
                }
            } else {
                //不是放进去
                myStack.push(c);
            }
        }
        return myStack.isEmpty();
    }

    @Test
    public void test20() {
        String s = "{[]}";
        System.out.println(isValid002(s));
    }

    @Test
    public void test155() {
        algorithm.cs.test.MinStack minStack = new algorithm.cs.test.MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        int min = minStack.getMin();
        minStack.pop();
        int min1 = minStack.getMin();
        minStack.pop();
        int min2 = minStack.getMin();
        System.out.println();
    }

    public int maxProfit122(int[] prices) {
        //遇到值比前一个值大相减即可。因为如果要是判断找到一个较大的值，然后这个的较大的值的下一个小于这个的话，
        // 再拿这个较大的值减去他前面的最小值，最后得到的结果同遇到大值即减小值，然后加起来的结果一样。
        int res = 0;
        for (int i=1;i<prices.length;i++) {
            int i1 = prices[i] - prices[i - 1];
            if (i1 > 0) {
                res += i1;
            }
        }
        return res;
    }

    @Test
    public void test122() {
        int[] prices = {7,6,4,3,1};
        System.out.println(maxProfit122(prices));
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> myList = new ArrayList<>();
        Map<Integer, Integer> myMap = new HashMap<>();
        if (nums1.length > nums2.length) {
            for (int n : nums2) {
                if (myMap.containsKey(n)) {
                    myMap.put(n, myMap.get(n)+1);
                } else {
                    myMap.put(n, 1);
                }
            }
            for (int n : nums1) {
                if (myMap.containsKey(n) && myMap.get(n)>0) {
                    myList.add(n);
                    myMap.put(n, myMap.get(n)-1);
                }
            }
        } else {
            for (int n : nums1) {
                if (myMap.containsKey(n)) {
                    myMap.put(n, myMap.get(n)+1);
                } else {
                    myMap.put(n, 1);
                }
            }
            for (int n : nums2) {
                if (myMap.containsKey(n) && myMap.get(n)>0) {
                    myList.add(n);
                    myMap.put(n, myMap.get(n)-1);
                }
            }
        }
        int[] res = new int[myList.size()];
        for (int i=0;i<myList.size();i++) {
            res[i] = myList.get(i);
        }
        return res;
    }

    @Test
    public void test350() {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        System.out.println(intersect(nums1, nums2));
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res = n ^ res;
        }
        return res;
    }

    @Test
    public void test136() {
        int[] nums = {4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> myMap = new HashMap<>();
        for (int n : nums) {
            int count = myMap.containsKey(n) ? myMap.get(n)+1 : 1;
            myMap.put(n, count);
        }
        Map.Entry<Integer, Integer> integerIntegerEntry = myMap.entrySet().stream().sorted((v1, v2) -> v2.getValue() - v1.getValue()).findFirst().get();
        return integerIntegerEntry.getKey();
    }

    //选举法
    public int majorityElement002(int[] nums) {
        //选票数
        int count = 0;
        //候选人
        int candidate = 0;
        for (int n : nums) {
            if (count == 0) {
                candidate = n;
            }
            //如果新的数和原来要统计的数不一致，则减一；减到0，更换候选人
            count += candidate==n ? 1 : -1;
        }
        return candidate;
    }

    @Test
    public void test169() {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement002(nums));
    }

    public int reverseBits(int n) {
        String nStr = binaryToDecimal(n);
        Stack<Character> stack = new Stack<>();
        for (char c : nStr.toCharArray()) {
            stack.add(c);
        }
        String res = "";
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        int i = Integer.parseInt(res, 2);
        return i;
    }

    @Test
    public void test190() {
        System.out.println(reverseBits(43261596));
    }
    //十进制转二进制
    public String binaryToDecimal(int n){
        String res = "";
        for(int i = 31;i >= 0; i--) {
            int i1 = n >>> i & 1;
            res += i1;
        }
        return res;
    }


    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n = n & n-1;
        };
        return res;
    }

    public int hammingWeight002(int n) {
        int res = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                res++;
            }
            mask = mask <<1;
        }
        return res;
    }

    @Test
    public void test191() {
        int n = Integer.parseInt("0000000000000000000000000000000111101", 2);
        System.out.println(hammingWeight002(n));
    }

    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test268() {
        int[] nums = {3,0,1};
        System.out.println(missingNumber(nums));
    }

    public int getSum(int a, int b) {
        //直到无位可进
        while (b != 0) {
            //相加后排出进位的影响后的树
            int temp = a ^ b;
            //需要进位的数
            b = (a & b) <<1;
            a = temp;
        }

        return a;
    }

    @Test
    public void test371() {
        Map<String, String> myMap = new HashMap<>();
        myMap.put(null, null);
        String s = myMap.get(null);
        String ss = myMap.get("");
        System.out.println(getSum(-4, 5));
    }

    //递归
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }
    //左右树需要互为镜像
    public boolean isSymmetric(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.val == b.val)
                && isSymmetric(a.left, b.right)
                && isSymmetric(a.right, b.left);
    }

    //迭代
    public boolean isSymmetric002(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode a = queue.poll();
            TreeNode b = queue.poll();
            if (a==null && b==null) continue;
            if (a==null || b==null) return false;
            if (a.val != b.val) return false;
            queue.add(a.left);
            queue.add(b.right);
            queue.add(a.right);
            queue.add(b.left);
        }
        return true;
    }

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

   @Test
   public void test101() {

   }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.right == null && root.left == null) return 1;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    @Test
    public void test104() {

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int count = nums.length;
        if (count==0) return null;
        if (count == 1) return new TreeNode(nums[0]);
        if (count == 2) {
            TreeNode treeNode = new TreeNode(nums[1]);
            TreeNode treeNode1 = new TreeNode(nums[0]);
            treeNode.left = treeNode1;
            return treeNode;
        } else if (count == 3) {
            TreeNode treeNode = new TreeNode(nums[1]);
            TreeNode treeNode1 = new TreeNode(nums[0]);
            TreeNode treeNode2 = new TreeNode(nums[2]);
            treeNode.left = treeNode1;
            treeNode.right = treeNode2;
            return treeNode;
        } else {
            TreeNode treeNode = new TreeNode(nums[count/2]);
            treeNode.left = sortedArrayToBST(Arrays.copyOfRange(nums,0, count/2));
            treeNode.right = sortedArrayToBST(Arrays.copyOfRange(nums,count/2+1, count));
            return treeNode;
        }
    }
    public TreeNode sortedArrayToBST001(int[] nums) {
        return helperSortedArrayToBST(nums, 0, nums.length);
    }
    public TreeNode helperSortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int p = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[p]);
        treeNode.left = helperSortedArrayToBST(nums, left, p-1);
        treeNode.right = helperSortedArrayToBST(nums, p+1, right);
        return treeNode;
    }
    @Test
    public void test108() {

    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> myMap = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            myMap.put(nums[i], i);
        }
        for (int i=0;i<nums.length;i++) {
            int key = target - nums[i];
            if (myMap.containsKey(key) && myMap.get(key) != i) {
                return new int[]{i, myMap.get(key)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public int[] twoSum002(int[] nums, int target) {
        int[] res = new int[2];
        int[] resNew = new int[nums.length];
        for(int i = 0; i<nums.length; i++) {
            resNew[i] = target - nums[i];
        }
        for (int i=0;i<nums.length;i++) {
            for (int j=0;j<nums.length;j++) {
                if (nums[i] == resNew[j] && i != j) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }
    @Test
    public void test1() {
        int[] nums = {3, 3};
        int target = 6;
        System.out.println(twoSum(nums, target));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        //第一行一直都是1
        res.add(new ArrayList<>());
        res.get(0).add(1);
        //一行一行开始处理
        for (int i=1;i<numRows;i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer>  preRow = res.get(i-1);
            //每一行的第一个值都为1
            row.add(1);
            for (int j=1;j<i; j++) {
                row.add(preRow.get(j-1)+preRow.get(j));
            }
            //每一行最后一个值为1
            row.add(1);
            res.add(row);
        }
        return res;
    }
    @Test
    public void test118() {
        System.out.println(generate(5));
    }

    public void moveZeroes(int[] nums) {
        //记录发现0的个数
        int count = 0;
        for (int i=nums.length-1; i>=0; i--) {
            if (nums[i] == 0) {
                for (int j=i;j<nums.length-1;j++) {
                    nums[j]=nums[j+1];
                }
                count++;
                nums[nums.length-count] = 0;
            }
        }
    }

    public void moveZeroes002(int[] nums) {
        //快慢指针
        for (int lastNoneZeroFountAt=0, cur =0; cur<nums.length;cur++) {
            if(nums[cur] != 0) {
                int temp = nums[cur];
                //互换
                //swap(nums[lastNoneZeroFoundAt], nums[cur])
                nums[cur] = nums[lastNoneZeroFountAt];
                nums[lastNoneZeroFountAt] = temp;
                //快慢指针
                //如果是等于零，则不会进来，lastNoneZero指向了0的位置
                lastNoneZeroFountAt++;
            }
        }
    }

    @Test
    public void test283() {
        int[] nums = {1, 0};
        moveZeroes002(nums);
        System.out.println();
    }

    public boolean containsDuplicate(int[] nums) {
        long count = Arrays.stream(nums).distinct().count();
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                return true;
            } else {
                map.put(n, 1);
            }
        }
        return false;
    }
    @Test
    public void test217() {
        int[] nums = {1,2,3,1};
        System.out.println(containsDuplicate(nums));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-1;
        int q = n-1;
        int y = m+n-1;
        while (p >= 0 && q >= 0) {
            nums1[y--] = (nums1[p] < nums2[q] ? nums2[q--] : nums1[p--]);
        }
        System.arraycopy(nums2, 0, nums1, 0, q+1);
    }
    @Test
    public void test88() {
        int[] nums1= {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        merge(nums1, 3, nums2, 3);
        System.out.println();
    }

    public void rotate(int[] nums, int k) {
        for (int i=0;i<k; i++) {
            int temp = nums[nums.length-1];
            for (int j=nums.length-1;j>0; j--) {
                nums[j]=nums[j-1];
            }
            nums[0] = temp;
        }
    }
    //借助于反转
    public void rotate002(int[] nums, int k) {
        //避免k大于数组长度，转了一圈后还是会回来
        k %= nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    @Test
    public void test189() {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate002(nums, k);
        System.out.println();
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        //快慢指针
        int i = 0;
        for (int j=1; j<nums.length;j++) {
            //当相等时，j独自加一，直到找到不相等的再放进去
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
    @Test
    public void test26() {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }

    public int[] plusOne(int[] digits) {
        for (int i=digits.length-1;i>-1;i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i]=0;
        }
        //如果最后始终没有找到不等于9的数
        int[] temp = new int[digits.length+1];
        temp[0]=1;
        return temp;

    }
    @Test
    public void test66() {
        int[] digits = {1, 2, 3};
        System.out.println(plusOne(digits));
    }

    Set<Integer> mySet = new HashSet<>(1024);
    public boolean isHappy(int n) {
        char[] chars = String.valueOf(n).toCharArray();

        mySet.add(n);
        int res = 0;
        for (int c : chars) {
            res += Math.pow(c-48, 2);
        }
        if (res == 1) {
            return true;
        }
        if (mySet.contains(res)) {
            return false;
        } else {
            return isHappy(res);
        }
    }

    @Test
    public void test202() {
        System.out.println(isHappy(2));
    }

    public int firstUniqChar(String s) {
        //判断是否出现过
        Map<Character, Integer> myMap1 = new HashMap<>();
        //记录出现的位置
        Map<Character, Integer> myMap2 = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (int i=0;i<chars.length;i++) {
            Integer integer = myMap1.putIfAbsent(chars[i], 1);
            if (integer == null) {
                //第一次出现
                myMap2.put(chars[i], i);
            } else {
                //不是第一次出现了，在备选答案中剔除
                if (myMap2.containsKey(chars[i])) {
                    myMap2.remove(chars[i]);
                }
            }
        }
        return myMap2.isEmpty() ? -1 : myMap2.entrySet().stream().findFirst().get().getValue();
    }

    @Test
    public void test387() {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
    }

    public int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        //把所有数值初始化为true
        Arrays.fill(isPrim, true);
        for (int i = 2;i*i<n;i++) {
            //某个数的倍数肯定不是素数
            if (isPrim[i]) {
                for (int j=i*i;j<n;j+=i) {
                    isPrim[j] = false;
                }
            }
        }
        int count = 0;
        for (int i=2;i<n;i++) {
            if (isPrim[i]) count++;
        }
        return count;
    }
    @Test
    public void test204() {
        System.out.println(countPrimes(10));
    }


    public class ListNode {
          int val;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        ListNode next;
          ListNode(int x) { val = x; }
     }

    public ListNode reverseList(ListNode head) {
        //迭代
        //要将每一个结点指向它的前一个结点，所以要记录下前一个节点
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

    public ListNode reverseList001(ListNode head) {
        if (head == null || head.next==null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    @Test
    public void test206() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        ListNode node = reverseList(node1);
        System.out.println();
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> myList = new ArrayList<>();
        //放到列表中然后使用双指针
        while (head != null) {
            myList.add(head.val);
            head = head.next;
        }
        int p1 = 0;
        int p2 = myList.size();
        while (p1 < p2) {
            Integer x = myList.get(p1);
            Integer y = myList.get(p2 - 1);
            if (Integer.compare(x, y) != 0) {
                return false;
            }
            p1++;
            p2--;
        }
        return true;
    }

    @Test
    public void test234() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        System.out.println(isPalindrome(node1));
    }

    //递归
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //如果其中存在为null的，返回那个不为null的
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    //迭代
    public ListNode mergeTwoLists002(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode pre = res;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        //
        pre.next = (l1 == null) ? l2 : l1;
        return res.next;
    }

    @Test
    public void test21() {

    }


    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null) {
            if (fast.next == null) return false;
            if (slow.next == fast.next) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
    @Test
    public void test141(){

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> mySet = new HashSet<>();
        while (headA != null) {
            mySet.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (mySet.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode002(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = (pA == null ? headB : pA.getNext());
            pB = (pB == null ? headA : pB.getNext());
        }
        return pA;
    }
    @Test
    public void test160() {

    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    @Test
    public void test237() {

    }

    public int mySqrt(int x) {
        //x = 2 * mySqrt(x/4)
        if (x < 2) return x;
        int left = mySqrt(x >> 2) << 1;
        int right = left + 1;
        return (long) right * right > x ? left : right;
    }

    @Test
    public void test69() {
        System.out.println(mySqrt(2147395600));
    }

    public int romanToInt(String s) {
        if (s == null) return 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> myMap = new HashMap<>();
        myMap.put('I', 1);
        myMap.put('V', 5);
        myMap.put('X', 10);
        myMap.put('L', 50);
        myMap.put('C', 100);
        myMap.put('D', 500);
        myMap.put('M', 1000);
        int res = myMap.get(chars[0]);
        for (int i=1; i < chars.length; i++) {
            if (myMap.get(chars[i]) > myMap.get(chars[i-1])) {
                res += myMap.get(chars[i]) - 2 * myMap.get(chars[i-1]);
            } else {
                res += myMap.get(chars[i]);
            }
        }
        return res;
    }
    @Test
    public void test13() {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    public boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        //如果都不可以被3整除了，就可以退出了
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    public boolean isPowerOfThree002(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    @Test
    public void test326() {
        System.out.println(isPowerOfThree(27));
    }


    /*public int trailingZeroes(int n) {
        //会溢出 不可以！！！
        long res = 1;
        for (int i = 1;i<=n; i++) {
            res *= i;
        }
        int count = 0;
        while (res % 10 == 0) {
            count++;
            res /= 10;
        }
        return count;
    }*/
    public int trailingZeroes(int n) {
        //其实就是要找出有多少对2/5，因为有5的时候肯定就有2了，所以就是要找有多少个5
        int count = 0;
        while (n > 0) {
            count += n/5;
            n /= 5;
        }
        return count;
    }

    @Test
    public void test172() {
        int a = '9';
        double b = Math.pow(3, 2);
        System.out.println(trailingZeroes(3));
    }

    public int titleToNumber(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        for (int i=0;i<chars.length;i++) {
            res += Math.pow(26, chars.length-i-1) * (chars[i]-64);
        }
        return res;
    }

    public int titleToNumber001(String s) {
        int res = 0;
        for (int i=0;i<s.length();i++) {
            int temp = s.charAt(i) - 'A' + 1;
            res = res * 26 + temp;
        }
        return res;
    }

    @Test
    public void test171() {
        System.out.println(titleToNumber("ZY"));
    }

    public boolean isPalindrome(String s) {
        int ap = 0;
        int bp = s.length()-1;
        s = s.toLowerCase();
        while (ap < bp) {
            if (!Character.isLetterOrDigit(s.charAt(ap))) {
                ap++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(bp))) {
                bp--;
                continue;
            }
            if (s.charAt(ap) != s.charAt(bp)) {
                return false;
            } else {
                ap++;
                bp--;
            }
        }
        return  true;
    }

    @Test
    public void test125() {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

    public void reverseString(char[] s) {
        int ap = 0;
        int bp = s.length-1;
        while (ap < bp) {
            //交换位置
            char temp = s[ap];
            s[ap] = s[bp];
            s[bp] = temp;
            ap++;
            bp--;
        }
    }

    @Test
    public void test344() {
        char[] s = {};
        reverseString(s);
        System.out.println();
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length==0) return "";
        //以第一个字符串的内容为基准，
        for (int i=0;i<strs[0].length();i++) {
            char c = strs[0].charAt(i);
            //依次和其他字符串比较
            for (int j=1; j<strs.length;j++) {
                if (i==strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring( 0, i);
                }
            }
        }
        return strs[0];
    }

    @Test
    public void test14() {

    }
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) return 0;
        if (needle.length() > haystack.length()) return -1;
        for (int i=0; i < haystack.length()-needle.length()+1; i++) {
            int temp = i;
            for (int j=0; j<needle.length();j++) {
                char c = haystack.charAt(i++);
                //如果发现不相等的了
                if (c != needle.charAt(j)) {
                    i = temp;
                    break;
                }
                //遍历到最后了
                if (j==needle.length()-1) {
                    return temp;
                }
            }
        }
        return -1;
    }
    public int strStr002(String haystack, String needle) {
        int hl = haystack.length();
        int nl = needle.length();
        for (int i=0; i<hl-nl+1; i++) {
            if (haystack.substring(i, i+nl).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
    @Test
    public void test28() {
        String haystack = "mississippi";
        String needle = "issipi";
        System.out.println(strStr(haystack, needle));
    }

    public String countAndSay(int n) {
        //f (n == 1) return "1";
        String res = "1";
        for (int i=1; i< n;i++) {
            res = countAndSayNext(res);
        }
        return res;
    }
    public String countAndSayNext(String s) {
        String res = "";
        int count = 1;
        for (int i=1;i<s.length();i++) {
            if (s.charAt(i-1) == s.charAt(i)) {
                count++;
            } else {
                res += count+String.valueOf(s.charAt(i-1));
                count = 1;
            }
        }
        //最后的一次
        res += count+ String.valueOf(s.charAt(s.length() - 1));
        return res;
    }

    @Test
    public void test38() {
        System.out.println(countAndSay(6));
    }

    private class CQueue {
        //始终往A里放，B里删
        Stack<Integer> stackA = new Stack();
        Stack<Integer> stackB = new Stack();

        public CQueue() {

        }

        public void appendTail(int value) {
            while (!stackB.isEmpty()) {
                stackA.push(stackB.pop());
            }
            stackA.push(value);
        }

        public int deleteHead() {
            //已经在B里了
            if (!stackB.isEmpty()) {
                return stackB.pop();
            }
            if (!stackA.isEmpty()) {
                while (!stackA.isEmpty()) {
                    stackB.push(stackA.pop());
                }
                return stackB.pop();
            }
            else {
                return -1;
            }
        }
    }

    @Test
    public void test09() {
        int a;
        CQueue cQueue = new CQueue();
        a = cQueue.deleteHead();
        cQueue.appendTail(12);
        a = cQueue.deleteHead();
        cQueue.appendTail(10);
        cQueue.appendTail(9);
        a = cQueue.deleteHead();
        a = cQueue.deleteHead();
        a = cQueue.deleteHead();
        cQueue.appendTail(20);
        a = cQueue.deleteHead();
        cQueue.appendTail(1);
        cQueue.appendTail(8);
        cQueue.appendTail(20);
        cQueue.appendTail(1);
        cQueue.appendTail(11);
        cQueue.appendTail(2);
        a = cQueue.deleteHead();
        a = cQueue.deleteHead();
        a = cQueue.deleteHead();
        a = cQueue.deleteHead();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length==0 || k == 0) return new int[0];
        int[] res = new int[nums.length-k+1];
        //使用一个队列保存窗口数组从大到小的样子
        Deque<Integer> queue = new LinkedList();
        //未形成窗口时
        for (int i=0;i<k;i++) {
            //保证queue里最前面存的是最大的
            while (!queue.isEmpty() && queue.peekLast() < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(nums[i]);
        }
        //形成窗口后
        for (int i=k;i<nums.length;i++) {
            res[i-k] = queue.peekFirst();
            //最大的那个数恰好是窗口移动后要剔除的那个
            if (nums[i-k] == queue.peekFirst()) {
                queue.removeFirst();
            }
            //保证queue里的数从大到小
            while (!queue.isEmpty() &&queue.peekLast() < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(nums[i]);
        }
        res[nums.length-k] = queue.peekFirst();
        return res;
    }
    @Test
    public void test59() {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(maxSlidingWindow(nums, k));
    }

    private class MinStack {

        Stack<Integer> dataStack = new Stack<>();
        Stack<Integer> helperStack = new Stack<>();
        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            dataStack.push(x);
            if (helperStack.isEmpty() || helperStack.peek() >= x) {
                helperStack.push(x);
            }
        }

        public void pop() {
            if (dataStack.peek().equals(helperStack.peek())) helperStack.pop();
            dataStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int min() {
            return helperStack.peek();
        }
    }

    @Test
    public void test30() {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        int min = minStack.min();
        minStack.pop();
        int min1 = minStack.min();
        System.out.println();
    }

    public String removeOuterParentheses(String S) {
        StringBuilder res = new StringBuilder("");
        Stack<Character> myStack = new Stack<>();
        for (Character c : S.toCharArray()) {
            /*if ('('==c && myStack.isEmpty()) {
                myStack.push(c);
            } else if (')' == c && myStack.size() >1) {
                myStack.pop();
                res += String.valueOf(c);
            } else if ('(' == c) {
                myStack.push(c);
                res += String.valueOf(c);
            } else {
                myStack.pop();
            }*/
            if ('('==c) {
                if (!myStack.isEmpty()) {
                    res.append(c);
                }
                myStack.push(c);
            } else{
                if (myStack.size() >1) {
                    res.append(c);
                }
                myStack.pop();
            }
        }
        return res.toString();
    }

    public String removeOuterParentheses002(String S) {
        StringBuilder sb = new StringBuilder("");
        int count = 0;
        for (Character c : S.toCharArray()) {
            if (c == ')') count--;
            if (count >=1) sb.append(c);
            if (c == '(') count++;
        }
        return sb.toString();
    }

    @Test
    public void test1021() {
        System.out.println(removeOuterParentheses002("(()())(())(()(()))"));
    }

    public String removeDuplicates(String S) {
        Stack<Character> myStack = new Stack<>();
        for (Character c : S.toCharArray()) {
            if (myStack.isEmpty() || myStack.peek() != c) {
                myStack.push(c);
            } else {
                myStack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!myStack.isEmpty()) {
            sb.append(myStack.pop());
        }
        return sb.reverse().toString();
    }
    @Test
    public void test1047() {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //使用map保存nums中每个数字的位置信息
        Map<Integer, Integer> myMap = new HashMap<>();
        for (int i=0; i<nums2.length;i++) {
            myMap.put(nums2[i], i);
        }
        int[] res = new int[nums1.length];
        for (int i=0;i<nums1.length;i++) {
            for (int j=myMap.get(nums1[i]);j<nums2.length;j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                } else {
                    if (nums2.length-1 == j) {
                        res[i] = -1;
                    }
                }
            }
        }
        return res;
    }
    public int[] nextGreaterElement002(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        //使用单调栈做数组参数下一个较大参数的临时判断
        Stack<Integer> myStack = new Stack<>();
        //记录nums2中数字的下一个较大数
        Map<Integer, Integer> myMap = new HashMap<>();
        for (int i : nums2) {
            while (!myStack.isEmpty() && myStack.peek() < i) {
                //找到了其下一个较大的数
                myMap.put(myStack.pop(), i);
            }
            myStack.push(i);
        }
        while (!myStack.empty()) {
            myMap.put(myStack.pop(), -1);
        }
        for (int i=0;i<nums1.length;i++) {
            res[i] = myMap.get(nums1[i]);
        }
        return res;
    }

    @Test
    public void test496() {
        int[] nums1 = {2,4};
        int[] nums2 = {1,2,3,4};
        System.out.println(nextGreaterElement(nums1, nums2));
    }

    public boolean backspaceCompare(String S, String T) {
         Stack<Character> myStack = new Stack<>();
         Stack<Character> myStack2 = new Stack<>();
         for (Character c : S.toCharArray()) {
             if (c == '#') {
                 if (myStack.empty()) {
                     continue;
                 } else {
                     myStack.pop();
                 }
             } else {
                 myStack.push(c);
             }
         }
        for (Character c : T.toCharArray()) {
            if (c == '#') {
                if (myStack2.empty()) {
                    continue;
                } else {
                    myStack2.pop();
                }
            } else {
                myStack2.push(c);
            }
        }
         if (myStack.size() != myStack2.size()) return false;
         while (!myStack.empty()) {
             if (myStack.pop() != myStack2.pop()) return false;
         }
         return true;
    }


    @Test
    public void test844() {
        String t = "bxj##tw";
        String s = "bxo#j##tw";
        System.out.println(backspaceCompare(t, s));

    }

    public int cuttingRope(int n) {
        if(n<=3) return n-1;
        //记录长度为n的截后的最大值
        int[] res = new int[n+1];
        res[1] = 1;
        res[2] = 2;
        res[3] = 3;
        //截出的段数越多，最后的乘积越大，最后一截肯定是2或3，因为4可以拆成2*2，而5没有2*3大
        for (int i=4;i<=n;i++) {
            res[i] = Math.max(2 * res[i-2], 3 * res[i-3]);
        }
        return res[n];
    }
    @Test
    public void test14_1() {
        System.out.println(cuttingRope(10));
    }



}
