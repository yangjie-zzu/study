package algorithm;

import java.util.Random;

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r=new Random();
		int c=r.nextInt(10);
		System.out.println(c);
		int[] a=new int[10];
		for(int i=0;i<a.length;i++){
			a[i]=r.nextInt(10);
			System.out.print(i);
		}
		System.out.println();
		Sort.quickSort(a, 0, a.length);
		Sort.print(a);
		System.out.println(binarySearch(a,c));
		

	}
	/*
	 * 0123456889
	 * 9
	 * 
	 */
	public static int binarySearch(int[] a,int c){
		int i=1,j=a.length-1;
		int k;
		while(i<=j){
			k=(i+j)/2;
			if(a[k]==c){
				return k;
			}else if(c<a[k]){
				j=k-1;
			}else if(c>a[k]){
				i=k+1;
			}
		}
		return -(i+1);
	}

}
