package algorithm;

import java.util.Random;

public class HeapSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] a=new Integer[10];
		Random r=new Random();
		for(int i=0;i<a.length;i++){
			a[i]=r.nextInt(10);
		}
		Sort.print(a);
		sort(a);
		Sort.print(a);

	}
	
	public static void sort(Integer[] a){
		for(int i=a.length-1;i>=0;i--){
			adjustMent(a,a.length,i);
		}
		for(int i=a.length-1;i>0;i--){
			exchange(a,0,i);
			adjustMent(a,i,0);
		}
	}
	
	public static void adjustMent(Integer[] a,int n,int i){
		int k=i*2+1;
		if(k>=n){
			return;
		}else if(k+1>=n){
			adjustMent(a,n,k);
			if(a[i].compareTo(a[k])<0){
				exchange(a,i,k);
			}
		}else{
			adjustMent(a,n,k);
			if(a[i].compareTo(a[k])<0){
				exchange(a,i,k);
			}
			adjustMent(a,n,++k);
			if(a[i].compareTo(a[k])<0){
				exchange(a,i,k);
			}
		}
	}
	
	public static void exchange(Object[] a,int i,int j){
		Object temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}

}
