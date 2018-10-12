package com.bmw.property.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.common.utils.DataEntity;

/**
 * 类名: CostSet  
 * 类描述: 费项设置实体类 
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:22:26    
 */
@TableName("t_cost_set")
public class CostSet extends DataEntity<CostSet> {

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
     * 费项名称
     */
    @TableField("cost_name")
    private String costName;
    /**
     * 费项类别ID（常规费项、公摊费项、临时费项）
     */
    @TableField("cost_type_id")
    private Integer costTypeId;
    /**
     * 费项类别名称（常规费项、公摊费项、临时费项）
     */
    @TableField("cost_type_name")
    private String costTypeName;
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
     * 关联对象ID
     */
    @TableField("relevance_id")
    private Integer relevanceId;
    /**
     * 关联对象name
     */
    @TableField("relevance_name")
    private String relevanceName;


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

	public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Integer getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(Integer costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName;
    }

    public Integer getNeighborhoodsId() {
        return neighborhoodsId;
    }

    public void setNeighborhoodsId(Integer neighborhoodsId) {
        this.neighborhoodsId = neighborhoodsId;
    }

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

	public String getRelevanceName() {
		return relevanceName;
	}

	public void setRelevanceName(String relevanceName) {
		this.relevanceName = relevanceName;
	}

	@Override
    public String toString() {
        return "CostSet{" +
        "id=" + id +
        ", costName=" + costName +
        ", costTypeId=" + costTypeId +
        ", costTypeName=" + costTypeName +
        ", neighborhoodsId=" + neighborhoodsId +
        ", neighborhoodsName=" + neighborhoodsName +
        "}";
    }
}
