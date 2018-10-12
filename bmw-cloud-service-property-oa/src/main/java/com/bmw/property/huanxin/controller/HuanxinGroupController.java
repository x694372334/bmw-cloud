package com.bmw.property.huanxin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.model.UserRoleVO;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.bmw.property.huanxin.model.HuanxinGroup;
import com.bmw.property.huanxin.service.IHuanxinGroupService;
import com.bmw.property.service.IInhabitantInfoService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 *  huanxingroup控制器
 *
 * @author fengshuonan
 * @Date 2018-08-14 09:03:59
 */
@Controller
@RequestMapping("/huanxinGroup")
public class HuanxinGroupController extends BaseController {

    private String PREFIX = "/huanxin/";

    @Autowired
    private IHuanxinGroupService huanxinGroupService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IInhabitantInfoService inhabitantInfoService;

    /**
     * 跳转到 huanxingroup首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "huanxinGroup.html";
    }

    /**
     * 跳转到添加 huanxingroup
     */
    @RequestMapping("/huanxinGroup_add")
    public String huanxinGroupAdd(Model model) {
    	Integer eId = (Integer)super.getSession().getAttribute("eId");
    	List<UserRoleVO> userList = userService.findUserRoleViewByEid(eId);
    	model.addAttribute("userList",userList);
        return PREFIX + "huanxinGroup_add.html";
    }
    
    /**
     * 跳转到修改群信息
     */
    @RequestMapping("/detailgroup/{id}")
    public String detailgroup(@PathVariable Integer id,Model model) {
    	HuanxinGroup huanxinGroup = huanxinGroupService.getdetail(id);
    	model.addAttribute("item",huanxinGroup);
        return PREFIX + "huanxinGroup_edit.html";
    }
    
    /**
     * 跳转到分配群成员
     */
    @RequestMapping("/distributionUser/{groupId}/{groupType}")
    public String distributionUser(@PathVariable String groupId,@PathVariable String groupType,Model model) {
    	ShiroExt shiro = new ShiroExt();
    	JSONArray users1= new JSONArray();
    	JSONArray users2= new JSONArray();
		String userId = shiro.getUser().getId().toString();
    	Integer eId = (Integer)super.getSession().getAttribute("eId");
    	List<UserRoleVO> userList = userService.findUserRoleViewByEidAndIsHuanxin(eId);
    	//获取群主信息
    	HuanxinGroup huanxinGroup = new HuanxinGroup();
    	huanxinGroup.setGroupId(groupId);
    	JSONArray jsonArray = huanxinGroupService.findList(huanxinGroup);
    	Integer ownerId = 0;
    	for(int i=0;i<jsonArray.size();i++) {
    		JSONObject ob  = (JSONObject) jsonArray.get(i);//得到json对象
    		ownerId = ob.getInteger("groupOwnerId");
		}
    	Iterator <UserRoleVO> it =userList.iterator();
    	//不显示群主
    	while(it.hasNext()){
    		if(it.next().getUid()==ownerId) {
    			it.remove();
    		}
    	}
    	
    	if("1".equals(groupType)) {
    		model.addAttribute("userList",userList);
    		users1 = huanxinGroupService.finUserIdByGroupId(groupId);
    		model.addAttribute("users1",users1);
    	}
    	if("2".equals(groupType)) {
    		model.addAttribute("inhabitantList",inhabitantInfoService.findAppUserByAid(userId));
    		users2 = huanxinGroupService.finInhabitantIdByGroupId(groupId);
    		model.addAttribute("users2",users2);
    	}
    	if("3".equals(groupType)) {
    		model.addAttribute("userList",userList);
    		users1 = huanxinGroupService.finUserIdByGroupId(groupId);
    		model.addAttribute("users1",users1);
    		model.addAttribute("inhabitantList",inhabitantInfoService.findAppUserByAid(userId));
    		users2 = huanxinGroupService.finInhabitantIdByGroupId(groupId);
    		model.addAttribute("users2",users2);
    	}
    	model.addAttribute("groupId",groupId);
    	model.addAttribute("groupType",groupType);
        return PREFIX + "distributionUser.html";
    }

    /**
     * 跳转到修改 huanxingroup
     */
    @RequestMapping("/huanxinGroup_update/{huanxinGroupId}")
    public String huanxinGroupUpdate(@PathVariable Integer huanxinGroupId, Model model) {
        HuanxinGroup huanxinGroup = huanxinGroupService.getdetail(huanxinGroupId);
        model.addAttribute("item",huanxinGroup);
        LogObjectHolder.me().set(huanxinGroup);
        return PREFIX + "huanxinGroup_edit.html";
    }

