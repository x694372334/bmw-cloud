package com.bmw.property.module.feignService.base.dept;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;


import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;


@Component
public class DeptFeignServiceHystrix implements DeptFeignService{
	
	/**
	 * 新增部门
	 * @author lyl
	 * 2018年07月03日
	 */
	@Override
	public Result add(@PathVariable String jsonString){
		return ResultUtils.errorMethodData(HttpStatus.NOT_IMPLEMENTED, "调用base服务base/dept/add接口异常");
	}
	
	/**
	 * 获取所有部门列表
	 * @author lyl
	 * 2018年04月17日
	 */
	@Override
	public Result deptList(@PathVariable("deptName") String deptName) {
		return ResultUtils.errorMethodData(HttpStatus.NOT_IMPLEMENTED, "调用base服务base/dept/deptList接口异常");
	}

}
