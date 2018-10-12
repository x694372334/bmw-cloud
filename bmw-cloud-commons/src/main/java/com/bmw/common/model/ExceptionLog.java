package com.bmw.common.model;

import java.sql.Timestamp;


/**
 * 体思奇系统异常日志管理
 * 
 * @author lyl
 * @date 2016-11-03 10:05:49
 */
public class ExceptionLog {


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


    private String className;


    /**
     * 异常方法名
     */
    private String classMethod;


    /**
     * 创建时间
     */
    private Timestamp createDate;


    /**
     * 系统类型
     */
    private String sysType;


    /**
     * 异常内容
     */
    private byte[] exceptionContent;



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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    /**
     * 异常方法名
     */
    public String getClassMethod() {
        return classMethod;
    }

    /**
     * 异常方法名
     */
    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod == null ? null : classMethod.trim();
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

    /**
     * 异常内容
     */
    public byte[] getExceptionContent() {
        return exceptionContent;
    }

    /**
     * 异常内容
     */
    public void setExceptionContent(byte[] exceptionContent) {
        this.exceptionContent = exceptionContent;
    }
}