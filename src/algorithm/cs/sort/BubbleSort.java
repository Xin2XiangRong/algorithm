package algorithm.cs.sort;
/**
 * 对数组中的数值，依次比较相邻元素的大小，如果前面的数据大于后面的数据，则交换两个数据
 */
import java.util.Random;

public class BubbleSort {
	public static void bubbleSort(int[] a) {
		int i, j, temp;
		for(i=1;i<a.length;i++) {		//一次循环后，把相对最大的数值排到了最后
			for(j=0;j<a.length-i;j++) {
				if(a[j+1]<a[j]) {		//比较相邻两个变量，保证较大的数字在后面
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
			System.out.print("第"+i+"步排序结果为");
			for(int k=0;k<a.length;k++) {
				System.out.printf("%d  ", a[k]);
			}
			System.out.println();
		}
		System.out.print("排序后数组为：");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
	}
	public static void main(String[] args) {
		int i;
		/*int[] a=new int[10];
		for(i=0;i<10;i++) {
			Random random=new Random();
			int tp=random.nextInt(20);
			a[i]=tp;
		}*/
		int[] a={27,28,14,7,2,0,3,5,11,9};
		System.out.print("排序前数组为：");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.printf("\n");
		bubbleSort(a);
		
	}

}
