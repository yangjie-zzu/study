package com.blog.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value="/loginedSocket")
public class LoginedSocket {
	
	private static Logger logger=Logger.getLogger(LoginedSocket.class);
	
	@OnOpen
	public void onOpen(Session session){
		logger.info("打开连接："+session.getId());
	}
	
	@OnClose
	public void onClose(Session session){
		logger.info("关闭连接："+session.getId());
	}
	
	@OnMessage
	public void onMessage(String message,Session session) throws IOException{
	}
	
	@OnError
	public void onError(Session session,Throwable error){
		logger.info("连接错误："+session.getId());
		error.printStackTrace();
	}

}
