package com.blog.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entity.Blogger;
import com.blog.service.BloggerService;

@Controller
public class BloggerController {
	
	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping(value="reg")
	@ResponseBody
	public String regBlogger(@RequestParam(value="username") String userName,@RequestParam(value="password") String password,HttpServletResponse response) throws IOException, NoSuchAlgorithmException{
		Blogger blogger=new Blogger();
		blogger.setUserName(userName);
		blogger.setPassword(password);
		return bloggerService.addBlogger(blogger);
	}
	
	@RequestMapping(value="login")
	@ResponseBody
	public String bloggerLogin(@RequestParam(value="username") String userName,@RequestParam(value="password") String password,HttpSession session) throws NoSuchAlgorithmException {
		return bloggerService.bloggerLogin(userName, password, session);
	}

}
