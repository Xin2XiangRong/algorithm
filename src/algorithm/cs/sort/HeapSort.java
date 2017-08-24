package algorithm.cs.sort;
/**
 * 构造堆结构，堆排序输出
 * @author Administrator
 *
 */
public class HeapSort {
	public static void heapSort(int[] a, int n) {
		int i,j,temp;
		for(i=n/2-1;i>=0;i--) {
			while(2*i+1<n){			//第i个节点有子树
				j=2*i+1;
				if(j+1<n) {				//有右子树
					if(a[j+1]>a[j]) {			//如果右子树大于左子树，则j+1，j指向右子树
						j++;
					}
				}
				if(a[j]>a[i]) {	//如果子树的值比根节点值大，则交换数据
					temp=a[i];
					a[i]=a[j];
					a[j]=temp;
					i=j;			//根节点被换，因为右子树的数据被换，不能保证以右子树作为根节点时堆是否成立，所以要重新建立
				}
				else {
					break;
				}
			}
		}
		//输出构成的堆
		System.out.print("原始数据构成的堆：");
		for(int k=0;k<n;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.println();
		for(i=n-1;i>0;i--) {
			temp=a[0];
			a[0]=a[i];
			a[i]=temp;			//将最大的数（根节点）移到数组末尾
			
			int k=0;				//从第一个数据开始检查
			while(2*k+1<i) {		//第k个节点有子树
				j=2*k+1;
				if(j+1<i) {				//有右子树
					if(a[j+1]>a[j]) {
						j++;					//保证j指向的是较大值的那一个子树
					}
				}
				if(a[k]<a[j]) {				//如果节点数值比其子节点数值小，则交换数据
					temp=a[k];
					a[k]=a[j];
					a[j]=temp;
					k=j;				//堆被破坏，重新比较
				}
				else {
					break;
				}
			}
			
			System.out.print("第"+(n-i)+"步排序结果为：");
			for(int h=0;h<n;h++) {
				System.out.print(" "+a[h]);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		int[] a={27,28,14,7,2,0,3,5,11,9};
		System.out.print("排序前的数据为：");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.println();
		heapSort(a,a.length);
		System.out.print("排序后的数据为：");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}

	}

}
