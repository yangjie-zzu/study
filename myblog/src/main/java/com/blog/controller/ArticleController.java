package com.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entity.Article;
import com.blog.entity.Page;
import com.blog.entity.QueryCondition;
import com.blog.service.ArticleService;

@Controller
public class ArticleController {
	
	public static Logger logger=Logger.getLogger(ArticleController.class);
	
	@Resource
	private ArticleService articleService;
	
	@RequestMapping(value="write")
	@ResponseBody
	public String addArticle(@RequestBody Article article,HttpSession session){
		return articleService.addArticle(article,session);
	}
	
	@RequestMapping(value="showArticles")
	@ResponseBody
	public Page getArticlePage(@RequestBody QueryCondition queryCondition){
		return articleService.getArticles(queryCondition);
	}
	
	@RequestMapping(value="article/{id}")
	public String getArticleById(@PathVariable("id") Integer id,Model model){
		Article article=articleService.getArticleById(id);
		model.addAttribute(article);
		return "article";
		
	}

}
