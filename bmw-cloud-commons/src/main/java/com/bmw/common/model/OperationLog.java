package com.bmw.common.model;

import java.sql.Timestamp;


/**
 * 体思奇系统操作日志管理
 * 
 * @author lyl
 * @date 2018年6月12日 10:06:28
 */
public class OperationLog {


    /**
     * 主键
     */
    private String id;


    /**
     * 操作用户ID
     */
    private String userId;


    /**
     * 操作用户名称
     */
    private String userName;


    /**
     * 操作用户IP
     */
    private String userIp;


    /**
     * 操作类型
     */
    private String type;


    /**
     * 操作功能标识
     */
    private String ident;


    /**
     * 操作功能标识名称
     */
    private String identName;


    /**
     * 操作数据ID
     */
    private String dataId;


    /**
     * 操作时间
     */
    private Timestamp createDate;


    /**
     * 描述
     */
    private String describes;


    /**
     * 系统类型
     */
    private String sysType;



    /**
     * 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 操作用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 操作用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 操作用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 操作用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 操作用户IP
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * 操作用户IP
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    /**
     * 操作类型
     */
    public String getType() {
        return type;
    }

    /**
     * 操作类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 操作功能标识
     */
    public String getIdent() {
        return ident;
    }

    /**
     * 操作功能标识
     */
    public void setIdent(String ident) {
        this.ident = ident == null ? null : ident.trim();
    }

    /**
     * 操作功能标识名称
     */
    public String getIdentName() {
        return identName;
    }

    /**
     * 操作功能标识名称
     */
    public void setIdentName(String identName) {
        this.identName = identName == null ? null : identName.trim();
    }

    /**
     * 操作数据ID
     */
    public String getDataId() {
        return dataId;
    }

    /**
     * 操作数据ID
     */
    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    /**
     * 操作时间
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * 操作时间
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * 描述
     */
    public String getDescribes() {
        return describes;
    }

    /**
     * 描述
     */
    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    /**
     * 系统类型
     */
    public String getSysType() {
        return sysType;
    }

    /**
     * 系统类型
     */
    public void setSysType(String sysType) {
        this.sysType = sysType == null ? null : sysType.trim();
    }
}