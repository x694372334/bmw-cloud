package com.bmw.base.web.dept;

import java.io.UnsupportedEncodingException;
import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bmw.base.module.dept.domain.Dept;
import com.bmw.base.module.dept.service.DeptService;
import com.bmw.common.exception.RestPreconditionFailedException;
import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;
import com.bmw.common.utils.oa.ToolUtil;


@RestController
@RequestMapping("base/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	
	
	/**
	 * 获取所有部门列表
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="deptList/{deptName}",method=RequestMethod.GET)
	public Result deptList(@PathVariable("deptName") String deptName){
		if(StringUtils.isBlank(deptName) || deptName.equals("1")) {
			throw new RestPreconditionFailedException("请提交正确的部门名称");
		}
		Dept dept=new Dept();
		dept.setFullname(deptName);
		dept.setSimplename(deptName);
		List<Dept> categorys = deptService.findLike(dept);
		return ResultUtils.getMethodData(categorys);
	}
	
	
	/**
	 * 获取获取子部门
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="deptListById/{deptId}",method=RequestMethod.GET)
	public Result deptListById(@PathVariable("deptId") String deptId){
		if(StringUtils.isBlank(deptId)) {
			throw new RestPreconditionFailedException("请提交正确的部门Id");
		}
		Dept dept=new Dept();
		dept.setPids(deptId);
		List<Dept> categorys = deptService.findLike(dept);
		return ResultUtils.getMethodData(categorys);
	}
	
	
	/**
	 * 获取部门详情
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="deptDetail/{deptId}",method=RequestMethod.GET)
	public Result deptList(@PathVariable("deptId") Integer deptId){
		if(deptId==null) {
			throw new RestPreconditionFailedException("请提交正确的id");
		}
		Dept categorys = deptService.findById(deptId);
		return ResultUtils.getMethodData(categorys);
	}
	
	
	
	/**
	 * 获取部门树
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="deptTree",method=RequestMethod.GET)
	public Result deptTree(){
		return ResultUtils.getMethodData(deptService.tree());
	}
	
	
	
	/**
	 * 新增部门
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="add/{jsonString}",method=RequestMethod.POST)
	public Result add(@PathVariable("jsonString") String jsonString){
		if(StringUtils.isBlank(jsonString)) {
			throw new RestPreconditionFailedException("请提交正确的表单信息");
		}
		try {
			String params=new String (Base64Utils.decode(jsonString.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			Dept dept=JSONObject.toJavaObject(object, Dept.class);
			deptSetPids(dept);
			deptService.add(dept);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("添加部门成功");
	}
	
	/**
	 * 修改部门
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="update/{jsonString}",method=RequestMethod.POST)
	public Result update(@PathVariable("jsonString") String jsonString){
		if(StringUtils.isBlank(jsonString)) {
			throw new RestPreconditionFailedException("请提交正确的表单信息");
		}
		try {
			String params=new String (Base64Utils.decode(jsonString.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			Dept dept=JSONObject.toJavaObject(object, Dept.class);
			deptSetPids(dept);
			deptService.update(dept);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("修改部门成功");
	}
	
	/**
	 * 新增部门
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="testLcn/{jsonString}",method=RequestMethod.POST)
	public Result testLcn(@PathVariable("jsonString") String jsonString){
		if(StringUtils.isBlank(jsonString)) {
			throw new RestPreconditionFailedException("请提交正确的表单信息");
		}
		try {
			String params=new String (Base64Utils.decode(jsonString.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			Dept dept=JSONObject.toJavaObject(object, Dept.class);
			deptSetPids(dept);
//			deptService.testLcn(dept,jsonString);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("添加部门成功");
	}
	/**
	 * 删除部门
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="del/{jsonString}",method=RequestMethod.POST)
	public Result del(@PathVariable("jsonString") String jsonString){
		if(StringUtils.isBlank(jsonString)) {
			throw new RestPreconditionFailedException("请提交正确的表单信息");
		}
		try {
			String params = new String (Base64Utils.decode(jsonString.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			deptService.deleteDept(object.getInteger("deptId"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return ResultUtils.postMethodData("删除部门成功");
	}
	
	
	
	
	
	private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0)) {
            dept.setPid(0);
            dept.setPids("[0],");
        } else {
            int pid = dept.getPid();
            Dept temp = deptService.findById(pid);
            String pids = temp.getPids();
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }
	
}
