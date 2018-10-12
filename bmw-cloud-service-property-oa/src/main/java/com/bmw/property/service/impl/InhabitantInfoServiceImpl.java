package com.bmw.property.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.bmw.property.model.ApplyInfo;
import com.bmw.property.model.BuildingInfo;
import com.bmw.property.model.InhabitantInfo;
import com.bmw.property.model.MBaseInfo;
import com.bmw.property.model.RoomInfo;
import com.bmw.property.service.IInhabitantInfoService;


@Service
public class InhabitantInfoServiceImpl implements IInhabitantInfoService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${inhabitantInfo.inhabitantInfoList}")
	private String inhabitantInfo_inhabitantInfoList="";

	@Value("${inhabitantInfo.inhabitantInfoDetail}")
	private String inhabitantInfo_inhabitantInfoDetail="";

	@Value("${inhabitantInfo.add}")
	private String inhabitantInfo_add="";

	@Value("${inhabitantInfo.update}")
	private String inhabitantInfo_update="";

	@Value("${inhabitantInfo.del}")
	private String inhabitantInfo_del="";
	
	@Value("${inhabitantInfo.findRoomInfo}")
	private String inhabitantInfo_findRoomInfo="";
	
	@Value("${inhabitantInfo.findRoomInfoExcel}")
	private String inhabitantInfo_findRoomInfoExcel="";
	
	@Value("${inhabitantInfo.findQuery}")
	private String inhabitantInfo_findQuery="";
	
	@Value("${inhabitantInfo.flowabled_add}")
	private String flowabled_add="";

	@Value("${inhabitantInfo.flowabled_apply}")
	private String flowabled_apply="";

	@Value("${inhabitantInfo.flowabled_reject}")
	private String flowabled_reject="";
	
	@Value("${inhabitantInfo.findUserById}")
	private String findUserById="";

	@Value("${roomInfo.findBuildingReader}")
	private String roomInfo_findBuildingReader="";
	
	@Value("${roomInfo.findRoomInfoReader}")
	private String roomInfo_findRoomInfoReader="";

	@Value("${inhabitantInfo.findInhabitantByUid}")
	private String inhabitantInfo_findInhabitantByUid="";
	
	@Value("${huanxin.findAPPUserVOByAid}")
	private String huanxin_findAPPUserVOByAid="";
	
	@Value("${inhabitantInfo.findByAllInha}")
	private String inhabitantInfo_findByAllInha="";

	/**
	 * 功能：住户列表查询
	 * 开发者：金明禹
	 * */

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null; 
		JSONObject json=new JSONObject();
