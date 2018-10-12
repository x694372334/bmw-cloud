package com.stylefeng.guns.modular.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.service.IDictService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

@Service
@Transactional
public class DictServiceImpl implements IDictService {
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${dict.getDict}")
	private String dict_getDict="";

	@Value("${dict.detail}")
	private String dict_detail="";

	@Value("${dict.add}")
	private String dict_add="";

	@Value("${dict.edit}")
	private String dict_edit="";

	@Value("${dict.del}")
	private String dict_del="";
	
	@Value("${dict.getDictByPid}")
	private String dict_getDictByPid="";
	
	@Value("${dict.getDictByCode}")
	private String dict_getDictByCode="";
	
	
	@Override
	public JSONArray findList(String condition){
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+dict_getDict + condition);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	@Override
	public Dict getdetail(Integer dictId){
		Dict dict=new Dict();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+dict_detail + dictId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				dict=jsonObject.toJavaObject(Dict.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dict;
	}
	
	@Override
	public JSONArray getDictByPid(Integer dictId){
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+dict_getDictByPid + dictId);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	@Override
	public void add(String dictName, String dictValues) {
		if (ToolUtil.isOneEmpty(dictName, dictValues)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        JSONObject json=new JSONObject();
        json.put("dictName", dictName);
        json.put("dictValues",dictValues);
        
        try {
        	/*String params=Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+dict_add+params ,null);*/
			HttpUtils.doPost(bmw_cloud_baseservice_url+dict_add,json.toString(),null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public void update(Integer dictId, String dictName, String dictValues) {
		if (ToolUtil.isOneEmpty(dictId, dictName, dictValues)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        
        JSONObject json=new JSONObject();
        json.put("dictId", dictId);
        json.put("dictName", dictName);
        json.put("dictValues",dictValues);
        
        try {
			/*String params=Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+dict_edit+params ,null);*/
			HttpUtils.doPost(bmw_cloud_baseservice_url+dict_edit,json.toString(),null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public void del(Integer dictId) {
		JSONObject json=new JSONObject();
        json.put("dictId", dictId);
        
        try {
			String params=Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+dict_del+params );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public JSONArray getDictByCode(String code) {
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+dict_getDictByCode + code);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
   
}
