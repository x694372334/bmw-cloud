package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.state.IsMenu;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class HisWarpper extends BaseControllerWarpper {

    public HisWarpper(List<Map<String, Object>> list) {
        super(list);
    }
    
    public HisWarpper(Object list) {
        super(list);
    }
    


    @Override
    public void warpTheMap(JSONObject map) {
        map.put("statusName", ConstantFactory.me().getMenuStatusName((Integer) map.get("status")));
        map.put("isMenuName", IsMenu.valueOf((Integer) map.get("ismenu")));
    }

}
