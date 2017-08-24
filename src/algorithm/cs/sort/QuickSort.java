package algorithm.cs.sort;

import java.util.Random;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * �趨һ���ֽ�ֵ���������Ϊ���������֣������ڷֽ�ֵ�����ݷŵ������ұߣ�С�ڷֽ�ֵ�ķŵ����
 * Ȼ�����������ֿ�����ȡһ���ֽ�ֵ���ٰ�������������
 */
public class QuickSort {
	public static void quickSort(int[] a,int l, int r) {
		int m,temp;
		int lt,rt;
		m=a[(l+r)/2];				//�ֽ紦
		lt=l;
		rt=r;
		while(lt<rt) {		//��ѭ�������ʹ�ֽ�ֵ���С�ڷֽ�ֵ���ұߴ��ڷֽ�ֵ
			while(a[lt]<m) {	//�ڷֽ�ֵ����ҵ����ڷֽ�ֵ����
				++lt;
			}
			while(a[rt]>m) {		//�ڷֽ�ֵ����ҵ�С�ֽ�ֵ����
				--rt;
			}
			if(lt<=rt){				//�����ҵ���������
				temp=a[lt];
				a[lt]=a[rt];
				a[rt]=temp;
				--rt;				//rt�������������벿��
				++lt;				//lt���Ա���Ұ벿��
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
		System.out.print("����ǰ������Ϊ��");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.println();
		quickSort(a,0,a.length-1);
		System.out.print("����������Ϊ��");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
	}

}
