package com.bmw.medical.model;

import java.util.Date;


/**
 * 排班历史表
 * 
 * @author jmy
 * @date 2018-06-07 03:46:09
 */
public class ScheduleHis {


    /**
     * 主键
     */
    private Integer id;


    /**
     * 排班日期
     */
    private String scheduleDate;


    /**
     * 显示方式
     */
    private String approach;


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



    public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
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
        this.isOpen = isOpen;
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