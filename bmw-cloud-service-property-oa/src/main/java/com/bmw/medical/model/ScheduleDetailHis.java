package com.bmw.medical.model;

import java.util.Date;


/**
 * 排班明细历史表
 * 
 * @author jmy
 * @date 2018-06-07 03:45:49
 */
public class ScheduleDetailHis {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 排班id
     */
    private Integer medicalScheduleId;


    /**
     * 排班日期
     */
    private Date scheduleDate;


    /**
     * 开始时间
     */
    private String scheduleStartTime;


    /**
     * 结束时间
     */
    private String scheduleEndTime;


    /**
     * 科室姓名
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
     * 是否可以预约
     */
    private Integer isAppointment;


    /**
     * 预约人数
     */
    private Integer appointmentNum;


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
     * 排班id
     */
    public Integer getMedicalScheduleId() {
        return medicalScheduleId;
    }

    /**
     * 排班id
     */
    public void setMedicalScheduleId(Integer medicalScheduleId) {
        this.medicalScheduleId = medicalScheduleId;
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
     * 开始时间
     */
    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    /**
     * 开始时间
     */
    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime == null ? null : scheduleStartTime.trim();
    }

    /**
     * 结束时间
     */
    public String getScheduleEndTime() {
        return scheduleEndTime;
    }

    /**
     * 结束时间
     */
    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime == null ? null : scheduleEndTime.trim();
    }

    /**
     * 科室姓名
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 科室姓名
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
     * 是否可以预约
     */
    public Integer getIsAppointment() {
        return isAppointment;
    }

    /**
     * 是否可以预约
     */
    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
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
}