package com.bmw.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
//import com.common.utils.excel.annotation.ExcelField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 账单明细表
 * </p>
 *
 * @author zhangt123
 * @since 2018-06-22
 */
@TableName("t_bill")
public class Bill extends Model<Bill> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 企业ID
     */
    @TableField("e_id")
    private Integer eId;
    /**
     * 关联费用标准主键id
     */
    @TableField("standard_id")
    private Integer standardId;
    /**
     * 关联费用标准主键名称
     */
    @TableField("standard_name")
    private String standardName;
    /**
     * 账单编号
     */
    @TableField("bill_no")
    private String billNo;
    /**
     * 是否已缴（0：是 1：否）
     */
    @TableField("is_fee")
    private Integer isFee;
    /**
     * 业主ID
     */
    @TableField("owner_id")
    private Integer ownerId;
    /**
     * 业主姓名
     */
    @TableField("owner_name")
    private String ownerName;
    /**
     * 房号/车位号
     */
    @TableField("object_no")
    private String objectNo;
    /**
     * 业主手机号
     */
    @TableField("owner_phone")
    private String ownerPhone;
    /**
     * 费用开始时间
     */
    @TableField("cost_start_time")
    @DateTimeFormat(pattern="yyyy-MM-dd") 
    private Date costStartTime;
    /**
     * 费用结束时间
     */
    @TableField("cost_end_time")
    @DateTimeFormat(pattern="yyyy-MM-dd") 
    private Date costEndTime;
    /**
     * 应缴合计
     */
    @TableField("should_total")
    private BigDecimal shouldTotal;
    /**
     * 实缴合计
     */
    @TableField("paid_total")
    private BigDecimal paidTotal;
    /**
     * 滞纳金
     */
    @TableField("overdue_fine")
    private BigDecimal overdueFine;
    /**
     * 催缴次数
     */
    @TableField("ask_count")
    private Integer askCount;
    /**
     * 申请优惠状态（待审批、审批通过、已拒绝）
     */
    private Integer status;
    /**
     * 优惠比例
     */
    @TableField("discount_rate")
    private Double discountRate;
    /**
     * 优惠金额
     */
    @TableField("discount_amount")
    private BigDecimal discountAmount;
    
    
    /**
     * 适用小区ID
     */
    @TableField("neighborhoods_id")
    private Integer neighborhoodsId;


    /**
     * 适用小区名称
     */
    @TableField("neighborhoods_name")
    private String neighborhoodsName;
    
    
    /**
     * 缴费方式
     */
    @TableField("pay_mode")
    private Integer payMode;
    /**
     * 支付方式：1、现金，2、支付宝，3、微信
     */
    @TableField("pay_way")
    private Integer payWay;
    
    /**
     * 收费项目
     */
    @TableField("cost_id")
    private Integer costId;
    
    /**
     * 基础应缴（不含滞纳金）
     */
    @TableField("should_basic")
    private BigDecimal shouldBasic;
    
    /**
     * 生成新账单id
     */
    @TableField("new_id")
    private Integer newId;
    
    /**
     * 房屋ID/车位ID
     */
    @TableField("object_id")
    private Integer objectId;
    
    /**
     * 备注
     */
    private String remark;
    /**
     * 退款金额（多次退款累加和）
     */
    @TableField("refund_amount")
    private BigDecimal refundAmount;

    
    /**
     * 关联对象ID
     */
    @TableField("relevance_id")
    private Integer relevanceId;


	/**
	 * 单元号
	 */
    private String unitnum;
    
    /**
     *	楼栋数
     */
    private String bname;
    
    /**
     * 	是否拥有关联关系（batch_room_base表）
     */
    private Integer isRelevance;
    
    private Integer isDelete;
    
    /**
     * 关联批量关联收费标准ID
     */
    @TableField("batch_id")
    private Integer batchId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public Integer getStandardId() {
        return standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }
    
//    @ExcelField(title="费用标准", align=2, sort=6,type=1,groups= {1})
    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

