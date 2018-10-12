package com.bmw.base.module.loginlog.mapper;

import com.bmw.base.module.loginlog.domain.LoginLog;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 登录记录Mapper类
 * 
 * @author lyl
 * @date 2018-04-28 08:50:01
 */
@Mapper
public interface LoginLogMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(LoginLog record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(LoginLog record);

    /**
     * 根据主键查询该记录
     * 
     */
    LoginLog findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<LoginLog> findLike(LoginLog condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<LoginLog> findList(LoginLog condition);
    
    /**
     * 获取登录日志
     */
    List<Map<String, Object>> getLoginLogs(@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("logName") String logName, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);
    
    /**
     * 清空登录日志
     */
    void deleteAll();
}