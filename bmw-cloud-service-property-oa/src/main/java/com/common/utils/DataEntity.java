/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.common.utils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;

/**
* @ClassName: DataEntity  
* @Description: 实体类基类
* @author wangliqing  
* @date 2018年6月21日  
* @param <T>
 */
public abstract class DataEntity<T>  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 删除标记（0：删除；1：正常；）
	 */
	public static final int DEL_FLAG_NORMAL = 0;
	public static final int DEL_FLAG_DELETE = 1;
	
	
	 /**
     * 创建人ID
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
    private Timestamp createTime;
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
    private Timestamp editTime;
    /**
     * 是否删除
     */
    @TableField("is_delete")
    private Integer isDelete;
    
	
	public DataEntity() {
		super();
		this.isDelete = DEL_FLAG_DELETE;
	}

	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert(){

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = Timestamp.valueOf(sdf.format(new Date()));
		ShiroUser shiroUser = ShiroKit.getUser();
		this.createManId = shiroUser.getId();
		this.createMan = shiroUser.getName();
		this.createTime = ts;
		this.editManId = shiroUser.getId();
		this.editMan = shiroUser.getName();
		this.editTime = ts;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate(){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = Timestamp.valueOf(sdf.format(new Date()));
		ShiroUser shiroUser = ShiroKit.getUser();
		this.editManId = shiroUser.getId();
		this.editMan = shiroUser.getName();
		this.editTime = ts;
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

	    public Timestamp getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Timestamp createTime) {
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

	    public Timestamp getEditTime() {
	        return editTime;
	    }

	    public void setEditTime(Timestamp editTime) {
	        this.editTime = editTime;
	    }

	    public Integer getIsDelete() {
	        return isDelete;
	    }

	    public void setIsDelete(Integer isDelete) {
	        this.isDelete = isDelete;
	    }
	
	

}
