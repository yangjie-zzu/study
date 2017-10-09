package algorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class TreeSet<E extends Comparable<? super E>> {
	
	private BinaryNode<E> root;
	
	public LinkedList<E> toList(){
		return toList(root);
	}
	
	public LinkedList<E> toList(BinaryNode<E> x){
		LinkedList<E> list=new LinkedList<>();
		if(x!=null){
			list.addAll(toList(x.left));
			list.add(x.item);
			list.addAll(toList(x.right));
		}
		return list;
	}
	
	public E getByIndex(int i){
		return toList().get(i);
	}
	
	public boolean add(E item){
		return add(root,item);
	}
	
	public boolean add(BinaryNode<E> x,E item){
		//System.out.println();
		if(x==null){
			root=new BinaryNode<>(null,item);
			return true;
		}
		if(item.compareTo(x.item)==0){
			return false;
		}else if(item.compareTo(x.item)<0){
			if(x.left==null){
				x.left=new BinaryNode<E>(x,item);
				return true;
			}else{
				return add(x.left,item);
			}
		}else{
			if(x.right==null){
				x.right=new BinaryNode<E>(x,item);
				return true;
			}else{
				return add(x.right,item);
			}
		}
	}
	
	public void deleteSinglet(BinaryNode<E> x){
		if(x.parent.left==x){
			x.parent.left=x.left!=null?x.left:x.right;
		}else{
			x.parent.right=x.left!=null?x.left:x.right;
		}
	}
	
	public boolean remove(E item){
		return remove(root,item);
	}
	
	public boolean remove(BinaryNode<E> x,E item){
		if(x==null){
			return false;
		}
		if(item.compareTo(x.item)==0){
			if(x.left==null&&x.right==null){
				deleteSinglet(x);
			}else if(x.left!=null&&x.right!=null){
				x.item=removeMin(x.right);
			}else{
				if(x.parent==null){
					root=x.left!=null?x.left:x.right;
					root.parent=null;
				}
				deleteSinglet(x);
			}
			return true;
		}else if(item.compareTo(x.item)<0){
			return remove(x.left,item);
		}else{
			return remove(x.right,item);
		}
	}
	
	public E removeMin(BinaryNode<E> x){
		if(x.left==null){
			deleteSinglet(x);
			return x.item;
		}else{
			return removeMin(x.left);
		}
	}
	
	public boolean contains(E item){
		return contains(root,item);
	}
	
	public boolean contains(BinaryNode<E> x,E item){
		if(x==null){
			return false;
		}
		if(item.compareTo(x.item)==0){
			return true;
		}else if(item.compareTo(x.item)<0){
			return contains(x.left,item);
		}else{
			return contains(x.right,item);
		}
	}
	
	public E getMin(){
		return getMin(root).item;
	}
	
	public BinaryNode<E> getMin(BinaryNode<E> x){
		if(x.left==null){
			return x;
		}else{
			return getMin(x.left);
		}
	}
	
	public E getMAX(){
		return getMax(root).item;
	}
	
	public BinaryNode<E> getMax(BinaryNode<E> x){
		if(x.right==null){
			return x;
		}else{
			return getMax(x.right);
		}
	}
	
	public BinaryNode<E> getRightLeft(BinaryNode<E> x){
		if(x.parent==null||x.parent.left==x){
			return x.parent;
		}else{
			return getRightLeft(x.parent);
		}
	}
	
	public E getRoot(){
		return root.item;
	}
	
	public void print(){
		print(root,0);
	}
	
	public void print(BinaryNode<E> x,int m){
		if(x==null){
			for(int i=0;i<m;i++){
				System.out.print("    ");
			}
			System.out.println("null");
			return;
		}
		for(int i=0;i<m;i++){
			System.out.print("    ");
		}
		System.out.println(x.item);
		print(x.left,m+1);
		print(x.right,m+1);
		
	}
	
	public Iterator<E> Iterator(){
		return new TreeIte();
	}
	
	private class BinaryNode<E extends Comparable<? super E>>{
		private E item;
		private BinaryNode<E> parent;
		private BinaryNode<E> left;
		private BinaryNode<E> right;
		private int height;
		private int balance;
		
		public BinaryNode(){
			
		}
		
		public BinaryNode(BinaryNode<E> parent,E item){
			this.item=item;
			this.parent=parent;
			this.left=null;
			this.right=null;
			this.height=0;
			this.balance=0;
		}
		
	}
	
	class TreeIte implements Iterator<E>{
		private BinaryNode<E> first=getMin(root);
		private BinaryNode<E> last=getMax(root);
		private BinaryNode<E> cursor=first;
		
		private boolean flag=false;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(!flag&&cursor!=last){
				return true;
			}else if(!flag&&cursor==last){
				flag=true;
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			BinaryNode<E> temp=cursor;
			if(cursor.right!=null){
				cursor=getMin(cursor.right);
			}else{
				cursor=getRightLeft(cursor);
			}
			return temp.item;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r=new Random();
		TreeSet<Integer> ts=new TreeSet<>();
		//int[] a={6,5,8,8,3,6,8,7,6,9};
		for(int i=0;i<10;i++){
			//int k=a[i];
			int k=r.nextInt(10);
			ts.add(k);
			System.out.print(k+",");
		}
		System.out.println();
		System.out.println(ts.toList());
		System.out.println(ts.getRoot());
		//ts.print();
		//System.out.println(ts.getRoot());
		//System.out.println(ts.contains(5));
		System.out.println(ts.remove(8));
		System.out.println(ts.toList());
		//ts.print();
		Iterator<Integer> ite=ts.Iterator();
		while(ite.hasNext()){
			System.out.print(ite.next());
		}

	}
	

}
