package com.bmw.app.model;

import java.io.Serializable;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
@TableName("t_commodity")
public class Commodity extends Model<Commodity> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String type;
    private String specification;
    private String unit;
    private BigDecimal price;
    private String category;
    @TableField("is_recommend")
    private String isRecommend;
    @TableField("back_ground_url")
    private String backGroundUrl;
    @TableField("commodity_url")
    private String commodityUrl;
    @TableField("detail_link")
    private String detailLink;
    @TableField("is_change")
    private String isChange;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getBackGroundUrl() {
        return backGroundUrl;
    }

    public void setBackGroundUrl(String backGroundUrl) {
        this.backGroundUrl = backGroundUrl;
    }

    public String getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(String commodityUrl) {
        this.commodityUrl = commodityUrl;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Commodity{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", specification=" + specification +
        ", unit=" + unit +
        ", price=" + price +
        ", category=" + category +
        ", isRecommend=" + isRecommend +
        ", backGroundUrl=" + backGroundUrl +
        ", commodityUrl=" + commodityUrl +
        ", detailLink=" + detailLink +
        ", isChange=" + isChange +
        "}";
    }
}
