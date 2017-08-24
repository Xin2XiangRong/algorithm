package algorithm.cs.sort;

import java.util.Random;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * 设定一个分界值，将数组分为左右两部分；将大于分界值的数据放到数组右边，小于分界值的放到左边
 * 然后左右两边又可以再取一个分界值，再按上述方法排序；
 */
public class QuickSort {
	public static void quickSort(int[] a,int l, int r) {
		int m,temp;
		int lt,rt;
		m=a[(l+r)/2];				//分界处
		lt=l;
		rt=r;
		while(lt<rt) {		//在循环后可以使分界值左边小于分界值，右边大于分界值
			while(a[lt]<m) {	//在分界值左边找到大于分界值的数
				++lt;
			}
			while(a[rt]>m) {		//在分界值左边找到小分界值的数
				--rt;
			}
			if(lt<=rt){				//交换找到的两个数
				temp=a[lt];
				a[lt]=a[rt];
				a[rt]=temp;
				--rt;				//rt可以用来标记左半部分
				++lt;				//lt可以标记右半部分
			}
		}
		/*if(lt==rt) {
			lt++;
		}*/
		if(l<rt) {
			quickSort(a, l, rt);
		}
		if(lt<r){
			quickSort(a,lt,r);
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
		quickSort(a,0,a.length-1);
		System.out.print("排序后的数据为：");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
	}

}
