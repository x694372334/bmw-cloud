package com.bmw.property.huanxin.service;

import com.alibaba.fastjson.JSONArray;
import com.common.utils.HttpUtils;
import com.bmw.property.model.Account;
import com.bmw.property.model.CostStandard;
import com.bmw.property.service.IAccountService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 个人账户表 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-09
 */
@Service
public class HuanxinService  {
	
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url="";
	
	@Value("${huanxin.registered}")
	private String huanxin_registered="";

	public void registeredHuanxin(String userId ,String username,String password ,String nickname) {
		try {
			HttpUtils.doGet(bmw_cloud_propertyservice_url+huanxin_registered+
					"?userId="+userId+"&username="+username+"&password="+password+"&nickname="+nickname+"&uAttribute=1");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
