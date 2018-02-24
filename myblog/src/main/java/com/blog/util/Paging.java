package com.blog.util;

import com.blog.entity.Page;
import com.blog.entity.QueryCondition;

public class Paging {
	
	public static void setPage(Page page,QueryCondition queryCondition,int totalArticles){
		int showNum=queryCondition.getShowNum();
		int totalPageNum=totalArticles%showNum==0?totalArticles/showNum:(totalArticles/showNum)+1;
		boolean isLastPage=queryCondition.getPageNum()<totalPageNum?false:true;
		int currentPageNum=isLastPage?totalPageNum:queryCondition.getPageNum();
		page.setIsLastPage(isLastPage);
		page.setPageNum(currentPageNum);
	}

}
