package com.bmw.property.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.bmw.property.model.RoomCheckRecord;
import com.bmw.property.service.IRoomCheckRecordService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收房验房 服务实现类
 * </p>
 *
 * @author zhangchengjun123
 * @since 2018-07-09
 */
@Service
public class RoomCheckRecordServiceImpl  implements IRoomCheckRecordService {
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url="";
	@Value("${RoomCheckRecord.list}")
	private String RoomCheckRecord_list="";
	@Value("${RoomCheckRecord.insert}")
	private String RoomCheckRecord_insert="";
	@Value("${RoomCheckRecord.detail}")
	private String RoomCheckRecord_detail="";
	@Value("${RoomCheckRecord.update}")
	private String RoomCheckRecord_update="";
	@Value("${RoomCheckRecord.delete}")
	private String RoomCheckRecord_delete="";
	/**
	 * 根据查询条件查询列表
	 */
	@Override
	public JSONObject selectByParams(Map<String, Object> param) {
		JSONObject json=new JSONObject();
		try {
			String res=HttpUtils.doPost(bmw_cloud_propertyservice_url+RoomCheckRecord_list, JSONObject.toJSONString(param), null);
			json=JSONObject.parseObject(res).getJSONObject("items");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public boolean insert(RoomCheckRecord entity) {
		String jsonrlt="";
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+RoomCheckRecord_insert,JSONObject.toJSONString(entity).toString(),null);
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
	public RoomCheckRecord detail(Integer id) {
		String jsonrlt="";
		RoomCheckRecord result=null;
		try {
			jsonrlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+RoomCheckRecord_detail+id);
			
			result=JSONObject.parseObject(jsonrlt).getJSONObject("items").toJavaObject(RoomCheckRecord.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	@Override
	public boolean updateById(RoomCheckRecord entity) {
		String jsonrlt="";
		Integer result=null;
		try {
			jsonrlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+RoomCheckRecord_update,JSONObject.toJSONString(entity),null);
			String statusCode=JSONObject.parseObject(jsonrlt).getString("statusCode");
			if(statusCode.equals("201")) {
				result=JSONObject.parseObject(jsonrlt).getInteger("items");
				if(result!=null&&result.intValue()>0) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Boolean deleteById(RoomCheckRecord entity) {
		String jsonrlt="";
		Integer result=null;
		try {
			jsonrlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+RoomCheckRecord_delete,JSONObject.toJSONString(entity),null);
			String statusCode=JSONObject.parseObject(jsonrlt).getString("statusCode");
			if(statusCode.equals("201")) {
				result=JSONObject.parseObject(jsonrlt).getInteger("items");
				if(result!=null&&result.intValue()>0) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
	@Override
	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}
		
	@Override
	public boolean insertAllColumn(RoomCheckRecord entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<RoomCheckRecord> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertBatch(List<RoomCheckRecord> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<RoomCheckRecord> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateBatch(List<RoomCheckRecord> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumnBatch(List<RoomCheckRecord> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumnBatch(List<RoomCheckRecord> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean updateAllColumnById(RoomCheckRecord entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RoomCheckRecord entity, Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<RoomCheckRecord> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(List<RoomCheckRecord> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnBatchById(List<RoomCheckRecord> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllColumnBatchById(List<RoomCheckRecord> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdate(RoomCheckRecord entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertOrUpdateAllColumn(RoomCheckRecord entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RoomCheckRecord selectById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomCheckRecord> selectBatchIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomCheckRecord> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomCheckRecord selectOne(Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectMap(Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectObj(Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount(Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RoomCheckRecord> selectList(Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RoomCheckRecord> selectPage(Page<RoomCheckRecord> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectMaps(Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> selectObjs(Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RoomCheckRecord> selectPage(Page<RoomCheckRecord> page, Wrapper<RoomCheckRecord> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	

	

	
}
