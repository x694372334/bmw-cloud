package com.bmw.base.module.account.domain;

import java.sql.Timestamp;


/**
 * 个人账户表
 * 
 * @author zhangt
 * @date 2018-07-06 11:34:35
 */
public class Account {


    /**
     * 主键id
     */
    private Integer id;


    /**
     * 姓名
     */
    private String name;


    /**
     * 账户名
     */
    private String accountName;


    /**
     * 昵称
     */
    private String petName;


    /**
     * 密码
     */
    private String pwd;


    /**
     * 分公司
     */
    private String company;


    /**
     * 部门ID
     */
    private Integer deptId;


    /**
     * 职位ID
     */
    private Integer positionId;


    /**
     * 职位名称
     */
    private String positionName;


    /**
     * 电话
     */
    private String phone;


    /**
     * 个性签名
     */
    private String signature;


    /**
     * 部门名称
     */
    private String deptName;


    /**
     * 创建人ID
     */
    private Integer createId;


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
     * 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 账户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 账户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 昵称
     */
    public String getPetName() {
        return petName;
    }

    /**
     * 昵称
     */
    public void setPetName(String petName) {
        this.petName = petName == null ? null : petName.trim();
    }

    /**
     * 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * 分公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 分公司
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * 部门ID
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * 部门ID
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 职位ID
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * 职位ID
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * 职位名称
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * 职位名称
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    /**
     * 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 个性签名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 个性签名
     */
    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    /**
     * 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * 创建人ID
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 创建人ID
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
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
}