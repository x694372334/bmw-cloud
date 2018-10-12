package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2018/10/8.
 */
@SpringBootApplication
public class BMWApplication {
    public static void main(String[] args)  {
//        Properties properties=new Properties();
//        InputStream in=bmwApplication.class.getClassLoader().getResourceAsStream("application-location.properties");
//        properties.load(in);
//        SpringApplication app=new SpringApplication(bmwApplication.class);
//        app.setDefaultProperties(properties);
//        app.run(args);
//        logger.info("bmwApplication is success!");
        SpringApplication.run(BMWApplication.class, args);
    }
}
