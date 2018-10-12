package com.bmw.property.huanxin.model;


/**
 * 
 * 
 * @author zhangt
 * @date 2018-08-08 09:45:38
 */
public class HuanxinUser {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String userName;


    /**
     * 密码
     */
    private String password;


    /**
     * 昵称
     */
    private String nickname;


    /**
     * pc端管家ID
     */
    private Integer pcUserId;


    /**
     * app业主ID
     */
    private Integer appUserId;


    /**
     * 环信用户ID
     */
    private String huanxinUserId;


    /**
     * 环信群组ID
     */
    private String huanxinGroupId;


    /**
     * 是否群主（1：是 ； 2：否）
     */
    private String groupOwner;



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
     * 账户
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 账户
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * pc端管家ID
     */
    public Integer getPcUserId() {
        return pcUserId;
    }

    /**
     * pc端管家ID
     */
    public void setPcUserId(Integer pcUserId) {
        this.pcUserId = pcUserId;
    }

    /**
     * app业主ID
     */
    public Integer getAppUserId() {
        return appUserId;
    }

    /**
     * app业主ID
     */
    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    /**
     * 环信用户ID
     */
    public String getHuanxinUserId() {
        return huanxinUserId;
    }

    /**
     * 环信用户ID
     */
    public void setHuanxinUserId(String huanxinUserId) {
        this.huanxinUserId = huanxinUserId == null ? null : huanxinUserId.trim();
    }

    /**
     * 环信群组ID
     */
    public String getHuanxinGroupId() {
        return huanxinGroupId;
    }

    /**
     * 环信群组ID
     */
    public void setHuanxinGroupId(String huanxinGroupId) {
        this.huanxinGroupId = huanxinGroupId == null ? null : huanxinGroupId.trim();
    }

    /**
     * 是否群主（1：是 ； 2：否）
     */
    public String getGroupOwner() {
        return groupOwner;
    }

    /**
     * 是否群主（1：是 ； 2：否）
     */
    public void setGroupOwner(String groupOwner) {
        this.groupOwner = groupOwner == null ? null : groupOwner.trim();
    }
}