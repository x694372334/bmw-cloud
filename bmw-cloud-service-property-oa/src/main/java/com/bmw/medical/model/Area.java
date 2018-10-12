package com.bmw.medical.model;

import java.util.Date;


/**
 * 区域表
 * 
 * @author jmy
 * @date 2018-05-28 10:28:43
 */
public class Area {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 编码
     */
    private String code;


    /**
     * 父节点code
     */
    private String parentCode;


    /**
     * 名称
     */
    private String name;


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
     * 是否删除
     */
    private Integer isDelete;



    /**
     * 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}