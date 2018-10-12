package com.bmw.zuul.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 服务结果模型
 * @author lyl
 * 2016年11月8日
 */
public class Result {

	/**
	 * 状态码
	 */
	@JSONField(ordinal = 1)
	private int statusCode;
	
	/**
	 * 消息
	 */
	@JSONField(ordinal = 2)
	private String message;
	
	
	/**
	 * 内容集合
	 */
	@JSONField(ordinal = 3)
	private Object items = null;
	
	
	/**
	 * 条目总数
	 */
	@JSONField(ordinal = 6)
	private Long totalCount = null;
	
	
	/**
	 * 分页页数，从1开始
	 */
	@JSONField(ordinal = 4)
	private Integer pageNumber = null;
	
	
	/**
	 * 每页显示数
	 */
	@JSONField(ordinal = 5)
	private Integer pageSize = null;


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getItems() {
		return items;
	}


	public void setItems(Object items) {
		this.items = items;
	}


	public Long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}


	public Integer getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}


	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


}
