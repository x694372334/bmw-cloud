package com.bmw.property.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 小区公告
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-19
 */
@TableName("community_bulletin")
public class CommunityBulletin extends Model<CommunityBulletin> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;
	private String content;
	@TableField("initiator_id")
	private Integer initiatorId;
	@TableField("pub_time")
	private Date pubTime;
	@TableField("verifier_id")
	private Integer verifierId;
	@TableField("verify_time")
	private Date verifyTime;

	private String initiatorName;

	private String verifierName;

	private Integer[] buildingIds;

	private List<String> buildingNames;

	private List<ZTreeNode> treeList;

	private String statusName;

	private Integer eId;

	private String verifyReply;
	
	private String taskId;
	

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public String getVerifyReply() {
		return verifyReply;
	}

	public void setVerifyReply(String verifyReply) {
		this.verifyReply = verifyReply;
	}

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	/**
	 * 1: 草稿 2: 审核中 3: 审核通过 4: 已驳回 5: 已发送 6: 已驳回
	 */
	private Integer status;

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

	public String getEditMan() {
		return editMan;
	}

	public void setEditMan(String editMan) {
		this.editMan = editMan;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer[] getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(Integer[] buildingIds) {
		this.buildingIds = buildingIds;
	}

	public List<String> getBuildingNames() {
		return buildingNames;
	}

	public void setBuildingNames(List<String> buildingNames) {
		this.buildingNames = buildingNames;
	}

	public List<ZTreeNode> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<ZTreeNode> treeList) {
		this.treeList = treeList;
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

	@Override
	public String toString() {
		return "CommunityBulletin{" + "id=" + id + ", title=" + title + ", content=" + content + ", initiatorId="
				+ initiatorId + ", pubTime=" + pubTime + ", verifierId=" + verifierId + ", verifyTime=" + verifyTime
				+ ", status=" + status + ", createManId=" + createManId + ", createMan=" + createMan + ", createTime="
				+ createTime + ", editManId=" + editManId + ", editTime=" + editTime + ", isDelete=" + isDelete + "}";
	}
}
