package com.bmw.property.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.common.utils.DataEntity;

import java.io.Serializable;

/**
 * 企业信息
 * @author zw
 * @since 2018-06-25
 */
@TableName("enterprise_info")
public class EnterpriseInfo extends DataEntity<EnterpriseInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "e_id", type = IdType.AUTO)
    private Integer eId;
    /**
     * 企业名称
     */
    @TableField("enterprise_name")
    private String enterpriseName;
    /**
     * 企业简称
     */
    @TableField("short_name")
    private String shortName;

	/**
     * 企业地址
     */
    @TableField("enterprise_address")
    private String enterpriseAddress;
    /**
     * 企业类型（1：企业   2：物业）
     */
    @TableField("enterprise_type")
    private String enterpriseType;
    /**
     * 企业法人
     */
    @TableField("enterprise_legal_person")
    private String enterpriseLegalPerson;
    /**
     * 企业电话
     */
    @TableField("enterprise_phone")
    private String enterprisePhone;
    /**
     * 工作范围
     */
    @TableField("work_scope")
    private String workScope;
    /**
     * 规章制度
     */
    @TableField("enterprise_rules")
    private String enterpriseRules;
    /**
     * 审核状态，0,审核中，1审核通过，2审核不通过
     */
    @TableField("audit_status")
    private Integer auditStatus;
    /**
     * 父企业id
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 营业执照
     */
    @TableField("e_license")
    private String eLicense;
    /**
     * 企业官网
     */
    @TableField("e_website")
    private String eWebsite;
    /**
     * 企业简介
     */
    @TableField("e_description")
    private String eDescription;

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    
    public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress;
    }

    public String getEnterpriseLegalPerson() {
        return enterpriseLegalPerson;
    }

    public void setEnterpriseLegalPerson(String enterpriseLegalPerson) {
        this.enterpriseLegalPerson = enterpriseLegalPerson;
    }

    public String getEnterprisePhone() {
        return enterprisePhone;
    }

    public void setEnterprisePhone(String enterprisePhone) {
        this.enterprisePhone = enterprisePhone;
    }

    public String getWorkScope() {
        return workScope;
    }

    public void setWorkScope(String workScope) {
        this.workScope = workScope;
    }

    public String getEnterpriseRules() {
        return enterpriseRules;
    }

    public void setEnterpriseRules(String enterpriseRules) {
        this.enterpriseRules = enterpriseRules;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String geteLicense() {
        return eLicense;
    }

    public void seteLicense(String eLicense) {
        this.eLicense = eLicense;
    }

    public String geteWebsite() {
        return eWebsite;
    }

    public void seteWebsite(String eWebsite) {
        this.eWebsite = eWebsite;
    }

    public String geteDescription() {
        return eDescription;
    }

    public void seteDescription(String eDescription) {
        this.eDescription = eDescription;
    }

    public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	@Override
    public String toString() {
        return "EnterpriseInfo{" +
        "eId=" + eId +
        ", enterpriseName=" + enterpriseName +
        ", enterpriseAddress=" + enterpriseAddress +
        ", enterpriseLegalPerson=" + enterpriseLegalPerson +
        ", enterprisePhone=" + enterprisePhone +
        ", workScope=" + workScope +
        ", enterpriseRules=" + enterpriseRules +
        ", auditStatus=" + auditStatus +
        ", parentId=" + parentId +
        ", eLicense=" + eLicense +
        ", eWebsite=" + eWebsite +
        ", eDescription=" + eDescription +
        "}";
    }
}
