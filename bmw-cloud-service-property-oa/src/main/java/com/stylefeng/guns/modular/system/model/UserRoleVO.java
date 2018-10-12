package com.stylefeng.guns.modular.system.model;


/**
 * 角色信息扩展（用户与角色关联视图）
 *
 * @author zhangt
 * @since 2017-07-11
 */
public class UserRoleVO {

    /**
     * 用户ID
     */
	private Integer uid;
	
	 /**
     * 用户名称
     */
	private String uname;
	
	/**
     * 角色ID
     */
	private Integer rid;
	
	/**
     * 是否是企业管理员角色
     */
	private Integer version;
	
	/**
     * 角色名称
     */
	private String rname;
	
	/**
     * 部门ID
     */
	private Integer did;
	
	/**
     * 部门名称
     */
	private String dname;
	
	/**
     * 企业ID
     */
	private Integer eid;
	
	/**
     * 企业名称
     */
	private String ename;

	/**
     * 物业所属企业ID
     */
	private Integer parenteid;
	
	/**
     * 物业所属企业名称
     */
	private String  parentename;
	
	 /**
     * 角色类型（普通用户分类使用 1:前台 2:管家  3:维修工  4:保洁'',）
     */
    private String type;
    
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getParenteid() {
		return parenteid;
	}

	public void setParenteid(Integer parenteid) {
		this.parenteid = parenteid;
	}

	public String getParentename() {
		return parentename;
	}

	public void setParentename(String parentename) {
		this.parentename = parentename;
	}

	
	
}
