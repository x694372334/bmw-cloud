package com.bmw.property.service;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.bmw.property.model.RepairsForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-03
 */
public interface IRepairsFormService  {
	
	public boolean insert(RepairsForm entity);
	
	public RepairsForm selectById(Serializable id);
	
	public RepairsForm detail4Update(Integer id);

	public JSONObject selectDoingList(Map<String, Object> param);
	
	public JSONObject selectDoneList(Map<String, Object> param);
	
	public Boolean isIdentified(Integer roomId);
	
	public Boolean refuse(Map<String, Object> sendMap);
	
	public Boolean receive(Map<String, Object> sendMap);
	
	public Boolean door2see(Map<String, Object> sendMap);
	
	public Boolean complete(Map<String, Object> sendMap);

	public Integer getMonthCount();

	public Integer getUncompletedCount();
	
	public Boolean delete(RepairsForm rf);
	
	public Boolean updateById(RepairsForm rf);
	
}
