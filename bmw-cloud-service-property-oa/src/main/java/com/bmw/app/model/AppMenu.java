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
@TableName("t_app_menu")
public class AppMenu extends Model<AppMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String code;
    private String name;
    private String icon;
    private String url;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "AppMenu{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", icon=" + icon +
        ", url=" + url +
        ", isOpen=" + isOpen +
        ", isChange=" + isChange +
        ", backGroundUrl=" + backGroundUrl +
        "}";
    }
}
