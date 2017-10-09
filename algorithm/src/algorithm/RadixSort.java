package algorithm;

import java.util.Random;

public class RadixSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m=10,n=6;
		int[][] a=new int[m][n];
		Random r=new Random();
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				a[i][j]=r.nextInt(10);
			}
		}
		print(a);
		radixSort(a,0,10);
		print(a);

	}
	
	public static void radixSort(int[][] a,int min,int max){
		for(int j=a[0].length-1;j>=0;j--){
			countSort(a,j,min,max);
		}
	}
	
	public static void countSort(int[][] a,int j,int min,int max){
		int m=max-min;
		int[] index=new int[m];
		for(int i=0;i<a.length;i++){
			int k=a[i][j]-min;
			index[k]++;
		}
		int equali_=index[0];
		index[0]=0;
		for(int i=1;i<index.length;i++){
			int temp=index[i];
			index[i]=index[i-1]+equali_;
			equali_=temp;
		}
		int[][] b=new int[a.length][a[0].length];
		for(int i=0;i<a.length;i++){
			int k=a[i][j]-min;
			b[index[k]]=a[i];
			index[k]++;
		}
		copyArray(b,a);
	}
	
	public static void print(int[][] a){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				System.out.print(a[i][j]);
			}
			System.out.print(",");
		}
		System.out.println();
	}
	
	public static void copyArray(int[][] a,int[][] b){
		for(int i=0;i<a.length;i++){
			b[i]=a[i];
		}
	}

}
