package com.stylefeng.guns.modular.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.naming.NoPermissionException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.dictmap.UserDict;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.factory.UserFactory;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.transfer.UserDto;
import com.stylefeng.guns.modular.system.warpper.UserWarpper;
import com.bmw.property.huanxin.service.HuanxinService;
import com.bmw.property.model.Department;
import com.bmw.property.model.Position;
import com.bmw.property.service.IDepartmentService;
import com.bmw.property.service.IPositionService;

/**
 * 系统管理员控制器
 *
 * @author lyl
 * @Date 2018年4月24日
 */
@Controller
@RequestMapping("/mgr")
public class UserMgrController extends BaseController {

    private static String PREFIX = "/system/user/";

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private IUserService userService;
    
    @Autowired
    private IPositionService positionService;
    
    @Autowired
    private HuanxinService huanxinService;
    
    @Value("${bmw.cloud.fileservice.url}")
	public String fileServerUrl;
    
    @Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
    
    @Value("${huanxin.huanxinUserByUserId}")
	public String  huanxin_huanxinUserByUserId="";
    
    

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("")
    public String index(Model model) {
    	ShiroExt shiro = new ShiroExt();
    	if(shiro.getUser().getIsAdmin()) {
       	 model.addAttribute("isAdmin", "1");
        }else {
       	 model.addAttribute("isAdmin", "2");
        }
        return PREFIX + "user.html";
    }

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("/user_add")
    public String addView(Model model) {
    	ShiroExt shiro = new ShiroExt();
    	Integer parentEId = shiro.getUser().getParentEId();
    	String parentEName = shiro.getUser().getParentEName();
    	 model.addAttribute("parentEId", parentEId);
         model.addAttribute("parentEName", parentEName);
         if(shiro.getUser().getIsAdmin()) {
        	 model.addAttribute("parentEIdReadOnly", "1");
         }else {
        	 model.addAttribute("parentEIdReadOnly", "2");
         }
        return PREFIX + "user_add.html";
    }

    /**
     * 跳转到角色分配页面
     */
    //@RequiresPermissions("/mgr/role_assign")  //利用shiro自带的权限检查
    @Permission
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = userService.detailUser(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_roleassign.html";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @Permission
    @RequestMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        User user = userService.detailUser(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        model.addAttribute("positionName", ConstantFactory.me().getPositionName(user.getPositionId()));
        model.addAttribute("eName", ConstantFactory.me().getEnterpriseInfoName(user.getParentEId()));
        LogObjectHolder.me().set(user);
        
        
    	ShiroExt shiro = new ShiroExt();
    	Integer parentEId = shiro.getUser().getParentEId();
    	String parentEName = shiro.getUser().getParentEName();
    	 model.addAttribute("parentEId", parentEId);
         model.addAttribute("parentEName", parentEName);
         if(shiro.getUser().getIsAdmin()) {
        	 model.addAttribute("parentEIdReadOnly", "1");
         }else {
        	 model.addAttribute("parentEIdReadOnly", "2");
         }
        return PREFIX + "user_edit.html";
    }

    /**
     * 跳转到查看用户详情页面
     */
    @RequestMapping("/user_info")
    public String userInfo(Model model) {
        Integer userId = ShiroKit.getUser().getId();
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = userService.detailUser(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return PREFIX + "user_view.html";
    }

    /**
     * 跳转到修改密码界面
     */
    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return PREFIX + "user_chpwd.html";
    }

    /**
     * 修改当前用户的密码
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
        if (!newPwd.equals(rePwd)) {
            throw new GunsException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        Integer userId = ShiroKit.getUser().getId();
        User user = userService.detailUser(userId);
        String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
        if (user.getPassword().equals(oldMd5)) {
            String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
            user.setPassword(newMd5);
            //user.updateById();
            userService.edit(user);
            return SUCCESS_TIP;
        } else {
            throw new GunsException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }

    /**
     * 查询管理员列表
     */
    @RequestMapping("/list")
    /*@Permission*/
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime,
    					@RequestParam(required = false) Integer deptid, @RequestParam(required = false) Integer parenteid) {
    	String url = bmw_cloud_baseservice_url+huanxin_huanxinUserByUserId;
    	if (ShiroKit.isAdmin()) {
        	JSONArray users = userService.selectUsers(name, beginTime, endTime, deptid ,parenteid);
            return new UserWarpper(users,url).warp();
        } else {
            JSONArray users = userService.selectUsers(name, beginTime, endTime, deptid,parenteid);
            return new UserWarpper(users,url).warp();
        }
    }

