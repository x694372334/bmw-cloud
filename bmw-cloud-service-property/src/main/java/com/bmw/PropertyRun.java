package com.bmw;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.bmw.common.globalHandler.GlobalErrorController;
//import com.bmw.common.globalHandler.GlobalExceptionHandler;
//import com.bmw.common.health.RedisHealthIndicator;
import com.bmw.common.log.ServiceLog;
import com.bmw.common.model.BMWCloudInfo;
//import com.bmw.common.mq.KafkaSource;
import com.bmw.common.springmvc.conversion.JsonHttpMessageConverter;
import com.bmw.common.utils.SpringContextUtils;

@RestController
@SpringBootApplication(exclude={RedisAutoConfiguration.class,})
@EnableDiscoveryClient
@EnableFeignClients
//@EnableBinding(KafkaSource.class)
public class PropertyRun {

    public static void main(String[] args) {
        SpringApplication.run(PropertyRun.class, args);
    }

    /**
     * 首页设置
     * @author lyl
     * 2016年11月1日
     */
    @RequestMapping("/")
    public String index(){
        return "Hello bmw cloud service property !";
    }

    /**
     * 服务日志功能初始化
     * @author lyl
     * 2016年11月2日
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceLog serviceLog(){
        return new ServiceLog("bmw-cloud-property");
    }

    /**
     * 本服务信息
     * @author lyl
     * 2016年11月9日
     */
    @Bean
    @ConditionalOnMissingBean
    public BMWCloudInfo bmwCloudInfo(){
        BMWCloudInfo tl = new BMWCloudInfo();
        tl.setServiceName("bmw-cloud-property");
        return tl;
    }

    /**
     * SpringContextUtils初始化
     * @author lyl
     * 2016年11月1日
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringContextUtils springContextUtils(){
        return new SpringContextUtils();
    }

    /**
     * 服务错误处理初始化
     * @author lyl
     * 2016年11月1日
     */
//    @Bean
//    @ConditionalOnMissingBean
//    public GlobalErrorController globalErrorController(){
//        return new GlobalErrorController();
//    }

    /**
     * 服务异常处理初始化
     * @author lyl
     * 2016年11月1日
     */
//    @Bean
//    @ConditionalOnMissingBean
//    public GlobalExceptionHandler globalExceptionHandler(){
//        return new GlobalExceptionHandler();
//    }


    /**
     * JSON转换
     * @author lyl
     * 2016年11月7日
     */
    @Bean
    @ConditionalOnMissingBean
    public JsonHttpMessageConverter jsonHttpMessageConverter(){
        return new JsonHttpMessageConverter();
    }

    /**
     * 自定义Redis监控指标
     * @author lyl
     * 2017年2月17日
     */
//    @Bean
//    public RedisHealthIndicator redisHealthIndicator(){
//        return new RedisHealthIndicator();
//    }
}