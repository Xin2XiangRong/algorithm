package algorithm.cs.sort;
/**
 * 首先将含有n个节点的待排序数据序列看作有n个长度为1的有序子表组成，将他们依次两两合并，得到长度为2的若干有序子表；
 * 然后再对这些子表进行两两合并，得到长度为4的若干有序子表；……，重复上述过程，一直重复到最后的子表长度为n，从而完成
 * 排序过程
 * @author Administrator
 *
 */
public class MergeSort {
	public static void mergeOne(int[] a, int[] b, int n, int length) {	//完成一遍合并的函数
		int s,e,j,i,k;		//s为序列开始坐标，i为被合并序列1的开始，j为被合并序列2的开始，e为合并后序列的结尾
		s=0;
		while(s+length<n) {
			e=s+2*length-1;
			if(e>=n) {
				e=n-1;
			}
			i=s;
			j=s+length;
			k=s;
			while(i<s+length && j<=e){			//如果两个有序表都未结束时，循环比较
				if(a[i]<=a[j]){				//将较小的元素复制到数组b中
					b[k++]=a[i++];
				}
				else {
					b[k++]=a[j++];
				}
			}
			while(i<s+length) {		//未合并部分复制到数组b
				b[k++]=a[i++];
			}
			while(j<=e) {
				b[k++]=a[j++];
			}
			
			s=e+1;
		}
		if(s<n) {	//剩余的数不够再组成序列时
			for(;s<n;s++) {
				b[s]=a[s];
			}
		}
	}
	public static void mergeSort(int[] a, int n) {
		int h,count,len,f;
		int[] p=new int[n];
		count=0;
		len=1;
		f=0;
		while(len<n) {
			if(f==1) {
				mergeOne(p,a,n,len);
			} else {
				mergeOne(a,p,n,len);
			}
			len*=2;
			f=1-f;
			
			count++;
			System.out.print("第"+count+"步排序结果：");
			for(h=0;h<a.length;h++){
				System.out.print(" "+a[h]);
			}
			System.out.println();
		}
		if(f==1) {
			for(h=0;h<n;h++) {
				a[h]=p[h];
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		mergeSort(a,a.length);
		System.out.print("排序后的数据为：");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
	}

}
