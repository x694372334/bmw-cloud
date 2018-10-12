package com.bmw.property.module.enterprise.domain;

import java.sql.Timestamp;


/**
 * 企业信息
 * 
 * @author zw
 * @date 2018-06-26 08:30:46
 */
public class EnterpriseInfo {


    /**
     * 主键id
     */
    private Integer eId;


    /**
     * 企业名称
     */
    private String enterpriseName;
    
    /**
     * 企业简称
     */
    private String shortName;
    
    /**
     * 企业类型（1：企业   2：物业）
     */
    private String enterpriseType;

    /**
     * 企业地址
     */
    private String enterpriseAddress;


    /**
     * 企业法人
     */
    private String enterpriseLegalPerson;


    /**
     * 企业电话
     */
    private String enterprisePhone;


    /**
     * 工作范围
     */
    private String workScope;


    /**
     * 审核状态，0,审核中，1审核通过，2审核不通过
     */
    private Integer auditStatus;


    /**
     * 父企业id
     */
    private Integer parentId;


    /**
     * 营业执照
     */
    private String eLicense;


    /**
     * 企业官网
     */
    private String eWebsite;


    /**
     * 创建人id
     */
    private Integer createManId;


    /**
     * 创建人
     */
    private String createMan;


    /**
     * 创建时间
     */
    private Timestamp createTime;


    /**
     * 修改人id
     */
    private Integer editManId;


    /**
     * 修改人
     */
    private String editMan;


    /**
     * 修改时间
     */
    private Timestamp editTime;


    /**
     * 是否删除，0 删除 1 正常
     */
    private Integer isDelete;

    /**
     * 规章制度
     */
    private String enterpriseRules;


    /**
     * 企业简介
     */
    private String eDescription;



    /**
     * 规章制度
     */
    public String getEnterpriseRules() {
        return enterpriseRules;
    }

    /**
     * 规章制度
     */
    public void setEnterpriseRules(String enterpriseRules) {
        this.enterpriseRules = enterpriseRules == null ? null : enterpriseRules.trim();
    }

    /**
     * 企业简介
     */
    public String geteDescription() {
        return eDescription;
    }

    /**
     * 企业简介
     */
    public void seteDescription(String eDescription) {
        this.eDescription = eDescription == null ? null : eDescription.trim();
    }

    /**
     * 主键id
     */
    public Integer geteId() {
        return eId;
    }

    /**
     * 主键id
     */
    public void seteId(Integer eId) {
        this.eId = eId;
    }

    /**
     * 企业名称
     */
    public String getEnterpriseName() {
        return enterpriseName;
    }

    /**
     * 企业名称
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }
    
    /**
     * 企业简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 企业简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName == null ? "" : shortName.trim();
    }

    /**
     * 企业地址
     */
    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    /**
     * 企业地址
     */
    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress == null ? null : enterpriseAddress.trim();
    }

    /**
     * 企业法人
     */
    public String getEnterpriseLegalPerson() {
        return enterpriseLegalPerson;
    }

    /**
     * 企业法人
     */
    public void setEnterpriseLegalPerson(String enterpriseLegalPerson) {
        this.enterpriseLegalPerson = enterpriseLegalPerson == null ? null : enterpriseLegalPerson.trim();
    }

    /**
     * 企业电话
     */
    public String getEnterprisePhone() {
        return enterprisePhone;
    }

    /**
     * 企业电话
     */
    public void setEnterprisePhone(String enterprisePhone) {
        this.enterprisePhone = enterprisePhone == null ? null : enterprisePhone.trim();
    }

    /**
     * 工作范围
     */
    public String getWorkScope() {
        return workScope;
    }

    /**
     * 工作范围
     */
    public void setWorkScope(String workScope) {
        this.workScope = workScope == null ? null : workScope.trim();
    }

    /**
     * 审核状态，0,审核中，1审核通过，2审核不通过
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 审核状态，0,审核中，1审核通过，2审核不通过
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 父企业id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 父企业id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 营业执照
     */
    public String geteLicense() {
        return eLicense;
    }

    /**
     * 营业执照
     */
    public void seteLicense(String eLicense) {
        this.eLicense = eLicense == null ? null : eLicense.trim();
    }

    /**
     * 企业官网
     */
    public String geteWebsite() {
        return eWebsite;
    }

    /**
     * 企业官网
     */
    public void seteWebsite(String eWebsite) {
        this.eWebsite = eWebsite == null ? null : eWebsite.trim();
    }

    /**
     * 创建人id
     */
    public Integer getCreateManId() {
        return createManId;
    }

    /**
     * 创建人id
     */
    public void setCreateManId(Integer createManId) {
        this.createManId = createManId;
    }

    /**
     * 创建人
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * 创建人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * 创建时间
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人id
     */
    public Integer getEditManId() {
        return editManId;
    }

    /**
     * 修改人id
     */
    public void setEditManId(Integer editManId) {
        this.editManId = editManId;
    }

    /**
     * 修改人
     */
    public String getEditMan() {
        return editMan;
    }

    /**
     * 修改人
     */
    public void setEditMan(String editMan) {
        this.editMan = editMan == null ? null : editMan.trim();
    }

    /**
     * 修改时间
     */
    public Timestamp getEditTime() {
        return editTime;
    }

    /**
     * 修改时间
     */
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    /**
     * 是否删除，0 删除 1 正常
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除，0 删除 1 正常
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
    
    
}