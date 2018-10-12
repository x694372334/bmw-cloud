package com.bmw.property.module.enterprise.service;

import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.common.utils.oa.ZTreeNode;
import com.bmw.property.module.enterprise.domain.EnterpriseInfo;
import com.bmw.property.module.enterprise.mapper.EnterpriseInfoMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 企业信息Service类
 * 
 * @author zw
 * @date 2018-06-26 08:30:46
 */
@Service
public class EnterpriseInfoService {

    @Autowired
    private EnterpriseInfoMapper enterpriseInfoMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public EnterpriseInfoService currentProxy() {
        return SpringContextUtils.getBean(EnterpriseInfoService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="enterprise_info",idenName="企业管理",idBeanName="id")
    public int add(EnterpriseInfo record) {
        return enterpriseInfoMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="enterprise_info",idenName="企业管理",idBeanName="id")
    public int delete(Integer eId) {
        return enterpriseInfoMapper.delete(eId);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="enterprise_info",idenName="企业管理",idBeanName="id")
    public int update(EnterpriseInfo record) {
        return enterpriseInfoMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public EnterpriseInfo findById(Integer eId) {
        return enterpriseInfoMapper.findById(eId);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<EnterpriseInfo> findLike(EnterpriseInfo condition) {
    	List<EnterpriseInfo>list = enterpriseInfoMapper.findLike(condition);
        return list;
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<EnterpriseInfo> findList(EnterpriseInfo condition) {
        return enterpriseInfoMapper.findList(condition);
    }
    public List<ZTreeNode> findTreeList(){
    	return enterpriseInfoMapper.findTreeList();
    }

	public List<ZTreeNode> findParentTreeList() {
		return enterpriseInfoMapper.findParentTreeList();
	}

	public List<ZTreeNode>  findTreeListByParentEId(String parenteid) {
		return enterpriseInfoMapper.findTreeListByParentEId(parenteid);
	}
}