package com.bmw.property.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bmw.property.model.ACInfo;
import com.bmw.property.model.OFeedback;

/**
 * <p>
 * 意见反馈 服务类
 * </p>
 *
 * @author liuwsh
 * @since 2018-07-30
 */
public interface IOFeedbackService {
	JSONObject findList(Map<String, Integer> paramMap);
	OFeedback getdetail(Integer oFeedbackId);
	void add(OFeedback condition);
	void update(OFeedback condition);
	void del(Integer oFeedbackId);
}