    /**
     * 添加管理员
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
   // @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断账号是否重复
        User theUser = userService.getByAccount(user.getAccount());
        if (theUser != null) {
            throw new GunsException(BizExceptionEnum.USER_ALREADY_REG);
        }

        // 完善账号信息
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreatetime(new Date());
        userService.add(UserFactory.createUser(user));
        
        //是否注册环信
		if ("1".equals(user.getHuanxinFlag())) {
			User condition = new User();
			condition.setAccount(user.getAccount());
			JSONObject rlt = (JSONObject) userService.findList(condition).get(0);
			String username = UUID.randomUUID().toString().replaceAll("-", "");
			String password = UUID.randomUUID().toString().replaceAll("-", "");
			huanxinService.registeredHuanxin(rlt.getString("id"), username, password, user.getName());
		}
        return SUCCESS_TIP;
    }

    /**
     * 修改管理员
     *
     * @throws NoPermissionException
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
    @ResponseBody
    public Tip edit(@Valid UserDto user, BindingResult result) throws NoPermissionException {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
            userService.edit(UserFactory.createUser(user));
            return SUCCESS_TIP;
        } else {
            assertAuth(user.getId());
            ShiroUser shiroUser = ShiroKit.getUser();
           /* if (shiroUser.getId().equals(user.getId())) {*/
                userService.edit(UserFactory.createUser(user));
                return SUCCESS_TIP;
          /*  } else {
                throw new GunsException(BizExceptionEnum.NO_PERMITION);
            }*/
        }
    }

    /**
     * 删除管理员（逻辑删除）
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
    /*@Permission*/
    @ResponseBody
    public Tip delete(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        assertAuth(userId);
        userService.setStatus(userId, ManagerStatus.DELETED.getCode());
        userService.userDropByUserId(userId);
        return SUCCESS_TIP;
    }

    /**
     * 查看管理员详情
     */
    @RequestMapping("/view/{userId}")
    @ResponseBody
    public User view(@PathVariable Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        return userService.detailUser(userId);
    }

    /**
     * 重置管理员的密码
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
   /* @Permission(Const.ADMIN_NAME)*/
    @ResponseBody
    public Tip reset(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        User user = userService.detailUser(userId);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
        userService.edit(user);
        return SUCCESS_TIP;
    }

    /**
     * 冻结用户
     */
    @RequestMapping("/freeze")
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
   /* @Permission(Const.ADMIN_NAME)*/
    @ResponseBody
    public Tip freeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能冻结超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        assertAuth(userId);
        userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结用户
     */
    @RequestMapping("/unfreeze")
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
   /* @Permission(Const.ADMIN_NAME)*/
    @ResponseBody
    public Tip unfreeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        userService.setStatus(userId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 分配角色
     */
    @RequestMapping("/setRole")
    /*@BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)*/
    @ResponseBody
    public Tip setRole(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        assertAuth(userId);
        String flag = userService.setRoles(userId, roleIds);
        if("0".equals(flag)){
        	return new ErrorTip(400,"角色非同一公司");
        }else if("2".equals(flag)){
        	return new ErrorTip(400,"用户与角色公司不统一");
        }
        return SUCCESS_TIP;
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
        		 String fileName[]=picture.getOriginalFilename().split("\\.");
        		 f=File.createTempFile(fileName[0],"."+fileName[1],null);
        		 picture.transferTo(f);  
    			 String res = HttpUtils.flieUploadFormal(fileServerUrl, "/yhtx", f);
    			 //截取真正的文件名称
    			 String fname = res.substring(res.lastIndexOf("/")+1, res.length());
    			 if(fname!=null && !fname.equals("")) {
    				 pictureName=fname;
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
     * 判断当前登录的用户是否有操作这个用户的权限
     */
    private void assertAuth(Integer userId) {
        if (ShiroKit.isAdmin()) {
            return;
        }
        List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
        User user = userService.detailUser(userId);
        Integer deptid = user.getDeptid();
        if (deptDataScope.contains(deptid)) {
            return;
        } else {
            throw new GunsException(BizExceptionEnum.NO_PERMITION);
        }

    }
    
    /**
     * 注册环信
     */
    @RequestMapping("/registeredHuanxin")
    @ResponseBody
    public Tip registeredHuanxin(@RequestParam Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = userService.detailUser(userId);
        String username = UUID.randomUUID().toString().replaceAll("-", "");
        String password = UUID.randomUUID().toString().replaceAll("-", "");
        huanxinService.registeredHuanxin(userId.toString(), username, password, user.getName());
        return SUCCESS_TIP;
    }
    
    /**
     * 注销环信
     */
    @RequestMapping("/dropHuanxin")
    @ResponseBody
    public Tip dropHuanxin(@RequestParam Integer userId, Model model) {
    	userService.userDropByUserId(userId);
        return SUCCESS_TIP;
    }
    
    
}
