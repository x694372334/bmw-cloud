package com.bmw.medical.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.medical.model.PayServiceDetail;

/**
 * <p>
 * 收费项目明细表 服务类
 * </p>
 *
 * @author liuwsh123
 * @since 2018-09-18
 */
public interface IPayServiceDetailService {
	JSONArray selectList(PayServiceDetail payServiceDetail);

	PayServiceDetail selectById(Integer payServiceDetailId);

	void insert(PayServiceDetail payServiceDetail);

	void update(PayServiceDetail payServiceDetail);

	void deleteById(Integer payServiceDetailId);
}
