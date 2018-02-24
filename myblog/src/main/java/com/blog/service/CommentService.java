package com.blog.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.blog.dao.CommentDao;
import com.blog.entity.Blogger;
import com.blog.entity.Comment;

@Service("commentService")
public class CommentService {
	
	@Resource
	private CommentDao commentdao;
	
	public String addComment(Comment comment, HttpSession session){
		Blogger blogger=(Blogger) session.getAttribute("blogger");
		if(blogger==null){
			return "{\"stat\":\"logout\"}";
		}else if(comment.getContent()!=null&&comment.getContent()!=""&&comment.getArticle().getId()!=null){
			comment.setAuthor(blogger);
			if(commentdao.addComment(comment)!=0){
				return "{\"stat\":\"success\"}";
			}else{
				return "{\"stat\":\"fail\"}";
			}
		}else{
			return "{\"stat\":\"empty\"}";
		}
	}

}
