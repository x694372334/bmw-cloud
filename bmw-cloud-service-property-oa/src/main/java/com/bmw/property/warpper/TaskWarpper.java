package com.bmw.property.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.modular.system.model.Dict;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.ToolUtil;

import java.util.Map;

/**
 * 字典列表的包装
 *
 * @author lyl
 * @date 2018年4月19日
 */
public class TaskWarpper extends BaseControllerWarpper {

    public TaskWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(JSONObject json) {
    	String tPrincipalView = "";
    	if(null!=json.get("tPrincipal")&&!"".equals(json.get("tPrincipal"))) {
        Integer id = Integer.parseInt(json.get("tPrincipal").toString());
        tPrincipalView = ConstantFactory.me().getUserNameById(id);
        json.put("tPrincipalView", tPrincipalView);
    	}
        
        String t_participation_per = json.get("tParticipationPer").toString();
        String tParticipationPerView = ConstantFactory.me().getUserNameByIds(t_participation_per);
        if(tPrincipalView != null&&!"".equals(tParticipationPerView)){
            json.put("tParticipationPerView", tParticipationPerView);
        }
        
    }

}
