package com.bmw.property.model;

import java.io.Serializable;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 收房验房
 * </p>
 *
 * @author zhangchengjun123
 * @since 2018-07-09
 */
@TableName("room_check_record")
public class RoomCheckRecord extends Model<RoomCheckRecord> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer eId;
	
	
	
	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	/**
	 * 业主姓名
	 */
	@TableField("owner_name")
	private String ownerName;
	/**
	 * 房间id
	 */
	@TableField("room_id")
	private Integer roomId;
	/**
	 * 联系方式
	 */
	private String contact;
	/**
	 * 验房时间
	 */
	@TableField("check_time")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date checkTime;
	/**
	 * 负责人ID
	 */
	@TableField("principal_user_id")
	private Integer principalUserId;
	/**
	 * 负责人用户名
	 */
	@TableField("principal_user_name")
	private String principalUserName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建人id
	 */
	@TableField("create_man_id")
	private Integer createManId;
	/**
	 * 创建人
	 */
	@TableField("create_man")
	private String createMan;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;
	/**
	 * 修改人id
	 */
	@TableField("edit_man_id")
	private Integer editManId;
	/**
	 * 修改时间
	 */
	@TableField("edit_time")
	private Date editTime;
	/**
	 * 是否删除 0 删除 1 未删
	 */
	@TableField("is_delete")
	private Integer isDelete;

	private Integer status;

	private String statusName;

	private String roomNum;
	
	private String checkTimeString;
	
	private String editMan;
	
	
	

	public String getEditMan() {
		return editMan;
	}

	public void setEditMan(String editMan) {
		this.editMan = editMan;
	}

	public String getCheckTimeString() {
		return checkTimeString;
	}

	public void setCheckTimeString(String checkTimeString) {
		this.checkTimeString = checkTimeString;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getPrincipalUserId() {
		return principalUserId;
	}

	public void setPrincipalUserId(Integer principalUserId) {
		this.principalUserId = principalUserId;
	}

	public String getPrincipalUserName() {
		return principalUserName;
	}

	public void setPrincipalUserName(String principalUserName) {
		this.principalUserName = principalUserName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateManId() {
		return createManId;
	}

	public void setCreateManId(Integer createManId) {
		this.createManId = createManId;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getEditManId() {
		return editManId;
	}

	public void setEditManId(Integer editManId) {
		this.editManId = editManId;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RoomCheckRecord{" + "id=" + id + ", ownerName=" + ownerName + ", roomId=" + roomId + ", contact="
				+ contact + ", checkTime=" + checkTime + ", principalUserId=" + principalUserId + ", principalUserName="
				+ principalUserName + ", remark=" + remark + ", createManId=" + createManId + ", createMan=" + createMan
				+ ", createTime=" + createTime + ", editManId=" + editManId + ", editTime=" + editTime + ", isDelete="
				+ isDelete + "}";
	}
}
