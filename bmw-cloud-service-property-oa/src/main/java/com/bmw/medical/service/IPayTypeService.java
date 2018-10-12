package com.bmw.medical.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Menu;
import com.bmw.medical.model.PayType;

/**
 * <p>
 * 收费项目分类 服务类
 * </p>
 *
 * @author liuwsh
 * @since 2018-09-18
 */
public interface IPayTypeService {
	JSONArray selectList(PayType payType);

	PayType selectById(Integer payTypeId);

	void insert(PayType payType);

	void update(PayType payType);

	void deleteById(Integer payTypeId);
	
    /**
     * 获取菜单列表树
     *
     */
    List<ZTreeNode> payTypeTreeList();
    
    PayType selectOne(PayType payType);
}