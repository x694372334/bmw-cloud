package com.bmw.property.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.common.utils.excelPoiRead2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.InhabitantInfo;
import com.bmw.property.model.ParkingInfo;
import com.bmw.property.service.IParkingInfoService;


@Service
public class ParkingInfoServiceImpl implements IParkingInfoService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${parkingInfo.parkingInfoList}")
	private String parkingInfo_parkingInfoList="";

	@Value("${parkingInfo.parkingInfoDetail}")
	private String parkingInfo_parkingInfoDetail="";
	
	@Value("${parkingInfo.selectData}")
	private String parkingInfo_selectData="";
	
	@Value("${parkingInfo.selectCheckedData}")
	private String parkingInfo_selectCheckedData="";

	@Value("${parkingInfo.add}")
	private String parkingInfo_add="";

	@Value("${parkingInfo.update}")
	private String parkingInfo_update="";

	@Value("${parkingInfo.del}")
	private String parkingInfo_del="";
	
	
	@Value("${parkingInfo.findInhabitant}")
	private String parkingInfo_findInhabitant="";
	
	
	@Value("${parkingInfo.findIVehicle}")
	private String parkingInfo_findIVehicle="";

	@Value("${parkingInfo.createCWTree}")
	private String parkingInfo_createCWTree="";
	

	@Value("${parkingInfo.findQuery}")
	private String parkingInfo_findQuery="";
	
	
	@Value("${parkingInfo.findBuilding}")
	private String parkingInfo_findBuilding="";
	
	
	@Value("${parkingInfo.findRoomInfo}")
	private String parkingInfo_findRoomInfo="";
	
	
	@Value("${parkingInfo.findInhabitantInfo}")
	private String parkingInfo_findInhabitantInfo="";
	
	
	@Value("${parkingInfo.findNId}")
	private String parkingInfo_findNId="";

	@Value("${batchRoomBase.getCount}")
	private String batchRoomBase_getCount;

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("pNum", condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+parkingInfo_parkingInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	

	@Override
	public JSONArray findQuery(String pNum , String nName ,String eId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("pNum", pNum);
		json.put("pName", nName);
		json.put("eId", eId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+parkingInfo_findQuery+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}


	@Override
	public ParkingInfo getdetail(Integer parkingInfoId) {
		ParkingInfo parkingInfo=new ParkingInfo();
		JSONObject json=new JSONObject();
        json.put("parkingInfoId", parkingInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+parkingInfo_parkingInfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				parkingInfo=jsonObject.toJavaObject(ParkingInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return parkingInfo;
	}

	@Override
	public void add(ParkingInfo parkingInfo) {
		ShiroExt shiro = new ShiroExt();
		parkingInfo.setCreateMan(shiro.getUser().getName());
		parkingInfo.setCreateManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(parkingInfo).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+parkingInfo_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(ParkingInfo parkingInfo) {
		ShiroExt shiro = new ShiroExt();
		parkingInfo.setEditMan(shiro.getUser().getName());
		parkingInfo.setEditManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(parkingInfo).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+parkingInfo_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer parkingInfoId) {
		JSONObject json=new JSONObject();
        json.put("parkingInfoId", parkingInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+parkingInfo_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public JSONArray findInhabitant(Integer parkingInfoId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("parkingInfoId",  parkingInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+parkingInfo_findInhabitant+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
	
	@Override
	public JSONArray findIVehicle(Integer parkingInfoId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("parkingInfoId",  parkingInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+parkingInfo_findIVehicle+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}

	@Override
	public List<ZTreeNode> createCWTree(Integer eId,Integer nbId) {
		Map<String,String> httpSetting=new HashMap<>();
		List<ZTreeNode> treeList=new ArrayList<>();
		Map<String,Integer> param=new HashMap<>();
		param.put("eId", eId);
		param.put("nbId", nbId);
		httpSetting.put("contentType", "application/json");
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+parkingInfo_createCWTree,JSONObject.toJSONString(param).toString(),null);
			treeList=JSONObject.parseObject(rlt).getJSONArray("items").toJavaList(ZTreeNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return treeList;
	}
	
	public Integer pType(String data) {
		Integer text= 0;
		if(data.equals("租用")) {
			text=1;
		}else if(data.equals("出售")) {
			text=2;
		}
		return text;
	}
	public Integer pStatus(String data) {
		Integer text= 0;
		if(data.equals("空闲")) {
			text=1;
		}else if(data.equals("已售")) {
			text=2;
		}else if(data.equals("已租")) {
			text=3;
		}else if(data.equals("自用")) {
			text=4;
		}
		return text;
	}
	
	
	@Override
	public void impExcel(MultipartFile file , String rId , String rcode , String bId) {
		InputStream in = null ;
		
		try {
			in = file.getInputStream();
		} catch (IOException e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		        //excel读取数据
				excelPoiRead2 test= new excelPoiRead2(); 
				ArrayList<ArrayList<String>> arr;
				ShiroExt shiro = new ShiroExt();
				List<ParkingInfo> list = new ArrayList<ParkingInfo>();
				String rCode = rcode;
				try {
					//读取excel文件
					arr = test.xlsx_reader(in,0,1,2,3,4,5,6,7,8,9,10,11,12);//后面的参数代表读取第几行
					//循环输出读取数据储存进实体类
					for(int i=1;i<arr.size();i++){
						ParkingInfo room = new ParkingInfo();
						ArrayList<String> row=arr.get(i);
						for(int j=0;j<row.size();j++){
							//楼宇
							room.setpNum(row.get(0));
							room.setpType(pType(row.get(1)));
							room.setpArea(BigDecimal.valueOf(Long.valueOf(row.get(2))));
							room.setpStatus(pStatus(row.get(3)));
							String[] data = row.get(4).split("\\|");
							room.setiId(Integer.parseInt(data[1]));// 住户
							room.setnId(Integer.parseInt(findNId(Integer.parseInt(data[1])))); // 小区
						}
						list.add(room);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  //后面的参数代表需要输出哪些列，参数个数可以任意
		for(int i=0;i<list.size();i++) {
			ParkingInfo roomText = new ParkingInfo();
			roomText = list.get(i);
			lotAdd(roomText);
		}
	}
	
	@Override
	public void lotAdd(ParkingInfo roomInfo) {
		ShiroExt shiro = new ShiroExt();
		roomInfo.setCreateMan(shiro.getUser().getName());
		roomInfo.setCreateManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(roomInfo).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+parkingInfo_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	


	@Override
	public Object selectData(Integer nbId, Integer id, Integer relevanceId) {
		JSONObject json=new JSONObject();
		json.put("nbId", nbId);
		json.put("batchId", id);
		json.put("relevanceId", relevanceId);
		List<Map<String,Object>> checkedMap = Lists.newArrayList();
		List<Map<String,Object>> uncheckedMap = Lists.newArrayList();
		
		Map<String,List<Map<String,Object>>> maps= Maps.newHashMap();
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+parkingInfo_selectData+params);
			List<ParkingInfo> unCheckList=JSONObject.parseObject(rlt).getJSONArray("items").toJavaList(ParkingInfo.class);
			
			String rlt1=HttpUtils.doGet(bmw_cloud_baseservice_url+parkingInfo_selectCheckedData+params);
			List<ParkingInfo> checkedList=JSONObject.parseObject(rlt1).getJSONArray("items").toJavaList(ParkingInfo.class);
			
			
			
			for(ParkingInfo p : unCheckList) {
				Map<String,Object> map = Maps.newHashMap();
				map.put("rId", p.getpId() + "@"); //@判断是否是后台带过来的还是前台后选择
				map.put("rRoomnum", p.getpNum());
				uncheckedMap.add(map);
			}
			maps.put("uncheckedMap", uncheckedMap);
			
			for(ParkingInfo p : checkedList) {
				Map<String,Object> map = Maps.newHashMap();
				map.put("rId", p.getpId() + "#"); //@判断是否是后台带过来的还是前台后选择
				map.put("rRoomnum", p.getpNum());
				checkedMap.add(map);
			}
			maps.put("checkedMap", checkedMap);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return maps;
	}
	
	@Override
	public String findNId(Integer iId) {
		JSONArray jsonArray=null;
		String data = "" ;
		JSONObject json=new JSONObject();
        json.put("iId", iId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+parkingInfo_findNId+params , null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
//			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			data = jsonObject.getString("nId");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data;
	}
	
	@Override
	public JSONArray findBuilding(Integer nId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("nId", nId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+parkingInfo_findBuilding+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
	
	@Override
	public JSONArray findRoomInfo(Integer bId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("bId", bId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+parkingInfo_findRoomInfo+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
	
	@Override
	public List<InhabitantInfo> findInhabitantInfo(Integer rId) {
		List<InhabitantInfo> list = new ArrayList<>();
		JSONObject json=new JSONObject();
        json.put("rId", rId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+parkingInfo_findInhabitantInfo+params , null);
			list=JSON.parseObject(rlt).getJSONArray("items").toJavaList(InhabitantInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
	}

	@Override
	public boolean isAssociated(Integer parkingInfoId) {
		ParkingInfo parkingInfo = getdetail(parkingInfoId);
		JSONObject parkingInfoJson=new JSONObject();
		parkingInfoJson.put("rId", parkingInfoId);
		parkingInfoJson.put("nbId", parkingInfo.getnId());
		parkingInfoJson.put("relevanceId", 2);
		
		try {
			String rlt  = HttpUtils.doPost(bmw_cloud_baseservice_url + batchRoomBase_getCount ,parkingInfoJson.toJSONString(), null);
			JSONObject rltJson=JSONObject.parseObject(rlt);
			if(null != rltJson  ) {
				String count = rltJson.getString("items");
				return Integer.valueOf(count) > 0?true :false;
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}


}