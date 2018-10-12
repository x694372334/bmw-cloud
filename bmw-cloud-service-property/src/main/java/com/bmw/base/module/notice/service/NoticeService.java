package com.bmw.base.module.notice.service;

import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.base.module.notice.domain.Notice;
import com.bmw.base.module.notice.mapper.NoticeMapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 通知表Service类
 * 
 * @author lyl
 * @date 2018-04-26 08:47:40
 */
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public NoticeService currentProxy() {
        return SpringContextUtils.getBean(NoticeService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_notice",idenName="新增通知",idBeanName="id")
    public int add(Notice record) {
        return noticeMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_notice",idenName="删除通知",idBeanName="id")
    public int delete(Integer id) {
        return noticeMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_notice",idenName="更新通知",idBeanName="id")
    public int update(Notice record) {
        return noticeMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public Notice findById(Integer id) {
        return noticeMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Notice> findLike(Notice condition) {
        return noticeMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Notice> findList(Notice condition) {
        return noticeMapper.findList(condition);
    }
    
    
    /**
     * 获取列表
     */
    public List<Map<String, Object>> list(@Param("condition") String condition){
    	return noticeMapper.list(condition);
    }
}