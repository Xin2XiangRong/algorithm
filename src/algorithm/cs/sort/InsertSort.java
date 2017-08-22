package algorithm.cs.sort;

import java.util.Random;

/**
 * 首先对数组的前两个数据进行排序，然后将第三个数据与排好的数据进行比较，将其插入到合适位置
 */

public class InsertSort {
	
	public static void insertSort(int[] a) {
		int i,j;
		int temp;
		for(i=1;i<a.length;i++) {		//a[i]表示要用来插入的数
			/*int index=a[i];					//用来保存要插入（比较）的数
			for(j=i-1;j>=0;j--) {					//j表示要插入的位置
				if(index<a[j]) {
					temp=a[j];
					a[j]=index;
					a[j+1]=temp;
				}
			}*/
			temp=a[i];		//要插入的数
			j=i-1;
			while(j>=0 && a[j]>temp) {
				a[j+1]=a[j];			//如过有比要插入的数小的就往后移动一位
				j--;
			}
			a[j+1]=temp;
			System.out.print("第"+i+"次排序结果为：");
			for(int k=0;k<a.length;k++) {
				System.out.printf("%d  ", a[k]);
			}
			System.out.println();
		}
		System.out.print("最后的排序结果为：");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
	}
	public static void main(String[] args) {
		final int SIZE=10;
		/*int[] a=new int[SIZE];
		int i;
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
		insertSort(a);
	}

}
