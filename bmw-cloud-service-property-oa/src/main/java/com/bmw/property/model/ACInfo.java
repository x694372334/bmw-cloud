package com.bmw.property.model;

import java.sql.Timestamp;


/**
 * 广告位分包信息
 * 
 * @author jmy
 * @date 2018-07-04 10:18:44
 */
public class ACInfo {


    /**
     * 主键id
     */
    private Integer id;


    /**
     * 广告位id
     */
    private Integer aId;


    /**
     * 分包商id
     */
    private Integer cId;


    /**
     * 小区id
     */
    private Integer nId;


    /**
     * 分包开始时间
     */
    private Timestamp cBTime;


    /**
     * 分包结束时间
     */
    private Timestamp cETime;


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
    
    /**状态变化*/
    private String isSuccess;

    

    public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

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
     * 广告位id
     */
    public Integer getaId() {
        return aId;
    }

    /**
     * 广告位id
     */
    public void setaId(Integer aId) {
        this.aId = aId;
    }

    /**
     * 分包商id
     */
    public Integer getcId() {
        return cId;
    }

    /**
     * 分包商id
     */
    public void setcId(Integer cId) {
        this.cId = cId;
    }

    /**
     * 小区id
     */
    public Integer getnId() {
        return nId;
    }

    /**
     * 小区id
     */
    public void setnId(Integer nId) {
        this.nId = nId;
    }

    /**
     * 分包开始时间
     */
    public Timestamp getcBTime() {
        return cBTime;
    }

    /**
     * 分包开始时间
     */
    public void setcBTime(Timestamp cBTime) {
        this.cBTime = cBTime;
    }

    /**
     * 分包结束时间
     */
    public Timestamp getcETime() {
        return cETime;
    }

    /**
     * 分包结束时间
     */
    public void setcETime(Timestamp cETime) {
        this.cETime = cETime;
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