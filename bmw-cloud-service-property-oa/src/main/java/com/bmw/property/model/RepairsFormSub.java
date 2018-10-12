package com.bmw.property.model;

import java.util.Date;


/**
 * 报修申请单-子表
 * 
 * @author zhangchengjun
 * @date 2018-07-05 01:37:56
 */
public class RepairsFormSub {


    private Integer id;


    private Integer repairsId;


    private Short currentStatus;


    /**
     * 1:业主 2:管家 3:管理员
     */
    private Short operType;


    /**
     * 操作用户id
     */
    private Integer operId;


    /**
     * 操作人姓名
     */
    private String operName;


    /**
     * 操作描述
     */
    private String operDesc;


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
    private Date createTime;


    /**
     * 修改人id
     */
    private Integer editManId;


    /**
     * 修改时间
     */
    private Date editTime;


    /**
     * 是否删除 0 删除 1 未删
     */
    private Integer isDelete;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepairsId() {
        return repairsId;
    }

    public void setRepairsId(Integer repairsId) {
        this.repairsId = repairsId;
    }

    public Short getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Short currentStatus) {
        this.currentStatus = currentStatus;
    }

    /**
     * 1:业主 2:管家 3:管理员
     */
    public Short getOperType() {
        return operType;
    }

    /**
     * 1:业主 2:管家 3:管理员
     */
    public void setOperType(Short operType) {
        this.operType = operType;
    }

    /**
     * 操作用户id
     */
    public Integer getOperId() {
        return operId;
    }

    /**
     * 操作用户id
     */
    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    /**
     * 操作人姓名
     */
    public String getOperName() {
        return operName;
    }

    /**
     * 操作人姓名
     */
    public void setOperName(String operName) {
        this.operName = operName == null ? null : operName.trim();
    }

    /**
     * 操作描述
     */
    public String getOperDesc() {
        return operDesc;
    }

    /**
     * 操作描述
     */
    public void setOperDesc(String operDesc) {
        this.operDesc = operDesc == null ? null : operDesc.trim();
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
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
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
     * 修改时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 修改时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * 是否删除 0 删除 1 未删
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除 0 删除 1 未删
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}