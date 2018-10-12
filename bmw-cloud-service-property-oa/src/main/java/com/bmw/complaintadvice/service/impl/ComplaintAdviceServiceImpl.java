package com.bmw.complaintadvice.service.impl;

import com.bmw.complaintadvice.model.ComplaintAdvice;
import com.bmw.complaintadvice.service.IComplaintAdviceService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.shiro.ShiroKit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * 投诉建议 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-06-21
 */
@Service
public class ComplaintAdviceServiceImpl  implements IComplaintAdviceService {
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${complaintAdvice.complaintAdviceList}")
	private String complaintAdvice_complaintAdviceList="";

	@Value("${complaintAdvice.complaintAdviceDetail}")
	private String complaintAdvice_complaintAdviceDetail="";

	@Value("${complaintAdvice.add}")
	private String complaintAdvice_add="";

	@Value("${complaintAdvice.update}")
	private String complaintAdvice_update="";

	@Value("${complaintAdvice.del}")
	private String complaintAdvice_del="";

	@Override
	public JSONArray findList(ComplaintAdvice condition) {
		condition.seteId(ShiroKit.getUser().geteId());
		JSONArray jsonArray=null;
		/*JSONObject json=new JSONObject();*/
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
		//json.put("tableName", tableName);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+complaintAdvice_complaintAdviceList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public ComplaintAdvice getdetail(Integer complaintAdviceId) {
		ComplaintAdvice complaintAdvice=new ComplaintAdvice();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+complaintAdvice_complaintAdviceDetail+complaintAdviceId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				complaintAdvice=jsonObject.toJavaObject(ComplaintAdvice.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return complaintAdvice;
	}

	@Override
	public void add(ComplaintAdvice complaintAdvice) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(complaintAdvice).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+complaintAdvice_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(ComplaintAdvice complaintAdvice) {
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(complaintAdvice).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url + complaintAdvice_update + params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer complaintAdviceId) {
		JSONObject json=new JSONObject();
        json.put("complaintAdviceId", complaintAdviceId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
    		HttpUtils.doPost(bmw_cloud_baseservice_url+complaintAdvice_del+params,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@Value("${table.findCode}")
	private String table_findCode="";
	
	@Value("${table.tree}")
	private String table_tree="";*/

}
