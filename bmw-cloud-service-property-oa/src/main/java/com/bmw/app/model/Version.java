package com.bmw.app.model;

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
 * 
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
@TableName("t_version")
public class Version extends Model<Version> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String network;
    private String number;
    private String gradeNumber;
    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;
    @TableField("grade_by")
    private String gradeBy;
    @TableField("grade_time")
    private Date gradeTime;
    @TableField("is_change")
    private String isChange;
    @TableField("is_open")
    private String isOpen;
    @TableField("version_type")
    private String versionType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGradeBy() {
        return gradeBy;
    }

    public void setGradeBy(String gradeBy) {
        this.gradeBy = gradeBy;
    }

    public Date getGradeTime() {
        return gradeTime;
    }

    public void setGradeTime(Date gradeTime) {
        this.gradeTime = gradeTime;
    }

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public String getGradeNumber() {
		return gradeNumber;
	}

	public void setGradeNumber(String gradeNumber) {
		this.gradeNumber = gradeNumber;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getVersionType() {
		return versionType;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Version{" +
        "id=" + id +
        ", network=" + network +
        ", number=" + number +
        ", gradeNumber=" + gradeNumber +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", gradeBy=" + gradeBy +
        ", gradeTime=" + gradeTime +
        ", isChange=" + isChange +
        ", isOpen=" + isOpen + 
        ", versionType=" + versionType + 
        "}";
    }
}