//    @ExcelField(title="账单编号", align=2, sort=2,type=1,groups= {1,2})
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Integer getIsFee() {
        return isFee;
    }

    public void setIsFee(Integer isFee) {
        this.isFee = isFee;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

//    @ExcelField(title="房号/车位号", align=2, sort=4,type=1,groups= {1,2})
    public String getObjectNo() {
        return objectNo;
    }

    public void setObjectNo(String objectNo) {
        this.objectNo = objectNo;
    }
    
//    @ExcelField(title="住户", align=2, sort=4,type=1,groups= {1,2})
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

   

//    @ExcelField(title="联系电话", align=2, sort=5,type=1,groups= {1,2})
    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    @DateTimeFormat(pattern="yyyy-MM-ddHH:mm:ss") 
    @JsonFormat(pattern="yyyy-MM-ddHH:mm:ss")
//    @ExcelField(title="开始时间", align=2, sort=7,type=1,groups= {1,2})
    public Date getCostStartTime() {
        return costStartTime;
    }

    public void setCostStartTime(Date costStartTime) {
        this.costStartTime = costStartTime;
    }

    @DateTimeFormat(pattern="yyyy-MM-ddHH:mm:ss") 
    @JsonFormat(pattern="yyyy-MM-ddHH:mm:ss")
//    @ExcelField(title="结束时间", align=2, sort=8,type=1,groups= {1,2})
    public Date getCostEndTime() {
        return costEndTime;
    }

    public void setCostEndTime(Date costEndTime) {
        this.costEndTime = costEndTime;
    }

//    @ExcelField(title="应收合计", align=2, sort=9,type=1,groups= {1,2})
    public BigDecimal getShouldTotal() {
        return shouldTotal;
    }

    public void setShouldTotal(BigDecimal shouldTotal) {
        this.shouldTotal = shouldTotal;
    }

//    @ExcelField(title="实缴合计", align=2, sort=12,type=1,groups= {1})
    public BigDecimal getPaidTotal() {
        return paidTotal;
    }

    public void setPaidTotal(BigDecimal paidTotal) {
        this.paidTotal = paidTotal;
    }

//    @ExcelField(title="滞纳金", align=2, sort=11,type=1,groups= {1})
    public BigDecimal getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(BigDecimal overdueFine) {
        this.overdueFine = overdueFine;
    }

    public Integer getAskCount() {
        return askCount;
    }

    public void setAskCount(Integer askCount) {
        this.askCount = askCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

//    @ExcelField(title="优惠", align=2, sort=10,type=1,groups= {1})
    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getPayMode() {
        return payMode;
    }

    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

//    @ExcelField(title="缴费状态", align=2, sort=12,type=1,groups= {1})
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }
    
    

    public Integer getNeighborhoodsId() {
		return neighborhoodsId;
	}

	public void setNeighborhoodsId(Integer neighborhoodsId) {
		this.neighborhoodsId = neighborhoodsId;
	}

//	@ExcelField(title="小区名称", align=2, sort=1,groups= {1,2})
	public String getNeighborhoodsName() {
		return neighborhoodsName;
	}

	public void setNeighborhoodsName(String neighborhoodsName) {
		this.neighborhoodsName = neighborhoodsName;
	}
	
	
	public Integer getRelevanceId() {
		return relevanceId;
	}

	public void setRelevanceId(Integer relevanceId) {
		this.relevanceId = relevanceId;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }
	
	

    public Integer getCostId() {
		return costId;
	}

	public void setCostId(Integer costId) {
		this.costId = costId;
	}
	
	

	public BigDecimal getShouldBasic() {
		return shouldBasic;
	}

	public void setShouldBasic(BigDecimal shouldBasic) {
		this.shouldBasic = shouldBasic;
	}

	public Integer getNewId() {
		return newId;
	}

	public void setNewId(Integer newId) {
		this.newId = newId;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	
//	@ExcelField(title="单元", align=2, sort=3,type=1,groups= {1,2})
	public String getUnitnum() {
		return unitnum;
	}

	public void setUnitnum(String unitnum) {
		this.unitnum = unitnum;
	}

//	@ExcelField(title="楼宇", align=2, sort=2,type=1,groups= {1,2})
	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Integer getIsRelevance() {
		return isRelevance;
	}

	public void setIsRelevance(Integer isRelevance) {
		this.isRelevance = isRelevance;
	}
	

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
    public String toString() {
        return "Bill{" +
        "id=" + id +
        ", standardId=" + standardId +
        ", standardName=" + standardName +
        ", billNo=" + billNo +
        ", isFee=" + isFee +
        ", ownerId=" + ownerId +
        ", ownerName=" + ownerName +
        ", objectNo=" + objectNo +
        ", ownerPhone=" + ownerPhone +
        ", costStartTime=" + costStartTime +
        ", costEndTime=" + costEndTime +
        ", shouldTotal=" + shouldTotal +
        ", paidTotal=" + paidTotal +
        ", overdueFine=" + overdueFine +
        ", askCount=" + askCount +
        ", neighborhoodsId=" + neighborhoodsId +
        ", neighborhoodsName=" + neighborhoodsName +
        ", status=" + status +
        ", discountRate=" + discountRate +
        ", discountAmount=" + discountAmount +
        ", payMode=" + payMode +
        ", payWay=" + payWay +
        ", remark=" + remark +
        ", refundAmount=" + refundAmount +
        "}";
    }
}
