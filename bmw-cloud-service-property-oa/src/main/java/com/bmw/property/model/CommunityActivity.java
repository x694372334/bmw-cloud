package com.bmw.property.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.stylefeng.guns.core.node.ZTreeNode;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 小区活动
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-25
 */
@TableName("community_activity")
public class CommunityActivity extends Model<CommunityActivity> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	private Integer eId;
	
	
	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	/**
	 * 活动名称
	 */
	@TableField("activity_name")
	private String activityName;
	/**
	 * 发起人
	 */
	@TableField("initiator_id")
	private Integer initiatorId;
	/**
	 * 发起时间
	 */
	@TableField("pub_time")
	private Date pubTime;
	/**
	 * 审核人
	 */
	@TableField("verifier_id")
	private Integer verifierId;
	/**
	 * 审核时间
	 */
	@TableField("verify_time")
	private Date verifyTime;
	/**
	 * 活动正文
	 */
	private String content;
	/**
	 * 1: 投票 2：报名
	 */
	@TableField("activity_type")
	private Integer activityType;
	/**
	 * 1 草稿、 2 审核中、 3 审核通过、 4 已驳回、 5 已发送、 6 已删除。
	 */
	private Integer status;

	private String initiatorName;

	private String verifierName;
	
	private String verifyReply;

	private String statusName;

	// 已阅读人数
	private int readCount;
	// 已阅读比例
	private double readCountRate;
	// 报名人数
	private int signUpCount;
	// 投票同意人数
	private int voteStatusYesCount;
	// 投票反对人数
	private int voteStatusNoCount;
	//小区活动类型
	private String activityTypeName;
	
	//点赞人数
	private int favourStatusCount;
	
	Integer neighborhoodIds[];
	
	private List<ZTreeNode> treeList;
	
	List<String> neighborhoodNames;
	
	private String taskId;
	
	
	public String getVerifyReply() {
		return verifyReply;
	}

	public void setVerifyReply(String verifyReply) {
		this.verifyReply = verifyReply;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getFavourStatusCount() {
		return favourStatusCount;
	}

	public void setFavourStatusCount(int favourStatusCount) {
		this.favourStatusCount = favourStatusCount;
	}

	public List<String> getNeighborhoodNames() {
		return neighborhoodNames;
	}

	public void setNeighborhoodNames(List<String> neighborhoodNames) {
		this.neighborhoodNames = neighborhoodNames;
	}


	public List<ZTreeNode> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<ZTreeNode> treeList) {
		this.treeList = treeList;
	}

	public Integer[] getNeighborhoodIds() {
		return neighborhoodIds;
	}

	public void setNeighborhoodIds(Integer[] neighborhoodIds) {
		this.neighborhoodIds = neighborhoodIds;
	}

	public void setVoteStatusNoCount(int voteStatusNoCount) {
		this.voteStatusNoCount = voteStatusNoCount;
	}

	public String getActivityTypeName() {
		return activityTypeName;
	}

	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public double getReadCountRate() {
		return readCountRate;
	}

	public void setReadCountRate(double readCountRate) {
		this.readCountRate = readCountRate;
	}

	public int getSignUpCount() {
		return signUpCount;
	}

	public void setSignUpCount(int signUpCount) {
		this.signUpCount = signUpCount;
	}

	public int getVoteStatusYesCount() {
		return voteStatusYesCount;
	}

	public void setVoteStatusYesCount(int voteStatusYesCount) {
		this.voteStatusYesCount = voteStatusYesCount;
	}

	public int getVoteStatusNoCount() {
		return voteStatusNoCount;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public String getVerifierName() {
		return verifierName;
	}

	public void setVerifierName(String verifierName) {
		this.verifierName = verifierName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
	@TableField("edit_man")
	private String editMan;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(Integer initiatorId) {
		this.initiatorId = initiatorId;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Integer getVerifierId() {
		return verifierId;
	}

	public void setVerifierId(Integer verifierId) {
		this.verifierId = verifierId;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
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

	public String getEditMan() {
		return editMan;
	}

	public void setEditMan(String editMan) {
		this.editMan = editMan;
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
		return "CommunityActivity{" + "id=" + id + ", activityName=" + activityName + ", initiatorId=" + initiatorId
				+ ", pubTime=" + pubTime + ", verifierId=" + verifierId + ", verifyTime=" + verifyTime + ", content="
				+ content + ", activityType=" + activityType + ", status=" + status + ", createManId=" + createManId
				+ ", createMan=" + createMan + ", createTime=" + createTime + ", editManId=" + editManId + ", editMan="
				+ editMan + ", editTime=" + editTime + ", isDelete=" + isDelete + "}";
	}
}
