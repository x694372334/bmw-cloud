package com.bmw.medical.model;

/**
 * 科室与医生关联表
 * 
 * @author jmy
 * @date 2018-06-07 04:17:29
 */
public class DeptDoctorRelation {

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 科室code
	 */
	private String medicalDepartmentCode;

	/**
	 * 医生code
	 */
	private String medicalDoctorCode;

	/**
	 * 医生职称ID
	 */
	private Integer medicalDoctorTitleid;

	/**
	 * 医生职称
	 */
	private String medicalDoctorTitle;

	/**
	 * 医生姓名
	 */
	private String medicalDoctorName;

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
	 * 科室code
	 */
	public String getMedicalDepartmentCode() {
		return medicalDepartmentCode;
	}

	/**
	 * 科室code
	 */
	public void setMedicalDepartmentCode(String medicalDepartmentCode) {
		this.medicalDepartmentCode = medicalDepartmentCode == null ? "" : medicalDepartmentCode.trim();
	}

	/**
	 * 医生code
	 */
	public String getMedicalDoctorCode() {
		return medicalDoctorCode;
	}

	/**
	 * 医生code
	 */
	public void setMedicalDoctorCode(String medicalDoctorCode) {
		this.medicalDoctorCode = medicalDoctorCode == null ? "" : medicalDoctorCode.trim();
	}

	/**
	 * 医生职称
	 */
	public String getMedicalDoctorTitle() {
		return medicalDoctorTitle;
	}

	public Integer getMedicalDoctorTitleid() {
		return medicalDoctorTitleid;
	}

	public void setMedicalDoctorTitleid(Integer medicalDoctorTitleid) {
		this.medicalDoctorTitleid = medicalDoctorTitleid;
	}

	public String getMedicalDoctorName() {
		return medicalDoctorName;
	}

	public void setMedicalDoctorName(String medicalDoctorName) {
		this.medicalDoctorName = medicalDoctorName;
	}

	/**
	 * 医生职称
	 */
	public void setMedicalDoctorTitle(String medicalDoctorTitle) {
		this.medicalDoctorTitle = medicalDoctorTitle == null ? "" : medicalDoctorTitle.trim();
	}
}