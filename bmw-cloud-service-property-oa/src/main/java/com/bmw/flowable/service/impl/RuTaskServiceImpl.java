package com.bmw.flowable.service.impl;

import com.bmw.flowable.model.TaskVO;
import com.bmw.flowable.service.IFlowableService;
import com.bmw.flowable.service.IRuTaskService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-23
 */
@Service
public class RuTaskServiceImpl implements IRuTaskService {
	
	    @Value("${bmw.cloud.propertyservice.url}")
		private String bmw_cloud_baseservice_url="";

		@Value("${ruTask.ruTaskList}")
		private String ruTask_ruTaskList="";
		
		@Value("${ruTask.printProcessImage}")
		private String ruTask_printProcessImage=""; 
		
		@Value("${ruTask.deletePro}")
		private String ruTask_deletePro="";
		
		@Override
		public JSONArray findList(String name) {
			ShiroExt shiro = new ShiroExt();
			String param = shiro.getUser().getId().toString();
			JSONArray jsonArray=null;
	    	try {
				String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+ruTask_ruTaskList+param+"/"+name);
				jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return jsonArray;
		}
		
		
		@Override
		public void printProcessImage(String taskId) { 
	    	try {  
				String url=bmw_cloud_baseservice_url+ruTask_printProcessImage+"/"+taskId;
				try {
					HttpUtils.doGetFileImage(url);
				} catch (ParseException | IOException e) { 
					e.printStackTrace();
				} 
			} catch (Exception e) { 
				e.printStackTrace();
			} 
		}


		@Override
		public void deletePro(String procInstId) {
			try {
				String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+ruTask_deletePro+"?procInstId="+procInstId);
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
