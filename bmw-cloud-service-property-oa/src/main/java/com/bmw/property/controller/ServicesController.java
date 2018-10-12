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
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.RepairsForm;
import com.bmw.property.model.Services;
import com.bmw.property.model.WDocuments;
import com.bmw.property.service.IServicesService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * services控制器
 *
 * @author fengshuonan
 * @Date 2018-07-17 10:34:48
 */
@Controller
@RequestMapping("/services")
public class ServicesController extends BaseController {

	private String PREFIX = "/property/services/";

	@Value("${servicesUploadPath}")
	private String servicesUploadPath;

	@Autowired
	private IServicesService servicesService;

	@Value("${bmw.cloud.fileservice.url}")
	public String fileServerUrl;

	/**
	 * 跳转到services首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "services.html";
	}

	/**
	 * 跳转到添加services
	 */
	@RequestMapping("/services_add")
	public String servicesAdd() {
		return PREFIX + "services_add.html";
	}

	/**
	 * 跳转到修改services
	 */
	@RequestMapping("/services_update/{servicesId}")
	public String servicesUpdate(@PathVariable Integer servicesId, Model model) {
		Services services = servicesService.getdetail(servicesId);
		model.addAttribute("item", services);
		model.addAttribute("url", fileServerUrl + services.getCover());
		LogObjectHolder.me().set(services);
		return PREFIX + "services_edit.html";
	}

	/**
	 * 跳转到查看services
	 */
	@RequestMapping("/services_view/{servicesId}")
	public String servicesView(@PathVariable Integer servicesId, Model model) {
		Services services = servicesService.getdetail(servicesId);
		model.addAttribute("url", fileServerUrl + services.getCover());
		model.addAttribute("item", services);
		LogObjectHolder.me().set(services);
		return PREFIX + "services_view.html";
	}

	/**
	 * 获取services列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(Services condition) {
		ShiroExt shiro = new ShiroExt();
		condition.seteId(shiro.getUser().geteId());
		return servicesService.findList(condition);
	}

	/*  *//**
			 * 新增services
			 *//*
				 * @RequestMapping(value = "/add")
				 * 
				 * @ResponseBody public Object add(Services services) {
				 * servicesService.add(services); return SUCCESS_TIP; }
				 */

	/**
     * 新增services
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Services services,HttpServletRequest request) {
        try {
        	if(((MultipartHttpServletRequest) request).getFiles("file").size()>0) {
        		MultipartFile file = ((MultipartHttpServletRequest) request).getFiles("file").get(0);
        		String url = uploadFile(file);
        		services.setCover(url);
        	}
        	ShiroExt shiro = new ShiroExt();
        	services.seteId(shiro.getUser().geteId());
			servicesService.add(services);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return SUCCESS_TIP;
    }

	/**
	 * 删除services
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer servicesId) {
		servicesService.del(servicesId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改services
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Services services) {
		servicesService.update(services);
		return SUCCESS_TIP;
	}

	/**
	 * services详情
	 */
	@RequestMapping(value = "/detail/{servicesId}")
	@ResponseBody
	public Object detail(@PathVariable("servicesId") Integer servicesId) {
		return servicesService.getdetail(servicesId);
	}

	/**
	 * 获取服务类别树
	 */
	@RequestMapping(value = "/getServiceSortTree")
	@ResponseBody
	public List<ZTreeNode> selectPositionTreeList() {
		List<ZTreeNode> serviceSortTree = this.servicesService.getServiceSortTree();
		serviceSortTree.add(ZTreeNode.createParent());
		return serviceSortTree;
	}

	/**
	 * 上传文档
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public String detail(@RequestParam("file") MultipartFile file) {
		String path = "";
		try {
			// 使用输入流读取前台的file文件
			InputStream is = file.getInputStream();

			// 循环读取输入流文件内容，通过输出流将内容写入新文件
			OutputStream os;
			// 防止上传文件重名 添加外层文件夹
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			path = servicesUploadPath + uuid + "//" + file.getOriginalFilename();
			File file2 = new File(path);
			File fileParent = file2.getParentFile();
			if (!fileParent.exists()) {
				fileParent.mkdirs();
			}
			os = new FileOutputStream(path);
			byte buffer[] = new byte[1024];
			int cnt = 0;
			while ((cnt = is.read(buffer)) > 0) {
				os.write(buffer, 0, cnt);
			}
			// 关闭输入输出流
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 下载文档
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/download")
	public void download(@RequestParam String cover, HttpServletResponse response) throws IOException {
		/*
		 * filePath = filePath.substring(0,filePath.lastIndexOf('\\') + 1); filePath =
		 * filePath+fileName;
		 */
		File tempFile = new File(cover.trim());
		String fileName = tempFile.getName();
		InputStream in = new FileInputStream(cover);
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
			url = HttpUtils.flieUploadFormal(fileServerUrl, "/specialservices", f);
			f.deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return url;
	}
}
