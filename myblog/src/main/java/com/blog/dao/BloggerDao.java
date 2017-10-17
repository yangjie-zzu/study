package com.blog.dao;

import com.blog.entity.Blogger;

public interface BloggerDao {
	
	public boolean isExistUserName(String userName);
	
	public Integer insertBlogger(Blogger blogger);
	
	public boolean isExistBlogger(String userName,String password);
	
	public Blogger getBloggerByUserName(String userName);

}
