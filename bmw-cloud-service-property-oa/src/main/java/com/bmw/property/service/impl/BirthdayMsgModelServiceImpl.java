package com.bmw.property.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.HttpUtils;
import com.bmw.property.model.BirthdayMsgHis;
import com.bmw.property.model.BirthdayMsgModel;
import com.bmw.property.model.BirthdayMsgSendSetting;
import com.bmw.property.service.IBirthdayMsgModelService;

/**
 * <p>
 * 生日短信模板 服务实现类
 * </p>
 *
 * @author zhangchengjun123
 * @since 2018-07-12
 */
@Service
public class BirthdayMsgModelServiceImpl implements IBirthdayMsgModelService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url;
	
	@Value("${BirthdayMsgModel.listByParam}")
	private String BirthdayMsgModel_listByParam;
	@Value("${BirthdayMsgModel.addModel}")
	private String BirthdayMsgModel_addModel;
	
	@Value("${BirthdayMsgModel.detailModel}")
	private String BirthdayMsgModel_detailModel;
	
	@Value("${BirthdayMsgModel.updateModel}")
	private String BirthdayMsgModel_updateModel;
	
	@Value("${BirthdayMsgModel.deleteModel}")
	private String BirthdayMsgModel_deleteModel;
	
	@Value("${BirthdayMsgModel.getMsgSettingByEid}")	
	private String BirthdayMsgModel_getMsgSettingByEid;
	
	@Value("${BirthdayMsgModel.modifySetting}")
	private String BirthdayMsgModel_modifySetting;
	
	@Value("${BirthdayMsgModel.hisList}")
	private String BirthdayMsgModel_hisList;
	
	@Value("${BirthdayMsgModel.export}")
	private String BirthdayMsgModel_export;

	@Override
	public JSONObject listByParam(Map<String, Integer> paramMap) {
		JSONObject returnJson=null;
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+BirthdayMsgModel_listByParam+new String(Base64Utils.encode(JSONObject.toJSONString(paramMap).getBytes("UTF-8"))));
			returnJson=JSONObject.parseObject(rlt).getJSONObject("items");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnJson;
	}
	
	@Override
	public boolean insert(BirthdayMsgModel model) {
		String jsonrlt="";
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+BirthdayMsgModel_addModel,JSONObject.toJSONString(model).toString(),null);
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
	 * 根据ID查询
	 */
	@Override
	public BirthdayMsgModel selectById(Serializable arg0) {
		BirthdayMsgModel model=null;
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+BirthdayMsgModel_detailModel+arg0);
			model=JSONObject.parseObject(rlt).getJSONObject("items").toJavaObject(BirthdayMsgModel.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 根据ID更新
	 */
	@Override
	public boolean updateById(BirthdayMsgModel arg0) {
		boolean flag=false;
		try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+BirthdayMsgModel_updateModel,JSONObject.toJSONString(arg0),null);
			Integer statusCode=JSONObject.parseObject(rlt).getInteger("statusCode");
			if(statusCode.intValue()==HttpStatus.CREATED.value()) {
				int count=JSONObject.parseObject(rlt).getIntValue("items");
				if(count>0) {
					flag=true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 根据ID删除，其实是更新操作，逻辑删除
	 */
	@Override
	public boolean deleteById(BirthdayMsgModel model) {
		boolean flag=false;
		try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+BirthdayMsgModel_deleteModel,JSONObject.toJSONString(model),null);
			Integer statusCode=JSONObject.parseObject(rlt).getInteger("statusCode");
			if(statusCode.intValue()==HttpStatus.CREATED.value()) {
				int count=JSONObject.parseObject(rlt).getIntValue("items");
				if(count>0) {
					flag=true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//BirthdayMsgModel_getMsgSettingByEid
	@Override
	public BirthdayMsgSendSetting getMsgSettingByEid(Integer eId) {
		BirthdayMsgSendSetting modelSetting=null;
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+BirthdayMsgModel_getMsgSettingByEid+eId);
			if(JSONObject.parseObject(rlt).getJSONObject("items")!=null) {
				modelSetting=JSONObject.parseObject(rlt).getJSONObject("items").toJavaObject(BirthdayMsgSendSetting.class);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return modelSetting;
		
	}
	
	@Override
	public boolean modifySetting(BirthdayMsgSendSetting setting) {
		boolean flag=false;
		try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+BirthdayMsgModel_modifySetting,JSONObject.toJSONString(setting),null);
			Integer statusCode=JSONObject.parseObject(rlt).getInteger("statusCode");
			if(statusCode.intValue()==HttpStatus.CREATED.value()) {
				int count=JSONObject.parseObject(rlt).getIntValue("items");
				if(count>0) {
					flag=true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public JSONObject hislistByParam(Map<String, Object> paramMap) {
		JSONObject returnJson=null;
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+BirthdayMsgModel_hisList+new String(Base64Utils.encode(JSONObject.toJSONString(paramMap).getBytes("UTF-8"))));
			returnJson=JSONObject.parseObject(rlt).getJSONObject("items");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnJson;
	}

	@Override
	public List<Map<String,Object>> export(Map<String, Object> paramMap) {
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		JSONObject returnJson=null;
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+BirthdayMsgModel_export+new String(Base64Utils.encode(JSONObject.toJSONString(paramMap).getBytes("UTF-8"))));
			returnJson=JSONObject.parseObject(rlt).getJSONObject("items");
			List<BirthdayMsgHis> list=returnJson.getJSONArray("result").toJavaList(BirthdayMsgHis.class);
			for(BirthdayMsgHis his:list) {
				Map<String,Object> tempMap=new HashMap<String,Object>();
				tempMap.put("ownerName", his.getOwnerName());
				tempMap.put("sendText", his.getSendText());
				tempMap.put("sendTime", DateUtils.formatDate(his.getSendTime(), "yyyy-MM-dd HH:mm:ss"));
				rows.add(tempMap);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	@Override
	public boolean delete(Wrapper<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBatchIds(Collection<? extends Serializable> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Serializable arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByMap(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean insertAllColumn(BirthdayMsgModel arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<BirthdayMsgModel> arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdate(BirthdayMsgModel arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumn(BirthdayMsgModel arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumnBatch(List<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumnBatch(List<BirthdayMsgModel> arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<BirthdayMsgModel> arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BirthdayMsgModel> selectBatchIds(Collection<? extends Serializable> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<BirthdayMsgModel> selectByMap(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount(Wrapper<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BirthdayMsgModel> selectList(Wrapper<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectMap(Wrapper<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Map<String, Object>> selectMapsPage(Page arg0, Wrapper<BirthdayMsgModel> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectObj(Wrapper<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> selectObjs(Wrapper<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BirthdayMsgModel selectOne(Wrapper<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BirthdayMsgModel> selectPage(Page<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BirthdayMsgModel> selectPage(Page<BirthdayMsgModel> arg0, Wrapper<BirthdayMsgModel> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(BirthdayMsgModel arg0, Wrapper<BirthdayMsgModel> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnBatchById(List<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnBatchById(List<BirthdayMsgModel> arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnById(BirthdayMsgModel arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<BirthdayMsgModel> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<BirthdayMsgModel> arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
