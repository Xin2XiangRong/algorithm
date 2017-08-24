package algorithm.cs.sort;

import java.util.Random;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * �Ѱ���n��Ԫ�ص�����ֳ�n/2���������У���1��Ԫ�����n/2+1������Ϊһ�ԡ�����
 * һ��ѭ��ʹÿһ�����ж��ź�˳��
 * Ȼ���ڱ��n/4�����У���n/4Ϊ�������������У��ٴ�����
 * @author Administrator
 *
 *��ʵshell�����ǻ��ڲ��������˼��
 */
public class ShellSort {
	public static void shellSort(int[] a) {
		int i,j,k,temp;
		int n=0;
		for(i=a.length/2;i>=1;i/=2) {		//i��ʾÿ������������еļ��
			for(j=i;j<a.length;j++) {		//��������������˲��������˼��
				k=j-i;									//Ҫ�����λ��
				temp=a[j];							//Ҫ�������
				while(k>=0 && temp<a[k]) {
					a[k+i]=a[k];			//�ϴ�����������ƣ�Ϊ��������ڳ�λ��
					k-=i;				//��iΪ��������μ��
				}
				a[k+i]=temp;
			}
			n++;
			System.out.print("��"+n+"��������Ϊ��");
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
		System.out.print("����ǰ������Ϊ��");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.println();
		shellSort(a);
	}

}
