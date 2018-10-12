package com.bmw.property.model;

import java.sql.Timestamp;


/**
 * 审批事项
 * 
 * @author Jinmy
 * @date 2018-07-18 11:17:39
 */
public class Approvalitem {


    /**
     * 项目id
     */
    private Integer aId;


    /**
     * 项目名称
     */
    private String iName;


    /**
     * 关联小区
     */
    private Integer nId;


    /**
     * 创建人id
     */
    private Integer createManId;


    /**
     * 创建人
     */
    private String createMan;


    /**
     * 创建时间
     */
    private Timestamp createTime;


    /**
     * 修改人id
     */
    private Integer editManId;


    /**
     * 修改人
     */
    private String editMan;


    /**
     * 修改时间
     */
    private Timestamp editTime;


    /**
     * 是否删除1未删除2已删除
     */
    private Integer isDelete;



    /**
     * 项目id
     */
    public Integer getaId() {
        return aId;
    }

    /**
     * 项目id
     */
    public void setaId(Integer aId) {
        this.aId = aId;
    }

    /**
     * 项目名称
     */
    public String getiName() {
        return iName;
    }

    /**
     * 项目名称
     */
    public void setiName(String iName) {
        this.iName = iName == null ? null : iName.trim();
    }

    /**
     * 关联小区
     */
    public Integer getnId() {
        return nId;
    }

    /**
     * 关联小区
     */
    public void setnId(Integer nId) {
        this.nId = nId;
    }

    /**
     * 创建人id
     */
    public Integer getCreateManId() {
        return createManId;
    }

    /**
     * 创建人id
     */
    public void setCreateManId(Integer createManId) {
        this.createManId = createManId;
    }

    /**
     * 创建人
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * 创建人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * 创建时间
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人id
     */
    public Integer getEditManId() {
        return editManId;
    }

    /**
     * 修改人id
     */
    public void setEditManId(Integer editManId) {
        this.editManId = editManId;
    }

    /**
     * 修改人
     */
    public String getEditMan() {
        return editMan;
    }

    /**
     * 修改人
     */
    public void setEditMan(String editMan) {
        this.editMan = editMan == null ? null : editMan.trim();
    }

    /**
     * 修改时间
     */
    public Timestamp getEditTime() {
        return editTime;
    }

    /**
     * 修改时间
     */
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    /**
     * 是否删除1未删除2已删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除1未删除2已删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}