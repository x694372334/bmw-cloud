package com.bmw.property.model;
import java.sql.Timestamp;


/**
 * 广告位信息
 * 
 * @author jmy
 * @date 2018-07-04 10:19:05
 */
public class Advertising {


    /**
     * 主键id
     */
    private Integer aId;


    /**
     * 关联小区
     */
    private Integer nId;


    /**
     * 广告位名称
     */
    private String aName;


    /**
     * 广告位类型1平面、2LED多媒体）
     */
    private Integer aType;


    /**
     * 广告位状态
     */
    private Integer aStatus;


    /**
     * 广告位承包商
     */
    private String aContractor;


    /**
     * 联系方式
     */
    private String aPhone;


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
     * 小区名字
     * */
    private String nName;
    
    private String cName;
    
    /**
     * 分包开始时间
     */
    private Timestamp cBTime;


    /**
     * 分包结束时间
     */
    private Timestamp cETime;
    
    /**
     * 中间表id
     * */
    private Integer cId ;
    
    


    
    public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public Timestamp getcBTime() {
		return cBTime;
	}

	public void setcBTime(Timestamp cBTime) {
		this.cBTime = cBTime;
	}

	public Timestamp getcETime() {
		return cETime;
	}

	public void setcETime(Timestamp cETime) {
		this.cETime = cETime;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	/**
     * 主键id
     */
    public Integer getaId() {
        return aId;
    }

    /**
     * 主键id
     */
    public void setaId(Integer aId) {
        this.aId = aId;
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
     * 广告位名称
     */
    public String getaName() {
        return aName;
    }

    /**
     * 广告位名称
     */
    public void setaName(String aName) {
        this.aName = aName == null ? null : aName.trim();
    }

    /**
     * 广告位类型1平面、2LED多媒体）
     */
    public Integer getaType() {
        return aType;
    }

    /**
     * 广告位类型1平面、2LED多媒体）
     */
    public void setaType(Integer aType) {
        this.aType = aType;
    }

    /**
     * 广告位状态
     */
    public Integer getaStatus() {
        return aStatus;
    }

    /**
     * 广告位状态
     */
    public void setaStatus(Integer aStatus) {
        this.aStatus = aStatus;
    }

    /**
     * 广告位承包商
     */
    public String getaContractor() {
        return aContractor;
    }

    /**
     * 广告位承包商
     */
    public void setaContractor(String aContractor) {
        this.aContractor = aContractor == null ? null : aContractor.trim();
    }

    /**
     * 联系方式
     */
    public String getaPhone() {
        return aPhone;
    }

    /**
     * 联系方式
     */
    public void setaPhone(String aPhone) {
        this.aPhone = aPhone == null ? null : aPhone.trim();
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