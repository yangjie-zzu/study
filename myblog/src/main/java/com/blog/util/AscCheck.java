package com.blog.util;

public class AscCheck {
	
	public static boolean check(String str){
		for(int i:str.toCharArray()){
			if(i<0||i>128){
				return false;
			}
		}
		return true;
	}
	
	public static int length(String str){
		int length=0;
		for(int i:str.toCharArray()){
			if(i>=0&&i<=128){
				length++;
			}else{
				length+=2;
			}
		}
		return length;
	}

}
