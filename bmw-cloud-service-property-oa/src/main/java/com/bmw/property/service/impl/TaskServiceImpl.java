package com.bmw.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.bmw.property.model.Task;
import com.bmw.property.model.TaskVO;
import com.bmw.property.service.ITaskService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * 工作任务 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-18
 */
@Service
public class TaskServiceImpl implements ITaskService {
	

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${task.taskList}")
	private String task_taskList="";

	@Value("${task.taskDetail}")
	private String task_taskDetail="";

	@Value("${task.add}")
	private String task_add="";

	@Value("${task.update}")
	private String task_update="";

	@Value("${task.del}")
	private String task_del="";

	@Value("${serviceSort.getTree}")
	private String serviceSort_getTree="";

	@Override
	public JSONArray findList(TaskVO condition) {
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+task_taskList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Task getdetail(Integer taskId) {
		Task task=new Task();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+task_taskDetail+taskId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				task=jsonObject.toJavaObject(Task.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return task;
	}

	@Override
	public void add(Task task) {
		ShiroExt shiro = new ShiroExt();
		task.setCreateMan(shiro.getUser().getName());
		task.setCreateManId(shiro.getUser().getId());
		task.setCreateTime(new Date());;
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(task);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+task_add, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Task task) {
		try {
			if(task.gettStatus()==3) {
				task.settCTime(new Date());
			}
    		JSONObject json = (JSONObject) JSONObject.toJSON(task);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			HttpUtils.doPost(bmw_cloud_baseservice_url+task_update, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer taskId) {
		List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
		NameValuePair item = new BasicNameValuePair("param", taskId.toString());
		namevalue.add(item);
		try {
			String s = HttpUtils.doPost(bmw_cloud_baseservice_url+task_del,namevalue);
			System.out.println(s);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
