package com.bmw.property.module.enterprise.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.bmw.common.utils.oa.ZTreeNode;
import com.bmw.property.module.enterprise.domain.EnterpriseInfo;


/**
 * 企业信息Mapper类
 * 
 * @author zw
 * @date 2018-06-26 08:30:46
 */
@Mapper
public interface EnterpriseInfoMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(EnterpriseInfo record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer eId);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(EnterpriseInfo record);

    /**
     * 根据主键查询该记录
     * 
     */
    EnterpriseInfo findById(Integer eId);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<EnterpriseInfo> findLike(EnterpriseInfo condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<EnterpriseInfo> findList(EnterpriseInfo condition);
    
    List<ZTreeNode> findTreeList();

	List<ZTreeNode> findParentTreeList();

	List<ZTreeNode> findTreeListByParentEId(String parenteid);
}