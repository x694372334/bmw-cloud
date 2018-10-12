package com.stylefeng.guns.modular.system.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.CommonUtils;
import com.common.utils.HttpUtils;
import com.common.utils.JSONUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.model.UserRoleVO;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.bmw.property.model.Services;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class UserServiceImpl implements IUserService {
	
	
	static Logger log =LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${user.detailUser}")
	private String user_detailUser="";
	
	@Value("${user.selectUsers}")
	private String user_selectUsers="";
	
	@Value("${user.getByAccount}")
	private String user_getByAccount="";
	
	@Value("${user.add}")
	private String user_add="";
	
	@Value("${user.edit}")
	private String user_edit="";
	
	@Value("${user.del}")
	private String user_del="";
	
	@Value("${user.setStatus}")
	private String user_setStatus="";
	
	@Value("${user.changePwd}")
	private String user_changePwd="";
	
	@Value("${user.setRoles}")
	private String user_setRoles="";
			
	@Value("${user.userList}")
	private String user_userList="";

	@Value("${user.findViewByUserId}")
    private String user_findViewByUserId="";
	
	@Value("${user.finduserList}")
    private String user_finduserList="";
	
	@Value("${user.findUserRoleViewByEid}")
    private String  user_findUserRoleViewByEid="";
	
	@Value("${user.findUserRoleViewByEidAndIsHuanxin}")
    private String  user_findUserRoleViewByEidAndIsHuanxin="";
	
	@Value("${huanxin.userDropByUserId}")
    private String huanxin_userDropByUserId="";
	
    @Override
    public void setStatus(Integer userId, int status) {
    	JSONObject json =new JSONObject();
    	json.put("userId", userId);
    	json.put("status", status);
    	try {
			String param=new String(Base64Utils.encode(json.toString().getBytes("UTF-8")));
			HttpUtils.doPost(bmw_cloud_baseservice_url+user_setStatus+param, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @Override
    public void changePwd(Integer userId, String pwd) {
    	JSONObject json =new JSONObject();
    	json.put("userId", userId);
    	json.put("pwd", pwd);
    	try {
			String param=new String(Base64Utils.encode(json.toString().getBytes("UTF-8")));
			HttpUtils.doPost(bmw_cloud_baseservice_url+user_changePwd+param, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    

    @Override
    public  String setRoles(Integer userId, String roleIds) {
    	JSONObject json =new JSONObject();
    	String result = "1";
    	json.put("userId", userId);
    	json.put("roleIds", roleIds);
    	try {
			String param=new String(Base64Utils.encode(json.toString().getBytes("UTF-8")));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+user_setRoles+param, null);
			System.out.println(rlt);
		    String flag = JSONObject.parseObject(rlt).getString("items");
		    if("ERRO".equals(flag)) {
		    	result = "0";
		    }else if("ERRO2".equals(flag)) {
		    	result = "2";
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			return result;
    }

    @Override
    public User getByAccount(String account) {
    	User user=new User();
    	try {
    		String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+user_getByAccount+account);
    		log.debug(bmw_cloud_baseservice_url+user_getByAccount+account);
    		log.debug("----------------rlt---------------------"+rlt);
    		user=JSONObject.toJavaObject(JSONObject.parseObject(new String(Base64Utils.decode(JSONObject.parseObject(rlt).getString("items").getBytes("UTF-8")))),User.class);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return user;
    }
    
    
    
    
    
    
    @Override
    public JSONArray selectUsers(@Param("name") String name, @Param("beginTime") String beginTime, 
    								@Param("endTime") String endTime, @Param("deptid") Integer deptid, @Param("parenteid") Integer parenteid) {
    	JSONArray rltArray=new JSONArray();
    	JSONObject json=new JSONObject();
    	int isadmin = 0;
    	ShiroExt shiro = new ShiroExt();
		if (shiro.getUser().getIsAdmin()) {
			isadmin = 1;
			json.put("uAttribute", "-1");
		}
		if(!shiro.getUser().getIsAdmin()) {
			parenteid = shiro.getUser().getParentEId();
		}
    	
	/*	if(1==isadmin) {
    		json.put("parenteid", "");
    	}else {*/
    		json.put("parenteid", parenteid);
    	/*}*/
    	if(StringUtils.isBlank(name)) {
    		json.put("name", "");
    	}else {
    		json.put("name", name);
    	}
    	if(StringUtils.isBlank(beginTime)) {
    		json.put("beginTime", "");
    	}else {
    		json.put("beginTime", beginTime);
    	}
    	if(StringUtils.isBlank(endTime)) {
    		json.put("endTime", "");
    	}else {
    		json.put("endTime", endTime);
    	}
    	if(deptid==null) {
    		json.put("deptid", 0);
    	}else {
    		json.put("deptid", deptid);
    	}
    	
    	try {
			String params=new String(Base64Utils.encode(json.toString().getBytes("UTF-8")));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+user_selectUsers+params);
			rltArray=JSONObject.parseArray(new String(Base64Utils.decode(JSONObject.parseObject(rlt).getString("items").getBytes("UTF-8"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return rltArray;
    }
    
    @Override
    public User detailUser(Integer userId) {
    	User user =new User();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+user_detailUser+userId,null);
			JSONObject object=JSONObject.parseObject(rlt);
			String userStr=new String(Base64Utils.decode(object.getString("items").getBytes("UTF-8")));
			user=JSONObject.toJavaObject(JSONObject.parseObject(userStr), User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return user;
    }
    
    @Override
    public void add(User user) {
    	try {
			String userStr=JSONObject.toJSONString(user);
			String param=new String(Base64Utils.encode(userStr.getBytes("UTF-8")));
			HttpUtils.doPost(bmw_cloud_baseservice_url+user_add+param, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    @Override
    public void edit(User user) {
    	try {
			String userStr=JSONUtils.convertObject2Json(user);
			String param=new String(Base64Utils.encode(userStr.getBytes("UTF-8")));
			HttpUtils.doPost(bmw_cloud_baseservice_url+user_edit+param, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    
    @Override
    public void del(Integer userId) {
    	try {
			HttpUtils.doDelete(bmw_cloud_baseservice_url+user_edit+userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    @Override
	public JSONArray findList(User condition) {
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+user_userList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
    
	@Override
	public List<UserRoleVO> findUserRoleViewByUserId(Integer userId) {
		JSONArray jsonArray = null;
		List<UserRoleVO> result =new ArrayList<UserRoleVO>();
		String rlt="";
		try {
			rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + user_findViewByUserId + userId);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonArray = JSON.parseObject(rlt).getJSONArray("items");
		for (int i = 0; i < jsonArray.size(); i++) {// 循环json数组
			JSONObject ob = (JSONObject) jsonArray.get(i);// 得到json对象
			UserRoleVO userRoleVO = JSONObject.toJavaObject(ob, UserRoleVO.class);
			result.add(userRoleVO);
		}
		return result;
	}
	
	
	  @Override
		public JSONArray finduserList(User condition) {
			JSONArray jsonArray=null;
	    	try {
	    		String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+user_finduserList,JSONObject.toJSONString(condition).toString(),null);
				jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return jsonArray;
		}
	  
		@Override
		public List<UserRoleVO> findUserRoleViewByEid(Integer eId) {
			JSONArray jsonArray = null;
			List<UserRoleVO> result =new ArrayList<UserRoleVO>();
			String rlt="";
			try {
				rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + user_findUserRoleViewByEid + eId);
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
			for (int i = 0; i < jsonArray.size(); i++) {// 循环json数组
				JSONObject ob = (JSONObject) jsonArray.get(i);// 得到json对象
				UserRoleVO userRoleVO = JSONObject.toJavaObject(ob, UserRoleVO.class);
				result.add(userRoleVO);
			}
			return result;
		}
		
		
		@Override
		public String userDropByUserId(Integer userId) {
			try {
				HttpUtils.doGet(bmw_cloud_baseservice_url + huanxin_userDropByUserId + "?userId=" + userId);
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		}

		@Override
		public List<UserRoleVO> findUserRoleViewByEidAndIsHuanxin(Integer eId) {
			JSONArray jsonArray = null;
			List<UserRoleVO> result =new ArrayList<UserRoleVO>();
			String rlt="";
			try {
				rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + user_findUserRoleViewByEidAndIsHuanxin + eId);
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
			for (int i = 0; i < jsonArray.size(); i++) {// 循环json数组
				JSONObject ob = (JSONObject) jsonArray.get(i);// 得到json对象
				UserRoleVO userRoleVO = JSONObject.toJavaObject(ob, UserRoleVO.class);
				result.add(userRoleVO);
			}
			return result;
		}
}
