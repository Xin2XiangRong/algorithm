package algorithm.cs.sort;

import java.util.Random;

/**
 * ���ȶ������ǰ�������ݽ�������Ȼ�󽫵������������źõ����ݽ��бȽϣ�������뵽����λ��
 */

public class InsertSort {
	
	public static void insertSort(int[] a) {
		int i,j;
		int temp;
		for(i=1;i<a.length;i++) {		//a[i]��ʾҪ�����������
			/*int index=a[i];					//��������Ҫ���루�Ƚϣ�����
			for(j=i-1;j>=0;j--) {					//j��ʾҪ�����λ��
				if(index<a[j]) {
					temp=a[j];
					a[j]=index;
					a[j+1]=temp;
				}
			}*/
			temp=a[i];		//Ҫ�������
			j=i-1;
			while(j>=0 && a[j]>temp) {
				a[j+1]=a[j];			//����б�Ҫ�������С�ľ������ƶ�һλ
				j--;
			}
			a[j+1]=temp;
			System.out.print("��"+i+"��������Ϊ��");
			for(int k=0;k<a.length;k++) {
				System.out.printf("%d  ", a[k]);
			}
			System.out.println();
		}
		System.out.print("����������Ϊ��");
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
		System.out.print("����ǰ������Ϊ��");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.println();
		insertSort(a);
	}

}
