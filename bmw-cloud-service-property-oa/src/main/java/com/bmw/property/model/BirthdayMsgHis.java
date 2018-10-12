package com.bmw.property.model;

import java.util.Date;


/**
 * 生日短信发送历史
 * 
 * @author zhangchengjun
 * @date 2018-07-16 11:37:37
 */
public class BirthdayMsgHis {


    private Integer id;


    /**
     * 企业ID
     */
    private Integer eId;
    
    /**
     * 住户ID
     */
    private Integer iId;


    /**
     * 模板id
     */
    private Integer modelId;


    /**
     * 发送的内容
     */
    private String sendText;


    /**
     * 手机号
     */
    private String phoneNo;


    /**
     * 业主姓名
     */
    private String ownerName;


    /**
     * 发送时间
     */
    private Date sendTime;


    /**
     * 1:待发送 2:发送成功3:发送失败
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


    

    public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 住户ID
     */
    public Integer getiId() {
        return iId;
    }

    /**
     * 住户ID
     */
    public void setiId(Integer iId) {
        this.iId = iId;
    }

    /**
     * 模板id
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * 模板id
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * 发送的内容
     */
    public String getSendText() {
        return sendText;
    }

    /**
     * 发送的内容
     */
    public void setSendText(String sendText) {
        this.sendText = sendText == null ? null : sendText.trim();
    }

    /**
     * 手机号
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * 手机号
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    /**
     * 业主姓名
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 业主姓名
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    /**
     * 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 1:待发送 2:发送成功3:发送失败
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 1:待发送 2:发送成功3:发送失败
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