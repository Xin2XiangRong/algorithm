package algorithm.cs.sort;
/**
 * ����ѽṹ�����������
 * @author Administrator
 *
 */
public class HeapSort {
	public static void heapSort(int[] a, int n) {
		int i,j,temp;
		for(i=n/2-1;i>=0;i--) {
			while(2*i+1<n){			//��i���ڵ�������
				j=2*i+1;
				if(j+1<n) {				//��������
					if(a[j+1]>a[j]) {			//�����������������������j+1��jָ��������
						j++;
					}
				}
				if(a[j]>a[i]) {	//���������ֵ�ȸ��ڵ�ֵ���򽻻�����
					temp=a[i];
					a[i]=a[j];
					a[j]=temp;
					i=j;			//���ڵ㱻������Ϊ�����������ݱ��������ܱ�֤����������Ϊ���ڵ�ʱ���Ƿ����������Ҫ���½���
				}
				else {
					break;
				}
			}
		}
		//������ɵĶ�
		System.out.print("ԭʼ���ݹ��ɵĶѣ�");
		for(int k=0;k<n;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.println();
		for(i=n-1;i>0;i--) {
			temp=a[0];
			a[0]=a[i];
			a[i]=temp;			//�������������ڵ㣩�Ƶ�����ĩβ
			
			int k=0;				//�ӵ�һ�����ݿ�ʼ���
			while(2*k+1<i) {		//��k���ڵ�������
				j=2*k+1;
				if(j+1<i) {				//��������
					if(a[j+1]>a[j]) {
						j++;					//��֤jָ����ǽϴ�ֵ����һ������
					}
				}
				if(a[k]<a[j]) {				//����ڵ���ֵ�����ӽڵ���ֵС���򽻻�����
					temp=a[k];
					a[k]=a[j];
					a[j]=temp;
					k=j;				//�ѱ��ƻ������±Ƚ�
				}
				else {
					break;
				}
			}
			
			System.out.print("��"+(n-i)+"��������Ϊ��");
			for(int h=0;h<n;h++) {
				System.out.print(" "+a[h]);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		int[] a={27,28,14,7,2,0,3,5,11,9};
		System.out.print("����ǰ������Ϊ��");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.println();
		heapSort(a,a.length);
		System.out.print("����������Ϊ��");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}

	}

}
