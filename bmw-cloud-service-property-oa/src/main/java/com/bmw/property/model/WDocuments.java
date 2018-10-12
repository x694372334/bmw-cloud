package com.bmw.property.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 工作文档
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-19
 */
@TableName("w_documents")
public class WDocuments extends Model<WDocuments> {

    private static final long serialVersionUID = 1L;

    /**
     * 文档id
     */
    @TableId(value = "d_id", type = IdType.AUTO)
    private Integer dId;
    /**
     * 小区id
     */
    @TableField("n_id")
    private Integer nId;
    /**
     * 文档名称
     */
    @TableField("d_name")
    private String dName;
    /**
     * 文档url
     */
    @TableField("d_url")
    private String dUrl;
    /**
     * 上传人
     */
    @TableField("up_user")
    private Integer upUser;
    /**
     * 上传时间
     */
    @TableField("up_time")
    private Date upTime;
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
     * 修改人
     */
    @TableField("edit_man")
    private String editMan;
    /**
     * 修改时间
     */
    @TableField("edit_time")
    private Date editTime;
    /**
     * 是否删除1未删除2已删除
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 共享等级（1：个人可见: 2：物业可见； 3：公司可见）
     */
    private String sharingLevel;


    /**
     * 标题
     */
    private String title;


    /**
     * 物业ID
     */
    private Integer eId;


    /**
     * 企业ID
     */
    private Integer parentEId;

    /**
     * 文档类型1国家法律2地方法规3内部文档
     */
    private Integer dType;
    
    

    public Integer getdType() {
		return dType;
	}

	public void setdType(Integer dType) {
		this.dType = dType;
	}

	public String getSharingLevel() {
		return sharingLevel;
	}

	public void setSharingLevel(String sharingLevel) {
		this.sharingLevel = sharingLevel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public Integer getnId() {
        return nId;
    }

    public void setnId(Integer nId) {
        this.nId = nId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdUrl() {
        return dUrl;
    }

    public void setdUrl(String dUrl) {
        this.dUrl = dUrl;
    }

    public Integer getUpUser() {
        return upUser;
    }

    public void setUpUser(Integer upUser) {
        this.upUser = upUser;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
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

    public String getEditMan() {
        return editMan;
    }

    public void setEditMan(String editMan) {
        this.editMan = editMan;
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

    @Override
    protected Serializable pkVal() {
        return this.dId;
    }

    @Override
    public String toString() {
        return "WDocuments{" +
        "dId=" + dId +
        ", nId=" + nId +
        ", dName=" + dName +
        ", dUrl=" + dUrl +
        ", upUser=" + upUser +
        ", upTime=" + upTime +
        ", createManId=" + createManId +
        ", createMan=" + createMan +
        ", createTime=" + createTime +
        ", editManId=" + editManId +
        ", editMan=" + editMan +
        ", editTime=" + editTime +
        ", isDelete=" + isDelete +
        "}";
    }
}
