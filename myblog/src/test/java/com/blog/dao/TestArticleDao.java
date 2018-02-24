package com.blog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.entity.Article;

public class TestArticleDao {
	
	@Test
	public void testGetArticleList(){
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		ArticleDao articleDao=ctx.getBean(ArticleDao.class);
		Map<String,Object> map=new HashMap<>();
		map.put("start", 1);
		map.put("showNum", 10);
		map.put("order", "publishtime");
		List<Article> al=articleDao.getArticleList(map);
		for(Article article:al){
			System.out.println(article.getPublishtime());
		}
		ctx.close();
	}

}
