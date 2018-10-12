package com.bmw.property.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 意见反馈
 * </p>
 *
 * @author liuwsh
 * @since 2018-07-30
 */
@TableName("o_feedback")
public class OFeedback {

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 所属小区Id
	 */
	@TableField("houses_id")
	private Integer housesId;

	/**
	 * 所属小区
	 */
	private String nName;

	/**
	 * 反馈人id
	 */
	@TableField("o_id")
	private Integer oId;

	/**
	 * 企业Id
	 */
	@TableField("e_id")
	private Integer eId;

	/**
	 * 反馈人名字
	 */
	private String oName;

	/**
	 * 反馈意见
	 */
	private String opinion;

	/**
	 * 反馈用户id
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 反馈用户名字
	 */
	private String userName;

	/**
	 * 物业回复用户名字
	 */
	private String uName;

	/**
	 * 反馈时间
	 */
	@TableField("feedback_time")
	private Date feedbackTime;

	/**
	 * 物业处理结果
	 */
	@TableField("handling_result")
	private String handlingResult;

	/**
	 * 物业处理时间
	 */
	@TableField("handling_time")
	private Date handlingTime;
	/**
	 * 物业回复用户
	 */
	@TableField("u_id")
	private Integer uId;
	/**
	 * 状态（0:未回复 1:已回复 ）
	 */
	private Integer status;
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
	 * 修改人
	 */
	@TableField("edit_man")
	private String editMan;
	/**
	 * 修改时间
	 */
	@TableField("edit_time")
	private Date editTime;
	/**
	 * 是否删除，0 删除 1 正常
	 */
	@TableField("is_delete")
	private Integer isDelete;
	/**
	 * 所属小区
	 */
	private Neighborhood neighborhood;

	public String getCreateMan() {
		return createMan;
	}

	public Integer getCreateManId() {
		return createManId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getEditMan() {
		return editMan;
	}

	public Integer getEditManId() {
		return editManId;
	}

	public Date getEditTime() {
		return editTime;
	}

	public Integer geteId() {
		return eId;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public String getHandlingResult() {
		return handlingResult;
	}

	public Date getHandlingTime() {
		return handlingTime;
	}

	public Integer getHousesId() {
		return housesId;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public String getnName() {
		return nName;
	}

	public Integer getoId() {
		return oId;
	}

	public String getoName() {
		return oName;
	}

	public String getOpinion() {
		return opinion;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getuId() {
		return uId;
	}

	public String getuName() {
		return uName;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public void setCreateManId(Integer createManId) {
		this.createManId = createManId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setEditMan(String editMan) {
		this.editMan = editMan;
	}

	public void setEditManId(Integer editManId) {
		this.editManId = editManId;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public void setHandlingResult(String handlingResult) {
		this.handlingResult = handlingResult;
	}

	public void setHandlingTime(Date handlingTime) {
		this.handlingTime = handlingTime;
	}

	public void setHousesId(Integer housesId) {
		this.housesId = housesId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public void setoId(Integer oId) {
		this.oId = oId;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
