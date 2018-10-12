package com.bmw.property.model;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 抄表基础设置
 * 
 * @author jmy
 * @date 2018-07-13 08:44:07
 */
public class MBaseInfo {


    /**
     * 主键id
     */
    private Integer sId;


    /**
     * 房间id
     */
    private Integer rId;


    /**
     * 设置类别1水表2电表
     */
    private Integer mType;


    /**
     * 倍率
     */
    private BigDecimal mMultiple;


    /**
     * 耗损
     */
    private BigDecimal mWastage;


    /**
     * 公摊
     */
    private BigDecimal mCommonality;


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
     * 是否生成账单 1：已生成 0：未生成
     */
    private Integer isCreatBill;

private String nName ;
    
    private String bName ;
    
    private String rRoomnum ;
    
    private String tMonth ;
    
    private BigDecimal uAmount ;
    
    private BigDecimal tNum ;
    
    private String text ;
    
    private String isApproval;
    
    private String id ;
    
    private String tableNumber ;
    
    public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsApproval() {
		return isApproval;
	}

	public void setIsApproval(String isApproval) {
		this.isApproval = isApproval;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal gettNum() {
		return tNum;
	}

	public void settNum(BigDecimal tNum) {
		this.tNum = tNum;
	}

	public String gettMonth() {
		return tMonth;
	}

	public void settMonth(String tMonth) {
		this.tMonth = tMonth;
	}

	public BigDecimal getuAmount() {
		return uAmount;
	}

	public void setuAmount(BigDecimal uAmount) {
		this.uAmount = uAmount;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
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

	/**
     * 主键id
     */
    public Integer getsId() {
        return sId;
    }

    /**
     * 主键id
     */
    public void setsId(Integer sId) {
        this.sId = sId;
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
     * 设置类别1水表2电表
     */
    public Integer getmType() {
        return mType;
    }

    /**
     * 设置类别1水表2电表
     */
    public void setmType(Integer mType) {
        this.mType = mType;
    }

    /**
     * 倍率
     */
    public BigDecimal getmMultiple() {
        return mMultiple;
    }

    /**
     * 倍率
     */
    public void setmMultiple(BigDecimal mMultiple) {
        this.mMultiple = mMultiple;
    }

    /**
     * 耗损
     */
    public BigDecimal getmWastage() {
        return mWastage;
    }

    /**
     * 耗损
     */
    public void setmWastage(BigDecimal mWastage) {
        this.mWastage = mWastage;
    }

    /**
     * 公摊
     */
    public BigDecimal getmCommonality() {
        return mCommonality;
    }

    /**
     * 公摊
     */
    public void setmCommonality(BigDecimal mCommonality) {
        this.mCommonality = mCommonality;
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
    

    public Integer getIsCreatBill() {
		return isCreatBill;
	}

	public void setIsCreatBill(Integer isCreatBill) {
		this.isCreatBill = isCreatBill;
	}

}