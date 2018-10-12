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
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Position;
import com.bmw.property.service.IPositionService;

@Service
public class PositionServiceImpl implements IPositionService {


	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_medicalservice_url="";
	
	@Value("${position.positionList}")
	private String position_positionList="";
	
	@Value("${position.positionDetail}")
	private String position_positionDetail="";
	
	@Value("${position.add}")
	private String position_add="";
	
	@Value("${position.update}")
	private String position_update="";
	
	@Value("${position.del}")
	private String position_del="";

	@Value("${position.tree}")
	private String position_tree="";
	
	@Value("${position.countCode}")
	private String position_countCode="";
	
	@Value("${position.countIsCode}")
	private String position_countIsCode="";
	
	@Value("${position.codeSelect}")
	private String position_codeSelect="";
	
	
	@Value("${position.deleteUpdate}")
	private String position_deleteUpdate="";
	

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("name", condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+position_positionList+params);
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
	public Position getdetail(Integer positionId) {
		Position position=new Position();
		JSONObject json=new JSONObject();
		json.put("positionId", positionId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_medicalservice_url+position_positionDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				position=jsonObject.toJavaObject(Position.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return position;
	}

	@Override
	public void add(Position position) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(position).toString().getBytes("UTF-8"));
    		List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("params", params);
			namevalue.add(item);
    		HttpUtils.doPost(bmw_cloud_medicalservice_url+position_add, namevalue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Position position) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(position).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_medicalservice_url+position_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void del(Integer positionId) {
		JSONObject json=new JSONObject();
        json.put("positionId", positionId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_medicalservice_url+position_del+params,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<ZTreeNode> positionTreeList() {
		List<ZTreeNode> list = new ArrayList();
    	JSONArray jsonArray=null; 
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+position_tree+"/1");
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
	public String positionCountCode(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+position_countCode+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public String positionCountIsCode(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+position_countIsCode+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public String positionCodeSelect(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+position_codeSelect+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public Position positionDeleteUpdate(String code) {
		JSONObject obj=null;
		Position position = new Position();
		try{
			String rlt=HttpUtils.doPost(bmw_cloud_medicalservice_url+position_deleteUpdate+code , null);
			obj=JSON.parseObject(rlt).getJSONObject("items");	
			position.setId(Integer.parseInt(obj.get("id").toString()));
			position.setChildrenCount(Integer.parseInt(obj.get("childrenCount").toString()));
			position.setName(obj.get("name").toString());
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return position;
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
