package com.bmw.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.HelpDocumentType;
import com.bmw.property.service.IHelpDocumentTypeService;

@Service
public class HelpDocumentTypeServiceImpl implements IHelpDocumentTypeService {


	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_medicalservice_url="";
	
	@Value("${helpDocumentType.helpDocumentTypeList}")
	private String helpDocumentType_helpDocumentTypeList="";
	
	@Value("${helpDocumentType.helpDocumentTypeDetail}")
	private String helpDocumentType_helpDocumentTypeDetail="";
	
	@Value("${helpDocumentType.add}")
	private String helpDocumentType_add="";
	
	@Value("${helpDocumentType.update}")
	private String helpDocumentType_update="";
	
	@Value("${helpDocumentType.del}")
	private String helpDocumentType_del="";

	@Value("${helpDocumentType.tree}")
	private String helpDocumentType_tree="";
	
	@Value("${helpDocumentType.countCode}")
	private String helpDocumentType_countCode="";
	
	@Value("${helpDocumentType.countIsCode}")
	private String helpDocumentType_countIsCode="";
	
	@Value("${helpDocumentType.codeSelect}")
	private String helpDocumentType_codeSelect="";
	
	
	@Value("${helpDocumentType.deleteUpdate}")
	private String helpDocumentType_deleteUpdate="";
	

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		ShiroExt shiro = new ShiroExt();
		json.put("name", condition);
		json.put("parentEId", shiro.getUser().getParentEId());
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+helpDocumentType_helpDocumentTypeList+params);
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
	public HelpDocumentType getdetail(Integer helpDocumentTypeId) {
		HelpDocumentType helpDocumentType=new HelpDocumentType();
		JSONObject json=new JSONObject();
		json.put("helpDocumentTypeId", helpDocumentTypeId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_medicalservice_url+helpDocumentType_helpDocumentTypeDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				helpDocumentType=jsonObject.toJavaObject(HelpDocumentType.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return helpDocumentType;
	}

	@Override
	public void add(HelpDocumentType helpDocumentType) {
		try {
			ShiroExt shiro = new ShiroExt();
			helpDocumentType.setParentEId(shiro.getUser().getParentEId());
    		String params=Base64Utils.encodeToString(JSON.toJSONString(helpDocumentType).toString().getBytes("UTF-8"));
    		List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("params", params);
			namevalue.add(item);
    		HttpUtils.doPost(bmw_cloud_medicalservice_url+helpDocumentType_add, namevalue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(HelpDocumentType helpDocumentType) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(helpDocumentType).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_medicalservice_url+helpDocumentType_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void del(Integer helpDocumentTypeId) {
		JSONObject json=new JSONObject();
        json.put("helpDocumentTypeId", helpDocumentTypeId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_medicalservice_url+helpDocumentType_del+params,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<ZTreeNode> helpDocumentTypeTreeList() {
		List<ZTreeNode> list = new ArrayList();
    	JSONArray jsonArray=null; 
    	try {
    		ShiroExt shiro = new ShiroExt();
			String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+helpDocumentType_tree+shiro.getUser().getParentEId());
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
	public String helpDocumentTypeCountCode(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+helpDocumentType_countCode+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public String helpDocumentTypeCountIsCode(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+helpDocumentType_countIsCode+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public String helpDocumentTypeCodeSelect(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+helpDocumentType_codeSelect+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public HelpDocumentType helpDocumentTypeDeleteUpdate(String code) {
		JSONObject obj=null;
		HelpDocumentType helpDocumentType = new HelpDocumentType();
		try{
			String rlt=HttpUtils.doPost(bmw_cloud_medicalservice_url+helpDocumentType_deleteUpdate+code , null);
			obj=JSON.parseObject(rlt).getJSONObject("items");	
			helpDocumentType.setId(Integer.parseInt(obj.get("id").toString()));
			helpDocumentType.setChildrenCount(Integer.parseInt(obj.get("childrenCount").toString()));
			helpDocumentType.setName(obj.getString("name"));
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return helpDocumentType;
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
