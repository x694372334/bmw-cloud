package com.bmw.medical.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.common.utils.HttpUtils;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Area;
import com.bmw.medical.model.Hospital;
import com.bmw.medical.service.IAreaService;
import com.bmw.medical.service.IHospitalService;
import com.bmw.medical.warpper.HospitalWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-05-28 15:45:52
 */
@Controller
@RequestMapping("/hospital")
public class HospitalController extends BaseController {

	private String PREFIX = "/medical/hospital/";

	@Autowired
	private IHospitalService hospitalService;

	@Autowired
	private IAreaService areaService;

	@Value("${bmw.cloud.fileservice.url}")
	public String fileServerUrl;
	@Autowired
	private GunsProperties gunsProperties;

	/**
	 * 跳转到hospital首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "hospital.html";
	}

	/**
	 * 跳转到添加hospital
	 */
	@RequestMapping("/hospital_add")
	public String departmentAdd() {
		return PREFIX + "hospital_add.html";
	}

	/**
	 * 跳转到修改hospital
	 */
	@RequestMapping("/hospital_update/{hospitalId}")
	public String areaUpdate(@PathVariable Integer hospitalId, Model model) {
		Hospital hospital = hospitalService.getdetail(hospitalId);
		model.addAttribute("item", hospital);
		Area area = areaService.areaDeleteUpdate(hospital.getMedicalAreaCode());
		model.addAttribute("medicalAreaCode", area.getName());
		LogObjectHolder.me().set(hospital);
		return PREFIX + "hospital_edit.html";
	}

	/**
	 * 获取轮播图片管理列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String condition) {
		return super.warpObject(new HospitalWarpper(hospitalService.findList(condition)));
	}

	/**
	 * 新增轮播图片管理
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Hospital hospital) {
		String count = hospitalService.findCode();
		Integer num = Integer.parseInt(count);
		num = num + 1;
		String data = "";
		if (num >= 10) {
			data = "00";
		} else if (num >= 100) {
			data = "0";
		} else {
			data = "000";
		}
		hospital.setCode(data + num);

		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();

		hospital.setIsDelete(0);
		hospital.setCreateMan(userName);
		hospital.setCreateManId(userId);
		hospital.setCreateTime(new Date());
		hospital.setEditMan(userName);
		hospital.setEditManId(userId);
		hospital.setEditTime(new Date());
		hospitalService.add(hospital);
		return SUCCESS_TIP;
	}

	/**
	 * 删除轮播图片管理
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer hospitalId) {
		hospitalService.del(hospitalId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改轮播图片管理
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Hospital hospital) {
		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();
		hospital.setEditMan(userName);
		hospital.setEditManId(userId);
		hospital.setEditTime(new Date());
		hospitalService.update(hospital);
		return SUCCESS_TIP;
	}

	/**
	 * 轮播图片管理详情
	 */
	@RequestMapping(value = "/detail/{hospitalId}")
	@ResponseBody
	public Object detail(@PathVariable("hospitalId") Integer hospitalId) {
		return hospitalService.getdetail(hospitalId);
	}

	/**
	 * 获取区域列表(树级父级菜单用)
	 */
	@RequestMapping(value = "/areaTreeList")
	@ResponseBody
	public List<ZTreeNode> areaTreeList() {
		List<ZTreeNode> roleTreeList = this.hospitalService.areaTreeList();
		roleTreeList.add(ZTreeNode.createParent());
		return roleTreeList;
	}

	/**
	 * 获取区域列表(选择父级菜单用)
	 */
	@RequestMapping(value = "/selectAreaTreeList")
	@ResponseBody
	public List<ZTreeNode> selectAreaTreeList() {
		List<ZTreeNode> roleTreeList = this.hospitalService.areaTreeList();
		roleTreeList.add(ZTreeNode.createParent());
		return roleTreeList;
	}

	/**
	 * 上传图片(上传到统一的文件管理服务器)
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/upload")
	@ResponseBody
	public String upload(@RequestPart("file") MultipartFile picture) {
		String pictureName = UUID.randomUUID().toString() + ".jpg";
		try {
			File f = null;
			try {
				String fileName[] = picture.getOriginalFilename().split("\\.");
				f = File.createTempFile(fileName[0], "." + fileName[1], null);
				picture.transferTo(f);
				String res = HttpUtils.flieUploadFormal(fileServerUrl, "/yhtx", f);
				// 截取真正的文件名称
				String fname = res.substring(res.lastIndexOf("/") + 1, res.length());
				if (fname != null && !fname.equals("")) {
					pictureName = fname;
				}
				f.deleteOnExit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
		}
		return pictureName;
	}

	/**
	 * 返回头像
	 *
	 * @author liuwsh
	 * @Date 2017/5/24 23:00
	 */
	@RequestMapping("tx/{pictureId}")
	public void txPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
		try {
			String file_upload_path = gunsProperties.getFileUploadPath().replace("\\", "");
			String path = file_upload_path + "/upload/formal//yhtx/" + pictureId + ".jpg";
			String filename = RandomStringUtils.randomAlphanumeric(5);
			File file = HttpUtils.doGetFileImage(path, System.getProperty("user.dir"), filename + ".jpg");
			ServletOutputStream out = response.getOutputStream();
			InputStream in = new FileInputStream(file);
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
