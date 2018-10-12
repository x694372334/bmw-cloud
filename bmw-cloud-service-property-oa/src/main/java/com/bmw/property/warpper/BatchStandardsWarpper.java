/**
 * Project Name:bmw-oa
 * File Name:CostSetWarpper.java
 * Package Name:com.bmw.property.warpper
 * Date:2018年6月21日下午2:59:48
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.bmw.property.warpper;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.DateUtil;

/**
* @ClassName: CostSetWarpper  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author wangliqing  
* @date 2018年6月21日  
*
 */
public class BatchStandardsWarpper extends BaseControllerWarpper {

	@Override
	protected void warpTheMap(JSONObject json) {
		Timestamp chargeableStartDate = json.getTimestamp("chargeableStartDate");
		Timestamp chargeableEndDate = json.getTimestamp("chargeableEndDate");
		if (chargeableStartDate != null) {
			json.put("chargeableStartDate", DateUtil.format(chargeableStartDate, "yyyy-MM-dd"));
		}
		if (chargeableEndDate != null) {
			json.put("chargeableEndDate", DateUtil.format(chargeableEndDate, "yyyy-MM-dd"));
		}
	}

	public BatchStandardsWarpper(Object obj) {
		super(obj);
	}
}

