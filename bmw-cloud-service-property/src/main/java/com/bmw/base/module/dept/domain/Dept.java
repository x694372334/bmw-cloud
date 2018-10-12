package com.bmw.base.module.dept.domain;


/**
 * 部门表
 * 
 * @author lyl
 * @date 2018-04-19 01:15:52
 */
public class Dept {


    /**
     * 主键id
     */
    private Integer id;


    /**
     * 排序
     */
    private Integer num;


    /**
     * 父部门id
     */
    private Integer pid;


    /**
     * 父级ids
     */
    private String pids;


    /**
     * 简称
     */
    private String simplename;


    /**
     * 全称
     */
    private String fullname;


    /**
     * 提示
     */
    private String tips;


    /**
     * 版本（乐观锁保留字段）
     */
    private Integer version;



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
     * 排序
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 排序
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 父部门id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 父部门id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 父级ids
     */
    public String getPids() {
        return pids;
    }

    /**
     * 父级ids
     */
    public void setPids(String pids) {
        this.pids = pids == null ? null : pids.trim();
    }

    /**
     * 简称
     */
    public String getSimplename() {
        return simplename;
    }

    /**
     * 简称
     */
    public void setSimplename(String simplename) {
        this.simplename = simplename == null ? null : simplename.trim();
    }

    /**
     * 全称
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * 全称
     */
    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    /**
     * 提示
     */
    public String getTips() {
        return tips;
    }

    /**
     * 提示
     */
    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    /**
     * 版本（乐观锁保留字段）
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 版本（乐观锁保留字段）
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}