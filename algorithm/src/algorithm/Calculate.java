package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculate {
	
	private static char[] ope={'+','-','*','/'};
	private static Stack<Character> opeStack;
	private static Stack<Integer> numStack;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 输入字符串，读取一行
		 * 如果是数字，则读取后续数字，直到非数字，转化为整数压入数字栈
		 * 如果是操作符，则压入操作符栈
		 * 3+2*4
		 * 324*+
		 * 324
		 * *+
		 */
		try(Scanner in=new Scanner(System.in)){
			String line=in.nextLine();
			Calculate calc=new Calculate();
			
			opeStack=calc.new Stack<>(line.length());
			numStack=calc.new Stack<>(line.length());
			
			for(int i=0;i<line.length();i++){
				char c=line.charAt(i);
				if(isNum(c)){
					int start=i;
					int end=getNumEnd(line,start);
					numStack.push(transferNum(line,start,end));
					i=end;
				}else if(isOperation(c)){
					pushOpe(c);
				}else if(c=='('){
					opeStack.push(c);
				}else if(c==')'){
					while(opeStack.top()!='('){
						popOpe();
					}
					opeStack.pop();
				}
			}
			
			while(!opeStack.isEmpty()){
				popOpe();
			}
			
			System.out.println(numStack.top());
		}

	}
	
	public static boolean isNum(char c){
		if(c>='0'&&c<='9'){
			return true;
		}
		return false;
	}
	
	public static boolean isOperation(char c){
		for(int i=0;i<ope.length;i++){
			if(ope[i]==c){
				return true;
			}
		}
		return false;
	}
	
	public static int getNumEnd(String str,int start){
		do{
			start++;
		}while(start<str.length()&&isNum(str.charAt(start)));
		return --start;
	}
	
	public static int transferNum(String str,int start,int end){
		int temp=0;
		for(int i=start;i<=end;i++){
			int k=str.charAt(i)-0x30;
			temp=temp*10+k;
		}
		return temp;
	}
	
	public static Integer cal(int num1,int num2,char c){
		switch(c){
		case '+':
			return num1+num2;
		case '-':
			return num1-num2;
		case '*':
			return num1*num2;
		case '/':
			return num1/num2;
		default:
			return null;
		}
	}

	public static void popOpe(){
		char c=opeStack.pop();
		int num1=numStack.pop();
		int num2=numStack.pop();
		numStack.push(cal(num2,num1,c));
	}
	
	public static Boolean pushAbled(char c){
		if(opeStack.isEmpty()){
			return true;
		}
		switch(c){
		case '+':;
		case '-':{
			switch(opeStack.top()){
			case '(':
				return true;
			default:
				return false;
			}
		}
		case '*':;
		case '/':{
			switch(opeStack.top()){
			case '*':;
			case '/':
				return false;
			default:
				return true;
			}
		}
		default:
			return null;
		}
	}
	
	public static void pushOpe(char c){
		while(!pushAbled(c)){
			popOpe();
		};
		opeStack.push(c);
	}
	
	public class Stack<E>{
		private List<E> s;
		private int top=-1;
		
		public Stack(int n){
			s=new ArrayList<E>(n);
		}
		
		public boolean isEmpty(){
			if(top==-1){
				return true;
			}
			return false;
		}
		
		public void push(E c){
			s.add(++top, c);
		}
		
		public E pop(){
			return s.get(top--);
		}
		
		public E top(){
			return s.get(top);
		}
	}

}
