package com.bmw.complaintadvice.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 投诉建议
 * </p>
 *
 * @author zhangt123
 * @since 2018-06-21
 */
@TableName("t_complaint_advice")
public class ComplaintAdvice extends Model<ComplaintAdvice> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 企业ID
     */
    @TableField("e_id")
    private Integer eId;
    /**
     * 业主姓名
     */
    @TableField("owner_name")
    private String ownerName;
    /**
     * 房屋号
     */
    @TableField("neighborhoods_no")
    private String neighborhoodsNo;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 投诉内容
     */
    @TableField("complaint_content")
    private String complaintContent;
    /**
     * 投诉时间
     */
    @TableField("complaint_time")
    private Date complaintTime;
    /**
     * 状态（0:未回复 1:已回复 ）
     */
    private Integer status;
    /**
     * 回复时间
     */
    @TableField("reply_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date replyTime;
    /**
     * 回复人id
     */
    @TableField("reply_user_id")
    private Integer replyUserId;
    /**
     * 回复人姓名
     */
    @TableField("reply_user_name")
    private String replyUserName;
    /**
     * 回复内容
     */
    @TableField("reply_content")
    private String replyContent;
    /**
     * 创建人ID
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
     * 是否删除
     */
    @TableField("is_delete")
    private Integer isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getNeighborhoodsNo() {
        return neighborhoodsNo;
    }

    public void setNeighborhoodsNo(String neighborhoodsNo) {
        this.neighborhoodsNo = neighborhoodsNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    public Date getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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
        return "ComplaintAdvice{" +
        "id=" + id +
        ", ownerName=" + ownerName +
        ", neighborhoodsNo=" + neighborhoodsNo +
        ", phone=" + phone +
        ", complaintContent=" + complaintContent +
        ", complaintTime=" + complaintTime +
        ", status=" + status +
        ", replyTime=" + replyTime +
        ", replyUserId=" + replyUserId +
        ", replyUserName=" + replyUserName +
        ", replyContent=" + replyContent +
        ", createManId=" + createManId +
        ", createMan=" + createMan +
        ", createTime=" + createTime +
        ", editManId=" + editManId +
        ", editMan=" + editMan +
        ", editTime=" + editTime +
        ", isDelete=" + isDelete +
        "}";
    }
}
