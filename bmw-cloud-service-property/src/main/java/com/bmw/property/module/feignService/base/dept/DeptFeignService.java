package com.bmw.property.module.feignService.base.dept;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bmw.common.model.Result;


/**
 * 部门公共服务
 * @author lyl
 * 2018年7月2日
 */
@FeignClient(value = "bmw-service-base", fallback = DeptFeignServiceHystrix.class)
public interface DeptFeignService {
	
	/**
	 * 新增部门
	 * @author lyl
	 * 2018年07月03日
	 */
	@RequestMapping(value="base/dept/add/{jsonString}",method=RequestMethod.POST)
	Result add(@PathVariable("jsonString") String jsonString);
	
	/**
	 * 获取所有部门列表
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="base/dept/deptList/{deptName}",method=RequestMethod.GET)
	Result deptList(@PathVariable("deptName") String deptName);
}
