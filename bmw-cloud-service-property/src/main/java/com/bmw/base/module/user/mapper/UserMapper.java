package com.bmw.base.module.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bmw.base.module.user.domain.User;
import com.bmw.base.module.user.domain.UserRoleVO;
import com.bmw.base.module.user.domain.UserVO;


/**
 * 管理员表Mapper类
 * 
 * @author lyl
 * @date 2018-04-24 08:22:34
 */
@Mapper
public interface UserMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(User record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(User record);

    /**
     * 根据主键查询该记录
     * 
     */
    User findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<User> findLike(User condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<User> findList(User condition);
    
    
    /**
     * 根据条件查询用户列表
     */
    List<User> selectUsers(@Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Integer deptid, @Param("parenteid") Integer parenteid);
    
    
    List<User> selectUsersNotAdmin(@Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Integer deptid, @Param("parenteid") Integer parenteid);
    /**
     * 通过账号获取用户
     */
    User getByAccount(@Param("account") String account);
    
    /**
     * 修改用户状态
     */
    void setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修改密码
     */
    void changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);
    
    /**
     * 设置用户的角色
     */
    void setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);
	
    List<UserVO> findListForApp(User condition);
    
    /**
     * 设置用户的角
     */
	Integer judgment(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
	
	/**
     * 设置用户的角
     */
	void createUserRolesRelation(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

	/**
     * 根据用户ID查询企业信息
     */
	List<UserRoleVO> findUserRoleViewByUserId(@Param("userId") Integer userId);
	
	/**
     * 根据用户ID查询企业信息
     */
	List<UserRoleVO> findViewByUserId(@Param("userId") Integer userId);


	
	/**
     * 根据角色ID查询企业信息
     */
	List<UserRoleVO> findUserRoleViewByRoleId(@Param("roleId") Integer roleId);

	/**
     * 根据用户ID和企业查询角色
     */
	List<UserRoleVO> findUserRoleViewByUserIdAndEid(@Param("userId") Integer userId, @Param("eid") Integer eid);

	void deleteRelationByRoleId(Integer userId);

	List<UserRoleVO> findUserRoleViewByEid(Integer eid);

	List<UserRoleVO> findUserRoleViewByRoleIds(@Param("roleIds") List<String> roleIds);

	List<UserRoleVO> findUserRoleViewByDids(@Param("dids") List<String> dids);

	List<UserRoleVO> findUserRoleViewByEidAndIsHuanxin(Integer eid);

}