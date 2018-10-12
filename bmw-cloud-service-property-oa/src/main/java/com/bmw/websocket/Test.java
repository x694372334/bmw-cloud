package com.bmw.websocket;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Test {

	public static void main(String[] args) {
		send();
	}

	public static void send() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			URI uri = URI.create("ws://10.10.10.47:8099/websocket/111");
			Session session = container.connectToServer(WebSocketClient.class, uri);

			WebsocketMsg websocketMsg = new WebsocketMsg();

			websocketMsg.setMsg("您好");
			websocketMsg.setToUserId("1");
//			List toUserIds = new ArrayList();
//			toUserIds.add("74");
//			toUserIds.add("9");
//			toUserIds.add("28");
//			toUserIds.add("15");
//			websocketMsg.setToUserIds(toUserIds);
//			
			websocketMsg.setSendType("toSingle");
//			websocketMsg.setSendLeave(true);
//			websocketMsg.setDeadline(60000L);
			websocketMsg.setSendAtTime(true);
//			websocketMsg.setToRoleId("1");
//			List<String> toRoleIds = new ArrayList<String>();
//			toRoleIds.add("2");
//			toRoleIds.add("4");
//			toRoleIds.add("6");
//			toRoleIds.add("8");
//			websocketMsg.setToRoleIds(toRoleIds);

			Calendar calendar = Calendar.getInstance();
			// 指定01:00:00点执行
			calendar.set(Calendar.HOUR_OF_DAY, 16);// 时
			calendar.set(Calendar.MINUTE, 10);// 分
			calendar.set(Calendar.SECOND, 0);// 秒
			Date date = calendar.getTime();
			websocketMsg.setSendTime(date);

			String string = WebSocketUtil.send(websocketMsg.toString());

		} catch (DeploymentException | IOException e) {
			e.printStackTrace();
		}
	}

}
