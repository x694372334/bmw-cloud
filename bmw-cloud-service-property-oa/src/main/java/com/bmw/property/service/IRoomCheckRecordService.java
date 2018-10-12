package com.bmw.property.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.bmw.property.model.RoomCheckRecord;

/**
 * <p>
 * 收房验房 服务类
 * </p>
 *
 * @author zhangchengjun123
 * @since 2018-07-09
 */
public interface IRoomCheckRecordService extends IService<RoomCheckRecord> {

	public JSONObject selectByParams(Map<String, Object> param);
	
	public RoomCheckRecord detail(Integer id);
	
	public Boolean deleteById(RoomCheckRecord entity);

}
