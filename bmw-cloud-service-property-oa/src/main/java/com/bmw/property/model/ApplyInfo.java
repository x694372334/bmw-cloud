package com.bmw.property.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 审批申请单
 * </p>
 *
 * @author Jinmy123
 * @since 2018-07-19
 */
public class ApplyInfo extends Model<ApplyInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 申请单编号
     */
    @TableField("a_code")
    private String aCode;
    /**
     * 申请类型
     */
    @TableField("a_type")
    private String aType;
    /**
     * 审请项目
     */
    @TableField("a_id")
    private Integer aId;
    /**
     * 申请内容
     */
    @TableField("a_details")
    private String aDetails;
    /**
     * 申请人
     */
    @TableField("a_user")
    private Integer aUser;
    /**
     * 申请时间
     */
    @TableField("a_time")
    private Date aTime;
    /**
     * 审批人
     */
    @TableField("ea_user")
    private Integer eaUser;
    /**
     * 审批时间
     */
    @TableField("ea_time")
    private Date eaTime;
    /**
     * 审批结果
     */
    @TableField("ea_result")
    private Integer eaResult;
    /**
     * 审批意见
     */
    @TableField("ea_opinion")
    private String eaOpinion;
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
     * 是否删除1未删除2已删除
     */
    @TableField("is_delete")
    private Integer isDelete;
    
    /**
     * 工作流id
     */
    @TableField("taskId")
    private String taskId;

    
    /**
     * 用户名
     * */
    private String name ;
    
    /**申请事项名称*/
    @TableField("a_name")
    private String aName;
    
    /**审批人*/
    private String approvalUserName;
    /**审批人id*/
    private String approvalUserId;
    
    
    
    public String getApprovalUserName() {
		return approvalUserName;
	}

	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public String getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(String approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getaCode() {
        return aCode;
    }

    public void setaCode(String aCode) {
        this.aCode = aCode;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getaDetails() {
        return aDetails;
    }

    public void setaDetails(String aDetails) {
        this.aDetails = aDetails;
    }

    public Integer getaUser() {
        return aUser;
    }

    public void setaUser(Integer aUser) {
        this.aUser = aUser;
    }

    public Date getaTime() {
        return aTime;
    }

    public void setaTime(Date aTime) {
        this.aTime = aTime;
    }

    public Integer getEaUser() {
        return eaUser;
    }

    public void setEaUser(Integer eaUser) {
        this.eaUser = eaUser;
    }

    public Date getEaTime() {
        return eaTime;
    }

    public void setEaTime(Date eaTime) {
        this.eaTime = eaTime;
    }

    public Integer getEaResult() {
        return eaResult;
    }

    public void setEaResult(Integer eaResult) {
        this.eaResult = eaResult;
    }

    public String getEaOpinion() {
        return eaOpinion;
    }

    public void setEaOpinion(String eaOpinion) {
        this.eaOpinion = eaOpinion;
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
        return "ApplyInfo{" +
        "id=" + id +
        ", aCode=" + aCode +
        ", aType=" + aType +
        ", aId=" + aId +
        ", aDetails=" + aDetails +
        ", aUser=" + aUser +
        ", aTime=" + aTime +
        ", eaUser=" + eaUser +
        ", eaTime=" + eaTime +
        ", eaResult=" + eaResult +
        ", eaOpinion=" + eaOpinion +
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
