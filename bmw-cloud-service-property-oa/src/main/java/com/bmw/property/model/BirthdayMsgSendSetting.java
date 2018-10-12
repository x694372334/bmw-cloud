package com.bmw.property.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 发送模式设置
 * 
 * @author zhangchengjun
 * @date 2018-07-13 04:43:27
 */
public class BirthdayMsgSendSetting {


    private Integer id;


    /**
     * 企业ID
     */
    private Integer eId;


    /**
     * 1:随机模板发送 2:固定模板发送3:不发送
     */
    private Short sendMode;


    /**
     * 1:生日当天 2:生日前一天
     */
    private Short sendDateMode;


    /**
     * send_mode为2时，发送此模板
     */
    private Integer modelId;


    /**
     * 记录几点几分发送
     */
    private String sendTime;
    


    /**
     * 1:启用 2:暂停（本字段废弃）
     */
    private Short status;


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


    private String editMan;


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

    /**
     * 企业ID
     */
    public Integer geteId() {
        return eId;
    }

    /**
     * 企业ID
     */
    public void seteId(Integer eId) {
        this.eId = eId;
    }

    /**
     * 1:随机模板发送 2:固定模板发送3:不发送
     */
    public Short getSendMode() {
        return sendMode;
    }

    /**
     * 1:随机模板发送 2:固定模板发送3:不发送
     */
    public void setSendMode(Short sendMode) {
        this.sendMode = sendMode;
    }

    /**
     * 1:生日当天 2:生日前一天
     */
    public Short getSendDateMode() {
        return sendDateMode;
    }

    /**
     * 1:生日当天 2:生日前一天
     */
    public void setSendDateMode(Short sendDateMode) {
        this.sendDateMode = sendDateMode;
    }

    /**
     * send_mode为2时，发送此模板
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * send_mode为2时，发送此模板
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * 记录几点几分发送
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * 记录几点几分发送
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 1:启用 2:暂停（本字段废弃）
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 1:启用 2:暂停（本字段废弃）
     */
    public void setStatus(Short status) {
        this.status = status;
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

    public String getEditMan() {
        return editMan;
    }

    public void setEditMan(String editMan) {
        this.editMan = editMan == null ? null : editMan.trim();
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