package com.bmw.app.service.impl;

import com.bmw.app.model.Version;
import com.bmw.app.dao.VersionMapper;
import com.bmw.app.service.IVersionService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
@Service
public class VersionServiceImpl implements IVersionService {

	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${version.versionList}")
	private String version_versionList="";
	
	@Value("${version.versionDetail}")
	private String version_versionDetail="";
	
	@Value("${version.add}")
	private String version_add="";
	
	@Value("${version.update}")
	private String version_update="";
	
	@Value("${version.del}")
	private String version_del="";
	
	
	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+version_versionList+condition);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Version getdetail(Integer versionId) {
		Version version=new Version();
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+version_versionDetail+versionId, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				version=jsonObject.toJavaObject(Version.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return version;
	}

	@Override
	public void add(Version version) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(version).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+version_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Version version) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(version).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+version_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void del(Integer versionId) {
		JSONObject json=new JSONObject();
        json.put("versionId", versionId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+version_del+params,null);
			//缓存被删除的部门名称
//	        LogObjectHolder.me().set(ConstantFactory.me().getversionName(versionId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
