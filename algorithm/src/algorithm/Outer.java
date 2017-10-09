package algorithm;

public class Outer {
	private String inner="dfs";
	private static String name="outer";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Outer outer=new Outer();
		System.out.println(outer.name);
		Outer.Inner.print();

	}
	
	public String getName(){
		return name;
	}
	
	static class Inner{
		private String name="inner";
		
		public void prin(){
			System.out.println(name);
		}
		
		public static void print(){
			System.out.println(Outer.name);
		}
	}

}
