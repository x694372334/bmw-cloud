package com.bmw.base.module.dict.domain;


/**
 * 字典表
 * 
 * @author lyl
 * @date 2018-04-16 04:33:03
 */
public class Dict {


    /**
     * 主键id
     */
    private Integer id;


    /**
     * 排序
     */
    private Integer num;


    /**
     * 父级字典
     */
    private Integer pid;


    /**
     * 名称
     */
    private String name;


    /**
     * 提示
     */
    private String tips;



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
     * 父级字典
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 父级字典
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
}