package com.bmw.property.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Doctor;
import com.bmw.property.model.ApplyInfo;
import com.bmw.property.model.InhabitantInfo;

public interface IInhabitantInfoService {
	
	/**
     * 住户列表
     */
	JSONArray findList(String condition);
	
	/**
     * 住户列表
     */
	JSONArray findQuery(String xqName, String lyName, String fjName, String zhName, String phoneNo, String eId);
	
	/**
     * 获取住户详情
     */
	InhabitantInfo getdetail(Integer hospitalId);
	
	
	/**
     * 新增住户信息
     */
	void add(InhabitantInfo hospital);
	
	/**
	 * 修改住户信息
	 */
	void update(InhabitantInfo hospital);
	
	/**
	 * 删除住户信息
	 */
	void del(Integer hospitalId);
	
	JSONArray findRoomInfo();
	
	JSONArray findRoomInfoExcel(String bId);
	
	
	void flowabled_add(InhabitantInfo inhabitantInfo);
	
	void flowabled_apply(String taskId, String iId, String user_id);
	
	void flowabled_reject(String taskId, String iId);
	
	void impExcel(MultipartFile file, String nId, String rcode, String bId);

	void lotAdd(InhabitantInfo roomInfo);
	
	Map<String,String> findUserById(String id);

	JSONArray findAppUserByAid(String userId);
	
	JSONArray findByAllInha(Integer inhabitantInfoId);
	
}
