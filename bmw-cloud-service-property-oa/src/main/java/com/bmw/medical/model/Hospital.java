package com.bmw.medical.model;

import java.util.Date;

/**
 * 医院表
 * 
 * @author jmy
 * @date 2018-05-28 10:30:45
 */
public class Hospital {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 区域编码
	 */
	private String medicalAreaCode;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 医院名称
	 */
	private String name;
	/**
	 * 医院级别
	 */
	private String level;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 简介
	 */
	private String description;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 地址
	 */
	private String address;
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
	 * 区域编码
	 */
	public String getMedicalAreaCode() {
		return medicalAreaCode;
	}

	/**
	 * 区域编码
	 */
	public void setMedicalAreaCode(String medicalAreaCode) {
		this.medicalAreaCode = medicalAreaCode == null ? null : medicalAreaCode.trim();
	}

	/**
	 * 医院名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 医院名称
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 医院级别
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 医院级别
	 */
	public void setLevel(String level) {
		this.level = level == null ? null : level.trim();
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
	 * 简介
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 简介
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * 电话
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 电话
	 */
	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	/**
	 * 地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 地址
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
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