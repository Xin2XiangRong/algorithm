package algorithm.cs.sort;
/**
 * ��ԭʼ������ѡ����С��һ�����ݣ�����͵�һλ��Ԫ�ؽ���
 * Ȼ���ٴ�n-1��������ѡ���С������
 */
import java.util.Random;


public class SelectSort {
	public static void selectSort(int[] a) {
		int i,j,k;
		int temp;
		int index;
	/*	for(i=0;i<a.length-1;i++) {		//ÿ�ζ���ɸѡ����С����ֵ�����ŵ�ǰ��
			for(j=i+1;j<a.length;j++) {		//ʹa[i]����Сֵ
				if(a[j]<a[i]) {
					temp=a[j];							//���������ҵ�Ԫ��Ȼ�󽻻���Ҫ��
					a[j]=a[i];
					a[i]=temp;
				}
			}*/
		for(i=0;i<a.length-1;i++) {
			index=i;
			for(j=i+1;j<a.length;j++) {
				if(a[j]<a[index])
					index=j;		//��index��ָ����С��ֵ��ָ��
				}
			if(index!=i) {		//�ҵ���С��ֵ������
				temp=a[i];
				a[i]=a[index];
				a[index]=temp;
			}
			System.out.print("��"+(i+1)+"��������Ϊ��");
			for(k=0;k<a.length;k++) {
				System.out.printf("%d  ",a[k]);
			}
			System.out.println();
		}
		System.out.print("����������Ϊ��");
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
		System.out.print("����ǰ������Ϊ��");
		for(j=0;j<SIZE;j++) {
			System.out.printf("%d  ", a[j]);
		}
		System.out.println();
		selectSort(a);
	}
}
