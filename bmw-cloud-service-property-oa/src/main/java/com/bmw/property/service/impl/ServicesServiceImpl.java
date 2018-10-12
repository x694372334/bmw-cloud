package com.bmw.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Announcement;
import com.bmw.property.model.Services;
import com.bmw.property.service.IServicesService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * 特色服务 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-17
 */
@Service
public class ServicesServiceImpl  implements IServicesService {
	
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${services.servicesList}")
	private String services_servicesList="";

	@Value("${services.servicesDetail}")
	private String services_servicesDetail="";

	@Value("${services.add}")
	private String services_add="";

	@Value("${services.update}")
	private String services_update="";

	@Value("${services.del}")
	private String services_del="";

	@Value("${serviceSort.getTree}")
	private String serviceSort_getTree="";
	@Override
	public JSONArray findList(Services condition) {
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+services_servicesList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Services getdetail(Integer servicesId) {
		Services services=new Services();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+services_servicesDetail+servicesId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				services=jsonObject.toJavaObject(Services.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return services;
	}

	@Override
	public void add(Services services) {
		ShiroExt shiro = new ShiroExt();
		services.setCreateMan(shiro.getUser().getName());
		services.setCreateManId(shiro.getUser().getId());
		services.setCreateTime(new Date());
		String[] uuid = UUID.randomUUID().toString().split("-");
		services.setServiceCode(uuid[0]);
		services.setnId(1);
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(services);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+services_add, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Services services) {
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(services);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+services_update, namevalue);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer servicesId) {
		List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
		NameValuePair item = new BasicNameValuePair("param", servicesId.toString());
		namevalue.add(item);
		try {
			String s = HttpUtils.doPost(bmw_cloud_baseservice_url+services_del,namevalue);
			System.out.println(s);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ZTreeNode> getServiceSortTree() {
		List<ZTreeNode> list = new ArrayList();
    	JSONArray jsonArray=null; 
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+serviceSort_getTree);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject ob  = (JSONObject) jsonArray.get(i);//得到json对象
				jsonArray.get(i);
				ZTreeNode tree=JSONObject.toJavaObject(ob, ZTreeNode.class);
				list.add(tree);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
