package com.bmw.base.module.loginlog.domain;

import java.sql.Timestamp;


/**
 * 登录记录
 * 
 * @author lyl
 * @date 2018-04-28 08:50:01
 */
public class LoginLog {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 日志名称
     */
    private String logname;


    /**
     * 管理员id
     */
    private Integer userid;


    /**
     * 创建时间
     */
    private Timestamp createtime;


    /**
     * 是否执行成功
     */
    private String succeed;


    /**
     * 登录ip
     */
    private String ip;


    /**
     * 具体消息
     */
    private String message;



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
     * 日志名称
     */
    public String getLogname() {
        return logname;
    }

    /**
     * 日志名称
     */
    public void setLogname(String logname) {
        this.logname = logname == null ? null : logname.trim();
    }

    /**
     * 管理员id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 管理员id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 创建时间
     */
    public Timestamp getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    /**
     * 是否执行成功
     */
    public String getSucceed() {
        return succeed;
    }

    /**
     * 是否执行成功
     */
    public void setSucceed(String succeed) {
        this.succeed = succeed == null ? null : succeed.trim();
    }

    /**
     * 登录ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 登录ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 具体消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 具体消息
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}