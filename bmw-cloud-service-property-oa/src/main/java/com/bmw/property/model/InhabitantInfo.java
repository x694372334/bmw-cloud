package com.bmw.property.model;

import java.sql.Timestamp;


/**
 * 住户信息
 * 
 * @author jmy
 * @date 2018-06-25 08:21:42
 */
public class InhabitantInfo {


    /**
     * 住户id
     */
    private Integer iId;


    /**
     * 住户姓名
     */
    private String iName;


    /**
     * 住户性别
     */
    private Integer iGender;


    /**
     * 业主身份证号
     */
    private String iIdcardno;


    /**
     * 住户手机号码
     */
    private String iPhoneno;


    /**
     * 住户身份1业主2业主家人3业主亲属4租户
     */
    private Integer iIdentity;


    /**
     * 入住状态1未迁入2已迁入3已迁出
     */
    private Integer oStatus;


    /**
     * 爱好
     */
    private String iInterest;


    /**
     * 职业
     */
    private String iProfession;


    /**
     * 公司
     */
    private String iCompany;


    /**
     * 宠物
     */
    private String iPet;


    /**
     * 关联房屋id
     */
    private Integer rId;


    /**
     * 是否认证 0未认证，1、已认证 2、未通过
     */
    private Integer ifCertification;

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
   

    private String rRoomnum;
    private String bName ; 
    private String nName ;
    
    private Integer isHOwner;

	public Integer getIsHOwner() {
		return isHOwner;
	}

	public void setIsHOwner(Integer isHOwner) {
		this.isHOwner = isHOwner;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
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
     * 住户姓名
     */
    public String getiName() {
        return iName;
    }

    /**
     * 住户姓名
     */
    public void setiName(String iName) {
        this.iName = iName == null ? null : iName.trim();
    }

    /**
     * 住户性别
     */
    public Integer getiGender() {
        return iGender;
    }

    /**
     * 住户性别
     */
    public void setiGender(Integer iGender) {
        this.iGender = iGender;
    }

    /**
     * 业主身份证号
     */
    public String getiIdcardno() {
        return iIdcardno;
    }

    /**
     * 业主身份证号
     */
    public void setiIdcardno(String iIdcardno) {
        this.iIdcardno = iIdcardno == null ? null : iIdcardno.trim();
    }

    /**
     * 住户手机号码
     */
    public String getiPhoneno() {
        return iPhoneno;
    }

    /**
     * 住户手机号码
     */
    public void setiPhoneno(String iPhoneno) {
        this.iPhoneno = iPhoneno == null ? null : iPhoneno.trim();
    }

    /**
     * 住户身份1业主2业主家人3业主亲属4租户
     */
    public Integer getiIdentity() {
        return iIdentity;
    }

    /**
     * 住户身份1业主2业主家人3业主亲属4租户
     */
    public void setiIdentity(Integer iIdentity) {
        this.iIdentity = iIdentity;
    }

    /**
     * 入住状态1未迁入2已迁入3已迁出
     */
    public Integer getoStatus() {
        return oStatus;
    }

    /**
     * 入住状态1未迁入2已迁入3已迁出
     */
    public void setoStatus(Integer oStatus) {
        this.oStatus = oStatus;
    }

    /**
     * 爱好
     */
    public String getiInterest() {
        return iInterest;
    }

    /**
     * 爱好
     */
    public void setiInterest(String iInterest) {
        this.iInterest = iInterest == null ? null : iInterest.trim();
    }

    /**
     * 职业
     */
    public String getiProfession() {
        return iProfession;
    }

    /**
     * 职业
     */
    public void setiProfession(String iProfession) {
        this.iProfession = iProfession == null ? null : iProfession.trim();
    }

    /**
     * 公司
     */
    public String getiCompany() {
        return iCompany;
    }

    /**
     * 公司
     */
    public void setiCompany(String iCompany) {
        this.iCompany = iCompany == null ? null : iCompany.trim();
    }

    /**
     * 宠物
     */
    public String getiPet() {
        return iPet;
    }

    /**
     * 宠物
     */
    public void setiPet(String iPet) {
        this.iPet = iPet == null ? null : iPet.trim();
    }

    /**
     * 关联房屋id
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * 关联房屋id
     */
    public void setrId(Integer rId) {
        this.rId = rId;
    }

    /**
     * 是否认证 0未认证，1、已认证 2、未通过
     */
    public Integer getIfCertification() {
        return ifCertification;
    }

    /**
     * 是否认证 0未认证，1、已认证 2、未通过
     */
    public void setIfCertification(Integer ifCertification) {
        this.ifCertification = ifCertification;
    }

 
}