package com.bmw.base.module.notice.domain;

import java.sql.Timestamp;


/**
 * 通知表
 * 
 * @author lyl
 * @date 2018-04-26 08:47:40
 */
public class Notice {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 标题
     */
    private String title;


    /**
     * 类型
     */
    private Integer type;


    /**
     * 创建时间
     */
    private Timestamp createtime;


    /**
     * 创建人
     */
    private Integer creater;


    /**
     * 内容
     */
    private String content;



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
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 创建人
     */
    public Integer getCreater() {
        return creater;
    }

    /**
     * 创建人
     */
    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}