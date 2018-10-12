package com.bmw.base.module.relation.domain;


/**
 * 角色和菜单关联表
 * 
 * @author lyl
 * @date 2018-04-26 10:30:27
 */
public class Relation {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 菜单id
     */
    private Long menuid;


    /**
     * 角色id
     */
    private Integer roleid;



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
     * 菜单id
     */
    public Long getMenuid() {
        return menuid;
    }

    /**
     * 菜单id
     */
    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    /**
     * 角色id
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 角色id
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}