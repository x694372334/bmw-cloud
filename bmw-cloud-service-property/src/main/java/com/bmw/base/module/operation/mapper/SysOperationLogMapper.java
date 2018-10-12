package com.bmw.base.module.operation.mapper;

import com.bmw.base.module.operation.domain.SysOperationLogWithBLOBs;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 操作日志Mapper类
 * 
 * @author lyl
 * @date 2018-04-27 10:54:12
 */
@Mapper
public interface SysOperationLogMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(SysOperationLogWithBLOBs record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(SysOperationLogWithBLOBs record);

    /**
     * 根据主键查询该记录
     * 
     */
    SysOperationLogWithBLOBs findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<SysOperationLogWithBLOBs> findLike(SysOperationLogWithBLOBs condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<SysOperationLogWithBLOBs> findList(SysOperationLogWithBLOBs condition);
    
    /**
     * 获取操作日志
     */
    List<Map<String, Object>> getOperationLogs(@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("logName") String logName, @Param("logType") String logType, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);
    
    /**
     * 获取操作日志
     */
    void deleteAllLog();
    
}