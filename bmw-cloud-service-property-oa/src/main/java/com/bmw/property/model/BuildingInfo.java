package com.bmw.property.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.activerecord.Model;
import com.common.utils.DataEntity;


/**
 * 楼宇信息
 * 
 * @author jmy
 * @date 2018-06-22 08:39:40
 */
public class BuildingInfo  {


    /**
     * 楼宇id
     */
    private Integer bId;


    /**
     * 楼宇编码（220101 0001 0001 0001）地区+小区+楼宇+房间
     */
    private String bCode;


    /**
     * 小区id
     */
    private Integer nId;


    /**
     * 楼宇号/楼宇名
     */
    private String bName;


    /**
     * 单元数量
     */
    private Integer bUnitCount;


    /**
     * 楼宇层数
     */
    private Integer bFloors;


    /**
     * 楼宇类型
     */
    private String bType;


    /**
     * 楼宇朝向
     */
    private String bOrientation;


    /**
     * 备注
     */
    private String remark;

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
     * 楼宇id
     */
    public Integer getbId() {
        return bId;
    }

    /**
     * 楼宇id
     */
    public void setbId(Integer bId) {
        this.bId = bId;
    }

    /**
     * 楼宇编码（220101 0001 0001 0001）地区+小区+楼宇+房间
     */
    public String getbCode() {
        return bCode;
    }

    /**
     * 楼宇编码（220101 0001 0001 0001）地区+小区+楼宇+房间
     */
    public void setbCode(String bCode) {
        this.bCode = bCode == null ? null : bCode.trim();
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
     * 楼宇号/楼宇名
     */
    public String getbName() {
        return bName;
    }

    /**
     * 楼宇号/楼宇名
     */
    public void setbName(String bName) {
        this.bName = bName == null ? null : bName.trim();
    }

    /**
     * 单元数量
     */
    public Integer getbUnitCount() {
        return bUnitCount;
    }

    /**
     * 单元数量
     */
    public void setbUnitCount(Integer bUnitCount) {
        this.bUnitCount = bUnitCount;
    }

    /**
     * 楼宇层数
     */
    public Integer getbFloors() {
        return bFloors;
    }

    /**
     * 楼宇层数
     */
    public void setbFloors(Integer bFloors) {
        this.bFloors = bFloors;
    }

    /**
     * 楼宇类型
     */
    public String getbType() {
        return bType;
    }

    /**
     * 楼宇类型
     */
    public void setbType(String bType) {
        this.bType = bType == null ? null : bType.trim();
    }

    /**
     * 楼宇朝向
     */
    public String getbOrientation() {
        return bOrientation;
    }

    /**
     * 楼宇朝向
     */
    public void setbOrientation(String bOrientation) {
        this.bOrientation = bOrientation == null ? null : bOrientation.trim();
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

}