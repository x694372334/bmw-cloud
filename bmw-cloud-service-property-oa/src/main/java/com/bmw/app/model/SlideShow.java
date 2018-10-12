package com.bmw.app.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
@TableName("t_slide_show")
public class SlideShow extends Model<SlideShow> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String title;
    private String url;
    private String sort;
    @TableField("is_open")
    private String isOpen;
    @TableField("is_change")
    private String isChange;
    @TableField("back_ground_url")
    private String backGroundUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public String getBackGroundUrl() {
        return backGroundUrl;
    }

    public void setBackGroundUrl(String backGroundUrl) {
        this.backGroundUrl = backGroundUrl;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SlideShow{" +
        "id=" + id +
        ", title=" + title +
        ", url=" + url +
        ", sort=" + sort +
        ", isOpen=" + isOpen +
        ", isChange=" + isChange +
        ", backGroundUrl=" + backGroundUrl +
        "}";
    }
}
