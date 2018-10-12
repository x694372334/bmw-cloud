package com.bmw.websocket;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ClientEndpoint
public class WebSocketClient {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Session session;

	/**
	 * 打开连接
	 * 
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
	}

	/**
	 * 接收消息
	 * 
	 * @param text
	 */
	@OnMessage
	public void onMessage(String message) {
		logger.info("接收到来自服务端的消息{}", message);
	}

	/**
	 * 异常处理
	 * 
	 * @param throwable
	 */
	@OnError
	public void onError(Throwable error) {
		logger.error("客户端连接异常，sessionId 为{}", this.session.getId());
		error.printStackTrace();
	}

	/**
	 * 关闭连接
	 */
	@OnClose
	public void onClose() throws IOException {
		session.close();
		logger.info("客户端连接关闭！关闭的sessionId为{", this.session.getId());
	}

	/**
	 * 主动发送消息
	 */
	public void send(String message) {
		session.getAsyncRemote().sendText(message);
		logger.info("客户端成功发送一条消息：{}", message);
	}

}
