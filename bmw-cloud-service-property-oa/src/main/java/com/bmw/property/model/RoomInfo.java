package com.bmw.property.model;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 房屋信息
 * 
 * @author jmy
 * @date 2018-06-26 10:44:51
 */
public class RoomInfo {
 

    /**
     * 房间id
     */
    private Integer rId;


    /**
     * 房间编码（220101 0001 0001 0001）地区+小区+楼宇+房间
     */
    private String rCode;


    /**
     * 所属小区
     */
    private Integer nId;


    /**
     * 楼宇id
     */
    private Integer bId;


    /**
     * 单元
     */
    private String rUnitnum;


    /**
     * 楼层
     */
    private Integer rFloor;


    /**
     * 房间号
     */
    private String rRoomnum;


    /**
     * 建筑面积
     */
    private Double rCoveredArea;


    /**
     * 套内面积
     */
    private Double rUsearea;


    /**
     * 公摊面积
     */
    private Double rPublicArea;


    /**
     * 房屋类型1、住宅 2、商铺 3、公寓
     */
    private Integer rRoomType;


    /**
     * 房屋举架
     */
    private BigDecimal rHeight;


    /**
     * 户型
     */
    private String houseType;


    /**
     * 入住状态1、已入住 2、未入住 3、未装修 
     */
    private Integer liveStatus;


    /**
     * 管家_id
     */
    private Integer stewardId;


    /**
     * 管家姓名
     */
    private String stewardName;


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
   
    private String bName;
    private String iCount;
    
    private String sCount;
    


    public String getiCount() {
		return iCount;
	}

	public void setiCount(String iCount) {
		this.iCount = iCount;
	}

	public String getsCount() {
		return sCount;
	}

	public void setsCount(String sCount) {
		this.sCount = sCount;
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
     * 房间id
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * 房间id
     */
    public void setrId(Integer rId) {
        this.rId = rId;
    }

    /**
     * 房间编码（220101 0001 0001 0001）地区+小区+楼宇+房间
     */
    public String getrCode() {
        return rCode;
    }

    /**
     * 房间编码（220101 0001 0001 0001）地区+小区+楼宇+房间
     */
    public void setrCode(String rCode) {
        this.rCode = rCode == null ? null : rCode.trim();
    }

    /**
     * 所属小区
     */
    public Integer getnId() {
        return nId;
    }

    /**
     * 所属小区
     */
    public void setnId(Integer nId) {
        this.nId = nId;
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
     * 单元
     */
    public String getrUnitnum() {
        return rUnitnum;
    }

    /**
     * 单元
     */
    public void setrUnitnum(String rUnitnum) {
        this.rUnitnum = rUnitnum == null ? null : rUnitnum.trim();
    }

    /**
     * 楼层
     */
    public Integer getrFloor() {
        return rFloor;
    }

    /**
     * 楼层
     */
    public void setrFloor(Integer rFloor) {
        this.rFloor = rFloor;
    }

    /**
     * 房间号
     */
    public String getrRoomnum() {
        return rRoomnum;
    }

    /**
     * 房间号
     */
    public void setrRoomnum(String rRoomnum) {
        this.rRoomnum = rRoomnum == null ? null : rRoomnum.trim();
    }

    /**
     * 建筑面积
     */
    public Double getrCoveredArea() {
        return rCoveredArea;
    }

    /**
     * 建筑面积
     */
    public void setrCoveredArea(Double rCoveredArea) {
        this.rCoveredArea = rCoveredArea;
    }

    /**
     * 套内面积
     */
    public Double getrUsearea() {
        return rUsearea;
    }

    /**
     * 套内面积
     */
    public void setrUsearea(Double rUsearea) {
        this.rUsearea = rUsearea;
    }

    /**
     * 公摊面积
     */
    public Double getrPublicArea() {
        return rPublicArea;
    }

    /**
     * 公摊面积
     */
    public void setrPublicArea(Double rPublicArea) {
        this.rPublicArea = rPublicArea;
    }

    /**
     * 房屋类型1、住宅 2、商铺 3、公寓
     */
    public Integer getrRoomType() {
        return rRoomType;
    }

    /**
     * 房屋类型1、住宅 2、商铺 3、公寓
     */
    public void setrRoomType(Integer rRoomType) {
        this.rRoomType = rRoomType;
    }

    /**
     * 房屋举架
     */
    public BigDecimal getrHeight() {
        return rHeight;
    }

    /**
     * 房屋举架
     */
    public void setrHeight(BigDecimal rHeight) {
        this.rHeight = rHeight;
    }

    /**
     * 户型
     */
    public String getHouseType() {
        return houseType;
    }

    /**
     * 户型
     */
    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    /**
     * 入住状态1、已入住 2、未入住 3、未装修 
     */
    public Integer getLiveStatus() {
        return liveStatus;
    }

    /**
     * 入住状态1、已入住 2、未入住 3、未装修 
     */
    public void setLiveStatus(Integer liveStatus) {
        this.liveStatus = liveStatus;
    }

    /**
     * 管家_id
     */
    public Integer getStewardId() {
        return stewardId;
    }

    /**
     * 管家_id
     */
    public void setStewardId(Integer stewardId) {
        this.stewardId = stewardId;
    }

    /**
     * 管家姓名
     */
    public String getStewardName() {
        return stewardName;
    }

    /**
     * 管家姓名
     */
    public void setStewardName(String stewardName) {
        this.stewardName = stewardName == null ? null : stewardName.trim();
    }

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

}