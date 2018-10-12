package com.bmw.property.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.tips.Tip;
import com.bmw.property.model.CostStandard;

/**
 * 类名: ICostStandardService  
 * 类描述: 费用标准 服务类  
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:28:11    
 */
public interface ICostStandardService {

	/**
	  * 方法名称 : selectById
	  * 作者 : wangliqing  
	  * 方法描述 : 根据id查询费用标准记录
	  * 创建时间 : 2018年6月26日 下午2:28:25    
	  * 参数 : @param costStandardId
	  * 返回类型 : CostStandard    
	  */
	CostStandard selectById(Integer costStandardId);

	/**
	  * 方法名称 : selectList
	  * 作者 : wangliqing  
	  * 方法描述 : 获取费用标准列表(分页)
	  * 创建时间 : 2018年6月26日 下午2:29:06    
	  * 参数 : @param param
	  * 返回类型 : JSONObject    
	  */
	JSONObject selectList(Map<String, Object> param);

	/**
	  * 方法名称 : insert
	  * 作者 : wangliqing  
	  * 方法描述 : 新增费用标准记录
	  * 创建时间 : 2018年6月26日 下午2:29:41    
	  * 参数 : @param costStandard  
	  * 返回类型 : void    
	  */
	void insert(CostStandard costStandard);

	/**
	  * 方法名称 : deleteById
	  * 作者 : wangliqing  
	  * 方法描述 : 删除费用标准记录(逻辑删除)
	  * 创建时间 : 2018年6月26日 下午2:30:00    
	  * 参数 : @param costStandardId  
	  * 返回类型 : void    
	 * @throws Exception 
	 */
	Tip deleteById(Integer costStandardId) throws Exception;

	/**
	  * 方法名称 : updateById
	  * 作者 : wangliqing  
	  * 方法描述 : 修改费用标准记录
	  * 创建时间 : 2018年6月26日 下午2:30:16    
	  * 参数 : @param costStandard  
	  * 返回类型 : void    
	  */
	void updateById(CostStandard costStandard);

	/**
	  * 方法名称 : getCostStandardsByCostId
	  * 作者 : wangliqing  
	  * 方法描述 : 根据费项设置主键查询费用标准列表
	  * 创建时间 : 2018年6月28日 上午10:19:36    
	  * 参数 : @param costId
	  * 返回类型 : JSONArray    
	 */
	JSONArray getCostStandardsByCostId(Integer costId);
}
