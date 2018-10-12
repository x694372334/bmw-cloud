package com.bmw.bill.model;


import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 账单明细申请优惠扩展类
 * 
 * @author zhangt
 * @date 2018-06-22 02:57:18
 */
public class BillDiscountsVO extends BillVO {

	/**
     * 申请原因
     */
    private String reason;
    
    
    /**
     * 滞纳金减免金额
     */
    private BigDecimal latenessOffer;
    
    /**
     * 申请优惠金额优惠金额
     */
    private BigDecimal discountsAmount;

    /**
     * 发起人
     */
    private String proposeMan;


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public BigDecimal getLatenessOffer() {
		return latenessOffer;
	}


	public void setLatenessOffer(BigDecimal latenessOffer) {
		this.latenessOffer = latenessOffer;
	}


	public String getProposeMan() {
		return proposeMan;
	}


	public void setProposeMan(String proposeMan) {
		this.proposeMan = proposeMan;
	}


	public BigDecimal getDiscountsAmount() {
		return discountsAmount;
	}


	public void setDiscountsAmount(BigDecimal discountsAmount) {
		this.discountsAmount = discountsAmount;
	}
    
    

}