package com.blog.dao;

import java.util.List;
import java.util.Map;

import com.blog.entity.Article;

public interface ArticleDao {
	
	public Integer addArticle(Article article);
	
	public List<Article> getArticleList(Map<String, Object> queryMap);
	
	public int getNumOfArticles(Map<String,Object> queryMap);
	
	public Article getArticleById(Integer id);

}
