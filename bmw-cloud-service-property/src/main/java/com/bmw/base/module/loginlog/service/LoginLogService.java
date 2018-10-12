package com.bmw.base.module.loginlog.service;

import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.base.module.loginlog.domain.LoginLog;
import com.bmw.base.module.loginlog.mapper.LoginLogMapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 登录记录Service类
 * 
 * @author lyl
 * @date 2018-04-28 08:50:01
 */
@Service
public class LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public LoginLogService currentProxy() {
        return SpringContextUtils.getBean(LoginLogService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_login_log",idenName="登录记录",idBeanName="id")
    public int add(LoginLog record) {
        return loginLogMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_login_log",idenName="登录记录",idBeanName="id")
    public int delete(Integer id) {
        return loginLogMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_login_log",idenName="登录记录",idBeanName="id")
    public int update(LoginLog record) {
        return loginLogMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public LoginLog findById(Integer id) {
        return loginLogMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<LoginLog> findLike(LoginLog condition) {
        return loginLogMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<LoginLog> findList(LoginLog condition) {
        return loginLogMapper.findList(condition);
    }
    
    
    /**
     * 获取登录日志
     */
    public List<Map<String, Object>> getLoginLogs(@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("logName") String logName, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc){
    	return loginLogMapper.getLoginLogs(beginTime, endTime, logName, orderByField, isAsc);
    }
    
    
    public void deleteAll() {
    	loginLogMapper.deleteAll();
    }
}