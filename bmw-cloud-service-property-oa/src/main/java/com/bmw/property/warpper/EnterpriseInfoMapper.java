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
 * @author
 * @date 2018年4月19日
 */
public class EnterpriseInfoMapper extends BaseControllerWarpper {

    public EnterpriseInfoMapper(Object list) {
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
    }

}
