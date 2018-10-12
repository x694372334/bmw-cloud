package com.bmw.property.huanxin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.bmw.property.huanxin.model.HuanxinGroup;
import com.bmw.property.huanxin.service.IHuanxinGroupService;
import com.bmw.property.model.AContractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-08-14
 */
@Service
public class HuanxinGroupServiceImpl  implements IHuanxinGroupService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${huanxin.huanxinGroupList}")
	private String huanxin_huanxinGroupList="";

	@Value("${huanxin.addHuanxinGroup}")
	private String huanxin_addHuanxinGroup="";
	
	@Value("${huanxin.groupDistribute}")
	private String huanxin_groupDistribute="";
	
	@Value("${huanxin.finUserIdByGroupId}")
	private String huanxin_finUserIdByGroupId="";
	
	@Value("${huanxin.groupInhabitantDistribute}")
	private String huanxin_groupInhabitantDistribute="";
	
	@Value("${huanxin.finInhabitantIdByGroupId}")
	private String huanxin_finInhabitantIdByGroupId="";
	
	@Value("${huanxin.deleteByGroupId}")
	private String huanxin_deleteByGroupId="";
	
	@Value("${huanxin.huanxinGroupIdDetail}")
	private String huanxin_huanxinGroupIdDetail="";
	
	@Value("${huanxin.groupOtherDistribute}")
	private String huanxin_groupOtherDistribute="";
	
	@Value("${huanxin.groupModify}")
	private String huanxin_groupModify="";
	@Override
	public JSONArray findList(HuanxinGroup condition) {
		JSONArray jsonArray=null;
    	try {
    		String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+huanxin_huanxinGroupList,JSONObject.toJSONString(condition).toString(),null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}


	@Override
	public HuanxinGroup getdetail(Integer huanxinGroupId) {
		HuanxinGroup huanxinGroup=new HuanxinGroup();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+huanxin_huanxinGroupIdDetail+huanxinGroupId, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				huanxinGroup=jsonObject.toJavaObject(HuanxinGroup.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return huanxinGroup;
	}

	@Override
	public String add(HuanxinGroup huanxinGroup) {
		JSONArray jsonArray=null;
		ShiroExt shiro = new ShiroExt();
		String result = "";
		huanxinGroup.setCreateManName(shiro.getUser().getName());
		huanxinGroup.setCreateManId(shiro.getUser().getId());
		try {
			String rlt= HttpUtils.doPost(bmw_cloud_baseservice_url+huanxin_addHuanxinGroup,JSONObject.toJSONString(huanxinGroup).toString(),null);
			result = JSON.parseObject(rlt).getString("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void update(HuanxinGroup huanxinGroup) {
		// TODO Auto-generated method stub
		try {
			String rlt= HttpUtils.doGet(bmw_cloud_baseservice_url+huanxin_groupModify+"?id="+huanxinGroup.getGroupId()+"&name="+huanxinGroup.getGroupName()+"&desc="+huanxinGroup.getGroupDesc());
	System.out.println(rlt);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer huanxinGroupId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void distribute(String[] userIds, String groupId) {
		// TODO Auto-generated method stub
		String ids = "";
		if(null!=userIds) {
		    ids = JSONArray.toJSONString(userIds);
		}
		NameValuePair value1 = new BasicNameValuePair("userIds", ids);
		NameValuePair value2 = new BasicNameValuePair("groupId", groupId);
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(value1);
		param.add(value2);
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+huanxin_groupDistribute, param);
			System.out.println(rlt);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void inhabitantDistribute(String[] inhabitantIds, String groupId) {
		// TODO Auto-generated method stub
		String ids = "";
		if(null!=inhabitantIds) {
		    ids = JSONArray.toJSONString(inhabitantIds);
		}
		NameValuePair value1 = new BasicNameValuePair("ids", ids);
		NameValuePair value2 = new BasicNameValuePair("groupId", groupId);
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(value1);
		param.add(value2);
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+huanxin_groupInhabitantDistribute, param);
			System.out.println(rlt);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public JSONArray finUserIdByGroupId(String groupId) {
		JSONArray jsonArray=null;
    	try {
    		String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+huanxin_finUserIdByGroupId+"?groupId="+groupId);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	@Override
	public JSONArray finInhabitantIdByGroupId(String groupId) {
		JSONArray jsonArray=null;
    	try {
    		String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+huanxin_finInhabitantIdByGroupId+"?groupId="+groupId);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	
	@Override
	public void deleteByGroupId(String groupId, String groupType) {
    	try {
    		String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+huanxin_deleteByGroupId+"?groupId="+groupId+"&groupType="+groupType);
		System.out.println(rlt);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void groupOtherDistribute(String[] userIds, String[] inhabitantIds, String groupId) {
		// TODO Auto-generated method stub
				String uIds = "";
				if(null!=userIds) {
					uIds = JSONArray.toJSONString(userIds);
				}
				
				String iIds = "";
				if(null!=inhabitantIds) {
					iIds = JSONArray.toJSONString(inhabitantIds);
				}
				
				NameValuePair value1 = new BasicNameValuePair("userIds", uIds);
				NameValuePair value2 = new BasicNameValuePair("inhabitantIds", iIds);
				NameValuePair value3 = new BasicNameValuePair("groupId", groupId);
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				param.add(value1);
				param.add(value2);
				param.add(value3);
				try {
					String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+huanxin_groupOtherDistribute, param);
					System.out.println(rlt);
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
