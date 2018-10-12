package com.bmw.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.BuildingInfo;
import com.bmw.property.model.EnterpriseInfo;
import com.bmw.property.service.IEnterpriseInfoService;
import com.bmw.property.warpper.EnterpriseInfoMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * 企业信息 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-25
 */
@Service
public class EnterpriseInfoServiceImpl implements IEnterpriseInfoService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${enterpriseinfo.enterpriseinfoList}")
	private String enterpriseinfo_enterpriseinfoList="";

	@Value("${enterpriseinfo.enterpriseinfoDetail}")
	private String enterpriseinfo_enterpriseinfoDetail="";

	@Value("${enterpriseinfo.add}")
	private String enterpriseinfo_add="";

	@Value("${enterpriseinfo.update}")
	private String enterpriseinfo_update="";

	@Value("${enterpriseinfo.del}")
	private String enterpriseinfo_del="";
	
	@Value("${enterpriseinfo.findTreeList}")
	private String enterpriseinfo_findTreeList="";
	
	@Value("${enterpriseinfo.findParentTreeList}")
	private String enterpriseinfo_findParentTreeList="";
	
	@Value("${enterpriseinfo.findTreeListByParentEId}")
	private String enterpriseinfo_findTreeListByParentEId="";
	
	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("enterpriseName", condition);
		json.put("isDelete", 1);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+enterpriseinfo_enterpriseinfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public EnterpriseInfo getdetail(Integer e_id) {
		EnterpriseInfo enterpriseinfo=new EnterpriseInfo();
		JSONObject json=new JSONObject();
        json.put("e_id", e_id);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+enterpriseinfo_enterpriseinfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				enterpriseinfo=jsonObject.toJavaObject(EnterpriseInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return enterpriseinfo;
	}

	@Override
	public void add(EnterpriseInfo enterpriseinfo) {
		enterpriseinfo.preInsert();
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(enterpriseinfo).toString().getBytes("UTF-8"));
    		List<NameValuePair> param = new ArrayList<NameValuePair>();
    		NameValuePair nameValuePair = new BasicNameValuePair("jsonParams", params);
    		param.add(nameValuePair);
			String s = HttpUtils.doPost(bmw_cloud_baseservice_url+enterpriseinfo_add ,param, null);
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(EnterpriseInfo enterpriseinfo) {
		enterpriseinfo.preUpdate();
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(enterpriseinfo).toString().getBytes("UTF-8"));
    		List<NameValuePair> param = new ArrayList<NameValuePair>();
    		NameValuePair nameValuePair = new BasicNameValuePair("jsonParams", params);
    		param.add(nameValuePair);
			HttpUtils.doPost(bmw_cloud_baseservice_url+enterpriseinfo_update, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String del(Integer e_id) {
		JSONObject json=new JSONObject();
        json.put("e_id", e_id);
        String result = "";
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doDelete(bmw_cloud_baseservice_url+enterpriseinfo_del+params);
			result = JSON.parseObject(rlt).getString("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
	}

	@Override
	public List<ZTreeNode> findTreeList() {
		// TODO Auto-generated method stub
		List<ZTreeNode> list = new ArrayList();
    	JSONArray jsonArray=null; 
    	int isadmin = 0;
    	ShiroExt shiro = new ShiroExt();
		Integer parenteid = shiro.getUser().getParentEId();
		if (shiro.getUser().getIsAdmin()) {
			isadmin = 1;
		}
    	try {
    		String rlt = "";
    		if(1==isadmin) {
    			rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+enterpriseinfo_findTreeList+"/1");
    		}else {
    			rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+enterpriseinfo_findTreeListByParentEId+parenteid);
    		}
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject ob  = (JSONObject) jsonArray.get(i);//得到json对象
				jsonArray.get(i);
				ZTreeNode tree=JSONObject.toJavaObject(ob, ZTreeNode.class);
				list.add(tree);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	@Override
	public List<ZTreeNode> findParentTreeList() {
		// TODO Auto-generated method stub
				List<ZTreeNode> list = new ArrayList();
		    	JSONArray jsonArray=null; 
		    	try {
					String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+enterpriseinfo_findParentTreeList+"/1");
					jsonArray=JSON.parseObject(rlt).getJSONArray("items");
					for(int i=0;i<jsonArray.size();i++) {
						JSONObject ob  = (JSONObject) jsonArray.get(i);//得到json对象
						jsonArray.get(i);
						ZTreeNode tree=JSONObject.toJavaObject(ob, ZTreeNode.class);
						list.add(tree);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	return list;
	}

}
