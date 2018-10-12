package com.bmw.property.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.bmw.property.model.Account;
import com.bmw.property.service.IAccountService;

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
public class AccountServiceImpl  implements IAccountService {

	@Override
	public JSONArray findList(Account condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getdetail(Integer accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(Integer accountId) {
		// TODO Auto-generated method stub
		
	}

}
