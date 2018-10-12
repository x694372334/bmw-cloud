package com.bmw.medical.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 收费项目分类
 * </p>
 *
 * @author liuwsh
 * @since 2018-09-18
 */
@TableName("medical_pay_type")
public class PayType extends Model<PayType> {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 父节点code
	 */
	private String parentCode;
	/**
	 * 排序
	 */
	private String sort;
	/**
	 * 层级
	 */
	private Integer level;
	/**
	 * 是否叶子结点
	 */
	private Integer isLeaf;
	/**
	 * 结点个数
	 */
	private Integer childrenCount;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date editTime;
	/**
	 * 是否删除
	 */
	@TableField("is_delete")
	private Integer isDelete;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 父节点code
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * 父节点code
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode == null ? null : parentCode.trim();
	}

	/**
	 * 排序
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * 排序
	 */
	public void setSort(String sort) {
		this.sort = sort == null ? null : sort.trim();
	}

	/**
	 * 层级
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 层级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 是否叶子结点
	 */
	public Integer getIsLeaf() {
		return isLeaf;
	}

	/**
	 * 是否叶子结点
	 */
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * 结点个数
	 */
	public Integer getChildrenCount() {
		return childrenCount;
	}

	/**
	 * 结点个数
	 */
	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
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
		return this.id;
	}

	@Override
	public String toString() {
		return "PayType{" + "id=" + id + ", name=" + name + ", createManId=" + createManId + ", createMan=" + createMan
				+ ", createTime=" + createTime + ", editManId=" + editManId + ", editMan=" + editMan + ", editTime="
				+ editTime + ", isDelete=" + isDelete + "}";
	}
}