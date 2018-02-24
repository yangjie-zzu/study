package com.blog.service;

import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.blog.dao.BloggerDao;
import com.blog.entity.Blogger;
import com.blog.util.AscCheck;
import com.blog.util.Md5Encode;
import com.blog.util.RegExp;

@Service("bloggerService")
public class BloggerService {
	
	public static Logger logger=Logger.getLogger(BloggerService.class);
	
	@Resource
	private BloggerDao bloggerDao;
	
	public String addBlogger(Blogger blogger) throws NoSuchAlgorithmException{
		if(RegExp.match(blogger.getUserName(), "^[a-zA-Z0-9](((-|_)(?!(-|_)))|[a-zA-Z0-9]){2,14}[a-zA-Z0-9]$")&&
				RegExp.match(blogger.getEmail(), "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
			if(AscCheck.check(blogger.getPassword())&&blogger.getPassword().length()>=6&&blogger.getPassword().length()<=16){
				blogger.setPassword(Md5Encode.encodingByMd5(blogger.getPassword()));
				if(bloggerDao.insertBlogger(blogger)!=0){
					return "{\"flag\":true}";
				}else{
					return "{\"flag\":false,\"error\":\"插入失败\"}";
				}
			}else{
				return "{\"flag\":false,\"error\":\"非法密码\"}";
			}
		}else{
			return "{\"flag\":false,\"error\":\"非法用户名或非法邮箱\"}";
		}
	}
	
	public String bloggerReged(String userName){
		if(bloggerDao.isExistUserName(userName)){
			return "{\"flag\":true}";
		}else{
			return "{\"flag\":false}";
		}
	}
	
	public String emailreged(String email){
		if(bloggerDao.isExistEmail(email)){
			return "{\"flag\":true}";
		}else{
			return "{\"flag\":false}";
		}
	}
	
	public String bloggerLogin(String userName,String password,HttpSession session) throws NoSuchAlgorithmException{
		if(userName==null||userName==""||password==null||password==""){
			return "{\"logined\":false,\"msg\":\"用户名和密码不能为空\"}";
		}
		if(!bloggerDao.isExistUserName(userName)){
			return "{\"logined\":false,\"msg\":\"用户"+userName+"不存在,<a href='register.html'>注册</a>\"}";
		}
		if(password!=null&&password!=""){
			password=Md5Encode.encodingByMd5(password);
		}
		if(bloggerDao.isExistBlogger(userName, password)){
			session.setAttribute("blogger", bloggerDao.getBloggerByUserName(userName));
			return "{\"logined\":true,\"msg\":\""+userName+"\"}";
		}else{
			return "{\"logined\":false,\"msg\":\"用户名或密码错误\"}";
		}
	}
	
	public String logined(HttpSession session){
		Blogger blogger=(Blogger) session.getAttribute("blogger");
		if(blogger!=null){
			return "{\"logined\":true,\"msg\":\""+blogger.getUserName()+"\"}";
		}else{
			return "{\"logined\":false}";
		}
	}
	
	public void logout(HttpSession session){
		session.removeAttribute("blogger");
	}

}
