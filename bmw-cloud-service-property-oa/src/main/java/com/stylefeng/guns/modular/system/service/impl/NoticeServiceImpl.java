package com.stylefeng.guns.modular.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.modular.system.model.Notice;
import com.stylefeng.guns.modular.system.service.INoticeService;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${notice.list}")
	private String notice_list="";
	
	@Value("${notice.deltial}")
	private String notice_deltial="";

	@Value("${notice.add}")
	private String notice_add="";
	
	@Value("${notice.update}")
	private String notice_update="";
	
	@Value("${notice.del}")
	private String notice_del="";

    @Override
    public JSONArray list(String condition) {
    	JSONArray rltArray=new JSONArray();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+notice_list+condition);
			rltArray=JSONObject.parseArray(JSONObject.parseObject(rlt).getString("items"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return rltArray;
    }
    
    
    @Override
    public Notice findById(Integer noticeId) {
    	Notice notice = new Notice();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+notice_deltial+noticeId);
			notice=JSONObject.toJavaObject(JSONObject.parseObject(new String(JSONObject.parseObject(rlt).getString("items").getBytes("UTF-8"))),Notice.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return notice;
    }
    
    
    @Override
    public void add(Notice notice) {
    	try {
    		String params=JSONObject.toJSONString(notice);
    		String jsonParams=new String(Base64Utils.encode(params.getBytes("UTF-8")));
			HttpUtils.doPost(bmw_cloud_baseservice_url+notice_add+jsonParams, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    
    @Override
    public void update(Notice notice) {
    	try {
    		String params=JSONObject.toJSONString(notice);
    		String jsonParams=new String(Base64Utils.encode(params.getBytes("UTF-8")));
			HttpUtils.doPost(bmw_cloud_baseservice_url+notice_update+jsonParams, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    @Override
    public void del(Integer noticeId) {
    	try {
    		HttpUtils.doDelete(bmw_cloud_baseservice_url+notice_del+noticeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}
