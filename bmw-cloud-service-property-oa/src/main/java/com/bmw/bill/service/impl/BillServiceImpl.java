package com.bmw.bill.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.bill.model.Bill;
import com.bmw.bill.model.BillDiscountsVO;
import com.bmw.bill.model.BillVO;
import com.bmw.bill.service.IBillService;
import com.bmw.flowable.service.IFlowableService;

/**
 * <p>
 * 账单明细表 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-06-22
 */
@Service
public class BillServiceImpl  implements IBillService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${bill.billList}")
	private String bill_billList="";

	@Value("${bill.billDetail}")
	private String bill_billDetail="";

	@Value("${bill.add}")
	private String bill_add="";

	@Value("${bill.update}")
	private String bill_update="";

	@Value("${bill.del}")
	private String bill_del="";
	
	@Value("${bill.billListByIds}")
	private String bill_billListByIds="";
	
	@Value("${bill.changeCommit}")
	private String bill_changeCommit="";
	
	@Value("${bill.discountsCommit}")
	private String bill_discountsCommit="";
	
	@Value("${bill.billListDiscount}")
	private String bill_billListDiscount="";
	
	@Value("${bill.discountsById}")
	private String bill_discountsById="";
	
	@Value("${bill.billListAll}")
	private String bill_billListAll="";
	
	@Value("${bill.upload}")
	private String bill_upload="";
	
	@Value("${bill.deleteById}")
	private String bill_deleteById="";
	
	@Value("${bill.updateStatus}")
	private String bill_updateStatus="";
	
	@Autowired
    private IFlowableService flowableService;
	
	@Override
	public JSONArray findList(Bill condition) {
		condition.seteId(ShiroKit.getUser().geteId());
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+bill_billList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	@Override
	public JSONArray findListAll(Bill condition) {
		condition.seteId(ShiroKit.getUser().geteId());
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+bill_billListAll+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	
	@Override
	public JSONArray billListDiscount(Bill condition) {
		condition.seteId(ShiroKit.getUser().geteId());
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+bill_billListDiscount+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	@Override
	public JSONArray billListByIds(String ids) {
		JSONArray jsonArray=null;
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+bill_billListByIds+ids);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	@Override
	public void changeCommit(String ids, String payWay, String remark) {
		
		JSONObject paramOjbect=new JSONObject();
		paramOjbect.put("ids", ids);
		paramOjbect.put("payWay", payWay);
		paramOjbect.put("remark", remark);
		try {
			HttpUtils.doGet(bmw_cloud_baseservice_url+bill_changeCommit+new String(Base64Utils.encode(paramOjbect.toString().getBytes("UTF-8"))));
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void discountsCommit(String billId, String reason, String discountsAmount, String latenessOffer) {
		try {
			ShiroExt shiro = new ShiroExt();
			String userId = shiro.getUser().getId().toString();
			JSONObject paramOjbect=new JSONObject();
			paramOjbect.put("billId", billId);
			paramOjbect.put("reason", reason);
			paramOjbect.put("discountsAmount", discountsAmount);
			paramOjbect.put("latenessOffer", latenessOffer);
			paramOjbect.put("userId", userId);
			
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+bill_discountsCommit+new String(Base64Utils.encode(paramOjbect.toString().getBytes("UTF-8"))));
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public BillVO getdetail(Integer billId) {
		BillVO bill=new BillVO();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+bill_billDetail+billId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				bill=jsonObject.toJavaObject(BillVO.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return bill;
	}
	
	@Override
	public BillDiscountsVO getBillDiscountsVO(Integer billId ,Integer discountsId) {
		BillDiscountsVO bill=new BillDiscountsVO();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+bill_discountsById+billId+"/"+discountsId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				bill=jsonObject.toJavaObject(BillDiscountsVO.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return bill;
	}

	@Override
	public Integer upload(String param,String neighborhoodsId) {
		Integer statusCode = 0;
    	try {
    	    param = new String(Base64Utils.encode(param.getBytes("UTF-8")));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", param);
			NameValuePair item2 = new BasicNameValuePair("neighborhoodsId", neighborhoodsId);
			namevalue.add(item);
			namevalue.add(item2);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+bill_upload, namevalue);
			//jsonArray=JSON.parseObject(result).getJSONArray("statusCode");
			statusCode=(Integer) JSON.parseObject(result).get("statusCode");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return statusCode;
	}
	
	@Override
	public void discountsPass(Integer billId, String taskId) {
		ShiroExt shiro = new ShiroExt();
		String userId = shiro.getUser().getId().toString();
		//flowableService.completeTask(taskId,userId, null);
		try {
			 HttpUtils.doGet(bmw_cloud_baseservice_url+bill_updateStatus+"?status=2&id="+billId+"&userId="+userId+"&taskId="+taskId);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void discountsEnd(Integer billId, String taskId) {
		//flowableService.deleteProcessInstance(taskId);
		try {
			ShiroExt shiro = new ShiroExt();
			String userId = shiro.getUser().getId().toString();
			HttpUtils.doGet(bmw_cloud_baseservice_url+bill_updateStatus+"?status=3&id="+billId+"&userId="+userId+"&taskId="+taskId);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void add(Bill complaintAdvice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Bill complaintAdvice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tip del(Integer billId) throws Exception {
		JSONObject json=new JSONObject();
        json.put("id", billId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
    		String rlt = HttpUtils.doDelete(bmw_cloud_baseservice_url+bill_deleteById+params,null);
    		json=JSONObject.parseObject(rlt);
    		
    		if(json.getInteger("statusCode") != 201) {
    			return new ErrorTip(1000,"系统异常，请联系管理员！");
    		}else {
    			return new SuccessTip(200,json.getString("items"));
    		}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}



}
