package algorithm.cs.sort;
/**
 * 从原始数组中选择最小的一个数据，将其和第一位的元素交换
 * 然后再从n-1个数据中选择次小的数据
 */
import java.util.Random;


public class SelectSort {
	public static void selectSort(int[] a) {
		int i,j,k;
		int temp;
		int index;
	/*	for(i=0;i<a.length-1;i++) {		//每次都是筛选出最小的数值将它放到前面
			for(j=i+1;j<a.length;j++) {		//使a[i]是最小值
				if(a[j]<a[i]) {
					temp=a[j];							//并不符合找到元素然后交换的要求
					a[j]=a[i];
					a[i]=temp;
				}
			}*/
		for(i=0;i<a.length-1;i++) {
			index=i;
			for(j=i+1;j<a.length;j++) {
				if(a[j]<a[index])
					index=j;		//用index来指向最小数值的指标
				}
			if(index!=i) {		//找到最小数值，交换
				temp=a[i];
				a[i]=a[index];
				a[index]=temp;
			}
			System.out.print("第"+(i+1)+"次排序结果为：");
			for(k=0;k<a.length;k++) {
				System.out.printf("%d  ",a[k]);
			}
			System.out.println();
		}
		System.out.print("最后的排序结果为：");
		for(int b:a) {
			System.out.printf("%d  ", b);
		}
	}
	public static void main(String[] args) {
		final int SIZE=10;
		int i,j;
		/*int[] a=new int[SIZE];
		for(i=0;i<SIZE;i++) {
			Random random=new Random();
			int tp=random.nextInt(20);
			a[i]=tp;
		}*/
		int[] a={27,28,14,7,2,0,3,5,11,9};
		System.out.print("排序前的数组为：");
		for(j=0;j<SIZE;j++) {
			System.out.printf("%d  ", a[j]);
		}
		System.out.println();
		selectSort(a);
	}
}
