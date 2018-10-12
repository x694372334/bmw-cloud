package com.bmw.common.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务操作日志记录,记录系统中增/删/改 操作。
 * @author lyl
 * 2018年6月12日
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceOperationLog {

	
	/**
	 * 操作类型 ：insert/delete/update
	 * @author lyl
	 * 2018年6月12日
	 */
	String type() default "";
	
	/**
	 * 操作标识
	 * @author lyl
	 * 2018年6月12日
	 */
	String iden() default "";
	
	
	/**
	 * 操作标识名称
	 * @author lyl
	 * 2018年6月12日
	 */
	String idenName() default "";
	
	
	/**
	 * 操作表主键Bean名称
	 * @author lyl
	 * 2018年6月12日
	 */
	String idBeanName() default "";
}
