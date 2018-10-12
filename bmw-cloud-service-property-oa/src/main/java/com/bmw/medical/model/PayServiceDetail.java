package com.bmw.medical.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 收费项目明细表
 * </p>
 *
 * @author liuwsh123
 * @since 2018-09-18
 */
@TableName("medical_pay_service_detail")
public class PayServiceDetail extends Model<PayServiceDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 科室名称
     */
    @TableField("department_name")
    private String departmentName;
    /**
     * 科室编码
     */
    @TableField("medical_department_code")
    private String medicalDepartmentCode;
    /**
     * 医生名称
     */
    @TableField("doctor_name")
    private String doctorName;
    /**
     * 医生编码
     */
    @TableField("medical_doctor_code")
    private String medicalDoctorCode;
    /**
     * 收费项目id
     */
    @TableField("pay_service_id")
    private Integer payServiceId;
    /**
     * 创建人id
     */
    @TableField("create_man_id")
    private Integer createManId;
    /**
     * 创建人
     */
    @TableField("create_man")
    private String createMan;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改人id
     */
    @TableField("edit_man_id")
    private Integer editManId;
    /**
     * 修改人
     */
    @TableField("edit_man")
    private String editMan;
    /**
     * 修改时间
     */
    @TableField("edit_time")
    private Date editTime;
    /**
     * 是否删除
     */
    @TableField("is_delete")
    private Integer isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getMedicalDepartmentCode() {
        return medicalDepartmentCode;
    }

    public void setMedicalDepartmentCode(String medicalDepartmentCode) {
        this.medicalDepartmentCode = medicalDepartmentCode;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getMedicalDoctorCode() {
        return medicalDoctorCode;
    }

    public void setMedicalDoctorCode(String medicalDoctorCode) {
        this.medicalDoctorCode = medicalDoctorCode;
    }

    public Integer getPayServiceId() {
        return payServiceId;
    }

    public void setPayServiceId(Integer payServiceId) {
        this.payServiceId = payServiceId;
    }

    public Integer getCreateManId() {
        return createManId;
    }

    public void setCreateManId(Integer createManId) {
        this.createManId = createManId;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEditManId() {
        return editManId;
    }

    public void setEditManId(Integer editManId) {
        this.editManId = editManId;
    }

    public String getEditMan() {
        return editMan;
    }

    public void setEditMan(String editMan) {
        this.editMan = editMan;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayServiceDetail{" +
        "id=" + id +
        ", departmentName=" + departmentName +
        ", medicalDepartmentCode=" + medicalDepartmentCode +
        ", doctorName=" + doctorName +
        ", medicalDoctorCode=" + medicalDoctorCode +
        ", payServiceId=" + payServiceId +
        ", createManId=" + createManId +
        ", createMan=" + createMan +
        ", createTime=" + createTime +
        ", editManId=" + editManId +
        ", editMan=" + editMan +
        ", editTime=" + editTime +
        ", isDelete=" + isDelete +
        "}";
    }
}
