package com.stylefeng.guns.core.base.warpper;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 控制器查询结果的包装类基类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:49:36
 */
public abstract class BaseControllerWarpper {

    public Object obj = null;

    public BaseControllerWarpper(Object obj) {
        this.obj = obj;
    }

    public Object warp() {
        if (this.obj instanceof List) {
        	JSONArray jsonArray = (JSONArray) JSONArray.toJSON(this.obj);
        	for(int i=0;i<jsonArray.size();i++){
        		JSONObject json = jsonArray.getJSONObject(i);
        		warpTheMap(json);
        	}
            return jsonArray;
        } else if (this.obj instanceof Map) {
        	JSONObject json = (JSONObject) JSONObject.toJSON(obj);
        	warpTheMap(json);
            return json;
        } else {
            return this.obj;
        }
    }

    protected abstract void warpTheMap(JSONObject  json);
}
