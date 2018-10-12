package com.bmw.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机短信模型
 * @author lyl
 * 2017年03月28日
 */
public class ShortMessageContent {
	
    /**
     * 手机号码
     */
	private List<Object> phoneNumbers = new ArrayList<Object>();
    
	/**
	 * 模板类型
	 */
	private String templeteType;

	/**
	 * 消息模板中占位符对应的参数值
	 */
	private List<Object> placeholderValues = new ArrayList<Object>();
	
	/**
	 * 最终发送内容
	 */
	private String messageContent;
	
	/**
     * 认证标识
     */
    private String token;

    /**
     * 来源IP
     */
    private String originIP;
	
	/**
	 * 兼容旧版-手机号码
	 */
	private String phoneNumber;
	
	/**
	 * 兼容旧版-短信内容
	 */
	private String phoneContent;
	
	
	public List<Object> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Object... phoneNumbers) {
		for(Object obj : phoneNumbers){
			this.phoneNumbers.add(obj);
		}
	}

	public String getTempleteType() {
		return templeteType;
	}

	public void setTempleteType(String templeteType) {
		this.templeteType = templeteType;
	}

	public List<Object> getPlaceholderValues() {
		return placeholderValues;
	}

	public void setPlaceholderValues(Object... placeholderValues) {
		for(Object obj : placeholderValues){
			this.placeholderValues.add(obj);
		}
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOriginIP() {
		return originIP;
	}

	public void setOriginIP(String originIP) {
		this.originIP = originIP;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneContent() {
		return phoneContent;
	}

	public void setPhoneContent(String phoneContent) {
		this.phoneContent = phoneContent;
	}
	
}
