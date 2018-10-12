package com.bmw.property.model;

import java.sql.Timestamp;


/**
 * 广告位承包商
 * 
 * @author jmy
 * @date 2018-07-04 11:30:14
 */
public class AContractor {


    /**
     * 承包商id
     */
    private Integer cId;


    /**
     * 承包商名称
     */
    private String cName;


    /**
     * 负责人
     */
    private String cPersion;


    /**
     * 分包状态
     */
    private Integer cStatus;


    /**
     * 小区id
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
     * 分包业务
     */
    private String cBusiness;

    private String nName;
    
    

    public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	/**
     * 承包商id
     */
    public Integer getcId() {
        return cId;
    }

    /**
     * 承包商id
     */
    public void setcId(Integer cId) {
        this.cId = cId;
    }

    /**
     * 承包商名称
     */
    public String getcName() {
        return cName;
    }

    /**
     * 承包商名称
     */
    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    /**
     * 负责人
     */
    public String getcPersion() {
        return cPersion;
    }

    /**
     * 负责人
     */
    public void setcPersion(String cPersion) {
        this.cPersion = cPersion == null ? null : cPersion.trim();
    }

    /**
     * 分包状态
     */
    public Integer getcStatus() {
        return cStatus;
    }

    /**
     * 分包状态
     */
    public void setcStatus(Integer cStatus) {
        this.cStatus = cStatus;
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

    /**
     * 分包业务
     */
    public String getcBusiness() {
        return cBusiness;
    }

    /**
     * 分包业务
     */
    public void setcBusiness(String cBusiness) {
        this.cBusiness = cBusiness == null ? null : cBusiness.trim();
    }
}