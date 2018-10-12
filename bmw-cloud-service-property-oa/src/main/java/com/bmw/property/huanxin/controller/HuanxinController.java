package com.bmw.property.huanxin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.bmw.property.huanxin.model.HuanxinUser;
import com.bmw.property.huanxin.service.HuanxinService;
import com.bmw.property.model.Account;
import com.bmw.property.model.BuildingInfo;
import com.bmw.property.service.IAccountService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * account控制器
 *
 * @author fengshuonan
 * @Date 2018-07-09 15:19:43
 */
@Controller
@RequestMapping("/huanxin")
public class HuanxinController extends BaseController {

    private String PREFIX = "/huanxin/";

    @Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url="";
	
	@Value("${huanxin.huanxinUserByUserId}")
	private String huanxin_huanxinUserByUserId="";
	
    /**
     * 跳转到account首页
     */
    @RequestMapping("")
    public String index(Model model) {
    	HuanxinUser huanxinUser= new HuanxinUser();
    	try {
    		ShiroExt shiro = new ShiroExt();
			String userId = shiro.getUser().getId().toString();
			String rlt = HttpUtils.doGet(bmw_cloud_propertyservice_url+huanxin_huanxinUserByUserId+
					"?userId="+userId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				huanxinUser=jsonObject.toJavaObject(HuanxinUser.class);
			}else {
				return PREFIX + "out.html";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
    	 model.addAttribute("username",huanxinUser.getUserName());
    	 model.addAttribute("pwd",huanxinUser.getPassword());
    	 model.addAttribute("nickname",huanxinUser.getNickname());
        return PREFIX + "index.html";
    }
}
