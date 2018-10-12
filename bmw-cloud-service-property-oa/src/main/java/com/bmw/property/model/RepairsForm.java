package com.bmw.property.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-03
 */
@TableName("repairs_form")
public class RepairsForm extends Model<RepairsForm> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	private Integer eId;
	private Integer appraiseScore;
	
	private String imgServerUrl;
	
	private String[] oldImgUrl;

	public String[] getOldImgUrl() {
		return oldImgUrl;
	}
	public void setOldImgUrl(String[] oldImgUrl) {
		this.oldImgUrl = oldImgUrl;
	}
	public String getImgServerUrl() {
		return imgServerUrl;
	}
	public void setImgServerUrl(String imgServerUrl) {
		this.imgServerUrl = imgServerUrl;
	}
	public Integer getAppraiseScore() {
		return appraiseScore;
	}
	public void setAppraiseScore(Integer appraiseScore) {
		this.appraiseScore = appraiseScore;
	}

	private List<String> contentImgList;

	public List<String> getContentImgList() {
		return contentImgList;
	}

	public void setContentImgList(List<String> contentImgList) {
		this.contentImgList = contentImgList;
	}

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	/**
	 * 维修地址
	 */
	private String repairsAddr;
	private String contentText;
	private String contentImg;
	private String contentVoice;
	private Integer appUserId;
	private String appUserName;
	private Integer roomId;
	/**
	 * 业主或者租户姓名（前台手输的）
	 */
	private String ownerName;
	private String ownerPhoneNo;
	/**
	 * 1:业主 2:管家 3:管理员
	 */
	@TableField("initiator_type")
	private Integer initiatorType;
	/**
	 * 管家姓名
	 */
	@TableField("steward_user_name")
	private String stewardUserName;
	/**
	 * 管家用户id
	 */
	@TableField("steward_user_id")
	private Integer stewardUserId;
	/**
	 * 管理员姓名
	 */
	@TableField("operator_user_name")
	private String operatorUserName;
	/**
	 * 管理员用户id
	 */
	@TableField("operator_user_id")
	private Integer operatorUserId;
	/**
	 * 预计上门时间
	 */
	@TableField("probably_arrive_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date probablyArriveTime;
	/**
	 * 实际上门时间
	 */
	@TableField("actual_arrive_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date actualArriveTime;
	/**
	 * 预计完成时间
	 */
	@TableField("probably_complete_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date probablyCompleteTime;
	/**
	 * 实际完成时间
	 */
	@TableField("actual_complete_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date actualCompleteTime;
	/**
	 * 1、已发单 2、已预约待上门查看 3、已接单维修中 4、维修完成待评价 5、已评价 6、已取消 7、已反馈住户
	 */
	private Integer status;

	/**
	 * 评价用户名
	 */
	private String appraiseUserName;

	/**
	 * 评价用户id
	 */
	private Integer appraiseUserId;

	/**
	 * 评价内容
	 */
	private String appraiseContent;

	/**
	 * 驳回原因
	 */
	private String refuseReason;

	private String editMan;

	public String getEditMan() {
		return editMan;
	}

	public void setEditMan(String editMan) {
		this.editMan = editMan;
	}

	public String getAppraiseUserName() {
		return appraiseUserName;
	}

	public void setAppraiseUserName(String appraiseUserName) {
		this.appraiseUserName = appraiseUserName;
	}

	public Integer getAppraiseUserId() {
		return appraiseUserId;
	}

	public void setAppraiseUserId(Integer appraiseUserId) {
		this.appraiseUserId = appraiseUserId;
	}

	public String getAppraiseContent() {
		return appraiseContent;
	}

	public void setAppraiseContent(String appraiseContent) {
		this.appraiseContent = appraiseContent;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

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

	private String roomNum;

	private String initiatorName;

	private String isOvertime;

	private String statusName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public String getIsOvertime() {
		return isOvertime;
	}

	public void setIsOvertime(String isOvertime) {
		this.isOvertime = isOvertime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRepairsAddr() {
		return repairsAddr;
	}

	public void setRepairsAddr(String repairsAddr) {
		this.repairsAddr = repairsAddr;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getContentImg() {
		return contentImg;
	}

	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}

	public String getContentVoice() {
		return contentVoice;
	}

	public void setContentVoice(String contentVoice) {
		this.contentVoice = contentVoice;
	}

	public Integer getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}

	public String getAppUserName() {
		return appUserName;
	}

	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerPhoneNo() {
		return ownerPhoneNo;
	}

	public void setOwnerPhoneNo(String ownerPhoneNo) {
		this.ownerPhoneNo = ownerPhoneNo;
	}

	public Integer getInitiatorType() {
		return initiatorType;
	}

	public void setInitiatorType(Integer initiatorType) {
		this.initiatorType = initiatorType;
	}

	public String getStewardUserName() {
		return stewardUserName;
	}

	public void setStewardUserName(String stewardUserName) {
		this.stewardUserName = stewardUserName;
	}

	public Integer getStewardUserId() {
		return stewardUserId;
	}

	public void setStewardUserId(Integer stewardUserId) {
		this.stewardUserId = stewardUserId;
	}

	public String getOperatorUserName() {
		return operatorUserName;
	}

	public void setOperatorUserName(String operatorUserName) {
		this.operatorUserName = operatorUserName;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	public Date getProbablyArriveTime() {
		return probablyArriveTime;
	}

	public void setProbablyArriveTime(Date probablyArriveTime) {
		this.probablyArriveTime = probablyArriveTime;
	}

	public Date getActualArriveTime() {
		return actualArriveTime;
	}

	public void setActualArriveTime(Date actualArriveTime) {
		this.actualArriveTime = actualArriveTime;
	}

	public Date getProbablyCompleteTime() {
		return probablyCompleteTime;
	}

	public void setProbablyCompleteTime(Date probablyCompleteTime) {
		this.probablyCompleteTime = probablyCompleteTime;
	}

	public Date getActualCompleteTime() {
		return actualCompleteTime;
	}

	public void setActualCompleteTime(Date actualCompleteTime) {
		this.actualCompleteTime = actualCompleteTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		return "RepairsForm{" + "id=" + id + ", repairsAddr=" + repairsAddr + ", contentText=" + contentText
				+ ", contentImg=" + contentImg + ", contentVoice=" + contentVoice + ", appUserId=" + appUserId
				+ ", appUserName=" + appUserName + ", roomId=" + roomId + ", ownerName=" + ownerName + ", ownerPhoneNo="
				+ ownerPhoneNo + ", initiatorType=" + initiatorType + ", stewardUserName=" + stewardUserName
				+ ", stewardUserId=" + stewardUserId + ", operatorUserName=" + operatorUserName + ", operatorUserId="
				+ operatorUserId + ", probablyArriveTime=" + probablyArriveTime + ", actualArriveTime="
				+ actualArriveTime + ", probablyCompleteTime=" + probablyCompleteTime + ", actualCompleteTime="
				+ actualCompleteTime + ", status=" + status + ", createManId=" + createManId + ", createMan="
				+ createMan + ", createTime=" + createTime + ", editManId=" + editManId + ", editTime=" + editTime
				+ ", isDelete=" + isDelete + "}";
	}
}
