package com.blog.entity;

import java.util.Date;

public class Comment {
	
	private Integer id;
	private String content;
	private Date publishtime;
	private Blogger author;
	private Article article;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	public Blogger getAuthor() {
		return author;
	}
	public void setAuthor(Blogger author) {
		this.author = author;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}

}
