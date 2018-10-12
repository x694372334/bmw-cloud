package com.bmw.property.model;

import java.sql.Timestamp;


/**
 * 住户车辆信息
 * 
 * @author jmy
 * @date 2018-07-04 10:00:55
 */
public class IVehicleInfo {


    /**
     * 车辆id
     */
    private Integer vId;


    /**
     * 住户id
     */
    private Integer iId;


    /**
     * 车辆品牌
     */
    private String vTrademark;


    /**
     * 车辆类型
     */
    private String vType;


    /**
     * 车牌号码
     */
    private String vNumber;

    /**
     * 小区
     */
    private String nName;
    
    /**
     * 住户
     */
    private String iName;

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

    private String CitySel;
    
    private String roomId;
    
    
    


    public String getCitySel() {
		return CitySel;
	}

	public void setCitySel(String citySel) {
		CitySel = citySel;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName == null ? null : iName.trim();
	}
	
	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName == null ? null : nName.trim();
	}

	/**
     * 车辆id
     */
    public Integer getvId() {
        return vId;
    }

    /**
     * 车辆id
     */
    public void setvId(Integer vId) {
        this.vId = vId;
    }

    /**
     * 住户id
     */
    public Integer getiId() {
        return iId;
    }

    /**
     * 住户id
     */
    public void setiId(Integer iId) {
        this.iId = iId;
    }

    /**
     * 车辆品牌
     */
    public String getvTrademark() {
        return vTrademark;
    }

    /**
     * 车辆品牌
     */
    public void setvTrademark(String vTrademark) {
        this.vTrademark = vTrademark == null ? null : vTrademark.trim();
    }

    /**
     * 车辆类型
     */
    public String getvType() {
        return vType;
    }

    /**
     * 车辆类型
     */
    public void setvType(String vType) {
        this.vType = vType == null ? null : vType.trim();
    }

    /**
     * 车牌号码
     */
    public String getvNumber() {
        return vNumber;
    }

    /**
     * 车牌号码
     */
    public void setvNumber(String vNumber) {
        this.vNumber = vNumber == null ? null : vNumber.trim();
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