package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 用户管理的包装类
 *
 * @author lyl
 * @date 2017年2月13日 下午10:47:03
 */
public class UserWarpper extends BaseControllerWarpper {
	
	private String url;
	
    public UserWarpper(JSONArray list) {
        super(list);
    }
    
    public UserWarpper(JSONArray list,String url) {
        super(list);
        this.url = url;
    }

    @Override
    public void warpTheMap(JSONObject map) {
        map.put("sexName", ConstantFactory.me().getSexName((Integer) map.get("sex")));
        map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("roleid")));
        map.put("deptName", ConstantFactory.me().getDeptName((Integer) map.get("deptid")));
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    	String rlt;
		try {
			rlt = HttpUtils.doGet(url+
					"?userId="+map.get("id"));
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				map.put("huanxinFlag","是");
			}else {
				map.put("huanxinFlag","否");
			}
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
