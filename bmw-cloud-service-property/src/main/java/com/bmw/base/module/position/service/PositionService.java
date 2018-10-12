package com.bmw.base.module.position.service;

import com.bmw.base.module.position.domain.Position;
import com.bmw.base.module.position.mapper.PositionMapper;
import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.common.utils.oa.ZTreeNode;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 职位表Service类
 * 
 * @author zhangt
 * @date 2018-06-29 03:27:21
 */
@Service
public class PositionService {

    @Autowired
    private PositionMapper positionMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public PositionService currentProxy() {
        return SpringContextUtils.getBean(PositionService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_position",idenName="职位表",idBeanName="id")
    public int add(Position record) {
        return positionMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_position",idenName="职位表",idBeanName="id")
    public int delete(Integer id) {
        return positionMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_position",idenName="职位表",idBeanName="id")
    public int update(Position record) {
        return positionMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public Position findById(Integer id) {
        return positionMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Position> findLike(Position condition) {
        return positionMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Position> findList(Position condition) {
        return positionMapper.findList(condition);
    }
    
    public List<ZTreeNode> positionTreeList(){
    	return positionMapper.positionTreeList();
    }
    
    public String positionCountCode(String code){
    	return positionMapper.positionCountCode(code);
    }
    
    public String positionCountIsCode(String code) {
    	return positionMapper.positionCountIsCode(code);
    }
    
    public String positionCodeSelect(String code){
    	return positionMapper.positionCodeSelect(code);
    }
    
    public Position positionDeleteUpdate(String code) {
    	return positionMapper.positionDeleteUpdate(code);
    }
}