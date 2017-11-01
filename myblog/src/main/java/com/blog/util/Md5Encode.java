package com.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encode {
	
	public static String encodingByMd5(String str) throws NoSuchAlgorithmException{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		md5.update(str.getBytes());
		byte[] strbyte=md5.digest();
		return bintohex(strbyte);
	}
	
	private static String bintohex(byte[] strbyte){
		char[] strchar=new char[strbyte.length*2];
		int i;
		int j=0;
		for(i=0;i<strbyte.length;i++){
			byte b=strbyte[i];
			int s=0;
			s+=(1&b)*1;
			b=(byte) (b>>1);
			s+=(1&b)*2;
			b=(byte) (b>>1);
			s+=(1&b)*4;
			b=(byte) (b>>1);
			s+=(1&b)*8;
			strchar[j++]=dectohex(s);
			s=0;
			s+=(1&b)*1;
			b=(byte) (b>>1);
			s+=(1&b)*2;
			b=(byte) (b>>1);
			s+=(1&b)*4;
			b=(byte) (b>>1);
			s+=(1&b)*8;
			strchar[j++]=dectohex(s);
		}
		return new String(strchar);
	}
	
	private static char dectohex(int a){
		switch(a){
		case 10:return 'A';
		case 11:return 'B';
		case 12:return 'C';
		case 13:return 'D';
		case 14:return 'E';
		case 15:return 'F';
		default:return (char) (a+0x30);
		}
	}
	
	@SuppressWarnings("unused")
	private static void print(byte b){
		for(int i=0;i<8;i++){
			int s=(000000001&b)==0?0:1;
			System.out.print(s);
			b=(byte) (b>>1);
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException{
		String str="123456";
		System.out.println(Md5Encode.encodingByMd5(str));
	}

}