    /**
     * 获取 huanxingroup列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HuanxinGroup condition) {
    	Integer eId = (Integer)super.getSession().getAttribute("eId");
    	condition.seteId(eId);
        return huanxinGroupService.findList(condition);
    }

    /**
     * 新增 huanxingroup
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HuanxinGroup huanxinGroup) {
    	Integer eId = (Integer)super.getSession().getAttribute("eId");
    	huanxinGroup.seteId(eId);
    	ShiroExt shiro = new ShiroExt();
		String userId = shiro.getUser().getId().toString();
		String housekeeper = "0";
		List<UserRoleVO> viewList =  userService.findUserRoleViewByUserId(Integer.parseInt(userId));
		for(UserRoleVO view : viewList) {
			if("2".equals(view.getType())) {
				housekeeper = "1";
				break;
			}
		}
		
		if((!"1".equals(housekeeper))&&(!"3".equals(huanxinGroup.getGroupType()))) {
			return new ErrorTip(400,"非管家不能建立该类群" );
		}
        String result = huanxinGroupService.add(huanxinGroup);
        if("ERRO".equals(result)) {
        	return new ErrorTip(400,"已存在该类型群" );
        }else if("ERRO2".equals(result)) {
        	return new ErrorTip(400,"该群主未注册环信" );
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除 huanxingroup
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer huanxinGroupId) {
        huanxinGroupService.del(huanxinGroupId);
        return SUCCESS_TIP;
    }
    
    /**
     * 删除 huanxingroup
     */
    @RequestMapping(value = "/deleteByGroupId")
    @ResponseBody
    public Object deleteByGroupId(@RequestParam("groupId") String groupId,@RequestParam("groupType") String groupType) {
        huanxinGroupService.deleteByGroupId(groupId,groupType);
        return SUCCESS_TIP;
    }
    
    /**
     * 分配群组人员（管家群）
     */
    @RequestMapping(value = "/distribute")
    @ResponseBody
    public Object distribute(@RequestParam(value="userIds[]", required=false) String[] userIds,@RequestParam("groupId") String groupId) {
    	huanxinGroupService.distribute(userIds,groupId);
        return SUCCESS_TIP;
    }
    
    /**
     * 分配群组人员（业主群）
     */
    @RequestMapping(value = "/inhabitantDistribute")
    @ResponseBody
    public Object inhabitantDistribute(@RequestParam(value="inhabitantIds[]", required=false) String[] inhabitantIds,@RequestParam("groupId") String groupId) {
    	huanxinGroupService.inhabitantDistribute(inhabitantIds,groupId);
        return SUCCESS_TIP;
    }
    
    
    /**
     * 分配群组人员（其他群）
     */
    @RequestMapping(value = "/distributeOther")
    @ResponseBody
    public Object distributeOther(@RequestParam(value="userIds[]", required=false) String[] userIds,@RequestParam(value="inhabitantIds[]", required=false) String[] inhabitantIds,@RequestParam("groupId") String groupId) {
    	huanxinGroupService.groupOtherDistribute(userIds,inhabitantIds,groupId);
        return SUCCESS_TIP;
    }
    


    /**
     * 修改 huanxingroup
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HuanxinGroup huanxinGroup) {
        huanxinGroupService.update(huanxinGroup);
        return SUCCESS_TIP;
    }

    /**
     *  huanxingroup详情
     */
    @RequestMapping(value = "/detail/{huanxinGroupId}")
    @ResponseBody
    public Object detail(@PathVariable("huanxinGroupId") Integer huanxinGroupId) {
        return huanxinGroupService.getdetail(huanxinGroupId);
    }
    
    /**
     *  获取物业下的人员
     */
    @RequestMapping(value = "/getUserListByEid")
    @ResponseBody
    public Object getUserListByEid() {
    	Integer eId = (Integer)super.getSession().getAttribute("eId");
        return userService.findUserRoleViewByEid(eId);
    }
    
    /**
     *  获取当前登录人
     */
    @RequestMapping(value = "/userNow")
    @ResponseBody
    public Object userNow() {
    	ShiroExt shiro = new ShiroExt();
		String userId = shiro.getUser().getId().toString();
		String userName = shiro.getUser().getName();
		User user = new User();
		user.setId(Integer.parseInt(userId));
		user.setName(userName);
		return user;
    }
}
