package com.bmw.property.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.common.utils.DataEntity;


/**
 * 小区信息
 * 
 * @author jmy
 * @date 2018-06-19 01:45:57
 */
public class Neighborhood  {


    /**
     * 主键id
     */
    private Integer nId;


    /**
     * 小区编码（220101 0001 0001 0001）地区+小区+楼宇+房间
     */
    private String nCode;


    /**
     * 企业id
     */
    private Integer eId;


    /**
     * 小区名称
     */
    private String nName;


    /**
     * 小区地址
     */
    private String nAddress;


    /**
     * 客服电话
     */
    private String serviceTel;


    /**
     * 物业负责人
     */
    private String pInCharge;


    /**
     * 物业负责人电话
     */
    private String pICTel;


    /**
     * 交房时间
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String nRoomTime;


    /**
     * 占地面积
     */
    private BigDecimal nCoveredArea;


    /**
     * 建筑面积
     */
    private BigDecimal nArchitectureArea;


    /**
     * 公共场所面积
     */
    private BigDecimal nPublicArea;


    /**
     * 绿化面积
     */
    private BigDecimal nGreenArea;


    /**
     * 车库面积
     */
    private BigDecimal cArea;


    /**
     * 小区实景
     */
    private String nImages;


    /**
     * 备注
     */
    private String remarks;

    
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
    
    private String bName ;
    
    private String rRoomnum ;
    
    /**
     * 物业名称
     * */
    
    private String enterpriseName;
    
    public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getrRoomnum() {
		return rRoomnum;
	}

	public void setrRoomnum(String rRoomnum) {
		this.rRoomnum = rRoomnum;
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
     * 小区简介
     */
    private String nDescription;


    
    private String adCode;
    private String adName;



	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	/**
     * 主键id
     */
    public Integer getnId() {
        return nId;
    }

    /**
     * 主键id
     */
    public void setnId(Integer nId) {
        this.nId = nId;
    }

    /**
     * 小区编码（220101 0001 0001 0001）地区+小区+楼宇+房间
     */
    public String getnCode() {
        return nCode;
    }

    /**
     * 小区编码（220101 0001 0001 0001）地区+小区+楼宇+房间
     */
    public void setnCode(String nCode) {
        this.nCode = nCode == null ? null : nCode.trim();
    }

    /**
     * 企业id
     */
    public Integer geteId() {
        return eId;
    }

    /**
     * 企业id
     */
    public void seteId(Integer eId) {
        this.eId = eId;
    }

    /**
     * 小区名称
     */
    public String getnName() {
        return nName;
    }

    /**
     * 小区名称
     */
    public void setnName(String nName) {
        this.nName = nName == null ? null : nName.trim();
    }

    /**
     * 小区地址
     */
    public String getnAddress() {
        return nAddress;
    }

    /**
     * 小区地址
     */
    public void setnAddress(String nAddress) {
        this.nAddress = nAddress == null ? null : nAddress.trim();
    }

    /**
     * 客服电话
     */
    public String getServiceTel() {
        return serviceTel;
    }

    /**
     * 客服电话
     */
    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel == null ? null : serviceTel.trim();
    }

    /**
     * 物业负责人
     */
    public String getpInCharge() {
        return pInCharge;
    }

    /**
     * 物业负责人
     */
    public void setpInCharge(String pInCharge) {
        this.pInCharge = pInCharge == null ? null : pInCharge.trim();
    }

    /**
     * 物业负责人电话
     */
    public String getpICTel() {
        return pICTel;
    }

    /**
     * 物业负责人电话
     */
    public void setpICTel(String pICTel) {
        this.pICTel = pICTel == null ? null : pICTel.trim();
    }

   
    public String getnRoomTime() {
		return nRoomTime;
	}

	public void setnRoomTime(String nRoomTime) {
		this.nRoomTime = nRoomTime;
	}

	/**
     * 占地面积
     */
    public BigDecimal getnCoveredArea() {
        return nCoveredArea;
    }

    /**
     * 占地面积
     */
    public void setnCoveredArea(BigDecimal nCoveredArea) {
        this.nCoveredArea = nCoveredArea;
    }

    /**
     * 建筑面积
     */
    public BigDecimal getnArchitectureArea() {
        return nArchitectureArea;
    }

    /**
     * 建筑面积
     */
    public void setnArchitectureArea(BigDecimal nArchitectureArea) {
        this.nArchitectureArea = nArchitectureArea;
    }

    /**
     * 公共场所面积
     */
    public BigDecimal getnPublicArea() {
        return nPublicArea;
    }

    /**
     * 公共场所面积
     */
    public void setnPublicArea(BigDecimal nPublicArea) {
        this.nPublicArea = nPublicArea;
    }

    /**
     * 绿化面积
     */
    public BigDecimal getnGreenArea() {
        return nGreenArea;
    }

    /**
     * 绿化面积
     */
    public void setnGreenArea(BigDecimal nGreenArea) {
        this.nGreenArea = nGreenArea;
    }

    /**
     * 车库面积
     */
    public BigDecimal getcArea() {
        return cArea;
    }

    /**
     * 车库面积
     */
    public void setcArea(BigDecimal cArea) {
        this.cArea = cArea;
    }

    /**
     * 小区实景
     */
    public String getnImages() {
        return nImages;
    }

    /**
     * 小区实景
     */
    public void setnImages(String nImages) {
        this.nImages = nImages == null ? null : nImages.trim();
    }

    /**
     * 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 小区简介
     */
    public String getnDescription() {
        return nDescription;
    }

    /**
     * 小区简介
     */
    public void setnDescription(String nDescription) {
        this.nDescription = nDescription == null ? null : nDescription.trim();
    }
}