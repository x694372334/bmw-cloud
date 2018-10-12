package com.stylefeng.guns.modular.system.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.model.UserRoleVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
public interface IUserService {

    /**
     * 修改用户状态
     */
	void setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修改密码
     */
	void changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 根据条件查询用户列表
     */
    JSONArray selectUsers(@Param("name") String name, @Param("beginTime") String beginTime,
                          @Param("endTime") String endTime, @Param("deptid") Integer deptid, @Param("deptid") Integer parenteid);

    /**
     * 设置用户的角色
     * @return 
     */
    String setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    /**
     * 通过账号获取用户
     */
    User getByAccount(@Param("account") String account);
    
    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    User detailUser(Integer userId);
    
    
    /**
     * 新增用户
     * @param userId
     * @return
     */
    void add(User user);
    
    /**
     * 修改用户
     * @param userId
     * @return
     */
    void edit(User user);
    
    /**
     * 删除用户信息
     * @param userId
     */
    public void del(Integer userId);

    /**
     * 查询用户信息
     */
	JSONArray findList(User condition);

	List<UserRoleVO> findUserRoleViewByUserId(Integer userId);

	JSONArray finduserList(User condition);

	List<UserRoleVO> findUserRoleViewByEid(Integer eId);

	String userDropByUserId(Integer userId);

	/**
     * 根据物业查询注销环信的用户
     */
	List<UserRoleVO> findUserRoleViewByEidAndIsHuanxin(Integer eId);

}
