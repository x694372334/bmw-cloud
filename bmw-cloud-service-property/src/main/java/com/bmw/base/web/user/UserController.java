package com.bmw.base.web.user;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bmw.base.module.role.service.RoleService;
import com.bmw.base.module.user.domain.User;
import com.bmw.base.module.user.domain.UserRoleVO;
import com.bmw.base.module.user.service.UserService;
import com.bmw.common.exception.RestPreconditionFailedException;
import com.bmw.common.model.Result;
//import com.bmw.common.utils.JedisUtil;
import com.bmw.common.utils.ResultUtils;
import com.bmw.common.utils.oa.ToolUtil;

@RestController
@RequestMapping("base/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
//	JedisUtil jedisUtil= JedisUtil.getInstance();
	
	/**
	 * 根据用户名查询用户
	 * @author lyl
	 * 2018年04月24日
	 */
	@RequestMapping(value="getByAccount/{account}",method=RequestMethod.GET)
	public Result getByAccount(@PathVariable("account") String account){
		String rlt="";
		try {
			User categorys = userService.getByAccount(account);
			String userStr=JSONObject.toJSONString(categorys);
			rlt = Base64Utils.encodeToString(userStr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultUtils.getMethodData(rlt);
	}
	
	
	/**
	 * 根据条件查询用户列表
	 * @author lyl
	 * 2018年04月24日
	 */
	@RequestMapping(value="selectUsers/{jsonParams}",method=RequestMethod.GET)
	public Result selectUsers(@PathVariable("jsonParams") String jsonParams){
		String rlt="";
		try {
			String params=new String(Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject json=JSONObject.parseObject(params);
			List<User> categorys = new ArrayList<User>();
			if(null!=json.getString("uAttribute")&&json.getString("uAttribute").equals("-1")){
				categorys = userService.selectUsers(json.getString("name"), json.getString("beginTime"), json.getString("endTime"), json.getInteger("deptid"), json.getInteger("parenteid"));
			}else {
				categorys = userService.selectUsersNotAdmin(json.getString("name"), json.getString("beginTime"), json.getString("endTime"), json.getInteger("deptid"), json.getInteger("parenteid"));
			}
			String userStr=JSONObject.toJSONString(categorys);
			rlt = Base64Utils.encodeToString(userStr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultUtils.getMethodData(rlt);
	}
    
    /**
     * 用户信息
     * @author lyl
	 * 2018年04月24日
     */
    @RequestMapping(value = "detail/{userId}",method=RequestMethod.GET)
    public Object detail(@PathVariable("userId") Integer userId) {
    	if(userId==null) {
    		throw new RestPreconditionFailedException("请输入正确的父id");
    	}
    	String rlt="";
		try {
			User categorys = userService.findById(userId);
	    	String userStr=JSONObject.toJSONString(categorys);
			rlt = Base64Utils.encodeToString(userStr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.getMethodData(rlt);
    }
    
    
    /**
     * 用户添加
     * @author lyl
	 * 2018年04月24日
     */
    @RequestMapping(value = "add/{jsonParams}",method=RequestMethod.POST)
    public Object add(@PathVariable("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			userService.add(object.toJavaObject(User.class));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("添加用户成功");
    }
    
    
    /**
     * 修改用户
     * @author lyl
	 * 2018年04月24日
     */
    @RequestMapping(value = "edit/{jsonParams}",method=RequestMethod.POST)
    public Object edit(@PathVariable("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			userService.update(object.toJavaObject(User.class));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("修改用户成功");
    }
    
    
    
    /**
     * 删除用户
     * @author lyl
	 * 2018年04月24日
     */
    @RequestMapping(value = "del/{jsonParams}",method=RequestMethod.DELETE)
    public Object del(@PathVariable("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			userService.delete(object.getInteger("dictId"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.deleteMethodData("删除用户成功");
    }
    
    
    /**
     * 修改用户状态
     * @author lyl
	 * 2018年04月24日
     */
    @RequestMapping(value = "setStatus/{jsonParams}",method=RequestMethod.POST)
    public Object setStatus(@PathVariable("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			userService.setStatus(object.getInteger("userId"), object.getInteger("status"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ResultUtils.postMethodData("修改用户状态成功"); 
    }

    /**
     * 修改密码
     * @author lyl
	 * 2018年04月24日
     */
    @RequestMapping(value = "changePwd/{jsonParams}",method=RequestMethod.POST)
    public Object changePwd(@PathVariable("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			userService.changePwd(object.getInteger("userId"), object.getString("pwd"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ResultUtils.postMethodData("修改密码成功");
    }
    
    /**
     * 设置用户的角色
     * @author lyl
	 * 2018年04月24日
     */
    @RequestMapping(value = "setRoles/{jsonParams}",method=RequestMethod.POST)
    public Object setRoles(@PathVariable("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			//校验角色是否来自同一个公司
	    	String flag = roleService.checkRoleIds(object.getString("roleIds"));
			if("0".equals(flag)) {
				return ResultUtils.postMethodData("ERRO");
			}
			//校验角色是否与用户为同一个公司
			String[] roleArray = object.getString("roleIds").split(",");
			Integer parentEId =  roleService.findById(Integer.parseInt(roleArray[0])).getParentEId();
			Integer pid = userService.findById(object.getInteger("userId")).getParentEId();
			if(pid!=parentEId) {
				return ResultUtils.postMethodData("ERRO2");
			}
			userService.setRoles(object.getInteger("userId"), object.getString("roleIds"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ResultUtils.postMethodData("设置用户的角色成功");
    }

	/**
	 * 根据条件查询用户列表
	 * @author lyl
	 * 2018年04月24日
	 */
	@RequestMapping(value="userList/{jsonString}",method=RequestMethod.GET)
	public Result userList(@PathVariable("jsonString") String jsonString){
		List<User> categorys = null;
		User user=new User();
		try {
			String params=new String (Base64Utils.decode(jsonString.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			user=JSONObject.toJavaObject(object, User.class);
			categorys = userService.findLike(user);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ResultUtils.getMethodData(categorys);
	}
    
////////////////////////////////////////////////////for 物业 app/////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/findListForApp",method=RequestMethod.POST)
    @ResponseBody
	public Result list(@RequestBody User conditions) throws UnsupportedEncodingException {
		String str=userService.findListForApp(conditions);
		return ResultUtils.getMethodData(str);
	}
    
    @RequestMapping(value = "/findUserRoleViewByUserId/{userId}",method=RequestMethod.GET)
	public Result findUserRoleViewByUserId(@PathVariable("userId") Integer userId)  {
		List<UserRoleVO> list=userService.findUserRoleViewByUserId(userId);
		return ResultUtils.getMethodData(list);
	}
    
    @RequestMapping(value = "/findViewByUserId/{userId}",method=RequestMethod.GET)
   	public Result findViewByUserId(@PathVariable("userId") Integer userId)  {
   		List<UserRoleVO> list=userService.findViewByUserId(userId);
   		return ResultUtils.getMethodData(list);
   	}
    
    @RequestMapping(value = "/findUserRoleViewByRoleId/{roleId}",method=RequestMethod.GET)
   	public Result findUserRoleViewByRoleId(@PathVariable("roleId") Integer roleId)  {
   		UserRoleVO vo=userService.findUserRoleViewByRoleId(roleId).get(0);
   		return ResultUtils.getMethodData(vo);
   	}
    
    @RequestMapping(value = "/findUserRoleViewByUserIdAndEid/{userId}/{eid}",method=RequestMethod.GET)
   	public Result findUserRoleViewByUserIdAndEid(@PathVariable("userId") Integer userId,@PathVariable("eid") Integer eid)  {
    	List<UserRoleVO> list = userService.findUserRoleViewByUserIdAndEid(userId,eid);
   		return ResultUtils.getMethodData(list);
   	}
    
    @RequestMapping(value = "/findUserRoleViewByEidAndIsHuanxin/{eid}",method=RequestMethod.GET)
   	public Result findUserRoleViewByEidAndIsHuanxin(@PathVariable("eid") Integer eid)  {
    	List<UserRoleVO> list = userService.findUserRoleViewByEidAndIsHuanxin(eid);
   		return ResultUtils.getMethodData(list);
   	}
    
    @RequestMapping(value = "/findUserRoleViewByEid/{eid}",method=RequestMethod.GET)
   	public Result findUserRoleViewByEid(@PathVariable("eid") Integer eid)  {
    	List<UserRoleVO> list = userService.findUserRoleViewByEid(eid);
   		return ResultUtils.getMethodData(list);
   	}
    
//    /**
//     * 物业App发送验证码
//     * @param phone
//     * @param limitTime
//     * @param verifyCode
//     * @return
//     */
//    @RequestMapping(value = "recordVerifyCode/{phone}/{limitTime}/{verifyCode}",method=RequestMethod.GET)
//   	public Result recordVerifyCode(@PathVariable("phone") String phone,@PathVariable("limitTime") int limitTime,@PathVariable("verifyCode") String verifyCode)  {
//    	JedisUtil.Strings strings=jedisUtil.new Strings();
//
//    	// 判断是否已经发送验证码
//		String existCode =strings.get(phone);
//		if (StringUtils.isNotBlank(existCode)) {
//			return ResultUtils.errorMethodData(HttpStatus.BAD_REQUEST, "规定时间内不能重复发送验证码");
//		}
//    	strings.setEx(phone, limitTime, verifyCode);
//    	strings.setEx(phone+"Time", limitTime+20, String.valueOf(new Date().getTime()));
//   		return ResultUtils.getMethodData();
//   	}
//    /**
//     * @param phone
//     * @param limitTime
//     * @param verifyCode
//     * @return
//     */
//    @RequestMapping(value = "verifyCode/{phone}/{limitTime}/{verifyCode}",method=RequestMethod.GET)
//    public Result verifyCode(@PathVariable("phone") String phone,@PathVariable("limitTime") int limitTime,@PathVariable("verifyCode") String verifyCode)  {
//    	JedisUtil.Strings strings=jedisUtil.new Strings();
//    	String old = strings.get(phone);
//    	// 获取上一次发送验证码的时间
//    	String lastRequstTime = strings.get(phone+"Time");
//    	if (null != lastRequstTime) {
//			// 时间间隔
//			long timespan = (new Date().getTime() - Long.parseLong( lastRequstTime)) /1000;
//			// 判断是否超期
//			if (timespan > limitTime) {
//				return ResultUtils.errorMethodData(HttpStatus.BAD_REQUEST, "验证码已过期");
//			}
//		}
//    	if(null != old && verifyCode.equals(old)) {
//    		return ResultUtils.getMethodData();
//    	}else {
//    		return ResultUtils.errorMethodData(HttpStatus.BAD_REQUEST, "验证码错误");
//    	}
//    }
    
    
	/**
	 * 根据条件查询用户列表
	 * @author lyl
	 * 2018年04月24日
	 */
	@RequestMapping(value="finduserList",method=RequestMethod.POST)
	public Result finduserList(@RequestBody User user){
		List<User> categorys = userService.findList(user);
		return ResultUtils.getMethodData(categorys);
	}
    
    
}
