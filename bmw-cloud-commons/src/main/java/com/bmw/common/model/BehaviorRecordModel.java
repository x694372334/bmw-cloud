package com.bmw.common.model;

import java.sql.Timestamp;


/**
 * 用户行为记录表
 * 
 * @author lyl
 * @date 2017-03-16 08:02:03
 */
public class BehaviorRecordModel {


    /**
     * 主键
     */
    private String behaviorId;


    /**
     * 用户名
     */
    private String userName;


    /**
     * 用户ID
     */
    private String userId;


    /**
     * 用户IP地址
     */
    private String userIp;


    /**
     * 访问的URL
     */
    private String requestUrl;


    /**
     * 访问的系统
     */
    private String requestSys;


    /**
     * 访问的功能
     */
    private String requestFunc;


    /**
     * 登录Token标识
     */
    private String token;


    /**
     * 访问时间
     */
    private Timestamp requestTime;


    /**
     * 备注
     */
    private String remark;


    /**
     * 创建时间
     */
    private Timestamp createTime;



    /**
     * 主键
     */
    public String getBehaviorId() {
        return behaviorId;
    }

    /**
     * 主键
     */
    public void setBehaviorId(String behaviorId) {
        this.behaviorId = behaviorId == null ? null : behaviorId.trim();
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 用户IP地址
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * 用户IP地址
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    /**
     * 访问的URL
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * 访问的URL
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    /**
     * 访问的系统
     */
    public String getRequestSys() {
        return requestSys;
    }

    /**
     * 访问的系统
     */
    public void setRequestSys(String requestSys) {
        this.requestSys = requestSys == null ? null : requestSys.trim();
    }

    /**
     * 访问的功能
     */
    public String getRequestFunc() {
        return requestFunc;
    }

    /**
     * 访问的功能
     */
    public void setRequestFunc(String requestFunc) {
        this.requestFunc = requestFunc == null ? null : requestFunc.trim();
    }

    /**
     * 登录Token标识
     */
    public String getToken() {
        return token;
    }

    /**
     * 登录Token标识
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * 访问时间
     */
    public Timestamp getRequestTime() {
        return requestTime;
    }

    /**
     * 访问时间
     */
    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
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
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}