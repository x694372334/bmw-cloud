package com.bmw.base.module.menu.domain;


/**
 * 菜单表
 * 
 * @author jmy
 * @date 2018-04-19 01:23:14
 */
public class Menu {


    /**
     * 主键id
     */
    private Long id;


    /**
     * 菜单编号
     */
    private String code;


    /**
     * 菜单父编号
     */
    private String pcode;


    /**
     * 当前菜单的所有父菜单编号
     */
    private String pcodes;


    /**
     * 菜单名称
     */
    private String name;


    /**
     * 菜单图标
     */
    private String icon;


    /**
     * url地址
     */
    private String url;


    /**
     * 菜单排序号
     */
    private Integer num;


    /**
     * 菜单层级
     */
    private Integer levels;


    /**
     * 是否是菜单（1：是  0：不是）
     */
    private Integer ismenu;


    /**
     * 备注
     */
    private String tips;


    /**
     * 菜单状态 :  1:启用   0:不启用
     */
    private Integer status;


    /**
     * 是否打开:    1:打开   0:不打开
     */
    private Integer isopen;



    /**
     * 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 菜单编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 菜单编号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 菜单父编号
     */
    public String getPcode() {
        return pcode;
    }

    /**
     * 菜单父编号
     */
    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    /**
     * 当前菜单的所有父菜单编号
     */
    public String getPcodes() {
        return pcodes;
    }

    /**
     * 当前菜单的所有父菜单编号
     */
    public void setPcodes(String pcodes) {
        this.pcodes = pcodes == null ? null : pcodes.trim();
    }

    /**
     * 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 菜单名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 菜单图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * url地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * url地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 菜单排序号
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 菜单排序号
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 菜单层级
     */
    public Integer getLevels() {
        return levels;
    }

    /**
     * 菜单层级
     */
    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    /**
     * 是否是菜单（1：是  0：不是）
     */
    public Integer getIsmenu() {
        return ismenu;
    }

    /**
     * 是否是菜单（1：是  0：不是）
     */
    public void setIsmenu(Integer ismenu) {
        this.ismenu = ismenu;
    }

    /**
     * 备注
     */
    public String getTips() {
        return tips;
    }

    /**
     * 备注
     */
    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    /**
     * 菜单状态 :  1:启用   0:不启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 菜单状态 :  1:启用   0:不启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 是否打开:    1:打开   0:不打开
     */
    public Integer getIsopen() {
        return isopen;
    }

    /**
     * 是否打开:    1:打开   0:不打开
     */
    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }
}