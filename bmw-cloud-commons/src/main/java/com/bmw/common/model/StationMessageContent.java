package com.bmw.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 站内信模型
 * @author lyl
 * 2016年12月28日
 */
public class StationMessageContent {
	
	/**
	 * 站内信-消息接收企业
	 */
	private String enterpriseId;
	
	/**
	 * 站内信-消息接收人
	 */
	private String userId;

	/**
	 * 站内信-模板类型
	 */
	private String templeteType;

	/**
	 * 站内信-消息模板中占位符对应的参数值
	 */
	private List<Object> placeholderValues = new ArrayList<Object>();
	
	
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
}
