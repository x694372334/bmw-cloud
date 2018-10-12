package com.bmw.property.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 特色服务订单
 * 
 * @author zhangchengjun
 * @date 2018-07-24 11:50:07
 */
public class SpecialServiceOrder {


    private Integer orderId;


    private Integer eId;


    private Integer paystatus;
    
    
    public Integer getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}

	 /**
     * 申请售后时间
     */
    private Date complaintTime ;

    public Date getComplaintTime() {
		return complaintTime;
	}

	public void setComplaintTime(Date complaintTime) {
		this.complaintTime = complaintTime;
	}
	/**
     * 订单编码
     */
    private String orderCode;


    /**
     * 手机号
     */
    private String userPhoneNo;


    /**
     * 服务项目id
     */
    private Integer serviceId;


    /**
     * 服务类别id
     */
    private Integer serviceSortId;


    /**
     * 服务类别：1:上门服务2 跑腿代办
     */
    private Short serviceType;


    /**
     * 支付方式 1一口价 2 订金 3 免费预约
     */
    private Short servicePaymentType;


    /**
     * 服务费用
     */
    private BigDecimal serviceCost;


    /**
     * 小区id
     */
    private Integer nId;


    /**
     * 小区名称
     */
    private String nName;


    /**
     * 楼宇id
     */
    private Integer bId;


    /**
     * 楼宇名
     */
    private String bName;


    /**
     * 房屋id
     */
    private Integer rId;


    /**
     * 房屋号
     */
    private String rNum;


    /**
     * 申请服务用户id
     */
    private Integer appUserId;


    /**
     * 申请服务用户
     */
    private String appUserRealname;


    /**
     * 指派服务人员
     */
    private String assignUserId;


    /**
     * 下单时间
     */
    private Date appSTime;


    /**
     * 业主端状态       
            1待接单
            2已接单
            3已派单
            4待支付
            5已完成
            6待解决
            7已解决
            8申请取消
            9已取消
     */
    private Short uOrderStatus;


    /**
     * 物业端状态
            1待接单
            2待派单
            3待服务
            4待支付
            5已完成
            6待解决
            7已解决
            8申请取消
            9已取消
     */
    private Short sOrderStatus;
    /**
     * 1现金
            2支付宝
            3微信
     */
    private Short paymentType;
    /**
     * 实收金额
     */
    private BigDecimal realIncome;
    /**
     * 尾款
     */
    private BigDecimal retainage;
    /**
     * 尾款支付状态：1 未支付 2 支付中 3 已支付
     */
    private Integer retainagePayStatus;
    /**
     * 订金金额
     */
    private BigDecimal deposit;
    /**
     * 订金支付状态：1 未支付 2 支付中 3 已支付
     */
    private Integer depositPayStatus;
    /**
     * 退款金额
     */
    private BigDecimal refund;
    /**
     * 订单创建时间
     */
    private Date orderTime;
    /**
     * 5分制
     */
    private Long fevaluateScore;
    /**
     * 1 未评价 2 已评价
     */
    private Short ifevaluate;
    /**
     * 1 未投诉 2 被投诉
     */
    private Short iscomplaint;
    /**
     * 1、投诉待处理2、投诉已处理
     */
    private Integer complaintStatus;
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
    private Date createTime;


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
    private Date editTime;
    /**
     * 是否删除 0 删除 1 未删
     */
    private Integer isDelete;


    /**
     * 评价内容
     */
    private String fevaluateContent;

    /**
     * 投诉内容
     */
    private String complaintContent;

    /**
     * 回复投诉内容
     */
    private String replyComplaint;


	private String serviceSortName;
	
	private String serviceName;
		
	private String serviceDetail;
	
	private String serviceTypeName;
	
	private String servicePaymentTypeName;
		
	private String sOrderStatusName;
	
	private String payStatusName;
	
	private String cover;
	
	
    private String fevaluateUserName;

    
    private String complaintUserName;


    
	public String getFevaluateUserName() {
		return fevaluateUserName;
	}

	public void setFevaluateUserName(String fevaluateUserName) {
		this.fevaluateUserName = fevaluateUserName;
	}

	public String getComplaintUserName() {
		return complaintUserName;
	}

	public void setComplaintUserName(String complaintUserName) {
		this.complaintUserName = complaintUserName;
	}

	public String getServiceDetail() {
		return serviceDetail;
	}

	public void setServiceDetail(String serviceDetail) {
		this.serviceDetail = serviceDetail;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getPayStatusName() {
		return payStatusName;
	}

	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}

	public String getsOrderStatusName() {
		return sOrderStatusName;
	}

	public void setsOrderStatusName(String sOrderStatusName) {
		this.sOrderStatusName = sOrderStatusName;
	}

	public String getServiceSortName() {
		return serviceSortName;
	}

	public void setServiceSortName(String serviceSortName) {
		this.serviceSortName = serviceSortName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	public String getServicePaymentTypeName() {
		return servicePaymentTypeName;
	}

	public void setServicePaymentTypeName(String servicePaymentTypeName) {
		this.servicePaymentTypeName = servicePaymentTypeName;
	}
	
	

    /**
     * 评价内容
     */
    public String getFevaluateContent() {
        return fevaluateContent;
    }

    /**
     * 评价内容
     */
    public void setFevaluateContent(String fevaluateContent) {
        this.fevaluateContent = fevaluateContent == null ? null : fevaluateContent.trim();
    }

    /**
     * 投诉内容
     */
    public String getComplaintContent() {
        return complaintContent;
    }

    /**
     * 投诉内容
     */
    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent == null ? null : complaintContent.trim();
    }

    /**
     * 回复投诉内容
     */
    public String getReplyComplaint() {
        return replyComplaint;
    }

    /**
     * 回复投诉内容
     */
    public void setReplyComplaint(String replyComplaint) {
        this.replyComplaint = replyComplaint == null ? null : replyComplaint.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    /**
     * 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 订单编码
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 手机号
     */
    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    /**
     * 手机号
     */
    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo == null ? null : userPhoneNo.trim();
    }

    /**
     * 服务项目id
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * 服务项目id
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 服务类别id
     */
    public Integer getServiceSortId() {
        return serviceSortId;
    }

    /**
     * 服务类别id
     */
    public void setServiceSortId(Integer serviceSortId) {
        this.serviceSortId = serviceSortId;
    }

    /**
     * 服务类别：1:上门服务2 跑腿代办
     */
    public Short getServiceType() {
        return serviceType;
    }

    /**
     * 服务类别：1:上门服务2 跑腿代办
     */
    public void setServiceType(Short serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 支付方式 1一口价 2 订金 3 免费预约
     */
    public Short getServicePaymentType() {
        return servicePaymentType;
    }

    /**
     * 支付方式 1一口价 2 订金 3 免费预约
     */
    public void setServicePaymentType(Short servicePaymentType) {
        this.servicePaymentType = servicePaymentType;
    }

    /**
     * 服务费用
     */
    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    /**
     * 服务费用
     */
    public void setServiceCost(BigDecimal serviceCost) {
        this.serviceCost = serviceCost;
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
     * 楼宇名
     */
    public String getbName() {
        return bName;
    }

    /**
     * 楼宇名
     */
    public void setbName(String bName) {
        this.bName = bName == null ? null : bName.trim();
    }

    /**
     * 房屋id
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * 房屋id
     */
    public void setrId(Integer rId) {
        this.rId = rId;
    }

    /**
     * 房屋号
     */
    public String getrNum() {
        return rNum;
    }

    /**
     * 房屋号
     */
    public void setrNum(String rNum) {
        this.rNum = rNum == null ? null : rNum.trim();
    }

    /**
     * 申请服务用户id
     */
    public Integer getAppUserId() {
        return appUserId;
    }

    /**
     * 申请服务用户id
     */
    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    /**
     * 申请服务用户
     */
    public String getAppUserRealname() {
        return appUserRealname;
    }

    /**
     * 申请服务用户
     */
    public void setAppUserRealname(String appUserRealname) {
        this.appUserRealname = appUserRealname == null ? null : appUserRealname.trim();
    }

    /**
     * 指派服务人员
     */
    public String getAssignUserId() {
        return assignUserId;
    }

    /**
     * 指派服务人员
     */
    public void setAssignUserId(String assignUserId) {
        this.assignUserId = assignUserId == null ? null : assignUserId.trim();
    }

    /**
     * 下单时间
     */
    public Date getAppSTime() {
        return appSTime;
    }

    /**
     * 下单时间
     */
    public void setAppSTime(Date appSTime) {
        this.appSTime = appSTime;
    }

    /**
     * 业主端状态       
            1待接单
            2已接单
            3已派单
            4待支付
            5已完成
            6待解决
            7已解决
            8申请取消
            9已取消
     */
    public Short getuOrderStatus() {
        return uOrderStatus;
    }

    /**
     * 业主端状态       
            1待接单
            2已接单
            3已派单
            4待支付
            5已完成
            6待解决
            7已解决
            8申请取消
            9已取消
     */
    public void setuOrderStatus(Short uOrderStatus) {
        this.uOrderStatus = uOrderStatus;
    }

    /**
     * 物业端状态
            1待接单
            2待派单
            3待服务
            4待支付
            5已完成
            6待解决
            7已解决
            8申请取消
            9已取消
     */
    public Short getsOrderStatus() {
        return sOrderStatus;
    }

    /**
     * 物业端状态
            1待接单
            2待派单
            3待服务
            4待支付
            5已完成
            6待解决
            7已解决
            8申请取消
            9已取消
     */
    public void setsOrderStatus(Short sOrderStatus) {
        this.sOrderStatus = sOrderStatus;
    }

    /**
     * 1现金
            2支付宝
            3微信
     */
    public Short getPaymentType() {
        return paymentType;
    }

    /**
     * 1现金
            2支付宝
            3微信
     */
    public void setPaymentType(Short paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 实收金额
     */
    public BigDecimal getRealIncome() {
        return realIncome;
    }

    /**
     * 实收金额
     */
    public void setRealIncome(BigDecimal realIncome) {
        this.realIncome = realIncome;
    }

    /**
     * 尾款
     */
    public BigDecimal getRetainage() {
        return retainage;
    }

    /**
     * 尾款
     */
    public void setRetainage(BigDecimal retainage) {
        this.retainage = retainage;
    }

    /**
     * 尾款支付状态：1 未支付 2 支付中 3 已支付
     */
    public Integer getRetainagePayStatus() {
        return retainagePayStatus;
    }

    /**
     * 尾款支付状态：1 未支付 2 支付中 3 已支付
     */
    public void setRetainagePayStatus(Integer retainagePayStatus) {
        this.retainagePayStatus = retainagePayStatus;
    }

    /**
     * 订金金额
     */
    public BigDecimal getDeposit() {
        return deposit;
    }

    /**
     * 订金金额
     */
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    /**
     * 订金支付状态：1 未支付 2 支付中 3 已支付
     */
    public Integer getDepositPayStatus() {
        return depositPayStatus;
    }

    /**
     * 订金支付状态：1 未支付 2 支付中 3 已支付
     */
    public void setDepositPayStatus(Integer depositPayStatus) {
        this.depositPayStatus = depositPayStatus;
    }

    /**
     * 退款金额
     */
    public BigDecimal getRefund() {
        return refund;
    }

    /**
     * 退款金额
     */
    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }

    /**
     * 订单创建时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 订单创建时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 5分制
     */
    public Long getFevaluateScore() {
        return fevaluateScore;
    }

    /**
     * 5分制
     */
    public void setFevaluateScore(Long fevaluateScore) {
        this.fevaluateScore = fevaluateScore;
    }

    /**
     * 1 未评价 2 已评价
     */
    public Short getIfevaluate() {
        return ifevaluate;
    }

    /**
     * 1 未评价 2 已评价
     */
    public void setIfevaluate(Short ifevaluate) {
        this.ifevaluate = ifevaluate;
    }

    /**
     * 1 未投诉 2 被投诉
     */
    public Short getIscomplaint() {
        return iscomplaint;
    }

    /**
     * 1 未投诉 2 被投诉
     */
    public void setIscomplaint(Short iscomplaint) {
        this.iscomplaint = iscomplaint;
    }

    /**
     * 1、投诉待处理2、投诉已处理
     */
    public Integer getComplaintStatus() {
        return complaintStatus;
    }

    /**
     * 1、投诉待处理2、投诉已处理
     */
    public void setComplaintStatus(Integer complaintStatus) {
        this.complaintStatus = complaintStatus;
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
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
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
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 修改时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * 是否删除 0 删除 1 未删
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除 0 删除 1 未删
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}