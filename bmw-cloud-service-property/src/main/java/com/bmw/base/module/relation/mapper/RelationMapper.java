package com.bmw.base.module.relation.mapper;

import com.bmw.base.module.relation.domain.Relation;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 角色和菜单关联表Mapper类
 * 
 * @author lyl
 * @date 2018-04-26 10:30:27
 */
@Mapper
public interface RelationMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Relation record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Relation record);

    /**
     * 根据主键查询该记录
     * 
     */
    Relation findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Relation> findLike(Relation condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Relation> findList(Relation condition);
    
    
    void delByMenuId(@Param("meanId") Integer meanId);
    
    void delByRoleId(@Param("roleId") Integer roleId);

	Integer findByRidMid(@Param("roleId") String roleId, @Param("menuid") String menuid);

	void addApp(@Param("roleId") String roleId, @Param("menuid") String menuid);

	List<Integer> findListByRoleId(Integer roleId);

	void deleteAppByRoleId(String roleId);
}