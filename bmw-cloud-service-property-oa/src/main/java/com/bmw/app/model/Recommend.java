package com.bmw.app.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-23
 */
@TableName("t_recommend")
public class Recommend extends Model<Recommend> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否主页显示 1:是 0:否
     */
    private Integer isshow;
    /**
     * 地址
     */
    private String address;
    /**
     * 文字简介
     */
    private String content;
    /**
     * 平米
     */
    private String metre;
    /**
     * 图片路径
     */
    private String url;
    /**
     * 背景色
     */
    private String backcolor;
    /**
     * 创建时间
     */
    private Date createtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMetre() {
        return metre;
    }

    public void setMetre(String metre) {
        this.metre = metre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBackcolor() {
        return backcolor;
    }

    public void setBackcolor(String backcolor) {
        this.backcolor = backcolor;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Recommend{" +
        "id=" + id +
        ", name=" + name +
        ", isshow=" + isshow +
        ", address=" + address +
        ", content=" + content +
        ", metre=" + metre +
        ", url=" + url +
        ", backcolor=" + backcolor +
        ", createtime=" + createtime +
        "}";
    }
}
