package com.bmw.flowable.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import com.bmw.flowable.model.TaskVO;


/**
 * <p>
 * 工作流通用类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-17
 */
public interface IFlowableService {


	/**
	 * 查询流程图
	 * @param 任务ID
	 */
	void printProcessImage(String taskId) throws IOException;
	
}
