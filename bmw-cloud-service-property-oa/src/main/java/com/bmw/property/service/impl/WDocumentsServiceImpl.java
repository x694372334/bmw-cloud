package com.bmw.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
//import com.common.utils.excel.DeleteFileUtil;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.bmw.property.model.WDocuments;
import com.bmw.property.service.IWDocumentsService;

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
 * 工作文档 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-19
 */
@Service
public class WDocumentsServiceImpl  implements IWDocumentsService {
	
	@Value("${wDocumentsUploadPath}")
    private String wDocumentsUploadPath;
	
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${wDocuments.wDocumentsList}")
	private String wDocuments_wDocumentsList="";

	@Value("${wDocuments.wDocumentsDetail}")
	private String wDocuments_wDocumentsDetail="";

	@Value("${wDocuments.add}")
	private String wDocuments_add="";

	@Value("${wDocuments.update}")
	private String wDocuments_update="";

	@Value("${wDocuments.del}")
	private String wDocuments_del="";

	@Value("${serviceSort.getTree}")
	private String serviceSort_getTree="";
	@Override
	public JSONArray findList(WDocuments condition) {
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+wDocuments_wDocumentsList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public WDocuments getdetail(Integer wDocumentsId) {
		WDocuments wDocuments=new WDocuments();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+wDocuments_wDocumentsDetail+wDocumentsId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				wDocuments=jsonObject.toJavaObject(WDocuments.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return wDocuments;
	}

	@Override
	public void add(WDocuments wDocuments) {
		ShiroExt shiro = new ShiroExt();
		wDocuments.setCreateMan(shiro.getUser().getName());
		wDocuments.setCreateManId(shiro.getUser().getId());
		wDocuments.setCreateTime(new Date());
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(wDocuments);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+wDocuments_add, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(WDocuments wDocuments) {
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(wDocuments);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+wDocuments_update, namevalue);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer wDocumentsId) {
		WDocuments wDocuments = getdetail(wDocumentsId);
		List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
		NameValuePair item = new BasicNameValuePair("param", wDocumentsId.toString());
		namevalue.add(item);
		try {
			String s = HttpUtils.doPost(bmw_cloud_baseservice_url+wDocuments_del,namevalue);
			if(s.indexOf("successful")!=-1) {
				String path = wDocumentsUploadPath+wDocuments.getdUrl();
				/*//删除文件
				DeleteFileUtil.delete(path);*/
				//删除外包文件夹以及文件
				String dir = path.substring(0,path.lastIndexOf("//"));
//				DeleteFileUtil.deleteDirectory(dir);
			}
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
