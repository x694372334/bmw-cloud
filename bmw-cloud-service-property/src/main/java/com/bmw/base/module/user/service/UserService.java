package com.bmw.base.module.user.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bmw.base.module.user.domain.User;
import com.bmw.base.module.user.domain.UserRoleVO;
import com.bmw.base.module.user.domain.UserVO;
import com.bmw.base.module.user.mapper.UserMapper;
import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
//import com.bmw.huanxin.module.domain.HuanxinUser;
//import com.bmw.huanxin.module.service.HuanxinUserService;
//import com.bmw.property.module.enterprise.domain.EnterpriseInfo;
//import com.bmw.property.module.enterprise.service.EnterpriseInfoService;

/**
 * 管理员表Service类
 * 
 * @author lyl
 * @date 2018-04-24 08:22:34
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Value("${image.server.url}")
	String image_server_url = "";

//	@Autowired
//	private HuanxinUserService huanxinUserService;
//
//	@Autowired
//	private EnterpriseInfoService enterpriseInfoService;

	/**
	 * 获取当前对象实例
	 * <p>
	 * <b> 为了记录操作日志</br>
	 * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()" </b>
	 * </p>
	 * 
	 */
	public UserService currentProxy() {
		return SpringContextUtils.getBean(UserService.class);
	}

	/**
	 * 新增实体类到数据库
	 * 
	 */
	@Transactional
	@ServiceOperationLog(type = "insert", iden = "sys_user", idenName = "添加oa系统用户", idBeanName = "id")
	public int add(User record) {
		return userMapper.add(record);
	}

	/**
	 * 根据主键删除该记录
	 * 
	 */
	@Transactional
	@ServiceOperationLog(type = "delete", iden = "sys_user", idenName = "删除oa系统用户", idBeanName = "id")
	public int delete(Integer id) {
		return userMapper.delete(id);
	}

	/**
	 * 根据主键修改该记录
	 * 
	 */
	@Transactional
	@ServiceOperationLog(type = "update", iden = "sys_user", idenName = "修改oa系统用户", idBeanName = "id")
	public int update(User record) {
		return userMapper.update(record);
	}

	/**
	 * 根据主键查询该记录
	 * 
	 */
	public User findById(Integer id) {
		return userMapper.findById(id);
	}

	/**
	 * 根据查询条件进行模糊查询
	 * 
	 */
	public List<User> findLike(User condition) {
		return userMapper.findLike(condition);
	}

	/**
	 * 根据查询条件进行匹配查询
	 * 
	 */
	public List<User> findList(User condition) {
		return userMapper.findList(condition);
	}

	/**
	 * 根据条件查询用户列表
	 */
	public List<User> selectUsers(String name, String beginTime, String endTime, Integer deptid, Integer parenteid) {
		return userMapper.selectUsers(name, beginTime, endTime, deptid, parenteid);
	}
	
	/**
	 * 根据条件查询用户列表
	 */
	public List<User> selectUsersNotAdmin(String name, String beginTime, String endTime, Integer deptid, Integer parenteid) {
		return userMapper.selectUsersNotAdmin(name, beginTime, endTime, deptid, parenteid);
	}

	/**
	 * 通过账号获取用户
	 */
	public User getByAccount(@Param("account") String account) {
		return userMapper.getByAccount(account);
	}

	/**
	 * 修改用户状态
	 */
	public void setStatus(@Param("userId") Integer userId, @Param("status") int status) {
		userMapper.setStatus(userId, status);
	}

	/**
	 * 修改密码
	 */
	@Transactional
	@ServiceOperationLog(type = "update", iden = "sys_user", idenName = "修改oa系统用户密码", idBeanName = "id")
	public void changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd) {
		userMapper.changePwd(userId, pwd);
	}

	/**
	 * 设置用户的角色
	 */
	@Transactional
	public void setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds) {
		String[] roleIdArray = roleIds.split(",");
		userMapper.deleteRelationByRoleId(userId);
		for (String roleId : roleIdArray) {
			userMapper.createUserRolesRelation(userId, Integer.parseInt(roleId));
		}
		userMapper.setRoles(userId, roleIds);
	}

	public String findListForApp(User condition) {
		UserVO vo = new UserVO();
		String string = "";
		List<UserVO> list = userMapper.findListForApp(condition);
		if (list.size() > 0) {
			vo = list.get(0);
			if (vo.getAvatar() != null) {
				vo.setAvatar(StringUtils.isNotEmpty(vo.getAvatar())
						? image_server_url + "/upload/formal//yhtx/" + vo.getAvatar()
						: null);
			}
			String str = JSONObject.toJSONString(vo, SerializerFeature.WriteNullStringAsEmpty);
			JSONObject json = JSONObject.parseObject(str);
			
			List<UserRoleVO> list1 = userMapper.findViewByUserId(vo.getId());
			Integer parenteid = null;
			String  parentename = null;
			for(UserRoleVO userRoleVO:list1) {
				if(null == parenteid) {
					parenteid =userRoleVO.getParenteid();
				}
				userRoleVO.setUname(null);
				userRoleVO.setUid(null);
				userRoleVO.setParenteid(null);
				userRoleVO.setParentename(null);
			}
			
			json.put("parenteid",null != parenteid?parenteid:"" );
//			//获取企业信息
//			EnterpriseInfo e = enterpriseInfoService.findById(parenteid);
//			json.put("parentename", null != e.getEnterpriseName()?e.getEnterpriseName():"");
//			json.put("parenteshortname", null != e.getShortName()?e.getShortName():"");
			
			JSONArray array= JSONArray.parseArray(JSON.toJSONString(list1,SerializerFeature.WriteNullStringAsEmpty));
			json.put("userRoles", array);
			
//			HuanxinUser huanxinUser = new HuanxinUser();
//			huanxinUser.setPcUserId(vo.getId());
//			List<HuanxinUser> huanxinUserList = huanxinUserService.findList(huanxinUser);
//			if (huanxinUserList.size() > 0) {
//				huanxinUser = huanxinUserList.get(0);
//			}
//			json.put("userName",null != huanxinUser.getUserName()?huanxinUser.getUserName():"");
//			json.put("huanxinPassword", null != huanxinUser.getPassword()?huanxinUser.getPassword():"");
//			json.put("nickname", null != huanxinUser.getNickname()?huanxinUser.getNickname():"");
//			json.put("huanxinUserId", null != huanxinUser.getHuanxinUserId()?huanxinUser.getHuanxinUserId():"");
//			json.put("huanxinGroupId", null != huanxinUser.getHuanxinGroupId()?huanxinUser.getHuanxinGroupId():"");
		

			string = JSONObject.toJSONString(json, SerializerFeature.WriteNullStringAsEmpty);
		}

		return string;
	}

	/**
	 * 根据用户ID查询企业信息
	 */
	public List<UserRoleVO> findUserRoleViewByUserId(Integer userId) {
		return userMapper.findUserRoleViewByUserId(userId);
	}

	/**
	 * 根据用户ID查询企业信息
	 */
	public List<UserRoleVO> findViewByUserId(Integer userId) {
		return userMapper.findViewByUserId(userId);
	}

	/**
	 * 根据角色ID查询企业信息
	 */
	public List<UserRoleVO> findUserRoleViewByRoleId(Integer roleId) {
		return userMapper.findUserRoleViewByRoleId(roleId);
	}
	
	/**
	 * 根据角色ID集合查询企业信息
	 */
	public List<UserRoleVO> findUserRoleViewByRoleIds(List<String> roleIds) {
		return userMapper.findUserRoleViewByRoleIds(roleIds);
	}

	/**
	 * 根据部门ID集合查询企业信息
	 */
	public List<UserRoleVO> findUserRoleViewByDids(List<String> dids) {
		return userMapper.findUserRoleViewByDids(dids);
	}
	
	/**
	 * 根据用户ID和企业查询角色
	 */
	public List<UserRoleVO> findUserRoleViewByUserIdAndEid(Integer userId, Integer eid) {
		return userMapper.findUserRoleViewByUserIdAndEid(userId, eid);
	}

    /**
     * 根据物业查询用户
     */
    public List<UserRoleVO> findUserRoleViewByEid(Integer eid) {
    	return userMapper.findUserRoleViewByEid(eid);
    }

	public List<UserRoleVO> findUserRoleViewByEidAndIsHuanxin(Integer eid) {
		return userMapper.findUserRoleViewByEidAndIsHuanxin(eid);
	}
    
}