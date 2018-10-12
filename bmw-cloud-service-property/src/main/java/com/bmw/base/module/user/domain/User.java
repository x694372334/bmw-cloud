package com.bmw.base.module.user.domain;

import java.sql.Timestamp;


/**
 * 管理员表
 * 
 * @author lyl
 * @date 2018-04-24 08:22:34
 */
public class User {


    /**
     * 主键id
     */
    private Integer id;


    /**
     * 头像
     */
    private String avatar;


    /**
     * 账号
     */
    private String account;


    /**
     * 密码
     */
    private String password;


    /**
     * md5密码盐
     */
    private String salt;


    /**
     * 名字
     */
    private String name;


    /**
     * 生日
     */
    private Timestamp birthday;


    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;


    /**
     * 电子邮件
     */
    private String email;


    /**
     * 电话
     */
    private String phone;


    /**
     * 角色id
     */
    private String roleid;


    /**
     * 部门id
     */
    private Integer deptid;


    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;


    /**
     * 创建时间
     */
    private Timestamp createtime;


    /**
     * 保留字段
     */
    private Integer version;
    
    /**
     * 职位ID
     */
    private Integer positionId;

    /**
     * 物业ID
     * */
    private Integer eId;
    
    /**
     * 企业id
     */
    private Integer parentEId;

    /**
     * 小区id
     */
    private Integer nId;
    
    /**
     * 用户属性  1管理员2前台3管家4维修工5保洁
     * */
    private Integer uAttribute;


    public Integer getnId() {
		return nId;
	}

	public void setnId(Integer nId) {
		this.nId = nId;
	}

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public Integer getuAttribute() {
		return uAttribute;
	}

	public void setuAttribute(Integer uAttribute) {
		this.uAttribute = uAttribute;
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
     * 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * md5密码盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * md5密码盐
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 生日
     */
    public Timestamp getBirthday() {
        return birthday;
    }

    /**
     * 生日
     */
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    /**
     * 性别（1：男 2：女）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别（1：男 2：女）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 电子邮件
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 角色id
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * 角色id
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    /**
     * 部门id
     */
    public Integer getDeptid() {
        return deptid;
    }

    /**
     * 部门id
     */
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间
     */
    public Timestamp getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	/**
     * 保留字段
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 保留字段
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