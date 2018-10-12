package com.bmw.property.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 特色服务
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-17
 */
@TableName("special_services")
public class Services extends Model<Services> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 企业id
     */
    @TableField("e_id")
    private Integer eId;
    /**
     * 小区ID
     */
    @TableField("n_id")
    private Integer nId;
    /**
     * 小区名称
     */
    @TableField("n_name")
    private String nName;
    /**
     * 运营端获取
     */
    @TableField("service_sort_code")
    private String serviceSortCode;
    /**
     * 服务类别名称
     */
    @TableField("service_sort_name")
    private String serviceSortName;
    /**
     * 服务名称
     */
    @TableField("service_name")
    private String serviceName;
    /**
     * 服务编号
     */
    @TableField("service_code")
    private String serviceCode;
    /**
     * 1:上门服务2 跑腿代办
     */
    @TableField("service_type")
    private Integer serviceType;
    /**
     * 1一口价 2 订金 3 免费预约
     */
    @TableField("payment_type")
    private Integer paymentType;
    /**
     * 服务价格
     */
    private BigDecimal price;
    /**
     * 服务详情
     */
    @TableField("service_detail")
    private String serviceDetail;
    /**
     * 封面
     */
    private String cover;
    /**
     * 创建人id
     */
    @TableField("create_man_id")
    private Integer createManId;
    /**
     * 创建人
     */
    @TableField("create_man")
    private String createMan;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改人id
     */
    @TableField("edit_man_id")
    private Integer editManId;
    /**
     * 修改时间
     */
    @TableField("edit_time")
    private Date editTime;
    /**
     * 是否删除 0 删除 1 未删
     */
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 订金
     */
    @TableField("deposit")
    private BigDecimal deposit;

    
    /**
     * 1:上门服务2 跑腿代办
     */
    private String serviceTypeName;


    /**
     * 1一口价 2 订金 3 免费预约
     */
    private String paymentTypeName;


	public String getServiceTypeName() {
		return serviceTypeName;
	}


	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}


	public String getPaymentTypeName() {
		return paymentTypeName;
	}


	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
    
    


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

    public Integer getnId() {
        return nId;
    }

    public void setnId(Integer nId) {
        this.nId = nId;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public String getServiceSortCode() {
        return serviceSortCode;
    }

    public void setServiceSortCode(String serviceSortCode) {
        this.serviceSortCode = serviceSortCode;
    }

    public String getServiceSortName() {
        return serviceSortName;
    }

    public void setServiceSortName(String serviceSortName) {
        this.serviceSortName = serviceSortName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEditManId() {
        return editManId;
    }

    public void setEditManId(Integer editManId) {
        this.editManId = editManId;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    
    

    public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Services{" +
        "id=" + id +
        ", eId=" + eId +
        ", nId=" + nId +
        ", nName=" + nName +
        ", serviceSortCode=" + serviceSortCode +
        ", serviceSortName=" + serviceSortName +
        ", serviceCode=" + serviceCode +
        ", serviceType=" + serviceType +
        ", paymentType=" + paymentType +
        ", price=" + price +
        ", serviceDetail=" + serviceDetail +
        ", cover=" + cover +
        ", createManId=" + createManId +
        ", createMan=" + createMan +
        ", createTime=" + createTime +
        ", editManId=" + editManId +
        ", editTime=" + editTime +
        ", isDelete=" + isDelete +
        "}";
    }
}
