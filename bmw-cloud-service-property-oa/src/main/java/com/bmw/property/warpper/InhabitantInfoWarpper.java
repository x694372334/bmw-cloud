package com.bmw.property.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.ToolUtil;
import com.bmw.property.model.InhabitantInfo;

import java.util.Map;

/**
 * 字典列表的包装
 *
 * @author lyl
 * @date 2018年4月19日
 */
public class InhabitantInfoWarpper extends BaseControllerWarpper {

    public InhabitantInfoWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(JSONObject map) {

    }


}
