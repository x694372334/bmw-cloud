package com.bmw.base.module.patient.mapper;

import com.bmw.base.module.patient.domain.Patient;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * Mapper类
 * 
 * @author fjm
 * @date 2018-05-09 08:40:10
 */
@Mapper
public interface PatientMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Patient record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Patient record);

    /**
     * 根据主键查询该记录
     * 
     */
    Patient findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Patient> findLike(Patient condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Patient> findList(Patient condition);
}