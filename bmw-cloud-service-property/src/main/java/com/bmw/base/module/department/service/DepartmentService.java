package com.bmw.base.module.department.service;

import com.bmw.base.module.department.domain.Department;
import com.bmw.base.module.department.mapper.DepartmentMapper;
import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.common.utils.oa.ZTreeNode;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 部门表Service类
 * 
 * @author zhangt
 * @date 2018-06-29 01:11:38
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public DepartmentService currentProxy() {
        return SpringContextUtils.getBean(DepartmentService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_department",idenName="部门表",idBeanName="id")
    public int add(Department record) {
        return departmentMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_department",idenName="部门表",idBeanName="id")
    public int delete(Integer id) {
        return departmentMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_department",idenName="部门表",idBeanName="id")
    public int update(Department record) {
        return departmentMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public Department findById(Integer id) {
        return departmentMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Department> findLike(Department condition) {
        return departmentMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Department> findList(Department condition) {
        return departmentMapper.findList(condition);
    }
    
    public List<ZTreeNode> departmentTreeList(){
    	return departmentMapper.departmentTreeList();
    }
    
    public String departmentCountCode(String code){
    	return departmentMapper.departmentCountCode(code);
    }
    
    public String departmentCountIsCode(String code) {
    	return departmentMapper.departmentCountIsCode(code);
    }
    
    public String departmentCodeSelect(String code){
    	return departmentMapper.departmentCodeSelect(code);
    }
    
    public Department departmentDeleteUpdate(String code) {
    	return departmentMapper.departmentDeleteUpdate(code);
    }

	public List<ZTreeNode> getDepartmentTreeListByParentEId(String parenteid) {
			return departmentMapper.getDepartmentTreeListByParentEId(parenteid);
	}
}