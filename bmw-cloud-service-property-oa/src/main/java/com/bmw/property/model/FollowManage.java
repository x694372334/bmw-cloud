package com.bmw.property.model;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.common.utils.DataEntity;

/**
 * 类名: FollowManage  
 * 类描述: 跟进管理实体类
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:23:14    
 */
@TableName("t_follow_manage")
public class FollowManage extends DataEntity<FollowManage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    
    /**
     * 企业ID
     */
    private Integer eId;
    
    /**
     * 业主ID
     */
    @TableField("owner_id")
    private Integer ownerId;
    /**
     * 业主姓名
     */
    @TableField("owner_name")
    private String ownerName;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 联系时间
     */
    @TableField("contact_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp contactTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date contact;
    
    private String contactTimeStr;
    /**
     * 联系事项
     */
    @TableField("contact_matters")
    private String contactMatters;
    /**
     * 接待人id
     */
    @TableField("receiver_id")
    private Integer receiverId;
    /**
     * 接待人名称
     */
    @TableField("receiver_name")
    private String receiverName;
    /**
     * 后期备忘
     */
    private String remark;
    
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

	public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getContactTime() {
        return contactTime;
    }

    public void setContactTime(Timestamp contactTime) {
        this.contactTime = contactTime;
    }
    
    public Date getContact() {
		return contact;
	}

	public void setContact(Date contact) {
		this.contact = contact;
	}

	public String getContactTimeStr() {
		return contactTimeStr;
	}

	public void setContactTimeStr(String contactTimeStr) {
		this.contactTimeStr = contactTimeStr;
	}

	public String getContactMatters() {
        return contactMatters;
    }

    public void setContactMatters(String contactMatters) {
        this.contactMatters = contactMatters;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
  

    @Override
    public String toString() {
        return "TFollowManage{" +
        "id=" + id +
        ", ownerId=" + ownerId +
        ", ownerName=" + ownerName +
        ", phone=" + phone +
        ", contactTime=" + contactTime +
        ", contactMatters=" + contactMatters +
        ", receiverId=" + receiverId +
        ", receiverName=" + receiverName +
        ", remark=" + remark +
        "}";
    }
    
}
