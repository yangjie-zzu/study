package com.blog.util;

import org.junit.Test;

public class TestSringFifth {
	
	@Test
	public void testGetFifth(){
		String str="<p><img src='/ueditor/jsp/upload/image/20180119/1516341375039029630.jpg' title='1516341375039029630.jpg' alt='201610271638088451.jpg'/></p>";
		String pattern="<[^<>]*>";
		System.out.println(StringFifth.getFifth(str, pattern));
	}

}
