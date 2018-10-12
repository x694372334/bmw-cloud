package com.bmw.property.service.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.codec.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.CommunityActivity;
import com.bmw.property.model.CommunityBulletin;
import com.bmw.property.service.ICommunityActivityService;

/**
 * <p>
 * 小区活动 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-25
 */
@Service
public class CommunityActivityServiceImpl  implements ICommunityActivityService {
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url="";
	@Value("${communityActivity.selectListByParam}")
	private String communityActivity_selectListByParam="";
	@Value("${communityActivity.sub}")
	private String communityActivity_sub="";
	@Value("${communityActivity.pub}")
	private String communityActivity_pub="";
	@Value("${communityActivity.callback}")
	private String communityActivity_callback="";
	@Value("${communityActivity.delete}")
	private String communityActivity_delete="";
	@Value("${communityActivity.queryNBTree}")
	private String communityActivity_queryNBTree="";
	@Value("${communityActivity.insert}")
	private String communityActivity_insert="";
	@Value("${communityActivity.selectById}")
	private String communityActivity_selectById="";
	@Value("${communityActivity.updateById}")
	private String communityActivity_updateById="";
	@Value("${communityActivity.exportExcel}")
	private String communityActivity_exportExcel="";
	@Value("${communityActivity.check}")
	private String communityActivity_check="";
	
	
	/**
	 * 新增小区活动
	 */
	@Override
	public boolean insert(CommunityActivity entity) {
		String jsonrlt="";
		System.out.println(JSONObject.toJSONString(entity));
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+communityActivity_insert,JSONObject.toJSONString(entity).toString(),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean updateById(CommunityActivity entity) {
		String jsonrlt="";
		System.out.println(JSONObject.toJSONString(entity));
		try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+communityActivity_updateById,JSONObject.toJSONString(entity).toString(),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 查询小区活动列表
	 * @author 张珵珺
	 * @date 2018-06-25
	 */
	public JSONObject selectListByParam(Map<String, Object> columnMap){
		JSONObject paramOjbect=new JSONObject();
		paramOjbect.put("eId",columnMap.get("eId"));
		paramOjbect.put("initiatorId", columnMap.get("initiatorId"));
		paramOjbect.put("activityName_", columnMap.get("activityName"));
		paramOjbect.put("verifierId", columnMap.get("verifierId"));
		paramOjbect.put("pageSize", columnMap.get("pageSize"));
		paramOjbect.put("pageNum", columnMap.get("pageNum"));
		JSONObject jsonrlt=new JSONObject();		
		try {
			String result=HttpUtils.doGet(bmw_cloud_propertyservice_url+communityActivity_selectListByParam+new String(Base64Utils.encode(paramOjbect.toString().getBytes("utf-8"))));
			jsonrlt=JSONObject.parseObject(result).getJSONObject("items");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jsonrlt;
	}
	/**
	 * 查询树
	 */
	@Override
	public List<ZTreeNode> queryNBTree(Integer caId, Integer eId) {
		String rlt="";
		JSONObject jsonParam=new JSONObject();
		jsonParam.put("caId", caId);
		jsonParam.put("eId", eId);
    	List<ZTreeNode> treeList=new ArrayList<>();
    	try {
	    	//根据企业ID查询小区树
			rlt = HttpUtils.doGet(bmw_cloud_propertyservice_url+communityActivity_queryNBTree+
					new String(Base64Utils.encode(jsonParam.toString().getBytes("utf-8"))));
			if(rlt!=null) {
				JSONObject jsonrlt=JSONObject.parseObject(rlt).getJSONObject("items");
				treeList=jsonrlt.getJSONArray("treeList").toJavaList(ZTreeNode.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return treeList;
	}

	
	@Override
	public Boolean sub(CommunityActivity entity) {
		String jsonrlt="";
		System.out.println(JSONObject.toJSONString(entity));
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+communityActivity_sub+new String(Base64Utils.encode(JSONObject.toJSONString(entity).toString().getBytes("UTF-8"))),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean pub(CommunityActivity entity) {
		String jsonrlt="";
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+communityActivity_pub+new String(Base64Utils.encode(JSONObject.toJSONString(entity).toString().getBytes("UTF-8"))),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean callback(CommunityActivity entity) {
		String jsonrlt="";
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+communityActivity_callback+new String(Base64Utils.encode(JSONObject.toJSONString(entity).toString().getBytes("UTF-8"))),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Boolean check(CommunityActivity entity) {
		String jsonrlt="";
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+communityActivity_check,JSONObject.toJSONString(entity).toString(),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean delete(CommunityActivity entity) {
		String jsonrlt="";
    	try {
			String rlt=HttpUtils.doDelete(bmw_cloud_propertyservice_url+communityActivity_delete+new String(Base64Utils.encode(JSONObject.toJSONString(entity).toString().getBytes("UTF-8"))),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public CommunityActivity selectById(Serializable id) {
		String rlt="";
		CommunityActivity ca=new CommunityActivity();
		try {
			rlt = HttpUtils.doGet(bmw_cloud_propertyservice_url+communityActivity_selectById+new String(Base64Utils.encode(id.toString().getBytes("UTF-8"))));
			if(rlt!=null) {
				JSONObject jsonrlt=JSONObject.parseObject(rlt).getJSONObject("items");
				ca=jsonrlt.toJavaObject(CommunityActivity.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ca;
	}
	@Override
	public List<Map<String, Object>> exportExcel(Integer activityId) {
		String rlt="";
		List<Map<String,Object>> retList= new ArrayList<>();
		try {
			rlt = HttpUtils.doGet(bmw_cloud_propertyservice_url+communityActivity_exportExcel+new String(Base64Utils.encode(activityId.toString().getBytes("UTF-8"))));
			if(rlt!=null) {
				JSONArray jsonrlt=JSONObject.parseObject(rlt).getJSONArray("items");
				for(int i=0;i<jsonrlt.size();i++) {
					JSONObject j=jsonrlt.getJSONObject(i);
					Map<String,Object> m=new LinkedHashMap<>();
					m.put("ownerName",j.get("ownerName"));
					m.put("roomNum",j.get("roomNum"));
					m.put("phoneNo",j.get("phoneNo"));
					m.put("activityTypeName",j.get("activityTypeName"));
					m.put("signUpStatusName",j.get("signUpStatusName"));
					m.put("voteStatusName",j.get("voteStatusName"));
					retList.add(m);
				}
			}
		}catch(Exception e) {
			
		}
		return retList;
	}
	@Override
	public List<CommunityActivity> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public boolean insertAllColumn(CommunityActivity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<CommunityActivity> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<CommunityActivity> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<CommunityActivity> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<CommunityActivity> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumnBatch(List<CommunityActivity> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumnBatch(List<CommunityActivity> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean updateAllColumnById(CommunityActivity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CommunityActivity entity, Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<CommunityActivity> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<CommunityActivity> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnBatchById(List<CommunityActivity> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnBatchById(List<CommunityActivity> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdate(CommunityActivity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumn(CommunityActivity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public List<CommunityActivity> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommunityActivity selectOne(Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectMap(Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectObj(Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount(Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CommunityActivity> selectList(Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CommunityActivity> selectPage(Page<CommunityActivity> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> selectObjs(Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CommunityActivity> selectPage(Page<CommunityActivity> page, Wrapper<CommunityActivity> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
