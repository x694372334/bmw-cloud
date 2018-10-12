package com.bmw.property.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.tips.Tip;
import com.bmw.property.model.BatchStandards;

/**
 * <p>
 * 批量关联收费标准 服务类
 * </p>
 *
 * @author wangliqing123
 * @since 2018-06-28
 */
public interface IBatchStandardsService {

	/**
	  * 方法名称 : selectById
	  * 作者 : wangliqing  
	  * 方法描述 : 根据id查询批量关联收费标准记录
	  * 创建时间 : 2018年6月26日 下午2:24:16    
	  * 参数 : @param batchStandardsId
	  * 返回类型 : BatchStandards    
	 */
	BatchStandards selectById(Integer batchStandardsId);

	/**
	  * 方法名称 : selectList
	  * 作者 : wangliqing  
	  * 方法描述 : 获取批量关联收费标准列表(分页)
	  * 创建时间 : 2018年6月26日 下午2:25:08    
	  * 参数 : @param param
	  * 返回类型 : JSONObject    
	 */
	JSONObject selectList(Map<String, Object> param);

	/**
	  * 方法名称 : insert
	  * 作者 : wangliqing  
	  * 方法描述 : 新增批量关联收费标准记录
	  * 创建时间 : 2018年6月26日 下午2:25:57    
	  * 参数 : @param batchStandards  
	  * 返回类型 : void    
	  */
	Tip insert(BatchStandards batchStandards);

	/**
	  * 方法名称 : deleteById
	  * 作者 : wangliqing  
	  * 方法描述 : 删除批量关联收费标准记录(逻辑删除)
	  * 创建时间 : 2018年6月26日 下午2:26:19    
	  * 参数 : @param batchStandardsId  
	  * 返回类型 : void    
	 * @throws Exception 
	  */
	Tip deleteById(Integer batchStandardsId) throws Exception;

	/**
	  * 方法名称 : updateById
	  * 作者 : wangliqing  
	  * 方法描述 : 修改批量关联收费标准记录
	  * 创建时间 : 2018年6月26日 下午2:27:00    
	  * 参数 : @param batchStandards  
	  * 返回类型 : void    
	  */
	void updateById(BatchStandards batchStandards);

	/**
	  * 方法名称 : addScopeIds
	  * 作者 : wangliqing  
	  * 方法描述 : 保存关联范围
	  * 创建时间 : 2018年8月2日 下午3:46:48    
	  * 参数 : @param scopeIds
	  * 参数 : @return  
	  * 返回类型 : Object    
	  * @throws
	 */
	Object addScopeIds(BatchStandards batchStandards);

	/**
	  * 方法名称 : deleteBill
	  * 作者 : wangliqing  
	  * 方法描述 : 删除账单信息
	  * 创建时间 : 2018年8月3日 上午10:39:28    
	  * 参数 : @param batchStandards
	  * 参数 : @return  
	  * 返回类型 : Object    
	  * @throws
	 */
	Object deleteBill(BatchStandards batchStandards);

	List<BatchStandards> findList(BatchStandards batchStandards);
	
	boolean isAssociated(BatchStandards batchStandards, Integer rId);

}
