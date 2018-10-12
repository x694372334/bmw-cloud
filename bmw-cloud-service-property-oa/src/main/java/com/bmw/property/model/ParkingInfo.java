package com.bmw.property.model;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 车位信息
 * 
 * @author jmy
 * @date 2018-06-28 02:49:17
 */
public class ParkingInfo {


    /**
     * 车位id
     */
    private Integer pId;


    /**
     * 车位类型 1、租用 2、出售
     */
    private Integer pType;


    /**
     * 车位号
     */
    private String pNum;


    /**
     * 面积
     */
    private BigDecimal pArea;


    /**
     * 状态
     */
    private Integer pStatus;


    /**
     * 备注
     */
    private String remark;


   

    /**
     * 关联小区id
     */
    private Integer nId;


    /**
     * 关联住户id
     */
    private Integer iId;


    /**
     * 关联车辆ID
     */
    private Integer vId;


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
   
    
    private String nName;


    
    

    public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
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

	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
     * 车位id
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * 车位id
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * 车位类型 1、租用 2、出售
     */
    public Integer getpType() {
        return pType;
    }

    /**
     * 车位类型 1、租用 2、出售
     */
    public void setpType(Integer pType) {
        this.pType = pType;
    }

    /**
     * 车位号
     */
    public String getpNum() {
        return pNum;
    }

    /**
     * 车位号
     */
    public void setpNum(String pNum) {
        this.pNum = pNum == null ? null : pNum.trim();
    }

    /**
     * 面积
     */
    public BigDecimal getpArea() {
        return pArea;
    }

    /**
     * 面积
     */
    public void setpArea(BigDecimal pArea) {
        this.pArea = pArea;
    }

    /**
     * 状态
     */
    public Integer getpStatus() {
        return pStatus;
    }

    /**
     * 状态
     */
    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

  

    /**
     * 关联小区id
     */
    public Integer getnId() {
        return nId;
    }

    /**
     * 关联小区id
     */
    public void setnId(Integer nId) {
        this.nId = nId;
    }

    /**
     * 关联住户id
     */
    public Integer getiId() {
        return iId;
    }

    /**
     * 关联住户id
     */
    public void setiId(Integer iId) {
        this.iId = iId;
    }

    /**
     * 关联车辆ID
     */
    public Integer getvId() {
        return vId;
    }

    /**
     * 关联车辆ID
     */
    public void setvId(Integer vId) {
        this.vId = vId;
    }
}