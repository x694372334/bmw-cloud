package com.bmw.base.module.user.domain;

/**
 * 管理员表Condition类
 * 
 * @author lyl
 * @date 2018-04-24 08:22:34
 */
public class UserVO extends User {

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 职务名称
	 */
	private String positionName;

	/**
	 * 企业名称
	 */
	private String enterpriseName;

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}