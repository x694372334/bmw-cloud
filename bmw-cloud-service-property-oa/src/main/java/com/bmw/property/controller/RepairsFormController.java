package com.bmw.property.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.bmw.property.model.RepairsForm;
import com.bmw.property.model.RepairsFormSub;
import com.bmw.property.service.IRepairsFormService;

/**
 * 报事报修控制器
 *
 * @author 张珵珺
 * @Date 2018-07-03 10:38:23
 */
@Controller
@RequestMapping("/repairsForm")
public class RepairsFormController extends BaseController {
	private static final Integer IS_DELETE_YES=0;
	private static final Integer IS_DELETE_NO=1;
	private static final Integer STATUS_YFD=1;
	@Value("${bmw.cloud.fileservice.url}")
	public String fileServerUrl;
    private String PREFIX = "/property/repairsForm/";

    @Autowired
    private IRepairsFormService repairsFormService;
    @Autowired
    private IUserService userService;
    
    
    
    /**
     * 测试轮播图片
     */
    @RequestMapping("/repairsForm_loadImages/{repairsFormId}")
    public String repairsFormLoadImages(@PathVariable Integer repairsFormId, Model model) {
    	RepairsForm form=repairsFormService.selectById(repairsFormId);
    	model.addAttribute("images",form.getContentImgList());
        return PREFIX + "repairsForm_images.html";
    }
    /**
     * 跳转到报事报修首页
     */
    @RequestMapping("/doing_index")
    public String doingindex(Model model) {
    	JSONArray userList = userService.selectUsers(null, null, null, null,null);
		model.addAttribute("userList", userList);
		JSONArray statusList=ConstantFactory.me().findDictByCode("bsbxzt");
		model.addAttribute("statusList", statusList);
        return PREFIX + "repairsForm_doing.html";
    }
    /**
     * 跳转到报事报修首页
     */
    @RequestMapping("/done_index")
    public String doneindex(Model model) {
    	JSONArray userList = userService.selectUsers(null, null, null, null,null);
		model.addAttribute("userList", userList);
		JSONArray statusList=ConstantFactory.me().findDictByCode("bsbxzt");
		model.addAttribute("statusList", statusList);
        return PREFIX + "repairsForm_done.html";
    }

    /**
     * 跳转到添加报事报修
     */
    @RequestMapping("/repairsForm_add")
    public String repairsFormAdd() {
        return PREFIX + "repairsForm_add.html";
    //	return PREFIX + "repairsForm_test.html";
    }
    /**
     * 跳转到添加报事报修
     */
    @RequestMapping("/repairsForm_detail/{repairsFormId}")
    public String repairsFormDetail(@PathVariable Integer repairsFormId, Model model) {
    	RepairsForm form=repairsFormService.selectById(repairsFormId);
    	model.addAttribute("form",form);
        return PREFIX + "repairsForm_detail.html";
    }
    
    /**
     * 跳转到添加报事报修
     */
    @RequestMapping("/repairsForm_openUpdate/{repairsFormId}")
    public String openUpdate(@PathVariable Integer repairsFormId, Model model) {
    	RepairsForm form=repairsFormService.detail4Update(repairsFormId);
    	int imgSize=form.getContentImgList()!=null?form.getContentImgList().size():0;
    	model.addAttribute("imgSize",imgSize);
    	model.addAttribute("form",form);
        return PREFIX + "repairsForm_update.html";
    }

