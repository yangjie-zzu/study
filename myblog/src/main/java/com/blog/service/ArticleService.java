package com.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.blog.dao.ArticleDao;
import com.blog.entity.Article;
import com.blog.entity.Blogger;
import com.blog.entity.Page;
import com.blog.entity.QueryCondition;
import com.blog.util.Paging;
import com.blog.util.StringFifth;

@Service("articleService")
public class ArticleService {
	
	public static Logger logger=Logger.getLogger(ArticleService.class);
	
	@Resource
	private ArticleDao articleDao;
	
	public String addArticle(Article article,HttpSession session){
		Blogger blogger=(Blogger) session.getAttribute("blogger");
		if(blogger==null){
			return "{\"stat\":\"logout\"}";
		}else if(article.getTitle()!=null&&article.getTitle()!=""&&article.getContent()!=null&&article.getContent()!=""){
			article.setPublisher(blogger);
			if(articleDao.addArticle(article)!=0){
				return "{\"stat\":\"success\"}";
			}else{
				return "{\"stat\":\"fail\"}";
			}
		}else{
			return "{\"stat\":\"empty\"}";
		}
	}
	
	public Page getArticles(QueryCondition queryCondition){
		Map<String,Object> map=new HashMap<>();
		map.put("id", queryCondition.getId());
		map.put("publisherId", queryCondition.getPublisherId());
		map.put("typeName", queryCondition.getTypeName());
		int totalArticles=articleDao.getNumOfArticles(map);
		Page page=new Page();
		Paging.setPage(page, queryCondition, totalArticles);
		map.put("start", (page.getPageNum()-1)*queryCondition.getShowNum());
		map.put("showNum", queryCondition.getShowNum());
		map.put("order", queryCondition.getOrder());
		List<Article> articles=articleDao.getArticleList(map);
		for(Article article:articles){
			article.setContent(StringFifth.getFifth(article.getContent(),"<[^<>]*>"));
		}
		page.setArticles(articles);
		return page;
	}
	
	public Article getArticleById(Integer id){
		Article article=articleDao.getArticleById(id);
		return article;
	}

}
