package com.bmw.base.module.notice.mapper;

import com.bmw.base.module.notice.domain.Notice;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 通知表Mapper类
 * 
 * @author lyl
 * @date 2018-04-26 08:47:40
 */
@Mapper
public interface NoticeMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Notice record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Notice record);

    /**
     * 根据主键查询该记录
     * 
     */
    Notice findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Notice> findLike(Notice condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Notice> findList(Notice condition);
    
    
    /**
     * 获取列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition);
}