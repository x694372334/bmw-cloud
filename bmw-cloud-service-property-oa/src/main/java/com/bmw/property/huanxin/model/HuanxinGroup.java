package com.bmw.property.huanxin.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangt123
 * @since 2018-08-14
 */
@TableName("huanxin_group")
public class HuanxinGroup extends Model<HuanxinGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 环信组ID
     */
    @TableField("group_id")
    private String groupId;
    /**
     * 环信组名称
     */
    @TableField("group_name")
    private String groupName;
    /**
     * 群主ID
     */
    @TableField("group_owner_id")
    private Integer groupOwnerId;
    /**
     * 群主名称
     */
    @TableField("group_owner_name")
    private String groupOwnerName;
    /**
     * 所属小区ID
     */
    @TableField("community_id")
    private Integer communityId;
    /**
     * 所属小区名称
     */
    @TableField("community_name")
    private String communityName;
    /**
     * 创建人ID
     */
    @TableField("create_man_id")
    private Integer createManId;
    /**
     * 创建人名称
     */
    @TableField("create_man_name")
    private String createManName;
    /**
     * 群类别（1：管家群；2：业主群；3：其他）
     */
    @TableField("group_type")
    private String groupType;
    /**
     * 物业ID
     */
    @TableField("e_id")
    private Integer eId;
    /**
     * 所属企业ID
     */
    @TableField("parent_e_id")
    private Integer parentEId;

    /**
     * 群组描述
     */
    @TableField("group_desc")
    private String groupDesc;


    /**
     * 人数限制
     */
    @TableField("group_limit")
    private Integer groupLimit;
    


	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public Integer getGroupLimit() {
		return groupLimit;
	}

	public void setGroupLimit(Integer groupLimit) {
		this.groupLimit = groupLimit;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupOwnerId() {
        return groupOwnerId;
    }

    public void setGroupOwnerId(Integer groupOwnerId) {
        this.groupOwnerId = groupOwnerId;
    }

    public String getGroupOwnerName() {
        return groupOwnerName;
    }

    public void setGroupOwnerName(String groupOwnerName) {
        this.groupOwnerName = groupOwnerName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getCreateManId() {
        return createManId;
    }

    public void setCreateManId(Integer createManId) {
        this.createManId = createManId;
    }

    public String getCreateManName() {
        return createManName;
    }

    public void setCreateManName(String createManName) {
        this.createManName = createManName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getParentEId() {
        return parentEId;
    }

    public void setParentEId(Integer parentEId) {
        this.parentEId = parentEId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "HuanxinGroup{" +
        "id=" + id +
        ", groupId=" + groupId +
        ", groupName=" + groupName +
        ", groupOwnerId=" + groupOwnerId +
        ", groupOwnerName=" + groupOwnerName +
        ", communityId=" + communityId +
        ", communityName=" + communityName +
        ", createManId=" + createManId +
        ", createManName=" + createManName +
        ", groupType=" + groupType +
        ", eId=" + eId +
        ", parentEId=" + parentEId +
        "}";
    }
}
