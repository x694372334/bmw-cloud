package com.bmw.medical.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.medical.model.PayService;
import com.bmw.medical.model.PayType;

/**
 * <p>
 * 收费项目 服务类
 * </p>
 *
 * @author liuwsh123
 * @since 2018-09-18
 */
public interface IPayServiceService {
	JSONArray selectList(PayService payService);

	PayService selectById(Integer payServiceId);

	void insert(PayService payService);

	void update(PayService payService);

	void deleteById(Integer payServiceId);
}
