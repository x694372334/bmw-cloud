
package com.bmw.property.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.BMWApplication;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.Annex;
import com.bmw.property.model.WDocuments;
import com.bmw.property.service.IWDocumentsService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * wdocuments控制器
 *
 * @author fengshuonan
 * @Date 2018-07-19 09:03:44
 */
@Controller
@RequestMapping("/wDocuments")
public class WDocumentsController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(WDocumentsController.class);
    
    private String PREFIX = "/property/wdocuments/";
    
    @Value("${bmw.cloud.fileservice.url}")
    private String wDocumentsUploadPath;

    @Autowired
    private IWDocumentsService wDocumentsService;

    /**
     * 跳转到wdocuments首页
     */
    @RequestMapping("")
    public String index() {
    	logger.info("进入办公文档。");
        return PREFIX + "wDocuments.html";
    }

    /**
     * 跳转到添加wdocuments
     */
    @RequestMapping("/wDocuments_add")
    public String wDocumentsAdd() {
        return PREFIX + "wDocuments_add.html";
    }
    
    /**
     * 跳转到添加wdocuments
     */
    @RequestMapping("/wDocuments_upload")
    public String wDocumentsUpload() {
        return PREFIX + "wDocuments_upload.html";
    }

    /**
     * 跳转到修改wdocuments
     */
    @RequestMapping("/wDocuments_update/{wDocumentsId}")
    public String wDocumentsUpdate(@PathVariable Integer wDocumentsId, Model model) {
        WDocuments wDocuments = wDocumentsService.getdetail(wDocumentsId);
        model.addAttribute("item",wDocuments);
        LogObjectHolder.me().set(wDocuments);
        return PREFIX + "wDocuments_edit.html";
    }

    /**
     * 获取wdocuments列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(WDocuments condition) {
    	  ShiroExt shiro = new ShiroExt();
    	  condition.setUpUser(shiro.getUser().getId());
		  condition.setCreateManId(shiro.getUser().getId());
		  condition.seteId(shiro.getUser().geteId());
		  condition.setParentEId(shiro.getUser().getParentEId());
        return wDocumentsService.findList(condition);
    }

    /**
     * 新增wdocuments
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WDocuments wDocuments) {
        wDocumentsService.add(wDocuments);
        return SUCCESS_TIP;
    }

    /**
     * 删除wdocuments
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer wDocumentsId) {
        wDocumentsService.del(wDocumentsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改wdocuments
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WDocuments wDocuments) {
        wDocumentsService.update(wDocuments);
        return SUCCESS_TIP;
    }

    /**
     * wdocuments详情
     */
    @RequestMapping(value = "/detail/{wDocumentsId}")
    @ResponseBody
    public Object detail(@PathVariable("wDocumentsId") Integer wDocumentsId) {
        return wDocumentsService.getdetail(wDocumentsId);
    }
    
    
    /**
     * 上传文档
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String detail(@RequestParam("file") MultipartFile file,@RequestParam("title") String title,
    		             @RequestParam("sharingLevel") String sharingLevel,@RequestParam("dType") Integer dType) {
		try {
			   //使用输入流读取前台的file文件              
	    	File f = null;
	    	String url="";
	    	try {
	    		 String fileName[]=file.getOriginalFilename().split("\\.");
	    		 f=File.createTempFile("temp-"+fileName[0],"."+fileName[1],null);//new File("D:\\")
	    		 file.transferTo(f);  
				 url=HttpUtils.flieUploadFormal(wDocumentsUploadPath, "/bgwd", f);
				 f.deleteOnExit();   
			} catch (IOException e) {
				e.printStackTrace();
			}
		        String filename= file.getOriginalFilename();
		        WDocuments wDocuments = new WDocuments();
		        wDocuments.setdName(filename);
		        wDocuments.setdUrl(url);
		        ShiroExt shiro = new ShiroExt();
		        wDocuments.setUpUser(shiro.getUser().getId());
		        wDocuments.setUpTime(new Date());
				wDocuments.setCreateMan(shiro.getUser().getName());
				wDocuments.setCreateManId(shiro.getUser().getId());
				wDocuments.setCreateTime(new Date());;
				wDocuments.seteId(shiro.getUser().geteId());
				wDocuments.setParentEId(shiro.getUser().getParentEId());
				wDocuments.setTitle(title);
				wDocuments.setSharingLevel(sharingLevel);
				wDocuments.setdType(dType);
				wDocumentsService.add(wDocuments);
		}catch (Exception e) {
			e.printStackTrace();
		}  
		return "";
    }
    
    /**
     * 下载文档
     * @throws IOException 
     */
	@RequestMapping(value = "/download")
	public void download(@RequestParam Integer wDocumentsId, HttpServletResponse response) throws IOException {
		WDocuments wDocuments = wDocumentsService.getdetail(wDocumentsId);
		String filePath = wDocumentsUploadPath + wDocuments.getdUrl();
		String fileName = wDocuments.getdName();

		URL url = null;
		url = new URL(filePath);
		DataInputStream in = new DataInputStream(url.openStream());
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(fileName.getBytes(), "iso-8859-1"));
		response.setContentType("application/msexcel");
		OutputStream out = response.getOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
		out.flush();
		out.close();
		in.close();
	}
}
