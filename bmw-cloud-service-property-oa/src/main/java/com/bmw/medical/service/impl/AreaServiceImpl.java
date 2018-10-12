package com.bmw.medical.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.app.model.Community;
import com.bmw.medical.model.Area;
import com.bmw.medical.service.IAreaService;

@Service
public class AreaServiceImpl implements IAreaService {


	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_medicalservice_url="";
	
	@Value("${area.areaList}")
	private String area_areaList="";
	
	@Value("${area.areaDetail}")
	private String area_areaDetail="";
	
	@Value("${area.add}")
	private String area_add="";
	
	@Value("${area.update}")
	private String area_update="";
	
	@Value("${area.del}")
	private String area_del="";

	@Value("${area.tree}")
	private String area_tree="";
	
	@Value("${area.countCode}")
	private String area_countCode="";
	
	@Value("${area.countIsCode}")
	private String area_countIsCode="";
	
	@Value("${area.codeSelect}")
	private String area_codeSelect="";
	
	
	@Value("${area.deleteUpdate}")
	private String area_deleteUpdate="";
	

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("name", condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+area_areaList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
//			if(json.size()>0){  
//				  for(int i=0;i<jsonArray.size();i++){  
//					  JSONObject job = jsonArray.getJSONObject(i);   
//				  }  
//			}  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Area getdetail(Integer areaId) {
		Area area=new Area();
		JSONObject json=new JSONObject();
		json.put("areaId", areaId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_medicalservice_url+area_areaDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				area=jsonObject.toJavaObject(Area.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return area;
	}

	@Override
	public void add(Area area) {
		try {
			HttpUtils.doPost(bmw_cloud_medicalservice_url + area_add,
					JSONObject.toJSONString(area).toString(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Area area) {
		try {
			HttpUtils.doPost(bmw_cloud_medicalservice_url + area_update,
					JSONObject.toJSONString(area).toString(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer areaId) {
		JSONObject json=new JSONObject();
        json.put("areaId", areaId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_medicalservice_url+area_del+params,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ZTreeNode> areaTreeList() {
		List<ZTreeNode> list = new ArrayList();
    	JSONArray jsonArray=null; 
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+area_tree+"/1");
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
	
	@Override
	public String areaCountCode(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+area_countCode+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public String areaCountIsCode(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+area_countIsCode+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public String areaCodeSelect(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+area_codeSelect+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public Area areaDeleteUpdate(String code) {
		JSONObject obj=null;
		Area area = new Area();
		try{
			String rlt=HttpUtils.doPost(bmw_cloud_medicalservice_url+area_deleteUpdate+code , null);
			obj=JSON.parseObject(rlt).getJSONObject("items");	
			if(null != obj) {
				area.setId(Integer.parseInt(obj.get("id").toString()));
				area.setChildrenCount(Integer.parseInt(obj.get("childrenCount").toString()));
				area.setName(obj.get("name").toString());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return area;
	}
	
	 /**
	 * JSON转Java实体
	 * @author jmy
	 * 2018年04月19日 
	 * */
	 public static<T> Object JSONToObj(String jsonStr,Class<T> obj) {
	        T t = null;
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            t = objectMapper.readValue(jsonStr,
	                    obj);
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }
	        return t;
	    }
	
}
