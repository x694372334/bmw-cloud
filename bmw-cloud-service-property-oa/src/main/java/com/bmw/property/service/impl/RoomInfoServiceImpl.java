package com.bmw.property.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.property.model.BuildingInfo;
import com.bmw.property.model.MBaseInfo;
import com.bmw.property.model.RoomInfo;
import com.bmw.property.service.IRoomInfoService;


@Service
public class RoomInfoServiceImpl implements IRoomInfoService {
	Logger log = LoggerFactory.getLogger(RoomInfoServiceImpl.class);
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${roomInfo.roomInfoList}")
	private String roomInfo_roomInfoList="";
	
	@Value("${roomInfo.selectData}")
	private String roomInfo_selectData="";
	
	@Value("${roomInfo.selectCheckedData}")
	private String roomInfo_selectCheckedData="";

	@Value("${roomInfo.roomInfoDetail}")
	private String roomInfo_roomInfoDetail="";

	@Value("${roomInfo.add}")
	private String roomInfo_add="";

	@Value("${roomInfo.update}")
	private String roomInfo_update="";

	@Value("${roomInfo.del}")
	private String roomInfo_del="";
	
	@Value("${roomInfo.findBuilding}")
	private String roomInfo_findBuilding="";
	
	@Value("${roomInfo.findBuildingData}")
	private String roomInfo_findBuildingData="";
	
	@Value("${roomInfo.findBuildingReader}")
	private String roomInfo_findBuildingReader="";

	@Value("${roomInfo.createNBTree}")
	private String roomInfo_createNBTree="";
	
	@Value("${roomInfo.findUser}")
	private String roomInfo_findUser="";
	
	@Value("${roomInfo.getNewCode}")
	private String roomInfo_getNewCode="";
	
	@Value("${roomInfo.queryNBTree}")
	private String roomInfo_queryNBTree="";
	
	@Value("${mBaseInfo.add}")
	private String mBaseInfo_add="";
	
	@Value("${roomInfo.batchButler}")
	private String roomInfo_batchButler="";






