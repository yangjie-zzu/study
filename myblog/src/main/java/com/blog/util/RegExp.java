package com.blog.util;

import java.util.regex.Pattern;

public class RegExp {
	
	public static Boolean match(String str,String pattern){
		return Pattern.compile(pattern).matcher(str).matches();
	}

}
