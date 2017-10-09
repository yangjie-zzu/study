package algorithm;

import java.util.Random;

public class SearchKMax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random=new Random();
		int[] a=new int[10];
		for(int i=0;i<a.length;i++){
			a[i]=random.nextInt(10);
		}
		int k=random.nextInt(10)+1;
		int[] ac=a.clone();
		Sort.mergeSort(ac);
		Sort.print(ac);
		System.out.println("k="+k);
		System.out.println(searchkmax1(a,k));
		//int[] c={1,1,5,6,7,7,8,8,8,9};
		//Sort.print(c);
		System.out.println(searchkmax2(a,k));
		

	}
	
	public static int searchkmax1(int[] a,int k){
		Sort.bubbleSort(a);
		return a[a.length-k];
	}
	
	public static int searchkmax2(int[] a,int k){
		int[] b=new int[k];
		Sort.copyArray(a, b, 0);
		Sort.selectSort(b);
		for(int i=k;i<a.length;i++){
			if(a[i]>b[0]){
				for(int j=1;j<b.length;j++){
					if(b[j]<a[i]){
						b[j-1]=b[j];
						if(j==b.length-1){
							b[j]=a[i];
						}
					}else{
						b[j-1]=a[i];
					}
				}
			}
		}
		return b[0];
	}

}
