package algorithm.cs.sort;
/**
 * �������е���ֵ�����αȽ�����Ԫ�صĴ�С�����ǰ������ݴ��ں�������ݣ��򽻻���������
 */
import java.util.Random;

public class BubbleSort {
	public static void bubbleSort(int[] a) {
		int i, j, temp;
		for(i=1;i<a.length;i++) {		//һ��ѭ���󣬰����������ֵ�ŵ������
			for(j=0;j<a.length-i;j++) {
				if(a[j+1]<a[j]) {		//�Ƚ�����������������֤�ϴ�������ں���
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
			System.out.print("��"+i+"��������Ϊ");
			for(int k=0;k<a.length;k++) {
				System.out.printf("%d  ", a[k]);
			}
			System.out.println();
		}
		System.out.print("���������Ϊ��");
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
		System.out.print("����ǰ����Ϊ��");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.printf("\n");
		bubbleSort(a);
		
	}

}
