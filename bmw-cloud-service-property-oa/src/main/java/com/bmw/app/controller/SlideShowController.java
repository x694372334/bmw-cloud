package com.bmw.app.controller;

import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.warpper.DictWarpper;

import org.springframework.web.bind.annotation.RequestParam;
import com.bmw.app.model.SlideShow;
import com.bmw.app.service.ISlideShowService;
import com.bmw.app.warpper.SlideShowWarpper;
import com.bmw.property.model.Services;

/**
 * 轮播图片管理控制器
 *
 * @author fengshuonan
 * @Date 2018-05-17 10:25:52
 */
@Controller
@RequestMapping("/slideShow")
public class SlideShowController extends BaseController {

    private String PREFIX = "/app/slideShow/";

    @Autowired
    private ISlideShowService slideShowService;

    @Value("${bmw.cloud.fileservice.url}")
	public String fileServerUrl;
    
    /**
     * 跳转到轮播图片管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "slideShow.html";
    }

    /**
     * 跳转到添加轮播图片管理
     */
    @RequestMapping("/slideShow_add")
    public String slideShowAdd() {
        return PREFIX + "slideShow_add.html";
    }

    /**
     * 跳转到修改轮播图片管理
     */
    @RequestMapping("/slideShow_update/{slideShowId}")
    public String slideShowUpdate(@PathVariable Integer slideShowId, Model model) {
        SlideShow slideShow = slideShowService.getdetail(slideShowId);
        model.addAttribute("item",slideShow);
        LogObjectHolder.me().set(slideShow);
        return PREFIX + "slideShow_edit.html";
    }

    /**
     * 获取轮播图片管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return super.warpObject(new SlideShowWarpper(slideShowService.findList(condition)));
    }

    /**
     * 新增轮播图片管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SlideShow slideShow,HttpServletRequest request) {
    	 try {
         	if(((MultipartHttpServletRequest) request).getFiles("file").size()>0) {
         		MultipartFile file = ((MultipartHttpServletRequest) request).getFiles("file").get(0);
         		String url = uploadFile(file);
         		slideShow.setUrl(url);
         	}
         	slideShowService.add(slideShow);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         return SUCCESS_TIP;
    }

    /**
     * 删除轮播图片管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer slideShowId) {
        slideShowService.del(slideShowId);
        return SUCCESS_TIP;
    }

    /**
     * 修改轮播图片管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SlideShow slideShow,HttpServletRequest request) {
    	try {
         	if(((MultipartHttpServletRequest) request).getFiles("file").size()>0) {
         		MultipartFile file = ((MultipartHttpServletRequest) request).getFiles("file").get(0);
         		String url = uploadFile(file);
         		slideShow.setUrl(url);
         	}
         	slideShowService.update(slideShow);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
        return SUCCESS_TIP;
    }

    /**
     * 轮播图片管理详情
     */
    @RequestMapping(value = "/detail/{slideShowId}")
    @ResponseBody
    public Object detail(@PathVariable("slideShowId") Integer slideShowId) {
        return slideShowService.getdetail(slideShowId);
    }
    
    /**
	 * 上传附件
	 */
	private String uploadFile(MultipartFile file) throws IOException {
		String url = "";
		File f = null;
		try {
			String fileName[] = file.getOriginalFilename().split("\\.");
			f = File.createTempFile("temp-" + fileName[0], "." + fileName[1], null);// new File("D:\\")
			file.transferTo(f);
			url = HttpUtils.flieUploadFormal(fileServerUrl, "/slideShow", f);
			f.deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return url;
	}
}
