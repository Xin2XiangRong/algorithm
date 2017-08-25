package algorithm.cs.sort;
/**
 * ���Ƚ�����n���ڵ�Ĵ������������п�����n������Ϊ1�������ӱ���ɣ����������������ϲ����õ�����Ϊ2�����������ӱ�
 * Ȼ���ٶ���Щ�ӱ���������ϲ����õ�����Ϊ4�����������ӱ��������ظ��������̣�һֱ�ظ��������ӱ���Ϊn���Ӷ����
 * �������
 * @author Administrator
 *
 */
public class MergeSort {
	public static void mergeOne(int[] a, int[] b, int n, int length) {	//���һ��ϲ��ĺ���
		int s,e,j,i,k;		//sΪ���п�ʼ���꣬iΪ���ϲ�����1�Ŀ�ʼ��jΪ���ϲ�����2�Ŀ�ʼ��eΪ�ϲ������еĽ�β
		s=0;
		while(s+length<n) {
			e=s+2*length-1;
			if(e>=n) {
				e=n-1;
			}
			i=s;
			j=s+length;
			k=s;
			while(i<s+length && j<=e){			//������������δ����ʱ��ѭ���Ƚ�
				if(a[i]<=a[j]){				//����С��Ԫ�ظ��Ƶ�����b��
					b[k++]=a[i++];
				}
				else {
					b[k++]=a[j++];
				}
			}
			while(i<s+length) {		//δ�ϲ����ָ��Ƶ�����b
				b[k++]=a[i++];
			}
			while(j<=e) {
				b[k++]=a[j++];
			}
			
			s=e+1;
		}
		if(s<n) {	//ʣ������������������ʱ
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
			System.out.print("��"+count+"����������");
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
		System.out.print("����ǰ������Ϊ��");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
		System.out.println();
		mergeSort(a,a.length);
		System.out.print("����������Ϊ��");
		for(int k=0;k<a.length;k++) {
			System.out.printf("%d  ", a[k]);
		}
	}

}
