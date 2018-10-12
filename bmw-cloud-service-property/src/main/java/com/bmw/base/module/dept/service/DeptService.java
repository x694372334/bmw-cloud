package com.bmw.base.module.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.codingapi.tx.annotation.TxTransaction;
//import com.codingapi.tx.annotation.TxTransaction;
import com.bmw.base.module.dept.domain.Dept;
import com.bmw.base.module.dept.mapper.DeptMapper;
import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.model.Result;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.common.utils.oa.ZTreeNode;
import com.bmw.property.module.feignService.base.dept.DeptFeignService;


/**
 * 部门表Service类
 *
 * @author lyl
 * @date 2018-04-19 01:15:52
 */
@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;
    
    @Autowired
    private DeptFeignService deptFeign;
    
    



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public DeptService currentProxy() {
        return SpringContextUtils.getBean(DeptService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_dept",idenName="部门管理",idBeanName="id")
    public int add(Dept record) {
        return deptMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_dept",idenName="部门管理",idBeanName="id")
    public int delete(Integer id) {
        return deptMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_dept",idenName="部门管理",idBeanName="id")
    public int update(Dept record) {
        return deptMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Dept> findLike(Dept condition) {
        return deptMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Dept> findList(Dept condition) {
        return deptMapper.findList(condition);
    }
    
    
    /**
     * 获取ztree的节点列表
     */
    public List<ZTreeNode> tree(){
    	return deptMapper.tree();
    }
    
    /**
     * 删除部门及子部门
     * @param deptId
     */
    public void deleteDept(Integer deptId) {
        Dept condition =new Dept();
        condition.setPids(deptId.toString());
        List<Dept> subDepts = deptMapper.findLike(condition);
        for (Dept temp : subDepts) {
        	this.currentProxy().delete(temp.getId());
        }
        this.currentProxy().delete(deptId);
    }
    
    
//    /**
//     * 测试lcn分布式事务
//     * 模拟自服务先添加数据，后跨服务添加数据
//     */
//    @Transactional
////    @TxTransaction(isStart=true)//是否是事务发起点
//    public int testLcn(Dept record, String jsonString) {
//
//
//    	//先后跨服务添加数据
//    	Result result=deptFeign.deptList("123");
//    	System.out.println(result.getMessage());
//    	System.out.println(result.getStatusCode());
//    	Result result2=deptFeign.add(jsonString);
//    	System.out.println(result2.getMessage());
//    	System.out.println(result2.getStatusCode());
//
//    	//自服务先添加数据
//    	deptMapper.add(record);
//    	int v = 100/0;
//        return 1;
//    }
}