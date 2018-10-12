package com.stylefeng.guns.core.log.factory;

import com.stylefeng.guns.core.common.constant.state.LogSucceed;
import com.stylefeng.guns.core.common.constant.state.LogType;
import com.stylefeng.guns.modular.system.model.LoginLog;
import com.stylefeng.guns.modular.system.model.OperationLog;
import com.stylefeng.guns.modular.system.service.impl.LoginLogServiceImpl;
import com.stylefeng.guns.modular.system.service.impl.OperationLogServiceImpl;
import com.stylefeng.guns.core.db.Db;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.core.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 日志操作任务创建工厂
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午9:18:27
 */
public class LogTaskFactory {

//    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
//    private static LoginLogServiceImpl loginLogService = SpringContextHolder.getBean(LoginLogServiceImpl.class);
//    private static OperationLogServiceImpl operationLogService = SpringContextHolder.getBean(OperationLogServiceImpl.class);
//
//    public static TimerTask loginLog(final Integer userId, final String ip) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, userId, null, ip);
//                    loginLogService.add(loginLog);
//                } catch (Exception e) {
//                    logger.error("创建登录日志异常!", e);
//                }
//            }
//        };
//    }
//
//    public static TimerTask loginLog(final String username, final String msg, final String ip) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                LoginLog loginLog = LogFactory.createLoginLog(
//                        LogType.LOGIN_FAIL, null, "账号:" + username + "," + msg, ip);
//                try {
//                	loginLogService.add(loginLog);
//                } catch (Exception e) {
//                    logger.error("创建登录失败异常!", e);
//                }
//            }
//        };
//    }
//
//    public static TimerTask exitLog(final Integer userId, final String ip) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                LoginLog loginLog = LogFactory.createLoginLog(LogType.EXIT, userId, null,ip);
//                try {
//                	loginLogService.add(loginLog);
//                } catch (Exception e) {
//                    logger.error("创建退出日志异常!", e);
//                }
//            }
//        };
//    }
//
//    public static TimerTask bussinessLog(final Integer userId, final String bussinessName, final String clazzName, final String methodName, final String msg) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                OperationLog operationLog = LogFactory.createOperationLog(
//                        LogType.BUSSINESS, userId, bussinessName, clazzName, methodName, msg, LogSucceed.SUCCESS);
//                try {
//                    operationLogService.add(operationLog);
//                } catch (Exception e) {
//                    logger.error("创建业务日志异常!", e);
//                }
//            }
//        };
//    }
//
//    public static TimerTask exceptionLog(final Integer userId, final Exception exception) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                String msg = ToolUtil.getExceptionMsg(exception);
//                OperationLog operationLog = LogFactory.createOperationLog(
//                        LogType.EXCEPTION, userId, "", null, null, msg, LogSucceed.FAIL);
//                try {
//                    operationLogService.add(operationLog);
//                } catch (Exception e) {
//                    logger.error("创建异常日志异常!", e);
//                }
//            }
//        };
//    }
}