//		json.put("iName", condition);
		json.put("rId", condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+inhabitantInfo_inhabitantInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	
	@Override
	public JSONArray findAppUserByAid(String aid) {
		JSONArray jsonArray=null; 
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+huanxin_findAPPUserVOByAid+"?aid="+aid);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	/**
	 * 功能：住户列表查询(目前使用的)
	 * 开发者：金明禹
	 * */
	
	@Override
	public JSONArray findQuery(String xqName , String lyName , String fjName,String zhName,String phoneNo,String eId) {
		JSONArray jsonArray=null; 
		JSONObject json=new JSONObject();
		json.put("eId", eId);
		json.put("bName", lyName);
		json.put("nName", xqName);
		json.put("rRoomnum", fjName);
		json.put("iName", zhName);
		json.put("iPhoneno", phoneNo);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+inhabitantInfo_findQuery+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	/**
	 * 功能：住户根据ID查看
	 * 开发者：金明禹
	 * */
	
	@Override
	public InhabitantInfo getdetail(Integer inhabitantInfoId) {
		InhabitantInfo inhabitantInfo=new InhabitantInfo();
		JSONObject json=new JSONObject();
        json.put("inhabitantInfoId", inhabitantInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+inhabitantInfo_inhabitantInfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				inhabitantInfo=jsonObject.toJavaObject(InhabitantInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return inhabitantInfo;
	}
	
	/**
	 * 功能：住户根据ID查看
	 * 开发者：金明禹
	 * */
	
	@Override 
	public Map<String,String> findUserById(String id) {
		List<Map<String,String>> inhabitantInfo = null ; //=new HashMap();
			String rlt;
			try {
				rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+findUserById+id, null);
				JSONArray jsonObject=JSON.parseObject(rlt).getJSONArray("items");
				if(jsonObject!=null) {
					inhabitantInfo=jsonObject.toJavaObject(List.class);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return inhabitantInfo.get(0);
	}
	
	/**
	 * 功能：住户新增
	 * 开发者：金明禹
	 * */

	@Override
	public void add(InhabitantInfo inhabitantInfo) {
		ShiroExt shiro = new ShiroExt();
		inhabitantInfo.setCreateMan(shiro.getUser().getName());
		inhabitantInfo.setCreateManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+inhabitantInfo_add, HttpUtils.convertParam(inhabitantInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 功能：住户修改
	 * 开发者：金明禹
	 * */

	@Override
	public void update(InhabitantInfo inhabitantInfo) {
		ShiroExt shiro = new ShiroExt();
		inhabitantInfo.setEditMan(shiro.getUser().getName());
		inhabitantInfo.setEditManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+inhabitantInfo_update, HttpUtils.convertParam(inhabitantInfo));
//    		String params=Base64Utils.encodeToString(JSON.toJSONString(inhabitantInfo).toString().getBytes("UTF-8"));
//			HttpUtils.doPost(bmw_cloud_baseservice_url+inhabitantInfo_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	/**
	 * 功能：住户删除 
	 * 开发者：金明禹
	 * */
	@Override
	public void del(Integer inhabitantInfoId) {
		JSONObject json=new JSONObject();
        json.put("inhabitantInfoId", inhabitantInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+inhabitantInfo_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 功能：住户查询房屋列表信息
	 * 开发者：金明禹
	 * */
	
	@Override
	public JSONArray findRoomInfo() {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("roomInfoId",  "");
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+inhabitantInfo_findRoomInfo+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
	
	/**
	 * 功能：住户查询房屋列表信息
	 * 开发者：金明禹
	 * */
	
	@Override
	public JSONArray findRoomInfoExcel(String bId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("bId",  bId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+inhabitantInfo_findRoomInfoExcel+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}

	
	@Override
	public void flowabled_add(InhabitantInfo inhabitantInfo) {
		ShiroExt shiro = new ShiroExt();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("eaUser", shiro.getUser().getId());
		jsonObject.put("businessId", inhabitantInfo.getiId());
		try {
			String params=Base64Utils.encodeToString(JSON.toJSONString(jsonObject).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+flowabled_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	  /**
     * 批准
     *
     * @param taskId 任务ID
     */
	@Override
	public void flowabled_apply(String taskId , String iId , String user_id) {
		ShiroExt shiro = new ShiroExt();
		String userId = shiro.getUser().getId().toString();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("taskId", taskId);
		jsonObject.put("iId", iId);
		jsonObject.put("userId", user_id);
		jsonObject.put("assignee", userId);
	      //通过审核
	        try {
	        	String params=Base64Utils.encodeToString(JSON.toJSONString(jsonObject).toString().getBytes("UTF-8"));
	        	String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+flowabled_apply+params, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	
	/**
     * 驳回
     *
     * @param taskId 任务ID
     */
	@Override
    public void flowabled_reject(String taskId , String iId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("taskId", taskId);
		jsonObject.put("iId", iId);
	        try {
	        	String params=Base64Utils.encodeToString(JSON.toJSONString(jsonObject).toString().getBytes("UTF-8"));
				String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+flowabled_reject+params, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    }
	
	public Integer iGender(String name) {
		Integer data = 0 ;
		if(name.equals("男") ) {
			data = 1;
		}else{
			data = 2;
		}
		return data ;
	}
	
	public Integer iIdentity(String name) {
		Integer data = 0 ;
		if(name.equals("业主本人")) {
			data = 1;
		}else if(name.equals("业主家人")){
			data = 2;
		}else if(name.equals("业主亲属")) {
			data = 3;
		}else {
			data = 4 ;
		}
		return data ;
	}
	
	public Integer isHOwner(String name) {
		Integer data = 0 ;
		if(name.equals("是")) {
			data = 1;
		}else{
			data = 2;
		}
		return data ;
	}
	
	public Integer isRzzt(String data) {
		Integer text= 0;
		if(data.equals("未迁入")) {
			text=1;
		}else if(data.equals("已入住")) {
			text=2;
		}else if(data.equals("已迁出")) {
			text=3;
		}
		return text;
	}
	
	
	@Override
	public void impExcel(MultipartFile file , String nId , String rcode , String bId) {
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
				List<InhabitantInfo> list = new ArrayList<InhabitantInfo>();
				String rId = "" ;
				int[] data = new int[100];
				for(int i = 0 ; i <data.length;i++) {
					data[i] = i;
				}
				try {
					//读取excel文件
					arr = test.xlsx_reader(in,data);//后面的参数代表读取第几行               
					ArrayList<String> row1=arr.get(0);
					String text = row1.get(99);
					//循环输出读取数据储存进实体类
					for(int i=1;i<arr.size();i++){
						InhabitantInfo room = new InhabitantInfo();
						ArrayList<String> row=arr.get(i);
						if (row.get(0)!="---") {
							
//						for(int j=0;j<row.size();j++){
//							//房屋
							RoomInfo building = new RoomInfo();
							building.setbId(Integer.parseInt(text));
							building.setrRoomnum(row.get(0));
							try {
					    		String params=Base64Utils.encodeToString(JSON.toJSONString(building).toString().getBytes("UTF-8"));
					    		String  rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+inhabitantInfo_findRoomInfoExcel+params , null);
					    		JSONArray jsonArray=JSON.parseObject(rlt).getJSONArray("items");
					    			JSONObject jsonObject  = (JSONObject) jsonArray.get(0);
//					    		JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
					    		rId= jsonObject.get("rId").toString();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							room.setrId(Integer.parseInt(rId));
							room.setiName(row.get(1));
							room.setiGender(iGender(row.get(2)));
							room.setiIdcardno(row.get(3));
							room.setiPhoneno(row.get(4));
							room.setiIdentity(iIdentity(row.get(5)));
							room.setIsHOwner(isHOwner(row.get(6)));
							room.setoStatus(isRzzt(row.get(7)));
							room.setiInterest(row.get(8));
							room.setiProfession(row.get(9));
							room.setiCompany(row.get(10));
							room.setiPet(row.get(11));
//						}	
				
						list.add(room);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  //后面的参数代表需要输出哪些列，参数个数可以任意
		for(int i=0;i<list.size();i++) {
			InhabitantInfo roomText = new InhabitantInfo();
			roomText = list.get(i);
			lotAdd(roomText);
		}
	}
	
	@Override
	public void lotAdd(InhabitantInfo roomInfo) {
 		ShiroExt shiro = new ShiroExt();
		roomInfo.setCreateMan(shiro.getUser().getName());
		roomInfo.setCreateManId(shiro.getUser().getId());
		try {
//    		String params=Base64Utils.encodeToString(JSON.toJSONString(roomInfo).toString().getBytes("UTF-8"));
//			HttpUtils.doPost(bmw_cloud_baseservice_url+inhabitantInfo_add+params, null);
			HttpUtils.doPost(bmw_cloud_baseservice_url+inhabitantInfo_add, HttpUtils.convertParam(roomInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 功能：住户根据ID查看
	 * 开发者：金明禹
	 * */
	
	@Override
	public JSONArray findByAllInha(Integer inhabitantInfoId) {
		JSONArray jsonArray=new JSONArray();
		JSONObject json=new JSONObject();
        json.put("inhabitantInfoId", inhabitantInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+inhabitantInfo_findByAllInha+params, null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}


	
	
	
	

}