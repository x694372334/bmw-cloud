package com.bmw.medical.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 排班表
 * 
 * @author liuwsh
 * @date 2018-09-04 11:43:06
 */
public class Schedule {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 排班日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd") 
    private Date scheduleDate;


    /**
     * 显示方式
     */
    private String approach;


    /**
     * 科室名称
     */
    private String departmentName;


    /**
     * 科室编码
     */
    private String medicalDepartmentCode;


    /**
     * 医生姓名
     */
    private String doctorName;


    /**
     * 医生编码
     */
    private String medicalDoctorCode;


    /**
     * 预约人数
     */
    private Integer appointmentNum;


    /**
     * 是否启用
     */
    private Integer isOpen;


    /**
     * 创建人id
     */
    private Integer createManId;


    /**
     * 创建人
     */
    private String createMan;


    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 修改人id
     */
    private Integer editManId;


    /**
     * 修改人
     */
    private String editMan;


    /**
     * 修改时间
     */
    private Date editTime;


    /**
     * 是否删除
     */
    private Integer isDelete;



    /**
     * 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 排班日期
     */
    public Date getScheduleDate() {
        return scheduleDate;
    }

    /**
     * 排班日期
     */
    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    /**
     * 显示方式
     */
    public String getApproach() {
        return approach;
    }

    /**
     * 显示方式
     */
    public void setApproach(String approach) {
        this.approach = approach == null ? null : approach.trim();
    }

    /**
     * 科室名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 科室名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    /**
     * 科室编码
     */
    public String getMedicalDepartmentCode() {
        return medicalDepartmentCode;
    }

    /**
     * 科室编码
     */
    public void setMedicalDepartmentCode(String medicalDepartmentCode) {
        this.medicalDepartmentCode = medicalDepartmentCode == null ? null : medicalDepartmentCode.trim();
    }

    /**
     * 医生姓名
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * 医生姓名
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    /**
     * 医生编码
     */
    public String getMedicalDoctorCode() {
        return medicalDoctorCode;
    }

    /**
     * 医生编码
     */
    public void setMedicalDoctorCode(String medicalDoctorCode) {
        this.medicalDoctorCode = medicalDoctorCode == null ? null : medicalDoctorCode.trim();
    }

    /**
     * 预约人数
     */
    public Integer getAppointmentNum() {
        return appointmentNum;
    }

    /**
     * 预约人数
     */
    public void setAppointmentNum(Integer appointmentNum) {
        this.appointmentNum = appointmentNum;
    }

    /**
     * 是否启用
     */
    public Integer getIsOpen() {
        return isOpen;
    }

    /**
     * 是否启用
     */
    public void setIsOpen(Integer isOpen) {
        this.isOpen =null== isOpen?0:isOpen;
    }

    /**
     * 创建人id
     */
    public Integer getCreateManId() {
        return createManId;
    }

    /**
     * 创建人id
     */
    public void setCreateManId(Integer createManId) {
        this.createManId = createManId;
    }

    /**
     * 创建人
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * 创建人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人id
     */
    public Integer getEditManId() {
        return editManId;
    }

    /**
     * 修改人id
     */
    public void setEditManId(Integer editManId) {
        this.editManId = editManId;
    }

    /**
     * 修改人
     */
    public String getEditMan() {
        return editMan;
    }

    /**
     * 修改人
     */
    public void setEditMan(String editMan) {
        this.editMan = editMan == null ? null : editMan.trim();
    }

    /**
     * 修改时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 修改时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * 是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}