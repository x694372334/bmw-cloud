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
 * 帮助
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-09
 */
@TableName("t_help")
public class Help extends Model<Help> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建人ID
     */
    @TableField("create_id")
    private Integer createId;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Help{" +
        "id=" + id +
        ", title=" + title +
        ", content=" + content +
        ", createId=" + createId +
        ", createMan=" + createMan +
        ", createTime=" + createTime +
        ", editManId=" + editManId +
        ", editMan=" + editMan +
        ", editTime=" + editTime +
        "}";
    }
}
