package com.bmw.property.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * app物业菜单表
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-26
 */
@TableName("t_app_property_menu")
public class TAppPropertyMenu extends Model<TAppPropertyMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "m_id", type = IdType.AUTO)
    private Integer mId;
    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 菜单描述
     */
    @TableField("menu_description")
    private String menuDescription;
    /**
     * 排序
     */
    @TableField("menu_sort")
    private Integer menuSort;
    /**
     * url地址
     */
    @TableField("menu_url")
    private String menuUrl;
    /**
     * 菜单编号
     */
    @TableField("menu_code")
    private String menuCode;
    /**
     * 菜单图标
     */
    @TableField("menu_icon")
    private String menuIcon;
    /**
     * 菜单状态 :  1:启用   0:不启用
     */
    @TableField("menu_status")
    private Integer menuStatus;
    /**
     * 创建人id
     */
    @TableField("create_man_id")
    private Integer createManId;
    /**
     * 创建人
     */
    @TableField("create_man")
    private String createMan;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改人id
     */
    @TableField("edit_man_id")
    private Integer editManId;
    /**
     * 修改人
     */
    @TableField("edit_man")
    private String editMan;
    /**
     * 修改时间
     */
    @TableField("edit_time")
    private Date editTime;
    /**
     * 是否删除，0 删除 1 正常
     */
    @TableField("is_delete")
    private Integer isDelete;


    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public Integer getCreateManId() {
        return createManId;
    }

    public void setCreateManId(Integer createManId) {
        this.createManId = createManId;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEditManId() {
        return editManId;
    }

    public void setEditManId(Integer editManId) {
        this.editManId = editManId;
    }

    public String getEditMan() {
        return editMan;
    }

    public void setEditMan(String editMan) {
        this.editMan = editMan;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    protected Serializable pkVal() {
        return this.mId;
    }

    @Override
    public String toString() {
        return "TAppPropertyMenu{" +
        "mId=" + mId +
        ", menuName=" + menuName +
        ", menuDescription=" + menuDescription +
        ", menuSort=" + menuSort +
        ", menuUrl=" + menuUrl +
        ", menuCode=" + menuCode +
        ", menuIcon=" + menuIcon +
        ", menuStatus=" + menuStatus +
        ", createManId=" + createManId +
        ", createMan=" + createMan +
        ", createTime=" + createTime +
        ", editManId=" + editManId +
        ", editMan=" + editMan +
        ", editTime=" + editTime +
        ", isDelete=" + isDelete +
        "}";
    }
}
