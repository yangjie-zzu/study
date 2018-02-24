package com.blog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFifth {
	
	public static String getFifth(String str,String pattern){
		if(str==null){
			return "";
		}
		String reStr="";
		int n=1;
		int i=0;
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(str);
		do{
			if(m.find(i)){
				String regStr=m.group();
				int k=str.indexOf(regStr, i)-i>50-n?50-n:str.indexOf(regStr, i);
				String temp=str.substring(i, k);
				i=str.indexOf(regStr)+regStr.length();
				reStr+=temp;
			}else{
				int j=i+(50-n)>str.length()?str.length():i+(50-n);
				reStr+=str.substring(i, j);
				i=j;
			}
			n=reStr.length();
		}while(n<50&&i<str.length());
		return reStr;
	}

}
