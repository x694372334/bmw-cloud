package com.bmw.property.model;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.common.utils.DataEntity;

/**
 * 类名: CostStandard  
 * 类描述: 费用标准实体类
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:22:49    
 */
@TableName("t_cost_standard")
public class CostStandard extends DataEntity<CostStandard> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;
    
    /**
     * 企业ID
     */
    @TableField("e_id")
    private Integer eId;
    /**
     * 关联费项设置id
     */
    @TableField("cost_id")
    private Integer costId;
    /**
     * 关联费项设置名称
     */
    @TableField("cost_name")
    private String costName;
    /**
     * 收费标准名称
     */
    @TableField("standard_name")
    private String standardName;
    /**
     * 金额计算公式id
            1）计费单价*面积*收费周期
            2）计费单价*用量
            3）固定金额
            
     */
    @TableField("amount_formula_id")
    private Integer amountFormulaId;
    /**
     * 金额计算公式名称（字典）
            1）计费单价*面积*收费周期
            2）计费单价*用量
            3）固定金额
            
     */
    @TableField("amount_formula_name")
    private String amountFormulaName;
    /**
     * 计量方式id
            1）房屋建筑面积
            2）房屋套内面积
            2）房屋公摊面积
            3）车位面积
            4）用量（止度-起度）
            
     */
    @TableField("metering_id")
    private Integer meteringId;
    /**
     * 计量方式名称（字典）
            1）房屋建筑面积
            2）房屋套内面积
            2）房屋公摊面积
            3）车位面积
            4）用量（止度-起度）
            
     */
    @TableField("metering_name")
    private String meteringName;
    /**
     * 固定金额
     */
    private BigDecimal fixedAmount;
    /**
     * 计费单价
     */
    private BigDecimal price;
    /**
     * 收费周期(单位为月)
     */
    private Integer period;
    /**
     * 滞纳金设置(天数)
     */
    @TableField("late_fee")
    private String lateFee;
    /**
     * 滞纳金收取比例（%）
     */
    private Double scale;
    /**
     * 其他设置
     */
    @TableField("other_set")
    private String otherSet;
    /**
     * 默认账单收费规则id（当期收当期、当期收上期、当期收下期）
     */
    @TableField("default_fee_id")
    private Integer defaultFeeId;
    /**
     * 默认账单收费规则名称（当期收当期、当期收上期、当期收下期）
     */
    @TableField("default_fee_name")
    private String defaultFeeName;
   


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

	public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public Integer getAmountFormulaId() {
        return amountFormulaId;
    }

    public void setAmountFormulaId(Integer amountFormulaId) {
        this.amountFormulaId = amountFormulaId;
    }

    public String getAmountFormulaName() {
        return amountFormulaName;
    }

    public void setAmountFormulaName(String amountFormulaName) {
        this.amountFormulaName = amountFormulaName;
    }

    public Integer getMeteringId() {
        return meteringId;
    }

    public void setMeteringId(Integer meteringId) {
        this.meteringId = meteringId;
    }

    public String getMeteringName() {
        return meteringName;
    }

    public void setMeteringName(String meteringName) {
        this.meteringName = meteringName;
    }

    public BigDecimal getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(BigDecimal fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getLateFee() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public String getOtherSet() {
        return otherSet;
    }

    public void setOtherSet(String otherSet) {
        this.otherSet = otherSet;
    }

    public Integer getDefaultFeeId() {
        return defaultFeeId;
    }

    public void setDefaultFeeId(Integer defaultFeeId) {
        this.defaultFeeId = defaultFeeId;
    }

    public String getDefaultFeeName() {
        return defaultFeeName;
    }

    public void setDefaultFeeName(String defaultFeeName) {
        this.defaultFeeName = defaultFeeName;
    }

  

    @Override
    public String toString() {
        return "CostStandard{" +
        "id=" + id +
        ", costId=" + costId +
        ", costName=" + costName +
        ", standardName=" + standardName +
        ", amountFormulaId=" + amountFormulaId +
        ", amountFormulaName=" + amountFormulaName +
        ", meteringId=" + meteringId +
        ", meteringName=" + meteringName +
        ", price=" + price +
        ", period=" + period +
        ", lateFee=" + lateFee +
        ", scale=" + scale +
        ", otherSet=" + otherSet +
        ", defaultFeeId=" + defaultFeeId +
        ", defaultFeeName=" + defaultFeeName +
        "}";
    }
}
