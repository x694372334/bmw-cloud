package com.bmw.property.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.InhabitantInfo;
import com.bmw.property.model.ParkingInfo;

public interface IParkingInfoService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取医院详情
     */
	ParkingInfo getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(ParkingInfo hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(ParkingInfo hospital);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);
	
	JSONArray findInhabitant(Integer parkingInfoId);
	
	JSONArray findIVehicle(Integer parkingInfoId);

	/**
	 * @param nbId 
	  * 方法名称 : createCWTree
	  * 作者 : wangliqing  
	  * 方法描述 : 创建小区——车位Tree
	  * 创建时间 : 2018年6月29日 下午2:00:23    
	  * 参数 : @param eId
	  * 参数 : @return  
	  * 返回类型 : List<ZTreeNode>    
	  * @throws
	 */
	List<ZTreeNode> createCWTree(Integer eId, Integer nbId);
	
	/**
     * 医院列表
     */
	JSONArray findQuery(String pNum, String nName, String eId);
	
	void impExcel(MultipartFile file, String nId, String rcode, String bId);

	void lotAdd(ParkingInfo roomInfo);

	/**
	  * 方法名称 : selectData
	  * 作者 : wangliqing  
	  * 方法描述 : 查询车位列表
	  * 创建时间 : 2018年8月3日 下午1:56:29    
	  * 参数 : @param nbId
	  * 参数 : @param id
	  * 参数 : @param relevanceId
	  * 参数 : @return  
	  * 返回类型 : Object    
	  * @throws
	 */
	Object selectData(Integer nbId, Integer id, Integer relevanceId);
	
	JSONArray findBuilding(Integer nId);
	
	
	JSONArray findRoomInfo(Integer bId);
	
	
	List<InhabitantInfo> findInhabitantInfo(Integer rId);

	boolean isAssociated(Integer parkingInfoId);
	
	String findNId(Integer iId);

}
