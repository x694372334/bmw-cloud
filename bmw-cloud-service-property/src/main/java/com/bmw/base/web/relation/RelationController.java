package com.bmw.base.web.relation;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bmw.base.module.notice.domain.Notice;
import com.bmw.base.module.notice.service.NoticeService;
import com.bmw.base.module.relation.domain.Relation;
import com.bmw.base.module.relation.service.RelationService;
import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;

@RestController
@RequestMapping("base/relation")
public class RelationController {
	
	@Autowired
	private RelationService relationService;

	/**    
	* <p>Description: 新增角色关联</p>  
	* @author lyl  
	* @date 2018年4月26日  
	*/  
	@RequestMapping(value="add/{jsonParams}",method=RequestMethod.POST)
	public Result add(@PathVariable("jsonParams") String jsonParams){
		try {
			String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			relationService.add(JSONObject.toJavaObject(object, Relation.class));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultUtils.postMethodData("新增角色关联成功");
	}
	
	
	/**    
	* <p>Description: 新增角色关联</p>  
	* @author lyl  
	* @date 2018年4月26日  
	*/  
	@RequestMapping(value="addApp",method=RequestMethod.POST)
	public Result add(@RequestParam("roleId") String roleId,@RequestParam("menuIds") String menuIds){
		if(menuIds==null||"".equals(menuIds)) {
			menuIds = "";
		}else {
			menuIds = menuIds.substring(0, menuIds.length()-1);
		}
		relationService.addApp(roleId,menuIds);
		return ResultUtils.postMethodData("新增角色关联成功");
	}
	
	
	/**    
	* <p>Description: 按照菜单id删除角色关联</p>  
	* @author lyl  
	* @date 2018年4月26日  
	*/  
	@RequestMapping(value="delByMenuId/{menuId}",method=RequestMethod.DELETE)
	public Result delByMenuId(@PathVariable("menuId") Integer menuId){
		relationService.delByMenuId(menuId);
		return ResultUtils.deleteMethodData("按照菜单id删除角色关联成功");
	}
	
	/**    
	* <p>Description: 按照角色id删除角色关联</p>  
	* @author lyl  
	* @date 2018年4月26日  
	*/  
	@RequestMapping(value="delByRoleId/{roleId}",method=RequestMethod.DELETE)
	public Result delByRoleId(@PathVariable("roleId") Integer roleId){
		relationService.delByRoleId(roleId);
		return ResultUtils.deleteMethodData("按照角色id删除角色关联成功");
	}
	
	/**    
	* 查询角色已有的app权限 
	*/  
	@RequestMapping(value="findListByRoleId/{roleId}",method=RequestMethod.GET)
	public Result findListByRoleId(@PathVariable("roleId") Integer roleId){
		List<Integer> result = relationService.findListByRoleId(roleId);
		return ResultUtils.getMethodData(result);
	}
}
