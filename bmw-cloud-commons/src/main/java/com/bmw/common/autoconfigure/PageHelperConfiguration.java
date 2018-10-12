package com.bmw.common.autoconfigure;

import java.util.Properties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/**
 * MyBatis PageHelper 配置
 * @author Jinmy
 * 2018年10月11日
 */
@Configuration
public class PageHelperConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public PageHelper interceptors(){
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();

        props.setProperty("reasonable", "false"); // 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        props.setProperty("supportMethodsArguments", "true"); // 支持通过Mapper接口参数来传递分页参数
        props.setProperty("returnPageInfo", "check"); // always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page

        pageHelper.setProperties(props);

        return pageHelper;
    }



}