	@Override
	public JSONArray findList(String xqName ,String lyName , String fjName,String eId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("bName", lyName);
		json.put("nName", xqName);
		json.put("rRoomnum", fjName);
		ShiroExt shiro = new ShiroExt();
//		if(shiro.getUser().isAdmin) {
//			json.put("eId", 0);
//		}else {
//			json.put("eId", ShiroKit.getUser().geteId());
//		}
		json.put("eId", eId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+roomInfo_roomInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	

	@Override
	public Object selectData(Integer nbId,Integer id, Integer relevanceId, Integer costId) {
		JSONObject json=new JSONObject();
		json.put("nbId", nbId);
		json.put("batchId", id);
		json.put("relevanceId", relevanceId);
		json.put("costId", costId);
		List<Map<String,Object>> checkedMap = Lists.newArrayList();
		List<Map<String,Object>> uncheckedMap = Lists.newArrayList();
		
		Map<String,List<Map<String,Object>>> maps= Maps.newHashMap();
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+roomInfo_selectData+params);
			List<RoomInfo> unCheckList=JSONObject.parseObject(rlt).getJSONArray("items").toJavaList(RoomInfo.class);
			
			String rlt1=HttpUtils.doGet(bmw_cloud_baseservice_url+roomInfo_selectCheckedData+params);
			List<RoomInfo> checkedList=JSONObject.parseObject(rlt1).getJSONArray("items").toJavaList(RoomInfo.class);
			
			
			
			for(RoomInfo r : unCheckList) {
				Map<String,Object> map = Maps.newHashMap();
				map.put("rId", r.getrId() + "@"); //@判断是否是后台带过来的还是前台后选择
				map.put("rRoomnum", r.getrRoomnum() + "(" + r.getbName() + ")");
				uncheckedMap.add(map);
			}
			maps.put("uncheckedMap", uncheckedMap);
			
			for(RoomInfo r : checkedList) {
				Map<String,Object> map = Maps.newHashMap();
				map.put("rId", r.getrId() + "#");
				map.put("rRoomnum", r.getrRoomnum() + "(" + r.getbName() + ")");
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
	public RoomInfo getdetail(Integer roomInfoId) {
		RoomInfo roomInfo=new RoomInfo();
		JSONObject json=new JSONObject();
        json.put("roomInfoId", roomInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_roomInfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				roomInfo=jsonObject.toJavaObject(RoomInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return roomInfo;
	}

	@Override
	public void add(RoomInfo roomInfo) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", roomInfo.getrCode());
		map.put("test", "");
		map.put("number", "3");
		String data = getNewCode(map);	
		roomInfo.setrCode(data);
		ShiroExt shiro = new ShiroExt();
		roomInfo.setCreateMan(shiro.getUser().getName());
		roomInfo.setCreateManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(roomInfo).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_add+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
    		roomInfo.setrId(Integer.parseInt(jsonObject.get("rId").toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mBaseInfoAdd(roomInfo);

	}
	
	@Override
	public void lotAdd(RoomInfo roomInfo) {
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", roomInfo.getrCode());
		map.put("test", "");
		map.put("number", "3");
		String data = getNewCode(map);	
		roomInfo.setrCode(data);
		ShiroExt shiro = new ShiroExt();
		roomInfo.setCreateMan(shiro.getUser().getName());
		roomInfo.setCreateManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(roomInfo).toString().getBytes("UTF-8"));
    		String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_add+params, null);
    		JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
    		roomInfo.setrId(Integer.parseInt(jsonObject.get("rId").toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mBaseInfoAdd(roomInfo);

	}
	
	public void mBaseInfoAdd(RoomInfo roomInfo) {
		ShiroExt shiro = new ShiroExt();
		MBaseInfo mBase = new MBaseInfo();
		for(int i=0;i<2;i++) {
			mBase.setrId(roomInfo.getrId());
			mBase.setmType(i+1);
			mBase.setIsDelete(1);
			mBase.setCreateManId(shiro.getUser().getId());
			mBase.setCreateMan(shiro.getUser().getName());
			try {
	    		String params=Base64Utils.encodeToString(JSON.toJSONString(mBase).toString().getBytes("UTF-8"));
				HttpUtils.doPost(bmw_cloud_baseservice_url+mBaseInfo_add+params, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	@Override
	public void update(RoomInfo roomInfo) {
		if(null!=roomInfo.getrCode()) {
			Integer count = roomInfo.getrCode().length();
			if(count!=16) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("code", roomInfo.getrCode());
				map.put("test", "");
				map.put("number", "3");
				String data = getNewCode(map);	
				roomInfo.setrCode(data);
			}
		}
		ShiroExt shiro = new ShiroExt();
		roomInfo.setEditMan(shiro.getUser().getName());
		roomInfo.setEditManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_update, HttpUtils.convertParam(roomInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer roomInfoId) {
		JSONObject json=new JSONObject();
        json.put("roomInfoId", roomInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+roomInfo_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ZTreeNode> createNBTree(Integer eId, Integer level,Integer nbId) {
		Map<String,String> httpSetting=new HashMap<>();
		List<ZTreeNode> treeList=new ArrayList<>();
		Map<String,Integer> param=new HashMap<>();
		param.put("eId", eId);
		param.put("level", level);
		if(nbId.intValue()!=0) {
			param.put("nbId", nbId);	
		}
		httpSetting.put("contentType", "application/json");
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_createNBTree,JSONObject.toJSONString(param).toString(),null);
			treeList=JSONObject.parseObject(rlt).getJSONArray("items").toJavaList(ZTreeNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return treeList;
	}
	
	@Override
	public JSONArray findBuilding(Integer roomInfoId) {
		System.out.println(roomInfoId);
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("roomInfoId", roomInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_findBuilding+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
	
	@Override
	public JSONArray findBuildingData(Integer roomInfoId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("roomInfoId", roomInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_findBuildingData+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
	
	@Override
	public JSONArray findUser(Integer eId) {
		JSONArray jsonArray=null;
    	try {
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+roomInfo_findUser+eId);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
	
	
	@Override
	public String getNewCode(Map<String,String> map) {
		JSONObject json=new JSONObject();
        json.put("code", map.get("code"));
        json.put("test", map.get("test"));
        json.put("number", map.get("number"));
        String data = "" ;
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
    		String  rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_getNewCode+params , null);
    		JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
    		data= jsonObject.get("neighbor").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data ;
	}
	
	public RoomInfo saveRoom(RoomInfo room) {
		ShiroExt shiro = new ShiroExt();
		room.setCreateManId(shiro.getUser().getId());
		room.setCreateMan(shiro.getUser().getName());
		return room;
	}
	
	//判断房间类型等
	public String isType(String data) {
		String text="";
		if(data.equals("住宅")) {
			text = "1";
		}else if(data.equals("公寓")) {
			text = "2";
		}else if(data.equals("办公")) {
			text = "3";
		}else if(data.equals("商铺")) {
			text = "4";
		}else if(data.equals("厂房")) {
			text = "5";
		}else if(data.equals("仓库")) {
			text = "6";
		}else if(data.equals("酒店")) {
			text = "7";
		}else if(data.equals("别墅")) {
			text = "8";
		}else if(data.equals("其他")) {
			text = "9";
		}
		return text ; 
	}
	
	public String isRzzt(String data) {
		String text="";
		if(data.equals("未迁入")) {
			text="1";
		}else if(data.equals("已入住")) {
			text="2";
		}else if(data.equals("已迁出")) {
			text="3";
		}
		return text;
	}
	
	//入住状态判断
	
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
				ShiroExt shiro = new ShiroExt();
				List<RoomInfo> list = new ArrayList<RoomInfo>();
				String rCode = rcode;
				try {
					//读取excel文件
					arr = test.xlsx_reader(in,0,1,2,3,4,5,6,7,8,9,10);//后面的参数代表读取第几行
					//循环输出读取数据储存进实体类
					for(int i=1;i<arr.size();i++){
						RoomInfo room = new RoomInfo();
						ArrayList<String> row=arr.get(i);
						for(int j=0;j<row.size();j++){
							//楼宇
							BuildingInfo building = new BuildingInfo();
							building.setnId(Integer.parseInt(nId));
							building.setbName(row.get(0));
							try {
					    		String params=Base64Utils.encodeToString(JSON.toJSONString(building).toString().getBytes("UTF-8"));
					    		String  rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_findBuildingReader+params , null);
					    		JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
					    		bId= jsonObject.get("bId").toString();
					    		rCode= jsonObject.get("bCode").toString();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							room.setbId(Integer.parseInt(bId));
							/****************************************************/
							room.setnId(Integer.parseInt(nId)); // 小区保存
							room.setrCode(rCode);//房屋编码   后期需从前台发送过来
							room.setrUnitnum(row.get(1));//单元
							room.setrFloor(Integer.parseInt(row.get(2)));//楼层
							room.setrRoomnum(row.get(3));//房间号
 							room.setrRoomType(Integer.parseInt(isType(row.get(4))));//房间类型
							room.setLiveStatus(Integer.parseInt(isRzzt(row.get(5))));//入住状态                                                                                                                                     
							room.setrCoveredArea(Double.valueOf(row.get(6)));//建筑面积
							room.setrUsearea(Double.valueOf(row.get(7)));//套内面积
							room.setrPublicArea(Double.valueOf(row.get(8)));//公摊面积
							room.setrHeight(BigDecimal.valueOf(Double.valueOf(row.get(9))));
							room.setCreateManId(shiro.getUser().getId());//创建用户
							room.setCreateMan(shiro.getUser().getName());//创建用户Id
							room.setHouseType(row.get(10));
							
						}
						list.add(room);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  //后面的参数代表需要输出哪些列，参数个数可以任意
		for(int i=0;i<list.size();i++) {
			RoomInfo roomText = new RoomInfo();
			roomText = list.get(i);
			lotAdd(roomText);
		}
	}
	
	/**
     * 文件转base64字符串
     * @param file
     * @return
     */
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            base64= Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return base64;
    }
    
    @Override
	public JSONArray findBuilding(Integer nId , String name) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("nId", nId);
        json.put("bName", name);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_findBuilding+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
    
	/**
	 * 查询树
	 */
	@Override
	public List<ZTreeNode> queryNBTree(Integer eId) {
		String rlt="";
		JSONObject jsonParam=new JSONObject();
		jsonParam.put("eId", eId);
    	List<ZTreeNode> treeList=new ArrayList<>();
    	try {
	    	//根据企业ID查询小区树
			rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+roomInfo_queryNBTree+
					eId);
			if(rlt!=null) {
				JSONObject jsonrlt=JSONObject.parseObject(rlt).getJSONObject("items");
				treeList=jsonrlt.getJSONArray("treeList").toJavaList(ZTreeNode.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return treeList;
	}

	@Override
	public Tip BatchButler(Map<String, Object> paramMap) {
		Tip tip=null;
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+roomInfo_batchButler,
					JSONObject.toJSONString(paramMap),null);
			JSONObject jsonrlt = JSONObject.parseObject(rlt);
			if (jsonrlt.getIntValue("statusCode")==HttpStatus.CREATED.value()) {//返回码是201
				tip= new SuccessTip();
			} else {
				log.error("{}"+jsonrlt.getString("msg"));
					tip= new ErrorTip(HttpStatus.NOT_IMPLEMENTED.value(),jsonrlt.getString("msg")); 
			}
		} catch (ParseException | IOException e) {
			log.error("{}批量关联关键失败",e);
			tip= new ErrorTip(HttpStatus.NOT_IMPLEMENTED.value(),"后台异常，请联系管理员"); 
		}
		return tip;
	}
    
    

}