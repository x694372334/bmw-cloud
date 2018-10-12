package com.bmw.property.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.bmw.property.model.BirthdayMsgModel;
import com.bmw.property.model.BirthdayMsgSendSetting;

/**
 * <p>
 * 生日短信模板 服务类
 * </p>
 *
 * @author zhangchengjun123
 * @since 2018-07-12
 */
public interface IBirthdayMsgModelService extends IService<BirthdayMsgModel> {
	public JSONObject listByParam(Map<String, Integer> paramMap);
	
	public boolean deleteById(BirthdayMsgModel model);
	
	public BirthdayMsgSendSetting getMsgSettingByEid(Integer eId);
	
	public boolean modifySetting(BirthdayMsgSendSetting setting);
	
	public JSONObject hislistByParam(Map<String, Object> paramMap);
	
	public List<Map<String,Object>> export(Map<String, Object> paramMap);
}
