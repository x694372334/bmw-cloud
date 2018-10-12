package com.bmw.medical.service;

import java.util.List;

import com.bmw.medical.model.DeptDoctorRelation;

public interface IDeptDoctorRelationService {
	/**
	 * 批量添加医生
	 * 
	 * @param list
	 * @return
	 */
	Object addList(List<DeptDoctorRelation> list);

	/**
	 * 批量删除医生
	 * 
	 * @param arr
	 */
	void delDoctor(String[] arr);

	/**
	 * 批量更新职称
	 * 
	 * @param doctorIds
	 * @param titleId
	 * @param titleName
	 */
	void submitTitle(String doctorIds, Integer titleId, String titleName);
}