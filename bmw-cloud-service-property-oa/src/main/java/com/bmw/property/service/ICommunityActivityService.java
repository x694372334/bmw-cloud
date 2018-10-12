package com.bmw.property.service;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.CommunityActivity;

/**
 * <p>
 * 小区活动 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-25
 */
public interface ICommunityActivityService extends IService<CommunityActivity> {
	public JSONObject selectListByParam(Map<String, Object> columnMap);
	
	public List<ZTreeNode> queryNBTree(Integer id, Integer eId);
	
	public Boolean sub(CommunityActivity entity);
	
	public Boolean pub(CommunityActivity entity);
	
	public Boolean callback(CommunityActivity entity);
	
	public Boolean delete(CommunityActivity entity);
	
	public List<Map<String,Object>> exportExcel(Integer activityId);
	
	public Boolean check(CommunityActivity entity);

}
