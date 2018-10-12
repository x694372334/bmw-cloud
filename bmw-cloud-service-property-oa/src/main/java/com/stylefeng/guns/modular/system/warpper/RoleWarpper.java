package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.util.ToolUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 角色列表的包装类
 *
 * @author lyl
 * @date 2018年4月23日
 */
public class RoleWarpper extends BaseControllerWarpper {

    public RoleWarpper(JSONArray list) {
        super(list);
    }

    @Override
    public void warpTheMap(JSONObject map) {
    	if(!ToolUtil.isEmpty(map.get("pid"))) {
        map.put("pName", ConstantFactory.me().getSingleRoleName((Integer) map.get("pid")));
    	}
        if(!ToolUtil.isEmpty(map.get("deptid"))) {
        map.put("deptName", ConstantFactory.me().getDeptName((Integer) map.get("deptid")));
        }
    }

}
