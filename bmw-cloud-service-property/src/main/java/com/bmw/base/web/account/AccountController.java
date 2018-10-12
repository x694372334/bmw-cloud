package com.bmw.base.web.account;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bmw.base.module.account.domain.Account;
import com.bmw.base.module.account.service.AccountService;
import com.bmw.common.exception.RestPreconditionFailedException;
import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;
@RestController
@RequestMapping("base/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 获取列表
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="accountList/{jsonString}",method=RequestMethod.GET)
	public Result accountList(@PathVariable("jsonString") String jsonString){
		
		Account account=new Account();
		if(StringUtils.isBlank(jsonString)) {
			throw new RestPreconditionFailedException("请提交正确的表单信息");
		}
		try {
			String params=new String (Base64Utils.decode(jsonString.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			account=JSONObject.toJavaObject(object, Account.class);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 
		List<Account> categorys = accountService.findLike(account);
		return ResultUtils.getMethodData(categorys);
	}
	
	
	/**
	 * 根据ID查询
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="accountListById/{id}",method=RequestMethod.GET)
	public Result accountListById(@PathVariable("id") String id){
		if(StringUtils.isBlank(id)) {
			throw new RestPreconditionFailedException("请提交正确的Id");
		}
		Account account=new Account();
		account.setId(Integer.parseInt(id));
		List<Account> categorys = accountService.findLike(account);
		return ResultUtils.getMethodData(categorys);
	}
	
	
	/**
	 * 获取详情
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="accountDetail/{id}",method=RequestMethod.GET)
	public Result accountList(@PathVariable("id") Integer id){
		if(id==null) {
			throw new RestPreconditionFailedException("请提交正确的id");
		}
		Account categorys = accountService.findById(id);
		return ResultUtils.getMethodData(categorys);
	}
	
	
	/**
	 * 新增
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Result add(@RequestParam String param){
		if(StringUtils.isBlank(param)) {
			throw new RestPreconditionFailedException("请提交正确的表单信息");
		}
		try {
			String params=new String (Base64Utils.decode(param.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			Account account=JSONObject.toJavaObject(object, Account.class);
			account.setCreateTime( new Timestamp(new Date().getTime()));
			accountService.add(account);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("添加成功");
	}
	
	/**
	 * 修改
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	public Result update(@RequestParam String param){
		if(StringUtils.isBlank(param)) {
			throw new RestPreconditionFailedException("请提交正确的表单信息");
		}
		try {
			String params=new String (Base64Utils.decode(param.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			Account account=JSONObject.toJavaObject(object, Account.class);
			accountService.update(account);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("修改成功");
	}
	
	
	/**
	 * 删除
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="del",method=RequestMethod.POST)
	public Result del(@RequestParam String param){
		if(StringUtils.isBlank(param)) {
			throw new RestPreconditionFailedException("请提交正确的表单信息");
		}
        return ResultUtils.postMethodData("删除成功");
	}
	
	
}
