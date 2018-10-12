package com.bmw.bill.warpper;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Dict;

/**
 * 字典列表的包装
 *
 * @author lyl
 * @date 2018年4月19日
 */
public class BillWarpper extends BaseControllerWarpper {

    public BillWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(JSONObject map) {
        StringBuffer detail = new StringBuffer();
        Integer id = Integer.parseInt(map.get("id").toString());
        JSONArray jsonArray = ConstantFactory.me().findInDict(id);
        if(jsonArray != null){
            for (int i=0;i<jsonArray.size();i++) {
            	Dict dict=jsonArray.getObject(i,Dict.class);
                detail.append(dict.getNum() + ":" +dict.getName() + ",");
            }
            map.put("detail", ToolUtil.removeSuffix(detail.toString(),","));
        }
        
        Timestamp costStartTime = map.getTimestamp("costStartTime");
		Timestamp costEndTime = map.getTimestamp("costEndTime");
		if (costStartTime != null) {
			map.put("costStartTime", DateUtil.format(costStartTime, "yyyy-MM-dd"));
		}
		if (costEndTime != null) {
			map.put("costEndTime", DateUtil.format(costEndTime, "yyyy-MM-dd"));
		}
        
        
        
    }

}
