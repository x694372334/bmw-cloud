package com.bmw.property.warpper;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

/**
 * 字典列表的包装
 *
 * @author lyl
 * @date 2018年4月19日
 */
public class SelectBulterWarpper extends BaseControllerWarpper {

    public SelectBulterWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(JSONObject map) {
//        StringBuffer detail = new StringBuffer();
//        Integer id = Integer.parseInt(map.get("bId").toString());
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
