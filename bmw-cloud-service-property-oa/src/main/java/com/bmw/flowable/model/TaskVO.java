package com.bmw.flowable.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-23
 */
@TableName("act_ru_task")
public class TaskVO extends Model<TaskVO> {

    private static final long serialVersionUID = 1L;

    
    private String id;
    
    private Integer rev;
    
    private String executionId;
   
    private String procInstId;
    
    private String procDefId;
    
    private String scopeId;
    
    private String subScopeId;
   
    private String scopeType;
  
    private String scopeDefinitionId;
   
    private String name;

    private String parentTaskId;

    private String description;
   
    private String taskDefKey;

    private String owner;

    private String assignee;

    private String delegation;

    private Integer priority;

    private Date createTime;

    private Date dueDate;

    private String category;
  
    private Integer suspensionState;

    private String tenantId;
   
    private String formKey;

    private Date claimTime;
    
    private Integer isCountEnabled;
   
    private Integer varCount;

    private Integer idLinkCount;

    private String businessKey;
    //审批页面url
    private String url;
    //业务数据ID
    private String businessId;
    //部署流程名称
    private String deploymentName;
    
    /**
     * 发起人名称
     */
    private String userName;
    
    /**
     * 发起人ID
     */
    private Integer userId;

    /**
     * 业务类型
     */
    private Integer businessTypeNum;
    
    /**
     * 流程描述
     */
    private String descr;
    
	
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBusinessTypeNum() {
		return businessTypeNum;
	}

	public void setBusinessTypeNum(Integer businessTypeNum) {
		this.businessTypeNum = businessTypeNum;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getSubScopeId() {
        return subScopeId;
    }

    public void setSubScopeId(String subScopeId) {
        this.subScopeId = subScopeId;
    }

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    public String getScopeDefinitionId() {
        return scopeDefinitionId;
    }

    public void setScopeDefinitionId(String scopeDefinitionId) {
        this.scopeDefinitionId = scopeDefinitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(Integer suspensionState) {
        this.suspensionState = suspensionState;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

    public Integer getIsCountEnabled() {
        return isCountEnabled;
    }

    public void setIsCountEnabled(Integer isCountEnabled) {
        this.isCountEnabled = isCountEnabled;
    }

    public Integer getVarCount() {
        return varCount;
    }

    public void setVarCount(Integer varCount) {
        this.varCount = varCount;
    }

    public Integer getIdLinkCount() {
        return idLinkCount;
    }

    public void setIdLinkCount(Integer idLinkCount) {
        this.idLinkCount = idLinkCount;
    }
    
    

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	
	public String getDeploymentName() {
		return deploymentName;
	}

	public void setDeploymentName(String deploymentName) {
		this.deploymentName = deploymentName;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RuTask{" +
        "id=" + id +
        ", rev=" + rev +
        ", executionId=" + executionId +
        ", procInstId=" + procInstId +
        ", procDefId=" + procDefId +
        ", scopeId=" + scopeId +
        ", subScopeId=" + subScopeId +
        ", scopeType=" + scopeType +
        ", scopeDefinitionId=" + scopeDefinitionId +
        ", name=" + name +
        ", parentTaskId=" + parentTaskId +
        ", description=" + description +
        ", taskDefKey=" + taskDefKey +
        ", owner=" + owner +
        ", assignee=" + assignee +
        ", delegation=" + delegation +
        ", priority=" + priority +
        ", createTime=" + createTime +
        ", dueDate=" + dueDate +
        ", category=" + category +
        ", suspensionState=" + suspensionState +
        ", tenantId=" + tenantId +
        ", formKey=" + formKey +
        ", claimTime=" + claimTime +
        ", isCountEnabled=" + isCountEnabled +
        ", varCount=" + varCount +
        ", idLinkCount=" + idLinkCount +
        "}";
    }
}
