package com.bmw.base.module.position.mapper;

import com.bmw.base.module.position.domain.Position;
import com.bmw.common.utils.oa.ZTreeNode;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 职位表Mapper类
 * 
 * @author zhangt
 * @date 2018-06-29 03:27:21
 */
@Mapper
public interface PositionMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Position record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Position record);

    /**
     * 根据主键查询该记录
     * 
     */
    Position findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Position> findLike(Position condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Position> findList(Position condition);
    
    
    List<ZTreeNode> positionTreeList();
    
    String positionCountCode(String code);
    
    String positionCountIsCode(String code);
    
    String positionCodeSelect(String code);
    
    Position positionDeleteUpdate(String code);
}