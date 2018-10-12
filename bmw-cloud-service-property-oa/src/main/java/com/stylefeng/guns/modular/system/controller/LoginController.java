package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.google.code.kaptcha.Constants;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.exception.InvalidKaptchaException;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.log.factory.LogTaskFactory;
import com.stylefeng.guns.core.node.MenuNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ApiMenuFilter;
import com.stylefeng.guns.core.util.KaptchaUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.model.UserRoleVO;
import com.stylefeng.guns.modular.system.service.IMenuService;
import com.stylefeng.guns.modular.system.service.IUserService;
//import com.bmw.bill.model.BillVO;

import org.apache.http.ParseException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.stylefeng.guns.core.support.HttpKit.getIp;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;
    
    @Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
    
    @Value("${user.findUserRoleViewByUserId}")
	private String user_findUserRoleViewByUserId="";
    
    @Value("${user.findUserRoleViewByRoleId}")
   	private String user_findUserRoleViewByRoleId="";
    
    @Value("${user.findUserRoleViewByUserIdAndEid}")
   	private String user_findUserRoleViewByUserIdAndEid="";

    /**
     * 跳转到主页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        //获取菜单列表
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login.html";
        }
        
        //获取用户头像
        Integer id = ShiroKit.getUser().getId();
        User user = userService.detailUser(id);
        String avatar = user.getAvatar();
        model.addAttribute("avatar", avatar);
        
        //获取用户公司信息
        try {
        	JSONArray jsonArray=null;
        	UserRoleVO userRoleVO = new UserRoleVO();
        	//查询当前人的所有公司
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+user_findUserRoleViewByUserId+id);
			//查询当前角色下的公司
			String rlt2=HttpUtils.doGet(bmw_cloud_baseservice_url+user_findUserRoleViewByRoleId+roleList.get(0));
			JSONObject jsonObject=JSON.parseObject(rlt2).getJSONObject("items");
			if(jsonObject!=null) {
				userRoleVO=jsonObject.toJavaObject(UserRoleVO.class);
			}
			//查询当前用户下当前选择物业的角色
			String rlt3= "";
			if(null == userRoleVO.getEid()) {
				rlt3 = rlt;  //如果不是企业管理员 则只有一个角色  并且没有所属物业
			}else {
				rlt3=HttpUtils.doGet(bmw_cloud_baseservice_url+user_findUserRoleViewByUserIdAndEid+id+"/"+userRoleVO.getEid());
			}
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			JSONArray data=JSON.parseObject(rlt3).getJSONArray("items");
			JSONArray array = new JSONArray(data);//将json字符串转成json数组
			//if(array.size()>0) {
			List<Integer> param1 = new ArrayList<Integer>();
			for (int i = 0; i < array.size(); i++) {//循环json数组
				JSONObject ob  = (JSONObject) array.get(i);//得到json对象
				UserRoleVO userRoleVO2 = JSONObject.toJavaObject(ob, UserRoleVO.class);
				param1.add(userRoleVO2.getRid());
			}
			  List<MenuNode> menus = menuService.getMenusByRoleIds(param1);
			  if(user.getuAttribute()==1) {
				  menus = filterPropertyMenu(menus);
			  }
		      List<MenuNode> titles = MenuNode.buildTitle(menus);
		      titles = ApiMenuFilter.build(titles);

		    model.addAttribute("titles", titles);
			model.addAttribute("enterprises", jsonArray);
			model.addAttribute("entername", userRoleVO);
			//当前物业ID
			super.getSession().setAttribute("eId", userRoleVO.getEid());
			super.getSession().setAttribute("eName", userRoleVO.getEname());
	        ShiroKit.getUser().seteId(userRoleVO.getEid());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return "/index.html";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }

    /**
     * 点击登录执行的动作
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali() {

        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        String remember = super.getPara("remember");

        //验证验证码是否正确
        if (KaptchaUtil.getKaptchaOnOff()) {
            String kaptcha = super.getPara("kaptcha").trim();
            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
                throw new InvalidKaptchaException();
            }
        }

        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }

        currentUser.login(token);

        ShiroUser shiroUser = ShiroKit.getUser();
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("username", shiroUser.getAccount());
//        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));

        ShiroKit.getSession().setAttribute("sessionFlag", true);

        return REDIRECT + "/";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
//        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
        ShiroKit.getSubject().logout();
        return REDIRECT + "/login";
    }
    
    
    
    /**
     * 切换公司跳转到主页
     */
    @RequestMapping(value = "/renewLogin/{eid}/{rid}", method = RequestMethod.GET)
    public String renewLogin(@PathVariable("eid") String eid,@PathVariable("rid") Integer rid,Model model) {
        
        //获取菜单列表
        List<Integer> roleList = new ArrayList<Integer>();
        roleList.add(rid);
        //获取用户头像
        Integer id = ShiroKit.getUser().getId();
        User user = userService.detailUser(id);
        String avatar = user.getAvatar();
        model.addAttribute("avatar", avatar);
        
      //获取用户公司信息
        try {
        	JSONArray jsonArray=null;
        	UserRoleVO userRoleVO = new UserRoleVO();
        	//查询当前用户下的公司
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+user_findUserRoleViewByUserId+id);
			//查询当前角色下的公司
			String rlt2=HttpUtils.doGet(bmw_cloud_baseservice_url+user_findUserRoleViewByRoleId+roleList.get(0));
			JSONObject jsonObject=JSON.parseObject(rlt2).getJSONObject("items");
			if(jsonObject!=null) {
				userRoleVO=jsonObject.toJavaObject(UserRoleVO.class);
			}
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			//查询当前用户下当前选择物业的角色
			String rlt3= "";
			if(null == userRoleVO.getEid()) {
				rlt3 = rlt;  //如果不是企业管理员 则只有一个角色  并且没有所属物业
			}else {
				rlt3=HttpUtils.doGet(bmw_cloud_baseservice_url+user_findUserRoleViewByUserIdAndEid+id+"/"+userRoleVO.getEid());
			}
				JSONArray data=JSON.parseObject(rlt3).getJSONArray("items");
				JSONArray array = new JSONArray(data);//将json字符串转成json数组
				List<Integer> param1 = new ArrayList<Integer>();
				for (int i = 0; i < array.size(); i++) {//循环json数组
					JSONObject ob  = (JSONObject) array.get(i);//得到json对象
					UserRoleVO userRoleVO2 = JSONObject.toJavaObject(ob, UserRoleVO.class);
					param1.add(userRoleVO2.getRid());
				}
			      
		        List<MenuNode> menus = menuService.getMenusByRoleIds(param1);
		        if(user.getuAttribute()==1) {
					  menus = filterPropertyMenu(menus);
				  }
		        List<MenuNode> titles = MenuNode.buildTitle(menus);
		        titles = ApiMenuFilter.build(titles);
		        model.addAttribute("titles", titles);
		        model.addAttribute("enterprises", jsonArray);
		        model.addAttribute("entername", userRoleVO);
		        super.getSession().setAttribute("eId", userRoleVO.getEid());
				super.getSession().setAttribute("eName", userRoleVO.getEname());
				ShiroKit.getUser().seteId(userRoleVO.getEid());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return "/index.html";
    }
    
    //将物业的菜单过滤掉
    private List<MenuNode> filterPropertyMenu(List<MenuNode> menus){
    	List<MenuNode> result = new ArrayList<MenuNode>();
    	for(MenuNode menu : menus) {
    		if(menu.getPcodes().indexOf("[property]")==-1&&!menu.getCode().equals("property")) {
    			result.add(menu);
    		}
    	}
    	return result;
    }

}
