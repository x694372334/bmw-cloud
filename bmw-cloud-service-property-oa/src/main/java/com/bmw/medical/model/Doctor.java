package com.bmw.medical.model;

import java.util.Date;

/**
 * 医生表
 * 
 * @author jmy
 * @date 2018-06-07 03:55:06
 */
public class Doctor {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 医生姓名
	 */
	private String name;
	/**
	 * 擅长
	 */
	private String genius;
	/**
	 * 简介
	 */
	private String synopsis;
	/**
	 * 职务
	 */
	private String dutiesId;
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
	private Date createTime;
	/**
	 * 修改人id
	 */
	private Integer editManId;
	/**
	 * 修改人
	 */
	private String editMan;
	/**
	 * 修改时间
	 */
	private Date editTime;
	/**
	 * 是否删除
	 */
	private Integer isDelete;
	/**
	 * 科室
	 */
	private String department;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 头像
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 编码
	 */
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	 * 医生姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 医生姓名
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 擅长
	 */
	public String getGenius() {
		return genius;
	}

	/**
	 * 擅长
	 */
	public void setGenius(String genius) {
		this.genius = genius == null ? null : genius.trim();
	}

	/**
	 * 简介
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * 简介
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis == null ? null : synopsis.trim();
	}

	/**
	 * 职务
	 */
	public String getDutiesId() {
		return dutiesId;
	}

	/**
	 * 职务
	 */
	public void setDutiesId(String dutiesId) {
		this.dutiesId = dutiesId == null ? null : dutiesId.trim();
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
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
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
	 * 修改人
	 */
	public String getEditMan() {
		return editMan;
	}

	/**
	 * 修改人
	 */
	public void setEditMan(String editMan) {
		this.editMan = editMan == null ? null : editMan.trim();
	}

	/**
	 * 修改时间
	 */
	public Date getEditTime() {
		return editTime;
	}

	/**
	 * 修改时间
	 */
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	/**
	 * 是否删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	/**
	 * 是否删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}