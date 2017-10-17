package com.blog.service;

import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.blog.dao.BloggerDao;
import com.blog.entity.Blogger;
import com.blog.util.Md5Encod;

@Service("bloggerService")
public class BloggerService {
	
	private static Logger logger=Logger.getLogger(BloggerService.class);
	
	@Resource
	private BloggerDao bloggerDao;
	
	public String addBlogger(Blogger blogger) throws NoSuchAlgorithmException{
		logger.info(blogger.getUserName()+"注册");
		String userName=blogger.getUserName();
		String password=blogger.getPassword();
		if(userName==null||userName==""||password==null||password==""){
			return "用户输入密码不能为空";
		}
		if(!bloggerDao.isExistUserName(blogger.getUserName())){
			blogger.setPassword(Md5Encod.encodingByMd5(blogger.getPassword()));
			bloggerDao.insertBlogger(blogger);
			return "注册成功";
		}else{
			return "用户名已存在";
		}
	}
	
	public String bloggerLogin(String userName,String password,HttpSession session) throws NoSuchAlgorithmException{
		if(userName==null||userName==""||password==null||password==""){
			return "用户名和密码不能为空";
		}
		if(!bloggerDao.isExistUserName(userName)){
			return "用户不存在,<a href='register.html'>注册</a>";
		}
		if(password!=null&&password!=""){
			password=Md5Encod.encodingByMd5(password);
		}
		if(bloggerDao.isExistBlogger(userName, password)){
			session.setAttribute("blogger", bloggerDao.getBloggerByUserName(userName));
			return "登录成功";
		}else{
			return "用户名或密码错误";
		}
	}

}
