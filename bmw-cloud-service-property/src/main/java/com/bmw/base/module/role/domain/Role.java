package com.bmw.base.module.role.domain;


/**
 * 角色表
 * 
 * @author lyl
 * @date 2018-04-20 01:16:16
 */
public class Role {


    /**
     * 主键id
     */
    private Integer id;


    /**
     * 序号
     */
    private Integer num;


    /**
     * 父角色id
     */
    private Integer pid;


    /**
     * 角色名称
     */
    private String name;


    /**
     * 部门名称
     */
    private Integer deptid;


    /**
     * 提示
     */
    private String tips;
    
    /**
     * 角色类型（普通用户分类使用）
     */
    private String type;

    /**
     * 是否为企业管理员角色（1：是；2：否）
     */
    private Integer version;
    
    /**
     * 企业ID
     */
    private Integer parentEId;

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
     * 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 序号
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 序号
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 父角色id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 父角色id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 角色名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 部门名称
     */
    public Integer getDeptid() {
        return deptid;
    }

    /**
     * 部门名称
     */
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    /**
     * 提示
     */
    public String getTips() {
        return tips;
    }

    /**
     * 提示
     */
    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    /**
     * 保留字段(暂时没用）
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 保留字段(暂时没用）
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

	public Integer getParentEId() {
		return parentEId;
	}

	public void setParentEId(Integer parentEId) {
		this.parentEId = parentEId;
	}
    
    
}