package com.bmw.remindersrecord.service.impl;

import com.bmw.bill.model.Bill;
import com.bmw.bill.model.BillDiscountsVO;
import com.bmw.bill.model.BillVO;
import com.bmw.bill.service.IBillService;
import com.bmw.complaintadvice.model.ComplaintAdvice;
import com.bmw.remindersrecord.service.IRemindersRecordService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.shiro.ShiroKit;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * 账单明细表 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-06-22
 */
@Service
public class RemindersRecordServiceImpl  implements IRemindersRecordService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${remindersrecord.List}")
	private String remindersrecord_List="";

	@Value("${remindersrecord.urge}")
	private String remindersrecord_urge="";
	
	@Override
	public JSONArray findList(Bill condition) {
		condition.seteId(ShiroKit.getUser().geteId());
		JSONArray jsonArray=null;
		/*JSONObject json=new JSONObject();*/
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
		//json.put("tableName", tableName);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+remindersrecord_List+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}


	@Override
	public void urge(String ids) {
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+remindersrecord_urge+ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
