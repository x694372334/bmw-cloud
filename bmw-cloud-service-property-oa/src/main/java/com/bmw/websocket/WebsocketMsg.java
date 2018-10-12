package com.bmw.websocket;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 接收推送消息的实体类
 * 
 * @author liuwsh
 *
 */
public class WebsocketMsg {
	// 消息内容,不能为空
	private String msg;

	// 发送类型:发给指定用户，发给多个用户，发给指定角色，发给多个角色，发给所有用户,不能为空
	private String sendType;

	// 接收消息的用户id
	private String toUserId;

	// 接收消息的角色id
	private String toRoleId;

	// 接收消息的多个用户id集合
	private List<String> toUserIds;

	// 接收消息的多个角色id集合
	private List<String> toRoleIds;

	// 是否发送离线消息
	private Boolean sendLeave;

	// 准备发送离线消息的时间
	private Long firstSendTime;

	// 离线消息失效时间，默认为30min
	private Long deadline;

	// 是否指定具体时间点发送
	private Boolean sendAtTime;

	// 指定发送的时间点
	private Date sendTime;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg == null ? null : msg.trim();
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType == null ? null : sendType.trim();
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId == null ? null : toUserId.trim();
	}

	public String getToRoleId() {
		return toRoleId;
	}

	public void setToRoleId(String toRoleId) {
		this.toRoleId = toRoleId == null ? null : toRoleId.trim();
	}

	public List<String> getToUserIds() {
		return toUserIds;
	}

	public void setToUserIds(List<String> toUserIds) {
		this.toUserIds = toUserIds;
	}

	public List<String> getToRoleIds() {
		return toRoleIds;
	}

	public void setToRoleIds(List<String> toRoleIds) {
		this.toRoleIds = toRoleIds;
	}

	public Boolean getSendLeave() {
		return null == sendLeave ? false : sendLeave;
	}

	public void setSendLeave(Boolean sendLeave) {
		this.sendLeave = null == sendLeave ? false : sendLeave;
	}

	public Long getFirstSendTime() {
		return firstSendTime;
	}

	public void setFirstSendTime(Long firstSendTime) {
		this.firstSendTime = firstSendTime;
	}

	// 默认过期时间为30min
	public Long getDeadline() {
		return null == deadline ? 1000 * 60 * 30L : deadline;
	}

	public void setDeadline(Long deadline) {
		this.deadline = deadline;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Boolean getSendAtTime() {
		return null == sendAtTime ? false : sendAtTime;
	}

	public void setSendAtTime(Boolean sendAtTime) {
		this.sendAtTime = null == sendAtTime ? false : sendAtTime;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
