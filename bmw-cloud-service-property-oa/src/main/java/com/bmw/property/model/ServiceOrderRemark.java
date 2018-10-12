package com.bmw.property.model;

import java.sql.Timestamp;

/**
 * 特色服务订单备注
 * 
 * @author zhangchengjun
 * @date 2018-07-19 01:04:52
 */
public class ServiceOrderRemark {

	/**
	 * 备注id
	 */
	private Integer remarkId;

	/**
	 * 订单id
	 */
	private Integer orderId;

	/**
	 * 备注时间
	 */
	private Timestamp remarkTime;

	/**
	 * 1 业主 2 物业人员
	 */
	private Short userType;

	/**
	 * 备注人
	 */
	private Integer userId;

	/**
	 * 备注人姓名
	 */
	private String userName;

	/**
	 * 创建人id
	 */
	private Integer createManId;

	/**
	 * 创建人
	 */
	private String createMan;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 修改人id
	 */
	private Integer editManId;

	/**
	 * 修改时间
	 */
	private Timestamp editTime;

	/**
	 * 是否删除 0 删除 1 未删
	 */
	private Integer isDelete;

	/**
	 * 备注内容
	 */
	private String remarkContent;

	private String remarkName;

	private Short currentStatus;

	public Short getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Short currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	/**
	 * 备注id
	 */
	public Integer getRemarkId() {
		return remarkId;
	}

	/**
	 * 备注id
	 */
	public void setRemarkId(Integer remarkId) {
		this.remarkId = remarkId;
	}

	/**
	 * 订单id
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 订单id
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 备注时间
	 */
	public Timestamp getRemarkTime() {
		return remarkTime;
	}

	/**
	 * 备注时间
	 */
	public void setRemarkTime(Timestamp remarkTime) {
		this.remarkTime = remarkTime;
	}

	/**
	 * 1 业主 2 物业人员
	 */
	public Short getUserType() {
		return userType;
	}

	/**
	 * 1 业主 2 物业人员
	 */
	public void setUserType(Short userType) {
		this.userType = userType;
	}

	/**
	 * 备注人
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 备注人
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 备注人姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 备注人姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * 创建人id
	 */
	public Integer getCreateManId() {
		return createManId;
	}

	/**
	 * 创建人id
	 */
	public void setCreateManId(Integer createManId) {
		this.createManId = createManId;
	}

	/**
	 * 创建人
	 */
	public String getCreateMan() {
		return createMan;
	}

	/**
	 * 创建人
	 */
	public void setCreateMan(String createMan) {
		this.createMan = createMan == null ? null : createMan.trim();
	}

	/**
	 * 创建时间
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改人id
	 */
	public Integer getEditManId() {
		return editManId;
	}

	/**
	 * 修改人id
	 */
	public void setEditManId(Integer editManId) {
		this.editManId = editManId;
	}

	/**
	 * 修改时间
	 */
	public Timestamp getEditTime() {
		return editTime;
	}

	/**
	 * 修改时间
	 */
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	/**
	 * 是否删除 0 删除 1 未删
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	/**
	 * 是否删除 0 删除 1 未删
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 备注内容
	 */
	public String getRemarkContent() {
		return remarkContent;
	}

	/**
	 * 备注内容
	 */
	public void setRemarkContent(String remarkContent) {
		this.remarkContent = remarkContent == null ? null : remarkContent.trim();
	}
}