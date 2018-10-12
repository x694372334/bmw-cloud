package com.bmw.bill.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

//import com.common.utils.excel.annotation.ExcelField;


/**
 * 账单明细表
 * 
 * @author zhangt
 * @date 2018-06-25 01:22:33
 */
public class BillVO {


    /**
     * 主键id
     */
    private Integer id;


    /**
     * 关联费用标准主键id
     */
    private Integer standardId;


    /**
     * 关联费用标准主键名称
     */
    private String standardName;


    /**
     * 账单编号
     */
    private String billNo;


    /**
     * 是否已缴（0：是 1：否）
     */
    private Short isFee;


    /**
     * 业主ID
     */
    private Integer ownerId;


    /**
     * 业主姓名
     */
    private String ownerName;


    /**
     * 房号/车位号
     */
    private String objectNo;


    /**
     * 业主手机号
     */
    private String ownerPhone;


    /**
     * 费用开始时间
     */
    private Timestamp costStartTime;


    /**
     * 费用结束时间
     */
    private Timestamp costEndTime;


    /**
     * 应缴合计
     */
    private BigDecimal shouldTotal;


    /**
     * 实缴合计
     */
    private BigDecimal paidTotal;


    /**
     * 滞纳金
     */
    private BigDecimal overdueFine;


    /**
     * 催缴次数
     */
    private Integer askCount;


    /**
     * 申请优惠状态（待审批、审批通过、已拒绝）
     */
    private Integer status;


    /**
     * 优惠比例
     */
    private Double discountRate;


    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;


    /**
     * 缴费方式
     */
    private Integer payMode;


    /**
     * 支付方式：1、现金，2、支付宝，3、微信
     */
    private Integer payWay;


    /**
     * 退款金额（多次退款累加和）
     */
    private BigDecimal refundAmount;


    /**
     * 适用小区ID
     */
    private Integer neighborhoodsId;


    /**
     * 适用小区名称
     */
    private String neighborhoodsName;
    
    /**
     * 收费项目
     */
    private Integer costId;
    
    /**
     * 基础应缴（不含滞纳金）
     */
    private BigDecimal shouldBasic;
    
    /**
     * 生成新账单id
     */
    private Integer newId;
    
    /**
     * 房屋ID/车位ID
     */
    private Integer objectId;


    /**
     * 备注
     */
    private String remark;

    /**
     * 关联对象ID
     */
    private Integer relevanceId;


    /**
     * 关联批量关联收费标准ID
     */
    private Integer batchId;
    
    


    /**
     * 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 关联费用标准主键id
     */
    public Integer getStandardId() {
        return standardId;
    }

    /**
     * 关联费用标准主键id
     */
    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    /**
     * 关联费用标准主键名称
     */
    public String getStandardName() {
        return standardName;
    }

    /**
     * 关联费用标准主键名称
     */
    public void setStandardName(String standardName) {
        this.standardName = standardName == null ? null : standardName.trim();
    }

    /**
     * 账单编号
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * 账单编号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    /**
     * 是否已缴（0：是 1：否）
     */
    public Short getIsFee() {
        return isFee;
    }

    /**
     * 是否已缴（0：是 1：否）
     */
    public void setIsFee(Short isFee) {
        this.isFee = isFee;
    }

    /**
     * 业主ID
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * 业主ID
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 业主姓名
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 业主姓名
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    /**
     * 房号/车位号
     */
    public String getObjectNo() {
        return objectNo;
    }

    /**
     * 房号/车位号
     */
    public void setObjectNo(String objectNo) {
        this.objectNo = objectNo == null ? null : objectNo.trim();
    }

    /**
     * 业主手机号
     */
    public String getOwnerPhone() {
        return ownerPhone;
    }

    /**
     * 业主手机号
     */
    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone == null ? null : ownerPhone.trim();
    }

    /**
     * 费用开始时间
     */
    public Timestamp getCostStartTime() {
        return costStartTime;
    }

    /**
     * 费用开始时间
     */
    public void setCostStartTime(Timestamp costStartTime) {
        this.costStartTime = costStartTime;
    }

    /**
     * 费用结束时间
     */
    public Timestamp getCostEndTime() {
        return costEndTime;
    }

    /**
     * 费用结束时间
     */
    public void setCostEndTime(Timestamp costEndTime) {
        this.costEndTime = costEndTime;
    }

    /**
     * 应缴合计
     */
    public BigDecimal getShouldTotal() {
        return shouldTotal;
    }

    /**
     * 应缴合计
     */
    public void setShouldTotal(BigDecimal shouldTotal) {
        this.shouldTotal = shouldTotal;
    }

    /**
     * 实缴合计
     */
    public BigDecimal getPaidTotal() {
        return paidTotal;
    }

    /**
     * 实缴合计
     */
    public void setPaidTotal(BigDecimal paidTotal) {
        this.paidTotal = paidTotal;
    }

    /**
     * 滞纳金
     */
    public BigDecimal getOverdueFine() {
        return overdueFine;
    }

    /**
     * 滞纳金
     */
    public void setOverdueFine(BigDecimal overdueFine) {
        this.overdueFine = overdueFine;
    }

    /**
     * 催缴次数
     */
    public Integer getAskCount() {
        return askCount;
    }

    /**
     * 催缴次数
     */
    public void setAskCount(Integer askCount) {
        this.askCount = askCount;
    }

    /**
     * 申请优惠状态（待审批、审批通过、已拒绝）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 申请优惠状态（待审批、审批通过、已拒绝）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 优惠比例
     */
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * 优惠比例
     */
    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 优惠金额
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 优惠金额
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * 缴费方式
     */
    public Integer getPayMode() {
        return payMode;
    }

    /**
     * 缴费方式
     */
    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    /**
     * 支付方式：1、现金，2、支付宝，3、微信
     */
    public Integer getPayWay() {
        return payWay;
    }

    /**
     * 支付方式：1、现金，2、支付宝，3、微信
     */
    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    /**
     * 退款金额（多次退款累加和）
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 退款金额（多次退款累加和）
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 适用小区ID
     */
    public Integer getNeighborhoodsId() {
        return neighborhoodsId;
    }

    /**
     * 适用小区ID
     */
    public void setNeighborhoodsId(Integer neighborhoodsId) {
        this.neighborhoodsId = neighborhoodsId;
    }

    /**
     * 适用小区名称
     */
    public String getNeighborhoodsName() {
        return neighborhoodsName;
    }

    /**
     * 适用小区名称
     */
    public void setNeighborhoodsName(String neighborhoodsName) {
        this.neighborhoodsName = neighborhoodsName == null ? null : neighborhoodsName.trim();
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
    
}