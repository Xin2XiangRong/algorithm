package algorithm.cs.sort;

import java.util.Random;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * 把包含n个元素的数组分成n/2个数字序列，第1个元素与第n/2+1个数据为一对……；
 * 一次循环使每一个序列对排好顺序；
 * 然后在变成n/4个序列，以n/4为间隔组成数字序列，再次排序
 * @author Administrator
 *
 *其实shell排序是基于插入排序的思想
 */
public class ShellSort {
	public static void shellSort(int[] a) {
		int i,j,k,temp;
		int n=0;
		for(i=a.length/2;i>=1;i/=2) {		//i表示每次组成数字序列的间隔
			for(j=i;j<a.length;j++) {		//这里的排序体现了插入排序的思想
				k=j-i;									//要插入的位置
				temp=a[j];							//要插入的数
				while(k>=0 && temp<a[k]) {
					a[k+i]=a[k];			//较大的数字往后移，为插入的数腾出位置
					k-=i;				//以i为间隔，依次检查
				}
				a[k+i]=temp;
			}
			n++;
			System.out.print("第"+n+"步排序结果为：");
			for(int m=0;m<a.length;m++) {
				System.out.printf("%d  ",a[m]);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		final int SIZE=10;
		int i;
		/*int[] a = new int[SIZE];
		for(i=0;i<SIZE;i++) {
			Random random=new Random();
			int tp=random.nextInt(30);
			a[i]=tp;
		}*/
		int[] a={27,28,14,7,2,0,3,5,11,9};
		System.out.print("排序前的数据为：");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.println();
		shellSort(a);
	}

}
