package com.bmw.property.model;

import java.sql.Timestamp;


/**
 * 管理员表
 * 
 * @author Jinmy
 * @date 2018-08-13 04:34:08
 */
public class SelectBulter {


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
     * 物业id
     */
    private Integer eId;


    /**
     * 用户属性1管理员2前台3管家4维修工5保洁
     */
    private Integer uAttribute;


    /**
     * 职位ID
     */
    private Integer positionId;


    /**
     * 小区id
     */
    private Integer nId;


    /**
     * 企业id
     */
    private Integer parentEId;
    
    /**
     * 小区名称
     * */
    private String nName;
    
    /**
     * 用户id
     * */
    
    private String userId;
    
    
    private String bName ;
    
    private String rRoomnum ;
    
    private String uname ;
    
    private String iGender ;
    
    private String iIdcardno;
    
    private String iPhoneno ;
    
    private String iIdentity;
    
    private String oStatus;
    
    
    

    public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getrRoomnum() {
		return rRoomnum;
	}

	public void setrRoomnum(String rRoomnum) {
		this.rRoomnum = rRoomnum;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getiGender() {
		return iGender;
	}

	public void setiGender(String iGender) {
		this.iGender = iGender;
	}

	public String getiIdcardno() {
		return iIdcardno;
	}

	public void setiIdcardno(String iIdcardno) {
		this.iIdcardno = iIdcardno;
	}

	public String getiPhoneno() {
		return iPhoneno;
	}

	public void setiPhoneno(String iPhoneno) {
		this.iPhoneno = iPhoneno;
	}

	public String getiIdentity() {
		return iIdentity;
	}

	public void setiIdentity(String iIdentity) {
		this.iIdentity = iIdentity;
	}

	public String getoStatus() {
		return oStatus;
	}

	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
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

    /**
     * 物业id
     */
    public Integer geteId() {
        return eId;
    }

    /**
     * 物业id
     */
    public void seteId(Integer eId) {
        this.eId = eId;
    }

    /**
     * 用户属性1管理员2前台3管家4维修工5保洁
     */
    public Integer getuAttribute() {
        return uAttribute;
    }

    /**
     * 用户属性1管理员2前台3管家4维修工5保洁
     */
    public void setuAttribute(Integer uAttribute) {
        this.uAttribute = uAttribute;
    }

    /**
     * 职位ID
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * 职位ID
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * 小区id
     */
    public Integer getnId() {
        return nId;
    }

    /**
     * 小区id
     */
    public void setnId(Integer nId) {
        this.nId = nId;
    }

    /**
     * 企业id
     */
    public Integer getParentEId() {
        return parentEId;
    }

    /**
     * 企业id
     */
    public void setParentEId(Integer parentEId) {
        this.parentEId = parentEId;
    }
}