package com.bmw.property.huanxin.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.property.huanxin.model.HuanxinGroup;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-08-14
 */
public interface IHuanxinGroupService  {
	/**
     * 列表
     */
	JSONArray findList(HuanxinGroup condition);
	
	/**
     * 详情
     */
	HuanxinGroup getdetail(Integer huanxinGroupId);
	
	
	/**
     * 新增信息
	 * @return 
     */
	String add(HuanxinGroup huanxinGroup);
	
	/**
	 * 修改信息
	 */
	void update(HuanxinGroup huanxinGroup);
	
	/**
	 * 删除信息
	 */
	void del(Integer huanxinGroupId);

	/**
	 * 分配群组人员
	 */
	void distribute(String[] userIds, String groupId);

	/**
	 * 查询已分配群组的人员ID（回显使用）
	 */
	JSONArray finUserIdByGroupId(String groupId);

	void inhabitantDistribute(String[] inhabitantIds, String groupId);

	JSONArray finInhabitantIdByGroupId(String groupId);

	void deleteByGroupId(String groupId, String groupType);

	void groupOtherDistribute(String[] userIds, String[] inhabitantIds, String groupId);
}
