package com.bmw.base.module.dict.mapper;

import com.bmw.base.module.dict.domain.Dict;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 字典表Mapper类
 * 
 * @author lyl
 * @date 2018-04-16 04:33:03
 */
@Mapper
public interface DictMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Dict record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Dict record);

    /**
     * 根据主键查询该记录
     * 
     */
    Dict findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Dict> findLike(Dict condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Dict> findList(Dict condition);
    
    List<Dict> getDictByCode(Dict dict);
}