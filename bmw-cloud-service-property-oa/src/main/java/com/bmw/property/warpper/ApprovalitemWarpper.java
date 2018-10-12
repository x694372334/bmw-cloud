package com.bmw.property.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.ToolUtil;
import com.bmw.property.model.BuildingInfo;

import java.util.Map;

/**
 * 字典列表的包装
 *
 * @author lyl
 * @date 2018年4月19日
 */
public class ApprovalitemWarpper extends BaseControllerWarpper {

    public ApprovalitemWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(JSONObject map) {
//        StringBuffer detail = new StringBuffer();
//        Integer id = Integer.parseInt(map.get("aId").toString());
//        JSONArray jsonArray = ConstantFactory.me().findInDict(id);
//        if(jsonArray != null){
//            for (int i=0;i<jsonArray.size();i++) {
//            	BuildingInfo dict=jsonArray.getObject(i,BuildingInfo.class);
//                detail.append(dict.getbName() + ",");
//            }
//            map.put("detail", ToolUtil.removeSuffix(detail.toString(),","));
//        }
    }


}
