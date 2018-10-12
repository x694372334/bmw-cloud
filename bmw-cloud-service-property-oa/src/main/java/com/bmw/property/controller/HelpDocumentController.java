package com.bmw.property.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.ExcelUtils;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.bill.model.Bill;
import com.bmw.property.model.Annex;
import com.bmw.property.model.HelpDocument;
import com.bmw.property.service.IHelpDocumentService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * helpDocument控制器
 *
 * @author fengshuonan
 * @Date 2018-07-10 09:32:41
 */
@Controller
@RequestMapping("/helpDocument")
public class HelpDocumentController extends BaseController {

    private String PREFIX = "/property/helpDocument/";

    @Autowired
    private IHelpDocumentService helpDocumentService;
    
    @Value("${uploadPath}")
    private String uploadPath;
    
    @Value("${bmw.cloud.fileservice.url}")
	public String fileServerUrl;

    /**
     * 跳转到helpDocument首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "helpDocument.html";
    }

    /**
     * 跳转到添加helpDocument
     */
    @RequestMapping("/helpDocument_add")
    public String helpDocumentAdd() {
        return PREFIX + "helpDocument_add.html";
    }

    /**
     * 跳转到修改helpDocument
     */
    @RequestMapping("/helpDocument_update/{helpDocumentId}")
    public String helpDocumentUpdate(@PathVariable Integer helpDocumentId, Model model) {
        HelpDocument helpDocument = helpDocumentService.getdetail(helpDocumentId);
        model.addAttribute("item",helpDocument);
        LogObjectHolder.me().set(helpDocument);
        return PREFIX + "helpDocument_edit.html";
    }
    
    /**
     * 跳转到修改helpDocument
     */
    @RequestMapping("/helpDocument_view/{helpDocumentId}")
    public String helpDocumentView(@PathVariable Integer helpDocumentId, Model model) {
        HelpDocument helpDocument = helpDocumentService.getdetail(helpDocumentId);
        model.addAttribute("item",helpDocument);
        LogObjectHolder.me().set(helpDocument);
        return PREFIX + "helpDocument_view.html";
    }

    /**
     * 获取helpDocument列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HelpDocument condition) {
    	ShiroExt shiro = new ShiroExt();
    	condition.setParentEId(shiro.getUser().getParentEId());
        return helpDocumentService.findList(condition);
    }

    /**
     * 新增helpDocument
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HelpDocument helpDocument) {
    	String fileId = helpDocument.getFileId();
    	if(fileId!=null&&!"".equals(fileId)) {
    	//fileId = fileId.substring(0,fileId.length()-1);
    	helpDocument.setFileId(fileId);
    	}
    	ShiroExt shiro = new ShiroExt();
    	helpDocument.setParentEId(shiro.getUser().getParentEId());
        helpDocumentService.add(helpDocument);
        return SUCCESS_TIP;
    }

    /**
     * 删除helpDocument
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer helpDocumentId) {
        helpDocumentService.del(helpDocumentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改helpDocument
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HelpDocument helpDocument) {
        helpDocumentService.update(helpDocument);
        return SUCCESS_TIP;
    }

    /**
     * helpDocument详情
     */
    @RequestMapping(value = "/detail/{helpDocumentId}")
    @ResponseBody
    public Object detail(@PathVariable("helpDocumentId") Integer helpDocumentId) {
        return helpDocumentService.getdetail(helpDocumentId);
    }
    
    /**
     * 上传附件
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String detail(@RequestParam("file") MultipartFile file) {
    	int id = (int)((Math.random()*9+1)*1000000);
		try {
			 //使用输入流读取前台的file文件              
		    Annex annex = new Annex();
		    String url = uploadFile(file);
	        String filename= file.getOriginalFilename();
	        annex.setId(id);
	        annex.setFileName(filename);
	        annex.setFilePath(url);
	        //String[] items = filename.split(".");
	        //annex.setFileFormat(items[items.length-1]);
	        helpDocumentService.addAnnex(annex);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return id+"";
       // return helpDocumentService.getdetail(helpDocumentId);
    }
    
    /**
     * 获取附件集合
     */
    @RequestMapping(value = "/findFileById/{helpDocumentId}")
    @ResponseBody
    public Object findFileById(@PathVariable("helpDocumentId") Integer helpDocumentId) {
        return helpDocumentService.findFileById(helpDocumentId);
    }
    
    
    /**
     *上传附件
     */
    private String uploadFile(MultipartFile file) throws IOException {
    	String url = "";
    	File f = null;
       	try {
       		 String fileName[]=file.getOriginalFilename().split("\\.");
       		 f=File.createTempFile("temp-"+fileName[0],"."+fileName[1],null);//new File("D:\\")
       		 file.transferTo(f);  
   			 url=HttpUtils.flieUploadFormal(fileServerUrl, "/helpdocument", f);
   			 f.deleteOnExit();   
   		} catch (IOException e) {
   			e.printStackTrace();
   			throw e;
   		}
        return url;
   }
    
}
