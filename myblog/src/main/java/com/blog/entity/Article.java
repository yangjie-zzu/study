package com.blog.entity;

import java.util.Date;

public class Article {
	
	private Integer id;
	private String title;
	private String content;
	private Date publishtime;
	private Date lasttime;
	private Blogger publisher;
	private Type type;
	private Integer hit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Date getLasttime() {
		return lasttime;
	}
	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}
	public Blogger getPublisher() {
		return publisher;
	}
	public void setPublisher(Blogger publisher) {
		this.publisher = publisher;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}

}
