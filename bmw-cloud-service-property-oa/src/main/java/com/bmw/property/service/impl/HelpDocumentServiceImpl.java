package com.bmw.property.service.impl;

import com.bmw.property.model.Annex;
import com.bmw.property.model.Announcement;
import com.bmw.property.model.HelpDocument;
import com.bmw.property.service.IHelpDocumentService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.HttpUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * 帮助文档 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-10
 */
@Service
public class HelpDocumentServiceImpl  implements IHelpDocumentService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${helpDocument.helpDocumentList}")
	private String helpDocument_helpDocumentList="";

	@Value("${helpDocument.add}")
	private String helpDocument_add="";
	
	@Value("${annex.add}")
	private String annex_add="";
	
	@Value("${helpDocument.helpDocumentDetail}")
	private String helpDocument_helpDocumentDetail="";
	
	@Value("${helpDocument.findFileById}")
	private String helpDocument_findFileById="";
	
	@Value("${helpDocument.del}")
	private String helpDocument_del="";
	
	@Value("${helpDocument.update}")
	private String  helpDocument_update="";
	
	@Value("${bmw.cloud.fileservice.url}")
	public String fileServerUrl;
	
	@Override
	public JSONArray findList(HelpDocument condition) {
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+helpDocument_helpDocumentList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public HelpDocument getdetail(Integer helpDocumentId) {
		HelpDocument helpDocument=new HelpDocument();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+helpDocument_helpDocumentDetail+helpDocumentId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				helpDocument=jsonObject.toJavaObject(HelpDocument.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return helpDocument;
	}

	@Override
	public void add(HelpDocument helpDocument) {
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(helpDocument);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			HttpUtils.doPost(bmw_cloud_baseservice_url+helpDocument_add, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(HelpDocument helpDocument) {
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(helpDocument);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+helpDocument_update, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer helpDocumentId) {
		List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
		NameValuePair item = new BasicNameValuePair("param", helpDocumentId.toString());
		namevalue.add(item);
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+helpDocument_del,namevalue);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addAnnex(Annex annex) {
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(annex);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			HttpUtils.doPost(bmw_cloud_baseservice_url+annex_add, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据ID查询附件信息
	 */
	@Override
	public  JSONArray findFileById(Integer helpDocumentId) {
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+helpDocument_findFileById+helpDocumentId);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			List<Annex> list = new ArrayList<>();
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject ob  = (JSONObject) jsonArray.get(i);//得到json对象
				Annex annex=JSONObject.toJavaObject(ob, Annex.class);
				annex.setFilePath(annex.getFilePath());
				list.add(annex);
			}

			String str = JSONArray.toJSONString(list);
			jsonArray = (JSONArray) JSONArray.parse(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
}
