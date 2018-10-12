package com.bmw.base.module.patient.domain;

import java.sql.Timestamp;


/**
 * 
 * 
 * @author fjm
 * @date 2018-05-09 08:40:10
 */
public class Patient {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户id
     */
    private Integer accountId;


    /**
     * 就诊人姓名
     */
    private String name;


    /**
     * 就诊人年龄
     */
    private String age;


    /**
     * 身份证
     */
    private String idcard;


    /**
     * 性别
     */
    private String sex;


    /**
     * 身份类型
     */
    private String identityType;


    /**
     * 出生日期
     */
    private String birthday;


    /**
     * 备注
     */
    private String remark;


    /**
     * 创建时间
     */
    private Timestamp createDate;


    /**
     * 创建人
     */
    private Timestamp createBy;


    /**
     * 修改时间
     */
    private Timestamp updateDate;


    /**
     * 修改人
     */
    private Timestamp updateBy;


    /**
     * 科室code
     */
    private String departmentCode;


    /**
     * 医院code
     */
    private String hospitalCode;



    /**
     * 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 账户id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * 账户id
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * 就诊人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 就诊人姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 就诊人年龄
     */
    public String getAge() {
        return age;
    }

    /**
     * 就诊人年龄
     */
    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    /**
     * 身份证
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 身份证
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 身份类型
     */
    public String getIdentityType() {
        return identityType;
    }

    /**
     * 身份类型
     */
    public void setIdentityType(String identityType) {
        this.identityType = identityType == null ? null : identityType.trim();
    }

    /**
     * 出生日期
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 出生日期
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建时间
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * 创建人
     */
    public Timestamp getCreateBy() {
        return createBy;
    }

    /**
     * 创建人
     */
    public void setCreateBy(Timestamp createBy) {
        this.createBy = createBy;
    }

    /**
     * 修改时间
     */
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    /**
     * 修改时间
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 修改人
     */
    public Timestamp getUpdateBy() {
        return updateBy;
    }

    /**
     * 修改人
     */
    public void setUpdateBy(Timestamp updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 科室code
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * 科室code
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    /**
     * 医院code
     */
    public String getHospitalCode() {
        return hospitalCode;
    }

    /**
     * 医院code
     */
    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
    }
}