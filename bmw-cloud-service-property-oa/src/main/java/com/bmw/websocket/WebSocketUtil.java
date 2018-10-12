package com.bmw.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.common.utils.CommonUtils;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * @ServerEndpoint 可以把当前类变成websocket服务类
 */
@Component
@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocketUtil {
	static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	/**
	 * 获取当前连接数
	 */
	private static synchronized int getOnlineCount() {
		return onlineCount;
	}

	/**
	 * 当前连接数加一
	 */
	private static synchronized void addOnlineCount() {
		WebSocketUtil.onlineCount++;
	}

	/**
	 * 当前连接数减一
	 */
	private static synchronized void subOnlineCount() {
		WebSocketUtil.onlineCount--;
		if (WebSocketUtil.onlineCount < 0) {
			WebSocketUtil.onlineCount = 0;
		}
	}

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据，session这个全局变量有多份，所以不能用static修饰
	private Session session;

	// concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketUtil对象。
	private static CopyOnWriteArraySet<WebSocketUtil> webSocketUtilSet = new CopyOnWriteArraySet<WebSocketUtil>();

	// Session池：用于记录用户ID与session的关系
	private static Map<String, Session> sessionPool = new HashMap<String, Session>();
	// SessionId池：用于记录sessionID与用户ID的关系
	private static Map<String, String> sessionIds = new HashMap<String, String>();

	// leaveMsgs：用于存储离线消息 key：用户id；value:消息实体
	private static Map<String, WebsocketMsg> leaveMsgs = new HashMap<String, WebsocketMsg>();

	/**
	 * 连接建立成功时调用的方法
	 * 
	 * @param session 当前会话session
	 * @throws IOException
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam(value = "userId") String userId) throws IOException {
		// 绑定session到当前WebSocketUtil实例
		this.session = session;
		// 绑定用户ID与Session
		sessionPool.put(userId, session);
		// 绑定SessionID与用户ID
		sessionIds.put(session.getId(), userId);
		// 存储当前WebSocketUtil实例
		webSocketUtilSet.add(this);
		// 连接数加1
		addOnlineCount();
		logger.info("服务端有新连接加入，用户ID 为{}，当前连接数为{}：", userId, getOnlineCount());
		// 发送离线消息
		sendLeaveMsg(userId);
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		// 从set中删除自身
		webSocketUtilSet.remove(this);
		// 连接数减1
		subOnlineCount();
		// 获取关闭连接的用户ID
		String userId = sessionIds.get(this.session.getId());
		// 移除该用户的相关信息
		sessionPool.remove(userId);
		sessionIds.remove(this.session.getId());

		logger.info("用户ID为{}的连接关闭！当前在线人数为{}", userId, getOnlineCount());
	}

	/**
	 * 发生错误时调用
	 * 
	 * @throws IOException
	 */
	@OnError
	public void onError(Session session, Throwable error) throws IOException {
		// 从set中删除自身
		webSocketUtilSet.remove(this);
		// 连接数减1
		subOnlineCount();
		// 获取关闭连接的用户ID
		String userId = sessionIds.get(session.getId());
		// 移除该用户的相关信息
		sessionPool.remove(userId);
		sessionIds.remove(this.session.getId());

		logger.error("服务端连接异常，发生异常的用户ID 为{}", userId);
	}

	/**
	 * 发送消息给用户
	 * 
	 * @param session 连接session
	 * @param message 要发送的消息
	 * @param userId  接收消息的用户ID
	 * @throws IOException
	 */
	private static void sendMessage(Session session, WebsocketMsg websocketMsg, String userId) throws IOException {
		// 判断用户是否在线
		if (null != sessionPool.get(userId)) {
			// 在线
			// 获取要发送的消息内容
			String message = websocketMsg.getMsg();
			if (StringUtils.isNotBlank(message) && session != null && session.isOpen()) {
				session.getBasicRemote().sendText(message);
				logger.info("服务端成功发送一条消息：{} 到id为{}的用户 ", message, userId);
			}
		} else {
			// 不在线
			if (websocketMsg.getSendLeave()) {
				websocketMsg.setFirstSendTime(new Date().getTime());
				leaveMsgs.put(userId, websocketMsg);
			}
		}
	}

	/**
	 * 发送离线消息，主要是用于频繁断线
	 * 
	 * @param userId
	 * @throws IOException
	 */
	private void sendLeaveMsg(String userId) throws IOException {
		WebsocketMsg msg = leaveMsgs.get(userId);
		if (null != msg) {
			// 检查是否超时
			Long firstSendTime = msg.getFirstSendTime();
			if (new Date().getTime() - firstSendTime < msg.getDeadline()) {
				//发送离线消息
				sendMessage(sessionPool.get(userId), msg, userId);
			}else {
				//移除过期的消息
				leaveMsgs.remove(userId);
			}
		}
	}

	/**
	 * 收到客户端消息
	 * 
	 * @param message 客户端发送过来的消息
	 * @param session 当前会话session
	 * @throws IOException
	 */
	@OnMessage
	public static void onMessage(Session session, String msg) throws IOException {
		WebsocketMsg websocketMsg = JSON.parseObject(msg, WebsocketMsg.class);
		logger.info("接收到来自客户端的消息：{}", websocketMsg.toString());
//		if (null != websocketMsg) {
//			// 判断是否是定时任务
//			if (null == websocketMsg.getSendTime()) {
//				beforeSend(websocketMsg);
//			} else {
//				// 执行定时任务
//				doTimerSend(websocketMsg);
//			}
//		}
	}

	/**
	 * 发送消息前的处理事件：用于判断发送类型、分支等
	 * 
	 * @param websocketMsg
	 * @throws IOException
	 */
	private static void beforeSend(WebsocketMsg websocketMsg) throws IOException {
		// 发送类型:发给指定用户，发给多个用户，发给指定角色，发给多个角色，发给所有用户
		String sendType = websocketMsg.getSendType();
		switch (sendType) {
		case "toAll":// 发给所有用户
			sendToAll(websocketMsg);
			break;
		case "toSingle":// 发给指定用户
			sendToSingle(websocketMsg.getToUserId(), websocketMsg);
			break;
		case "toMany":// 发给多个用户
			sendToMany(websocketMsg.getToUserIds(), websocketMsg);
			break;
		case "toSingleRole":// 发给指定角色
			sendToRole(websocketMsg.getToRoleId(), websocketMsg);
			break;
		case "toManyRoles":// 发给多个角色
			sendToManyRoles(websocketMsg.getToRoleIds(), websocketMsg);
		}
	}

	/**
	 * 发送消息给所有人
	 * 
	 * @param msg
	 * @throws IOException
	 */
	private static void sendToAll(WebsocketMsg websocketMsg) throws IOException {
		String msg = websocketMsg.getMsg();
		for (WebSocketUtil item : webSocketUtilSet) {
			sendMessage(item.session, websocketMsg, sessionIds.get(item.session.getId()));
		}
		logger.info("服务端成功群发一条消息：{}", msg);
	}

	/**
	 * 发送消息给指定用户
	 * 
	 * @param msg
	 */
	private static void sendToSingle(String toUserId, WebsocketMsg websocketMsg) throws IOException {
		sendMessage(sessionPool.get(toUserId), websocketMsg, toUserId);
	}

	/**
	 * 发送消息给多人
	 * 
	 * @param msg
	 */
	private static void sendToMany(List<String> toUserIds, WebsocketMsg websocketMsg) throws IOException {
		for (String toUserId : toUserIds) {
			sendMessage(sessionPool.get(toUserId), websocketMsg, toUserId);
		}
	}

	/**
	 * 发送消息给指定角色
	 * 
	 * @param msg
	 */
	private static void sendToRole(String toRoleId, WebsocketMsg websocketMsg) throws IOException {
		sendToMany(getUsersByRoleId(toRoleId), websocketMsg);
	}

	/**
	 * 发送消息给多个角色（可能有人重复发送，待调整）
	 * 
	 * @param msg
	 */
	private static void sendToManyRoles(List<String> toRoleIds, WebsocketMsg websocketMsg) throws IOException {
		for (String toRoleId : toRoleIds) {
			sendToRole(toRoleId, websocketMsg);
		}
	}

	/**
	 * 根据角色id获取用户id
	 * 
	 * @param msg
	 */
	private static List<String> getUsersByRoleId(String toRoleId) {
		// 待实现
		return null;
	}

	/**
	 * 指定时间点发送消息
	 * 
	 * @param websocketMsg
	 */
	private static void doTimerSend(WebsocketMsg websocketMsg) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				try {
					beforeSend(websocketMsg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, websocketMsg.getSendTime()
//			若需循环执行，需增加以下代码，需要提供一个停止条件，暂时没有做
//			, period
		);
	}

	/**
	 * 校验msg的合法性
	 * 
	 * @param msg
	 * @return
	 */
	private static String validateMsg(String msg) {
		WebsocketMsg websocketMsg = JSON.parseObject(msg, WebsocketMsg.class);
		String sendType = websocketMsg.getSendType();
		if (StringUtils.isBlank(websocketMsg.getSendType())) {
			return "属性：发送类型（sendType）不能为空";
		}
		switch (sendType) {
		case "toSingle":// 发给指定用户
			if (StringUtils.isBlank(websocketMsg.getToUserId())) {
				return "属性：接收消息的用户id（toUserId）不能为空";
			}
			break;
		case "toMany":// 发给多个用户
			List<String> toUserIds = websocketMsg.getToUserIds();
			if (!(null != toUserIds && toUserIds.size() > 0)) {
				return "属性：接收消息的用户id集合（toUserIds）不能为空";
			}
			break;
		case "toSingleRole":// 发给指定角色
			if (StringUtils.isBlank(websocketMsg.getToRoleId())) {
				return "属性：接收消息的角色id（toRoleId）不能为空";
			}
			break;
		case "toManyRoles":// 发给多个角色
			List<String> toRoleIds = websocketMsg.getToRoleIds();
			if (!(null != toRoleIds && toRoleIds.size() > 0)) {
				return "属性：接收消息的角色id集合（toRoleIds）不能为空";
			}
		}
		System.out.println(websocketMsg.getSendAtTime());
		if (websocketMsg.getSendAtTime()) {
			if (null == websocketMsg.getSendTime()) {
				return "属性：指定时间点发送时，具体的发送时间（sendTime）不能为空";
			} else {
				if (websocketMsg.getSendTime().getTime() < new Date().getTime()) {
					return "属性：具体的发送时间（sendTime）不能小于当前时间";
				}
			}
		}

		return null;
	}

	/**
	 * 开放给外部的调用接口
	 * 
	 * @param msg WebsocketMsg类的json串
	 * @throws IOException
	 */
	public static String send(String msg) throws IOException {
		String error = validateMsg(msg);
		if (StringUtils.isBlank(error)) {
			WebsocketMsg websocketMsg = JSON.parseObject(msg, WebsocketMsg.class);
			logger.info("接收到来自客户端的消息：{}", websocketMsg.toString());
			if (null != websocketMsg) {
				// 判断是否是定时任务
				if (websocketMsg.getSendAtTime() && null != websocketMsg.getSendTime()) {
					// 执行定时任务
					doTimerSend(websocketMsg);
				} else {
					beforeSend(websocketMsg);
				}
			}
			return "";
		} else {
			return error;
		}
	}

}