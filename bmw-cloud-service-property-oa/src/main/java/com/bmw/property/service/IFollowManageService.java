package com.bmw.property.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.bmw.property.model.FollowManage;

/**
 * 类名: IFollowManageService  
 * 类描述: 跟进管理 服务类
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:31:16    
 */
public interface IFollowManageService {

	/**
	  * 方法名称 : selectById
	  * 作者 : wangliqing  
	  * 方法描述 : 根据id查询跟进管理记录
	  * 创建时间 : 2018年6月26日 下午2:31:34    
	  * 参数 : @param followManageId
	  * 返回类型 : FollowManage    
	  */
	FollowManage selectById(Integer followManageId);

	/**
	  * 方法名称 : selectList
	  * 作者 : wangliqing  
	  * 方法描述 : 获取跟进管理列表(分页)
	  * 创建时间 : 2018年6月26日 下午2:31:54    
	  * 参数 : @param map
	  * 返回类型 : JSONObject    
	  */
	JSONObject selectList(Map<String, Object> map);

	/**
	  * 方法名称 : insert
	  * 作者 : wangliqing  
	  * 方法描述 : 新增跟进管理记录
	  * 创建时间 : 2018年6月26日 下午2:32:08    
	  * 参数 : @param followManage  
	  * 返回类型 : void    
	  */
	void insert(FollowManage followManage);

	/**
	  * 方法名称 : deleteById
	  * 作者 : wangliqing  
	  * 方法描述 : 删除跟进管理记录(逻辑删除)
	  * 创建时间 : 2018年6月26日 下午2:32:24    
	  * 参数 : @param followManageId  
	  * 返回类型 : void    
	  */
	void deleteById(Integer followManageId);

	/**
	  * 方法名称 : updateById
	  * 作者 : wangliqing  
	  * 方法描述 : 修改跟进管理记录
	  * 创建时间 : 2018年6月26日 下午2:32:37    
	  * 参数 : @param followManage  
	  * 返回类型 : void    
	  */
	void updateById(FollowManage followManage);
	

}
