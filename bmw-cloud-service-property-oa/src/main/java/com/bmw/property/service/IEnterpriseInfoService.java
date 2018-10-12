package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.BuildingInfo;
import com.bmw.property.model.EnterpriseInfo;

/**
 * <p>
 * 企业信息 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-25
 */
public interface IEnterpriseInfoService {
	/**
     * 列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取详情
     */
	EnterpriseInfo getdetail(Integer e_id);
	
	
	/**
     * 新增信息
     */
	void add(EnterpriseInfo enterpriseinfo);
	
	/**
	 * 修改信息
	 */
	void update(EnterpriseInfo enterpriseinfo);
	
	/**
	 * 删除信息
	 * @return 
	 */
	String del(Integer e_id);
	/**
	 * 树形列表
	 */
	List<ZTreeNode> findTreeList();

	/**
	 * 只查询类型为企业的树形列表
	 */
	List<ZTreeNode> findParentTreeList();
}
