package com.bmw.property.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.tips.Tip;
import com.bmw.property.model.CostSet;

/**
 * 类名: ICostSetService  
 * 类描述: 费项设置 服务接口
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:23:52    
 */
public interface ICostSetService {

	/**
	  * 方法名称 : selectById
	  * 作者 : wangliqing  
	  * 方法描述 : 根据id查询费项设置记录
	  * 创建时间 : 2018年6月26日 下午2:24:16    
	  * 参数 : @param costSetId
	  * 返回类型 : CostSet    
	 */
	CostSet selectById(Integer costSetId);

	/**
	  * 方法名称 : selectList
	  * 作者 : wangliqing  
	  * 方法描述 : 获取费项设置列表(分页)
	  * 创建时间 : 2018年6月26日 下午2:25:08    
	  * 参数 : @param param
	  * 返回类型 : JSONObject    
	 */
	JSONObject selectList(Map<String, Object> param);

	/**
	  * 方法名称 : insert
	  * 作者 : wangliqing  
	  * 方法描述 : 新增费项设置记录
	  * 创建时间 : 2018年6月26日 下午2:25:57    
	  * 参数 : @param costSet  
	  * 返回类型 : void    
	  */
	void insert(CostSet costSet);

	/**
	  * 方法名称 : deleteById
	  * 作者 : wangliqing  
	  * 方法描述 : 删除费项设置记录(逻辑删除)
	  * 创建时间 : 2018年6月26日 下午2:26:19    
	  * 参数 : @param costSetId  
	  * 返回类型 : void    
	 * @throws Exception 
	  */
	Tip deleteById(Integer costSetId) throws Exception;

	/**
	  * 方法名称 : updateById
	  * 作者 : wangliqing  
	  * 方法描述 : 修改费项设置记录
	  * 创建时间 : 2018年6月26日 下午2:27:00    
	  * 参数 : @param costSet  
	  * 返回类型 : void    
	  */
	void updateById(CostSet costSet);

	/**
	  * 方法名称 : getCostSetAll
	  * 作者 : wangliqing  
	  * 方法描述 : 获取所有费项设置记录(无分页)
	  * 创建时间 : 2018年6月22日 下午3:34:33    
	  * 返回类型 : JSONArray    
	  */
	JSONArray getCostSetAll(Integer relevanceId);

	/**
	 * @throws Exception 
	 * 
	  * 方法名称 : isEdit
	  * 作者 : wangliqing  
	  * 方法描述 : 判断是否能够修改
	  * 创建时间 : 2018年8月1日 下午1:36:12    
	  * 参数 : @param costSetId
	  * 参数 : @return  
	  * 返回类型 : Object    
	  * @throws
	 */
	Tip isEdit(Integer costSetId) throws Exception;
	
}
