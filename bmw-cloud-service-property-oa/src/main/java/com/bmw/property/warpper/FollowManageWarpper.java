/**
 * Project Name:bmw-oa
 * File Name:FollowManageWarpper.java
 * Package Name:com.bmw.followManager.warpper
 * Date:2018年6月15日下午2:40:05
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.bmw.property.warpper;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.util.DateUtil;

/**
 * 
 * @ClassName: FollowManageWarpper
 * @Description: controller查询结果包装类
 * @author wangliqing
 * @date 2018年6月15日
 *
 */
public class FollowManageWarpper extends BaseControllerWarpper {

	public FollowManageWarpper(Object obj) {
		super(obj);
	}

	@Override
	protected void warpTheMap(JSONObject json) {
		Date contactTime = json.getDate("contactTime");
		if (contactTime != null) {
			json.put("contactTime", DateUtil.format(contactTime, "yyyy-MM-dd HH:mm"));
		}
	}

}
