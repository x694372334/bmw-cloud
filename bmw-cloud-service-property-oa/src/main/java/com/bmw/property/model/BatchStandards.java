package com.bmw.property.model;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.common.utils.DataEntity;

/**
 * <p>
 * 批量关联收费标准
 * </p>
 *
 * @author wangliqing123
 * @since 2018-06-28
 */
@TableName("t_batch_standards")
public class BatchStandards extends DataEntity<BatchStandards> {

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
     * 关联对象ID
     */
    @TableField("relevance_id")
    private Integer relevanceId;
    /**
     * 关联对象name
     */
    @TableField("relevance_name")
    private String relevanceName;
    /**
     * 关联范围id
     */
    @TableField("scope_id")
    private String scopeId;
    
    private String scopeNames;
    /**
     * 关联费项设置id
     */
    @TableField("cost_id")
    private Integer costId;
    /**
     * 关联费项设置名称
     */
    @TableField("cost_name")
    private String costName;
    /**
     * 收费标准id
     */
    @TableField("standard_id")
    private Integer standardId;
    /**
     * 收费标准名称
     */
    @TableField("standard_name")
    private String standardName;
    
    /**
     * 小区id
     */
    @TableField("nb_id")
    private Integer nbId;
    /**
     * 小区名称
     */
    @TableField("nb_name")
    private String nbName;
    
    /**
     * 计费开始时间
     */
    @TableField("chargeable_start_date")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp chargeableStartDate;
    /**
     * 计费结束时间
     */
    @TableField("chargeable_end_date")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp chargeableEndDate;
    
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    
    
    private String unScopeIds;
    
    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	/**
     * 备注
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

	public Integer getRelevanceId() {
        return relevanceId;
    }

    public void setRelevanceId(Integer relevanceId) {
        this.relevanceId = relevanceId;
    }

    public String getRelevanceName() {
        return relevanceName;
    }

    public void setRelevanceName(String relevanceName) {
        this.relevanceName = relevanceName;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }
    
    public String getScopeNames() {
		return scopeNames;
	}

	public void setScopeNames(String scopeNames) {
		this.scopeNames = scopeNames;
	}

	public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Integer getStandardId() {
        return standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public Integer getNbId() {
		return nbId;
	}

	public void setNbId(Integer nbId) {
		this.nbId = nbId;
	}

	public String getNbName() {
		return nbName;
	}

	public void setNbName(String nbName) {
		this.nbName = nbName;
	}

	public Timestamp getChargeableStartDate() {
        return chargeableStartDate;
    }

    public void setChargeableStartDate(Timestamp chargeableStartDate) {
        this.chargeableStartDate = chargeableStartDate;
    }

    public Timestamp getChargeableEndDate() {
        return chargeableEndDate;
    }

    public void setChargeableEndDate(Timestamp chargeableEndDate) {
        this.chargeableEndDate = chargeableEndDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getUnScopeIds() {
		return unScopeIds;
	}

	public void setUnScopeIds(String unScopeIds) {
		this.unScopeIds = unScopeIds;
	}

	@Override
    public String toString() {
        return "BatchStandards{" +
        "id=" + id +
        ", relevanceId=" + relevanceId +
        ", relevanceName=" + relevanceName +
        ", scopeId=" + scopeId +
        ", costId=" + costId +
        ", costName=" + costName +
        ", standardId=" + standardId +
        ", standardName=" + standardName +
        ", chargeableStartDate=" + chargeableStartDate +
        ", chargeableEndDate=" + chargeableEndDate +
        ", remark=" + remark +
        "}";
    }
}
