package com.bmw.property.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 工作任务
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-18
 */
@TableName("work_task")
public class Task extends Model<Task> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @TableId(value = "t_id", type = IdType.AUTO)
    private Integer tId;
    /**
     * 任务名称
     */
    @TableField("t_name")
    private String tName;
    /**
     * 任务描述
     */
    @TableField("t_describe")
    private String tDescribe;
    /**
     * 主负责人
     */
    @TableField("t_principal")
    private Integer tPrincipal;
    /**
     * 参与人
     */
    @TableField("t_participation_per")
    private String tParticipationPer;
    /**
     * 完成时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @TableField("t_c_time")
    private Date tCTime;
    /**
     * 任务总结
     */
    @TableField("t_summarize")
    private String tSummarize;
    /**
     * 任务状态
     */
    @TableField("t_status")
    private Integer tStatus;
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
     * 物业ID
     */
    private Integer eId;

    public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettDescribe() {
        return tDescribe;
    }

    public void settDescribe(String tDescribe) {
        this.tDescribe = tDescribe;
    }

    public Integer gettPrincipal() {
        return tPrincipal;
    }

    public void settPrincipal(Integer tPrincipal) {
        this.tPrincipal = tPrincipal;
    }

    public String gettParticipationPer() {
        return tParticipationPer;
    }

    public void settParticipationPer(String tParticipationPer) {
        this.tParticipationPer = tParticipationPer;
    }

    public Date gettCTime() {
        return tCTime;
    }

    public void settCTime(Date tCTime) {
        this.tCTime = tCTime;
    }

    public String gettSummarize() {
        return tSummarize;
    }

    public void settSummarize(String tSummarize) {
        this.tSummarize = tSummarize;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
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
        return this.tId;
    }

    @Override
    public String toString() {
        return "Task{" +
        "tId=" + tId +
        ", tName=" + tName +
        ", tDescribe=" + tDescribe +
        ", tPrincipal=" + tPrincipal +
        ", tParticipationPer=" + tParticipationPer +
        ", tCTime=" + tCTime +
        ", tSummarize=" + tSummarize +
        ", tStatus=" + tStatus +
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
