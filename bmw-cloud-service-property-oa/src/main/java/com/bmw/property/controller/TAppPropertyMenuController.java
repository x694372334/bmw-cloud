package com.bmw.property.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.TAppPropertyMenu;
import com.bmw.property.service.ITAppPropertyMenuService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * apppropertymenu控制器
 *
 * @author fengshuonan
 * @Date 2018-07-26 15:00:05
 */
@Controller
@RequestMapping("/tAppPropertyMenu")
public class TAppPropertyMenuController extends BaseController {

    private String PREFIX = "/property/apppropertymenu/";

    @Autowired
    private ITAppPropertyMenuService tAppPropertyMenuService;
    
    @Value("${servicesUploadPath}")
    private String servicesUploadPath;

    /**
     * 跳转到apppropertymenu首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tAppPropertyMenu.html";
    }

    /**
     * 跳转到添加apppropertymenu
     */
    @RequestMapping("/tAppPropertyMenu_add")
    public String tAppPropertyMenuAdd() {
        return PREFIX + "tAppPropertyMenu_add.html";
    }

    /**
     * 跳转到修改apppropertymenu
     */
    @RequestMapping("/tAppPropertyMenu_update/{tAppPropertyMenuId}")
    public String tAppPropertyMenuUpdate(@PathVariable Integer tAppPropertyMenuId, Model model) {
        TAppPropertyMenu tAppPropertyMenu = tAppPropertyMenuService.getdetail(tAppPropertyMenuId);
        model.addAttribute("item",tAppPropertyMenu);
        LogObjectHolder.me().set(tAppPropertyMenu);
        return PREFIX + "tAppPropertyMenu_edit.html";
    }

    /**
     * 获取apppropertymenu列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(TAppPropertyMenu condition) {
        return tAppPropertyMenuService.findList(condition);
    }

    /**
     * 新增apppropertymenu
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TAppPropertyMenu tAppPropertyMenu) {
        tAppPropertyMenuService.add(tAppPropertyMenu);
        return SUCCESS_TIP;
    }

    /**
     * 删除apppropertymenu
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tAppPropertyMenuId) {
        tAppPropertyMenuService.del(tAppPropertyMenuId);
        return SUCCESS_TIP;
    }

    /**
     * 修改apppropertymenu
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TAppPropertyMenu tAppPropertyMenu) {
        tAppPropertyMenuService.update(tAppPropertyMenu);
        return SUCCESS_TIP;
    }

    /**
     * apppropertymenu详情
     */
    @RequestMapping(value = "/detail/{tAppPropertyMenuId}")
    @ResponseBody
    public Object detail(@PathVariable("tAppPropertyMenuId") Integer tAppPropertyMenuId) {
        return tAppPropertyMenuService.getdetail(tAppPropertyMenuId);
    }
    
    /**
     * 上传文档
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String detail(@RequestParam("file") MultipartFile file) {
    	String path = "";
    	String uuid = "";
		try {
			   //使用输入流读取前台的file文件              
	        InputStream is=file.getInputStream();  

	        //循环读取输入流文件内容，通过输出流将内容写入新文件
	        OutputStream os;
	        //防止上传文件重名 添加外层文件夹
	        uuid = UUID.randomUUID().toString().replaceAll("-", "");
	        path = servicesUploadPath+uuid+"//"+file.getOriginalFilename();
	        File file2 = new File(path);
	        File fileParent = file2.getParentFile();
	        if(!fileParent.exists()){
	        	fileParent.mkdirs();
	        }
			os = new FileOutputStream(path);
			 byte buffer[]=new byte[1024];  
		        int cnt=0;  
		        while((cnt=is.read(buffer))>0){  
		            os.write(buffer, 0, cnt);  
		        }  
		        //关闭输入输出流
		        os.close();
		        is.close();  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return uuid+"//"+file.getOriginalFilename();
    }
    
    /**
     * 下载文档
     * @throws IOException 
     */
    @RequestMapping(value = "/download")
    public void download(@RequestParam String menuIcon,HttpServletResponse response) throws IOException {
    	/*filePath = filePath.substring(0,filePath.lastIndexOf('\\') + 1);
    	filePath = filePath+fileName;*/
    	menuIcon = servicesUploadPath + menuIcon;
    	 File tempFile =new File( menuIcon.trim());  
         String fileName = tempFile.getName();  
    	InputStream in = new FileInputStream(menuIcon);
    	response.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes(),"iso-8859-1"));
		response.setContentType("application/msexcel");
		OutputStream out=response.getOutputStream();
    	    byte[] b = new byte[1024];
    	    int len = 0;
    	    while((len = in.read(b))!=-1){
    	      out.write(b, 0, len);
    	    }
    	    out.flush();
    	    out.close();
    	    in.close();
    }
    
}
