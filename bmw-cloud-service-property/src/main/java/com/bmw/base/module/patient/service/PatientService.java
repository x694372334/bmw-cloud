package com.bmw.base.module.patient.service;

import com.bmw.base.module.patient.domain.Patient;
import com.bmw.base.module.patient.mapper.PatientMapper;
import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service类
 * 
 * @author fjm
 * @date 2018-05-09 08:40:10
 */
@Service
public class PatientService {

    @Autowired
    private PatientMapper patientMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public PatientService currentProxy() {
        return SpringContextUtils.getBean(PatientService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="his_patient",idenName="",idBeanName="id")
    public int add(Patient record) {
        return patientMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="his_patient",idenName="",idBeanName="id")
    public int delete(Integer id) {
        return patientMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="his_patient",idenName="",idBeanName="id")
    public int update(Patient record) {
        return patientMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public Patient findById(Integer id) {
        return patientMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Patient> findLike(Patient condition) {
        return patientMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Patient> findList(Patient condition) {
        return patientMapper.findList(condition);
    }
}