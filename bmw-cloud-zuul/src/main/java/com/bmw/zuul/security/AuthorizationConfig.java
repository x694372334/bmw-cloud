package com.bmw.zuul.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bmw.zuul.utils.CommonUtils;

/**
 * 安全访问授权用户配置
 * @author lyl
 * 2017年3月1日
 */
@Service
public class AuthorizationConfig {

	public Map<String,String> authUser;
	
	
	public AuthorizationConfig(){
		authUser = new HashMap<String,String>();
		init();
	}
	
	
	/**
	 * 初始化授权用户
	 * @author lyl
	 * 2017年3月1日
	 */
	public void init(){
		record("bmw-oa");
		record("bmw-test-auth");
		record("bmw-provider");
		record("bmw-ios");
		record("bmw-android");
		record("bmw-medical");
	}
	
	
	/**
	 * 录入授权用户返回的key值为合法请求码
	 * @param user 用户标识
	 * 
	 * @return 返回key值
	 * @author lyl
	 * 2017年3月1日
	 */
	public String record(String user){
		if(user == null){
			return null;
		}
		authUser.put(user, user);
		return user;
	}
	
	/**
	 * 验证是否授权
	 * @return true:合法授权，false：非法授权
	 * @author lyl
	 * 2017年3月1日
	 */
	public boolean validateAuth(String key){
		String code;
		boolean rlt=true;
		try {
			code = authUser.get(CommonUtils.HttpAccessDecode(key));
			if(StringUtils.isBlank(code)){
				rlt=false;
			}else{
				rlt=true;
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rlt;
		
	}
	
}