    /**
     * 跳转接单
     */
    @RequestMapping("/repairsForm_receive/{repairsFormId}")
    public String repairsFormReceive(@PathVariable Integer repairsFormId, Model model) {
        model.addAttribute("id",repairsFormId);
        LogObjectHolder.me().set(repairsFormId);
        return PREFIX + "repairsForm_receive.html";
    }
    /**
     * 跳转驳回
     */
    @RequestMapping("/repairsForm_Refuse/{repairsFormId}")
    public String repairsFormRefuse(@PathVariable Integer repairsFormId, Model model) {
    	   model.addAttribute("id",repairsFormId);
           LogObjectHolder.me().set(repairsFormId);
        return PREFIX + "repairsForm_refuse.html";
    }
    /**
     * 跳转上门查看
     */
    @RequestMapping("/repairsForm_door2see/{repairsFormId}")
    public String repairsFormdoor2see(@PathVariable Integer repairsFormId, Model model) {
    	   model.addAttribute("id",repairsFormId);
           LogObjectHolder.me().set(repairsFormId);
        return PREFIX + "repairsForm_door2see.html";
    }
    /**
     * 跳转完成页面
     */
    @RequestMapping("/repairsForm_complete/{repairsFormId}")
    public String repairsFormComplete(@PathVariable Integer repairsFormId, Model model) {
    	   model.addAttribute("id",repairsFormId);
           LogObjectHolder.me().set(repairsFormId);
        return PREFIX + "repairsForm_complete.html";
    }
    /**
     * 获取报事报修列表-进行中
     */
    @RequestMapping(value = "/doing_list")
    @ResponseBody
    public Object doingList(@RequestParam(required=false) String ownerName,@RequestParam(required=false) Integer stewardUserId,
    								@RequestParam(required=false) Integer roomId,@RequestParam(required=false) Integer status ) {
    	Map<String,Object> param=new HashMap<String,Object>();
    	Page<RepairsForm> page= new PageFactory<RepairsForm>().defaultPage();
    	param.put("eId", ShiroKit.getUser().geteId());
    	param.put("pageNum", page.getCurrent());
    	param.put("pageSize",page.getSize());
    	param.put("ownerName",ownerName);
    	param.put("stewardUserId",stewardUserId);
    	param.put("roomId",roomId);
    	param.put("status",status);
    	JSONObject json=repairsFormService.selectDoingList(param);
    	if(json!=null) {
    		page.setTotal(json.getInteger("total"));        
            page.setRecords(json.getJSONArray("result").toJavaList(RepairsForm.class)); 
            return super.packForBT(page);
    	}else {
    		return null;
    	}		
    }
    /**
     * 获取报事报修列表-进行中
     */
    @RequestMapping(value = "/done_list")
    @ResponseBody
    public Object doneList(@RequestParam(required=false) String ownerName,@RequestParam(required=false) Integer stewardUserId,
    								@RequestParam(required=false) Integer roomId,@RequestParam(required=false) Integer status ) {
    	Map<String,Object> param=new HashMap<String,Object>();
    	Page<RepairsForm> page= new PageFactory<RepairsForm>().defaultPage();
    	param.put("eId", ShiroKit.getUser().geteId());
    	param.put("pageNum", page.getCurrent());
    	param.put("pageSize",page.getSize());
    	param.put("ownerName",ownerName);
    	param.put("stewardUserId",stewardUserId);
    	param.put("roomId",roomId);
    	param.put("status",status);
    	JSONObject json=repairsFormService.selectDoneList(param);
    	if(json!=null) {
    		page.setTotal(json.getInteger("total"));        
            page.setRecords(json.getJSONArray("result").toJavaList(RepairsForm.class)); 
            return super.packForBT(page);
    	}else {
    		return null;
    	}		
    }

