package com.bmw.common.log;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.bmw.common.exception.RestRuntimeException;
import com.bmw.common.model.ExceptionLog;
import com.bmw.common.model.OperationLog;
import com.bmw.common.model.PerformanceLog;
import com.bmw.common.thread.ExecutorManager;

/**
 * 服务日志功能
 * @author lyl
 * 2018年6月12日
 */
@Aspect
public class ServiceLog {
	
	
	public ServiceLog(){}
	
	/**
	 * @param sysType 服务日志类型
	 */
	public ServiceLog(String sysType){
		this.sysType = sysType;
	}
	
	/**
	 * 服务日志类型
	 */
	private String sysType;
	
	/**
	 * 用户ID
	 */
	private String tlUserId = "TL-Request-userID";
	
	/**
	 * 用户名称
	 */
	private String tlUserName = "TL-Request-userName";
	
	/**
	 * 用户来源IP
	 */
	private String tlOriginIP = "TL-Request-originIP";
	

	
	@Autowired
	private HttpServletRequest request;
	
	
	
	
	/**
	 * 定义命名切点，系统操作日志切点（增、删、改）
	 * @author lyl
	 * 2016年7月13日
	 */
	@Pointcut("@annotation(com.bmw.common.log.ServiceOperationLog)")
	private void serviceOperationLog(){
		
	}
	
	/**
	 * 定义命名切点，系统服务层
	 * @author lyl
	 * 2016年7月13日
	 */
	@Pointcut("execution(* com.bmw.*.module..*.*Service.*(..)) ")
	private void servicePointCut(){
		
	}
	
	
	
//	/**
//	 * 服务操作功能日志
//	 * @author lyl
//	 * 2016年7月13日
//	 */
//	@AfterReturning(value="serviceOperationLog() && @annotation(log) && args(po)",argNames="log,po")
//	public void doAfterReturning(JoinPoint joinpoint,ServiceOperationLog operationLog,Object po){
//		String idValue = "";
//		if(po instanceof String){
//			idValue = (String)po;
//		}else if(po instanceof Integer) {
//			idValue=String.valueOf(po);
//		}else{
//			JSONObject json = (JSONObject) JSONObject.toJSON(po);
//			idValue = json.getString(operationLog.idBeanName());
//		}
//
//		String userId = request.getHeader(tlUserId);
//		String userName = request.getHeader(tlUserName);
//		String srcIP = request.getHeader(tlOriginIP);
//
//
//		OperationLog operLog = new OperationLog();
//		operLog.setUserId(userId);
//		operLog.setUserName(userName);
//		operLog.setUserIp(srcIP);
//		operLog.setType(operationLog.type());
//		operLog.setIdent(operationLog.iden());
//		operLog.setIdentName(operationLog.idenName());
//		operLog.setDataId(idValue);
//		operLog.setSysType(sysType);
//
//
//		// 发送到消息队列中
////		ExecutorManager.execute(new OperationLogThread(operLog));
//		operLog = null;
//
//	}
	
	
	
	
	/**
	 * 服务操作性能日志
	 * @author lyl
	 * 2016年7月13日
	 * @throws Throwable 
	 */
	@Around(value="servicePointCut()")
	public Object doAround(ProceedingJoinPoint joinpoint) throws Throwable{
		Object result = null;
		
    	long start = System.currentTimeMillis();
    	
    	result = joinpoint.proceed();
		
		long end = System.currentTimeMillis();
		long runTime = end - start;
		
		if(runTime>1000){ // 运行时间过长进行记录
			String userId = request.getHeader(tlUserId);
			String userName = request.getHeader(tlUserName);
			String srcIP = request.getHeader(tlOriginIP);
			
			PerformanceLog perfLog = new PerformanceLog();
			perfLog.setUserId(userId);
			perfLog.setUserName(userName);
			perfLog.setUserIp(srcIP);
			perfLog.setClassName(joinpoint.getTarget().getClass().getName());
			perfLog.setClassMethod(joinpoint.getSignature().getName());
			perfLog.setRunTime(String.valueOf(runTime));
			perfLog.setSysType(sysType);
			
			// 发送日志
//			ExecutorManager.execute(new PerformanceLogThread(perfLog));
			perfLog = null;
			
		}
			
			
			
	    return result;       
	} 
	
	
	
	
	 /**  
     * 异常通知 用于拦截服务异常日志  
     *  
     * @param joinPoint  
     * @param e  
     */    
    @AfterThrowing(value = "servicePointCut()", throwing = "e")    
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
    	// 如果是Rest运行时异常过滤
    	if(e instanceof RestRuntimeException){
    		return ;
    	}
    	String userId = request.getHeader(tlUserId);
		String userName = request.getHeader(tlUserName);
		String srcIP = request.getHeader(tlOriginIP);
		
        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setUserId(userId);
        exceptionLog.setUserName(userName);
        exceptionLog.setUserIp(srcIP);
        exceptionLog.setClassName(joinPoint.getTarget().getClass().getName());
        exceptionLog.setClassMethod(joinPoint.getSignature().getName());
        String exceptionContent = "异常代码："+e.getClass().getName()+",异常信息:"+e.getMessage();
        exceptionLog.setExceptionContent(exceptionContent.getBytes());
        exceptionLog.setSysType(sysType);
        
        // 发送日志
//        ExecutorManager.execute(new ExceptionLogThread(exceptionLog));
        exceptionLog = null;
        
    }    
	
	
	
}
