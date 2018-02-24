package com.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entity.Comment;
import com.blog.service.CommentService;

@Controller
public class CommentController {
	
	@Resource
	private CommentService commentService;
	
	@RequestMapping(value="comment")
	@ResponseBody
	public String addComment(@RequestBody Comment comment,HttpSession session){
		return commentService.addComment(comment, session);
	}

}