    //多文件上传
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
          for (int i = 0; i < files.size(); ++i) {
        	file=files.get(i);
        	System.out.println(file.getOriginalFilename());
        }
        return "upload successful";
    }
    /**
     * 上传图片
     */
    @RequestMapping(value = "/imageUpload")
    @ResponseBody
    public JSONObject imageUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
    	JSONObject json=new JSONObject();
    	File f = null;
    	String url="";
    	try {
    		 String fileName[]=file.getOriginalFilename().split("\\.");
    		 f=File.createTempFile("temp-"+fileName[0],"."+fileName[1],null);//new File("D:\\")
    		 file.transferTo(f);  
			 url=HttpUtils.flieUploadFormal(fileServerUrl, "/bsbx", f);
			 f.deleteOnExit();   
		} catch (IOException e) {
			e.printStackTrace();
		}
    	if(url!=null) {
    		json.put("code",1);
    		json.put("msg", url);
    	}else {
    		json.put("code", 2);
    		json.put("msg","文件上传失败！");
    	}	
    	return json;
    }
    
    private String imageMultiImages(HttpServletRequest request) throws IOException {
    	 List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
         MultipartFile file = null;
         StringBuffer contentImg=new StringBuffer();
           for (int i = 0; i < files.size(); ++i) {
         	file=files.get(i);
         	File f = null;
        	String url="";
        	try {
        		 String fileName[]=file.getOriginalFilename().split("\\.");
        		 f=File.createTempFile("temp-"+fileName[0],"."+fileName[1],null);//new File("D:\\")
        		 file.transferTo(f);  
    			 url=HttpUtils.flieUploadFormal(fileServerUrl, "/bsbx", f);
    			 if(i==0) {
    				 contentImg.append(url);
    			 }else {
    				 contentImg.append("&").append(url);
    			 }
    			 f.deleteOnExit();   
    		} catch (IOException e) {
    			e.printStackTrace();
    			throw e;
    		}
         }
         return contentImg.toString();
    }
    /**
     * 校验房屋是否被认证过
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/isIdentified/{roomId}")
    @ResponseBody
    public Boolean isIdentified(@PathVariable Integer roomId) {
    	return this.repairsFormService.isIdentified(roomId);
    }
    
    /**
     * 新增报事报修
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(RepairsForm repairsForm,HttpServletRequest request) {
    	// 取得当前登录用户
    	ShiroUser shiroUser = ShiroKit.getUser();
		repairsForm.seteId(shiroUser.geteId());
		repairsForm.setOperatorUserId(shiroUser.getId());
		repairsForm.setOperatorUserName(shiroUser.getName());
		repairsForm.setCreateManId(shiroUser.getId());
		repairsForm.setCreateMan(shiroUser.getName());
		repairsForm.setCreateTime(new Date());
		repairsForm.setIsDelete(IS_DELETE_NO);
		repairsForm.setStatus(STATUS_YFD);
		String contentImg;
		try {
			contentImg = imageMultiImages(request);
			repairsForm.setContentImg(contentImg);
			boolean flag=repairsFormService.insert(repairsForm);
	        if(flag) {
	        	return SUCCESS_TIP;
	        }else {
	        	return new ErrorTip(HttpStatus.NOT_IMPLEMENTED.value(),"添加新的报事报修单失败");
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorTip(HttpStatus.NOT_IMPLEMENTED.value(),"添加新的报事报修单失败");
		}              
    }
    
    /**
     * 新增报事报修
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(RepairsForm repairsForm,HttpServletRequest request) {
    	// 取得当前登录用户
    	ShiroUser shiroUser = ShiroKit.getUser();
		repairsForm.setOperatorUserId(shiroUser.getId());
		repairsForm.setOperatorUserName(shiroUser.getName());
		repairsForm.setEditManId(shiroUser.getId());
		repairsForm.setEditMan(shiroUser.getName());
		repairsForm.setEditTime(new Date());
		String contentImg=null;
		try {
			contentImg = imageMultiImages(request);
			String[] oldImgUrls=repairsForm.getOldImgUrl();
			if(oldImgUrls!=null) {
				for(String url:oldImgUrls) {
					if(url!=null&&!url.equals("")) {
						url=url.replaceAll(repairsForm.getImgServerUrl(),"");
						contentImg+="&"+url;
					}
				}
			}
			repairsForm.setContentImg(contentImg);
			boolean flag=repairsFormService.updateById(repairsForm);
	        if(flag) {
	        	return SUCCESS_TIP;
	        }else {
	        	return new ErrorTip(HttpStatus.NOT_IMPLEMENTED.value(),"编辑新的报事报修单失败");
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return new ErrorTip(HttpStatus.NOT_IMPLEMENTED.value(),"编辑新的报事报修单失败");
		}              
    }
    @RequestMapping(value = "/refuse")
    @ResponseBody
    public Object refuse(@RequestParam(required=true) Map<String,Object> paramMap) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	RepairsForm repairesForm=new RepairsForm();
    	repairesForm.setId(Integer.valueOf(paramMap.get("id").toString()));
    	repairesForm.setRefuseReason(paramMap.get("refuseReason").toString());
    	repairesForm.setEditManId(shiroUser.getId());
    	repairesForm.setEditMan(shiroUser.getName());
    	repairesForm.setEditTime(new Date());
    	RepairsFormSub sub=new RepairsFormSub();
    	sub.setRepairsId(Integer.valueOf(paramMap.get("id").toString()));
    	sub.setCreateManId(shiroUser.getId());
    	sub.setCreateMan(shiroUser.getName());
    	sub.setCreateTime(new Date());
    	sub.setOperType(Short.valueOf("3"));
    	sub.setOperId(shiroUser.getId());
    	sub.setOperName(shiroUser.getName());
    	sub.setOperDesc(paramMap.get("refuseReason").toString());
    	sub.setIsDelete(IS_DELETE_NO);
    	Map<String,Object> sendMap=new HashMap<>();
    	sendMap.put("form", repairesForm);
    	sendMap.put("sub", sub);
    	repairsFormService.refuse(sendMap);
    	return SUCCESS_TIP;
    }
    public Date convert2date(String strDate) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	ParsePosition pos = new ParsePosition(0);
    	Date strtodate = formatter.parse(strDate, pos);
    	return strtodate;
    }
    @RequestMapping(value = "/receive")
    @ResponseBody
    public Object receive(@RequestParam(required=true) Map<String,Object> paramMap) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	RepairsForm repairesForm=new RepairsForm();
    	repairesForm.setId(Integer.valueOf(paramMap.get("id").toString()));
    	repairesForm.setProbablyCompleteTime(convert2date(paramMap.get("probablyCompleteTime").toString()));
    	repairesForm.setEditManId(shiroUser.getId());
    	repairesForm.setEditMan(shiroUser.getName());
    	repairesForm.setEditTime(new Date());
    	RepairsFormSub sub=new RepairsFormSub();
    	sub.setRepairsId(Integer.valueOf(paramMap.get("id").toString()));
    	sub.setCreateManId(shiroUser.getId());
    	sub.setCreateMan(shiroUser.getName());
    	sub.setCreateTime(new Date());
    	sub.setOperType(Short.valueOf("3"));
    	sub.setOperId(shiroUser.getId());
    	sub.setOperName(shiroUser.getName());
    	sub.setOperDesc(paramMap.get("desc").toString());
    	sub.setIsDelete(IS_DELETE_NO);
    	Map<String,Object> sendMap=new HashMap<>();
       	sendMap.put("form", repairesForm);
    	sendMap.put("sub", sub);
    	repairsFormService.receive(sendMap);
    	return SUCCESS_TIP;
    }
    @RequestMapping(value = "/door2see")
    @ResponseBody
    public Object door2see(@RequestParam(required=true) Map<String,Object> paramMap) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	RepairsForm repairesForm=new RepairsForm();
    	repairesForm.setId(Integer.valueOf(paramMap.get("id").toString()));
    	repairesForm.setProbablyArriveTime(convert2date(paramMap.get("probablyArriveTime").toString()));
    	repairesForm.setEditManId(shiroUser.getId());
    	repairesForm.setEditMan(shiroUser.getName());
    	repairesForm.setEditTime(new Date());
    	RepairsFormSub sub=new RepairsFormSub();
    	sub.setRepairsId(Integer.valueOf(paramMap.get("id").toString()));
    	sub.setCreateManId(shiroUser.getId());
    	sub.setCreateMan(shiroUser.getName());
    	sub.setCreateTime(new Date());
    	sub.setOperType(Short.valueOf("3"));
    	sub.setOperId(shiroUser.getId());
    	sub.setOperName(shiroUser.getName());
    	sub.setOperDesc(paramMap.get("desc").toString());
    	sub.setIsDelete(IS_DELETE_NO);
    	Map<String,Object> sendMap=new HashMap<>();
       	sendMap.put("form", repairesForm);
    	sendMap.put("sub", sub);
    	repairsFormService.door2see(sendMap);
    	return SUCCESS_TIP;
    }
    @RequestMapping(value = "/complete")
    @ResponseBody
    public Object complete(@RequestParam(required=true) Map<String,Object> paramMap) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	RepairsForm repairesForm=new RepairsForm();
    	repairesForm.setId(Integer.valueOf(paramMap.get("id").toString()));
    	repairesForm.setActualCompleteTime(convert2date(paramMap.get("actualCompleteTime").toString()));
    	repairesForm.setEditManId(shiroUser.getId());
    	repairesForm.setEditMan(shiroUser.getName());
    	repairesForm.setEditTime(new Date());
    	RepairsFormSub sub=new RepairsFormSub();
    	sub.setRepairsId(Integer.valueOf(paramMap.get("id").toString()));
    	sub.setCreateManId(shiroUser.getId());
    	sub.setCreateMan(shiroUser.getName());
    	sub.setCreateTime(new Date());
    	sub.setOperType(Short.valueOf("3"));
    	sub.setOperId(shiroUser.getId());
    	sub.setOperName(shiroUser.getName());
    	sub.setOperDesc(paramMap.get("desc").toString());
    	sub.setIsDelete(IS_DELETE_NO);
    	Map<String,Object> sendMap=new HashMap<>();
       	sendMap.put("form", repairesForm);
    	sendMap.put("sub", sub);
    	repairsFormService.complete(sendMap);
    	return SUCCESS_TIP;
    }
    
    @RequestMapping(value="/delete")
    @ResponseBody
    public Object delete(@RequestParam("repairsFormId") Integer repairsFormId) {
    	RepairsForm rf=new RepairsForm();
    	rf.setId(repairsFormId);
    	ShiroUser shiroUser = ShiroKit.getUser();
    	rf.setEditManId(shiroUser.getId());
    	rf.setEditMan(shiroUser.getName());
    	repairsFormService.delete(rf);
    	return SUCCESS_TIP;
    }

   /* *//**
     * 修改报事报修
     *//*
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(RepairsForm repairsForm) {
        repairsFormService.updateById(repairsForm);
        return SUCCESS_TIP;
    }
*/
    /**
     * 报事报修详情
     */
    @RequestMapping(value = "/detail/{repairsFormId}")
    @ResponseBody
    public Object detail(@PathVariable("repairsFormId") Integer repairsFormId) {
        return repairsFormService.selectById(repairsFormId);
    }
}
