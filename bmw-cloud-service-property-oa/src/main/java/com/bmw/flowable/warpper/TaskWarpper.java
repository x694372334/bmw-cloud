package com.bmw.flowable.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.modular.system.model.Dict;
import com.bmw.flowable.model.TaskVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.ToolUtil;

import java.util.List;
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
    	/*String userName = "";
    	if(null!=json.get("userId")&&!"".equals(json.get("userId"))) {
        Integer id = Integer.parseInt(json.get("userId").toString());
        userName = ConstantFactory.me().getUserNameById(id);
    	}else {
    		userName = null;
    	}
        if(userName != null){
            json.put("userName", userName);
        }*/
        
        String businessTypeName = "";
		if (null != json.get("businessTypeNum") && !"".equals(json.get("businessTypeNum"))) {
			String businessTypeNum = json.get("businessTypeNum").toString();
			JSONArray sxlx = ConstantFactory.me().findDictByCode("sxlx");
			String array = sxlx.toJSONString();
			List<Dict> volist = JSONArray.parseArray(array, Dict.class);
			for (Dict dict : volist) {
				if (Integer.parseInt(businessTypeNum) == dict.getNum()) {
					businessTypeName = dict.getName();
				}
			}
		} else {
			businessTypeName = null;
		}
        if(businessTypeName != null&&!"".equals(businessTypeName)){
            json.put("businessTypeName", businessTypeName);
        }
        
    }

}
