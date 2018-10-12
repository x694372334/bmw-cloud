package com.bmw.base.module.operation.domain;

import java.sql.Timestamp;


/**
 * 操作日志
 * 
 * @author lyl
 * @date 2018-04-27 10:54:12
 */
public class SysOperationLog {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 日志类型
     */
    private String logtype;


    /**
     * 日志名称
     */
    private String logname;


    /**
     * 用户id
     */
    private Integer userid;


    /**
     * 类名称
     */
    private String classname;


    /**
     * 创建时间
     */
    private Timestamp createtime;


    /**
     * 是否成功
     */
    private String succeed;



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
     * 日志类型
     */
    public String getLogtype() {
        return logtype;
    }

    /**
     * 日志类型
     */
    public void setLogtype(String logtype) {
        this.logtype = logtype == null ? null : logtype.trim();
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
     * 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 类名称
     */
    public String getClassname() {
        return classname;
    }

    /**
     * 类名称
     */
    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
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
     * 是否成功
     */
    public String getSucceed() {
        return succeed;
    }

    /**
     * 是否成功
     */
    public void setSucceed(String succeed) {
        this.succeed = succeed == null ? null : succeed.trim();
    }
}