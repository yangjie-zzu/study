package com.blog.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entity.Blogger;
import com.blog.service.BloggerService;

@Controller
public class BloggerController {
	
	private static Logger logger=Logger.getLogger(BloggerController.class);
	
	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping(value="reg")
	@ResponseBody
	public String regBlogger(@RequestBody Blogger blogger) throws IOException, NoSuchAlgorithmException{
		return bloggerService.addBlogger(blogger);
	}

	@RequestMapping(value="reged")
	@ResponseBody
	public String reged(@RequestParam(value="username") String userName){
		return bloggerService.bloggerReged(userName);
	}
	
	@RequestMapping(value="emailreged")
	@ResponseBody
	public String emailreged(@RequestParam(value="email")String email){
		return bloggerService.emailreged(email);
	}
	
	@RequestMapping(value="login")
	@ResponseBody
	public String bloggerLogin(@RequestParam(value="username",required=false) String userName,
			@RequestParam(value="password",required=false) String password,
			HttpServletRequest request,
			HttpSession session) throws NoSuchAlgorithmException {
		if(userName==null&&password==null){
			Cookie[] cookies=request.getCookies();
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("username")){
					userName=cookie.getValue();
				}
				if(cookie.getName().equals("password")){
					password=cookie.getValue();
				}
			}
		}
		logger.info(userName+password);
		return bloggerService.bloggerLogin(userName, password, session);
	}
	
	@RequestMapping(value="logined")
	@ResponseBody
	public String logined(HttpSession session){
		return bloggerService.logined(session);
	}
	

}
