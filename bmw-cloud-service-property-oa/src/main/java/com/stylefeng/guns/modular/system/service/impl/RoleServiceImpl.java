package com.stylefeng.guns.modular.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.modular.system.model.Relation;
import com.stylefeng.guns.modular.system.model.Role;
import com.stylefeng.guns.modular.system.service.IRoleService;


@Service
public class RoleServiceImpl implements IRoleService {
			
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${role.getRole}")
	private String role_getRole="";
	
	@Value("${role.findRole}")
	private String role_findRole="";
	
	@Value("${role.getRoleDetail}")
	private String role_getRoleDetail="";
	
	@Value("${role.add}")
	private String role_add="";
	
	@Value("${role.edit}")
	private String role_edit="";
	
	@Value("${role.del}")
	private String role_del="";
	
	@Value("${role.roleTreeList}")
	private String role_roleTreeList="";
	
	@Value("${role.roleTreeListByRoleId}")
	private String role_roleTreeListByRoleId="";
	
	@Value("${relation.add}")
	private String relation_add="";

	@Value("${role.roleTreeListByParentEId}")
	private String role_roleTreeListByParentEId="";

    @Resource
    private RelationServiceImpl relationService;

    @Override
    @Transactional(readOnly = false)
    public void setAuthority(Integer roleId, String ids) {

        try {
			// 删除该角色所有的权限
        	relationService.deleteRelationByRole(roleId);
			// 添加新的权限
			for (Long id : Convert.toLongArray(true, Convert.toStrArray(",", ids))) {
			    Relation relation = new Relation();
			    relation.setRoleid(roleId);
			    relation.setMenuid(id);
			    relationService.add(relation);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    @Override
	public void setAppAuthority(Integer roleId, String ids) {
 		 relationService.addAppAssign(roleId,ids);
	}

   
    
    @Override
	public JSONArray findList(String condition){
		JSONArray jsonArray=null;
		int isadmin = 0;
		ShiroExt shiro = new ShiroExt();
		Integer parenteid = shiro.getUser().getParentEId();
		if (shiro.getUser().getIsAdmin()) {
			isadmin = 1;
		}
		String rlt= "";
    	try {
    		if(1==isadmin) {
    			rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+role_getRole + condition+"/0");
    		}else {
    			rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+role_getRole + condition+"/"+parenteid);
    		}
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
    
    
    @Override
	public JSONArray findRole(Role condition){
		JSONArray jsonArray=null;
		String rlt= "";
    	try {
    		rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+role_findRole,JSONObject.toJSONString(condition).toString(),null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
    
    @Override
    public Role getRoleDetail(Integer roleId) {
    	Role role=new Role();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+role_getRoleDetail + roleId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				role=jsonObject.toJavaObject(Role.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return role;
    }
    
    @Override
	public String add(Role role) {
    	String result = "1";
    	ShiroExt shiro = new ShiroExt();
		Integer parentEId = shiro.getUser().getParentEId();
		if(shiro.getUser().isAdmin) {
			//role.setParentEId(null);
			Role condition = new Role();
			condition.setParentEId(role.getParentEId());
			condition.setVersion(1);
			if(findRole(condition).size()>0) {
				result = "2";
				return result;
			}
		}else {
		role.setParentEId(parentEId);
		}
        try {
        	String params=Base64Utils.encodeToString(JSONObject.toJSONString(role).getBytes("UTF-8"));
        	List<NameValuePair> param = new ArrayList<NameValuePair>();
        	param.add(new BasicNameValuePair("jsonParams",params));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+role_add ,param);
			String flag = JSONObject.parseObject(rlt).getString("items");
			if("ERRO".equals(flag)) {
		    	result = "0";
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return result;
	}
    
    
    @Override
	public String edit(Role role) {
    	String result = "1";
        try {
        	String params=Base64Utils.encodeToString(JSONObject.toJSONString(role).getBytes("UTF-8"));
        	List<NameValuePair> param = new ArrayList<NameValuePair>();
        	param.add(new BasicNameValuePair("jsonParams",params));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+role_edit ,param);
			String flag = JSONObject.parseObject(rlt).getString("items");
			if("ERRO".equals(flag)) {
		    	result = "0";
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return result;
	}
    
    
    @Override
	public String del(Integer roleId) {
    	JSONObject json=new JSONObject();
        json.put("roleId", roleId);
        String result ="";
        try {
        	String params=Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doDelete(bmw_cloud_baseservice_url+role_del+params);
			result = JSON.parseObject(rlt).getString("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return result;
	}
    
    
    @Override
	public JSONArray roleTreeList() {
    	JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+role_roleTreeList);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return jsonArray;
	}
    
    @Override
  	public JSONArray roleTreeListByParentEId(Integer parenteid) {
      	JSONArray jsonArray=null;
      	try {
  			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+role_roleTreeListByParentEId+parenteid);
  			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
      	return jsonArray;
  	}
      
    
    @Override
    public JSONArray roleTreeListByRoleId(String[] roleId) {
    	JSONArray jsonArray=new JSONArray();
    	List<String> listStrings = Arrays.asList(roleId);  
    	Map<String, String> map = listStrings.stream().collect(Collectors.toMap(String::toString, a -> a,(b1,b)->b));

    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+role_roleTreeList);
			JSONArray rltJson=JSON.parseObject(rlt).getJSONArray("items");
			for(int i=0;i<rltJson.size();i++) {
				JSONObject ob  = (JSONObject) rltJson.get(i);//得到json对象
				ZTreeNode tree=JSONObject.toJavaObject(ob, ZTreeNode.class);
				String id=ob.getString("id");
				if(map.get(id)!=null){
					tree.setChecked(true);
					
				}
				jsonArray.add(tree);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return jsonArray;
    }
    
    @Override
    public JSONArray roleTreeListByParentEIdAndRoleId(Integer parenteid,String[] roleId) {
    	JSONArray jsonArray=new JSONArray();
    	List<String> listStrings = Arrays.asList(roleId);  
    	Map<String, String> map = listStrings.stream().collect(Collectors.toMap(String::toString, a -> a,(b1,b)->b));

    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+role_roleTreeListByParentEId+parenteid);
			JSONArray rltJson=JSON.parseObject(rlt).getJSONArray("items");
			for(int i=0;i<rltJson.size();i++) {
				JSONObject ob  = (JSONObject) rltJson.get(i);//得到json对象
				ZTreeNode tree=JSONObject.toJavaObject(ob, ZTreeNode.class);
				String id=ob.getString("id");
				if(map.get(id)!=null){
					tree.setChecked(true);
					
				}
				jsonArray.add(tree);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return jsonArray;
    }

}
