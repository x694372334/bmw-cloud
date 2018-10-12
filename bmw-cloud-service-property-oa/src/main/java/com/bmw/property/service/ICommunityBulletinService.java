package com.bmw.property.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.CommunityBulletin;

/**
 * <p>
 * 小区公告 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-19
 */
public interface ICommunityBulletinService{
	
	public CommunityBulletin selectById(Serializable id) ;
	
	public Tip insert(CommunityBulletin entity) ;
	
	public Tip updateById(CommunityBulletin entity) ;

	public JSONObject selectListByParam(Map<String, Object> param);
	
	public List<String> getBuildingNames(Integer cbId);
	
	public List<ZTreeNode> queryBuildingTree(Integer cbId, Integer eId);
	
	public Tip sub(CommunityBulletin entity);
	
	public Tip pub(CommunityBulletin entity);
	
	public Tip callback(CommunityBulletin entity);
	
	public Tip delete(CommunityBulletin entity);
	
	public Tip check(CommunityBulletin entity);
}
