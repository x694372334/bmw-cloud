package com.bmw.base.module.operation.service;

import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.base.module.operation.domain.SysOperationLogWithBLOBs;
import com.bmw.base.module.operation.mapper.SysOperationLogMapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 操作日志Service类
 * 
 * @author lyl
 * @date 2018-04-27 10:54:12
 */
@Service
public class SysOperationLogService {

    @Autowired
    private SysOperationLogMapper sysOperationLogMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public SysOperationLogService currentProxy() {
        return SpringContextUtils.getBean(SysOperationLogService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_operation_log",idenName="操作日志",idBeanName="id")
    public int add(SysOperationLogWithBLOBs record) {
        return sysOperationLogMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_operation_log",idenName="操作日志",idBeanName="id")
    public int delete(Integer id) {
        return sysOperationLogMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_operation_log",idenName="操作日志",idBeanName="id")
    public int update(SysOperationLogWithBLOBs record) {
        return sysOperationLogMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public SysOperationLogWithBLOBs findById(Integer id) {
        return sysOperationLogMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<SysOperationLogWithBLOBs> findLike(SysOperationLogWithBLOBs condition) {
        return sysOperationLogMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<SysOperationLogWithBLOBs> findList(SysOperationLogWithBLOBs condition) {
        return sysOperationLogMapper.findList(condition);
    }
    
    
    /**
     * 获取操作日志
     */
    public List<Map<String, Object>> getOperationLogs( @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("logName") String logName, @Param("logType") String logType, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc){
        return sysOperationLogMapper.getOperationLogs(beginTime, endTime, logName, logType, orderByField, isAsc);

    }
    
    /**
     * 获取操作日志
     */
    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="deleteAll",iden="sys_operation_log",idenName="操作日志",idBeanName="id")
    public void deleteAllLog(){
    	sysOperationLogMapper.deleteAllLog();
    }
}