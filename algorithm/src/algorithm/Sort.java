package algorithm;

import java.util.Random;

public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//selectSort(a);
		//bubbleSort(a);
		int[] a=new int[10];
		Random r=new Random();
		for(int i=0;i<10;i++){
			a[i]=r.nextInt(10);
			System.out.print(a[i]);
		}
		System.out.println();
		quickSort(a,0,a.length);
		//insertSort(a);
		//mergeSort(a);
		//countSort(a,0,10);
		print(a);
		//System.out.println(a.length);
		

	}
	//冒泡排序
	public static void bubbleSort(int[] a){
		for(int i=0;i<a.length-1;i++){
			for( int j=0;j<a.length-1-i;j++){
				if(a[j]>a[j+1]){
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
	}
	//选择排序
	public static void selectSort(int[] a){
		for(int i=0;i<a.length-1;i++){
			for(int j=i+1;j<a.length;j++){
				if(a[i]>a[j]){
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
		}
	}
	/*
	 * 627389,327689,326789,
	 *        726389,723689,
	 */
	//快速排序
	public static void quickSort(int[] a,int i,int n){
		if(n<=1){
			return;
		}
		int i1=i;
		int j=i+n-1;
		int k=a[i];
		while(i!=j){
			while(i!=j&&a[j]>=k){
				j--;
			}
			if(i!=j){
				int temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				//i++;
				//print(a);
			}
			while(i!=j&&a[i]<=k){
				i++;
			}
			if(i!=j){
				int temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				//j--;
				//print(a);
			}
		}
		int n1=i-i1;
		quickSort(a,i1,n1);
		int i2=i+1;
		int n2=i1+n-1-i;
		quickSort(a,i2,n2);
	}
	//插入排序
	public static void insertSort(int[] a){
		for(int i=0;i<a.length;i++){
			int j=i-1;
			int temp=a[i];
			while(j>=0&&temp<a[j]){
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=temp;
		}
	}
	//归并排序
	public static void mergeSort(int[] a){
		if(a.length<=1){
			return;
		}
		int[] m=new int[a.length/2];
		int[] n=new int[a.length-m.length];
		copyArray(a,m,0);
		mergeSort(m);
		copyArray(a,n,m.length);
		mergeSort(n);
		copyArray(putTogether(m,n),a,0);
		
	}
	//归并
	public static int[] putTogether(int[]a,int[] b){
		int[] c=new int[a.length+b.length];
		for(int i=0,j=0,k=0;i<c.length;i++){
			if(j<a.length&&k<b.length){
				if(a[j]<=b[k]){
					c[i]=a[j++];
				}else{
					c[i]=b[k++];
				}
			}else if(j>=a.length){
				c[i]=b[k++];
			}else{
				c[i]=a[j++];
			}
		}
		return c;
	}
	//数组复制
	public static void copyArray(int[] a,int[] b,int aStart){
		for(int i=aStart,j=0;j<b.length;j++){
			b[j]=a[i++];
		}
	}
	//计数排序
	public static void countSort(int[] a,int min,int max){
		int m=max-min;
		int[] index=new int[m];
		for(int i=0;i<a.length;i++){
			int k=a[i]-min;
			index[k]++;
		}
		int temp=index[0];
		index[0]=0;
		for(int i=1;i<index.length;i++){
			int temp1=index[i];
			index[i]=index[i-1]+temp;
			temp=temp1;
		}
		int[] b=new int[a.length];
		for(int i=0;i<a.length;i++){
			int k=a[i]-min;
			b[index[k]]=a[i];
			index[k]++;
		}
		copyArray(b,a,0);
	}
	
	public static void print(Integer[] a){
		for(int i:a){
			System.out.print(i);
		}
		System.out.println();
	}
	
	public static void print(int[] a){
		for(int i:a){
			System.out.print(i);
		}
		System.out.println();
	}

}
