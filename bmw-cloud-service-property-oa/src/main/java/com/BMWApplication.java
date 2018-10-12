package com;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * SpringBoot方式启动类
 *
 * @author stylefeng
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication
@PropertySource({"application-prod.properties","application-flowable.properties"})
public class BMWApplication {

    private final static Logger logger = LoggerFactory.getLogger(BMWApplication.class);

    public static void main(String[] args) throws IOException {
    	Properties properties=new Properties();
    	InputStream in= BMWApplication.class.getClassLoader().getResourceAsStream("application-location.properties");
    	properties.load(in);
    	SpringApplication app=new SpringApplication(BMWApplication.class);
    	app.setDefaultProperties(properties);
    	app.run(args);
    	logger.info("BMWApplication is success!");
//        SpringApplication.run(BMWApplication.class, args);
    }
}
