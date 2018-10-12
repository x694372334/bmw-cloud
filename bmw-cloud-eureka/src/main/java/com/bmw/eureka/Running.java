package com.bmw.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Administrator on 2018/10/8.
 */
@EnableEurekaServer //激活
@SpringBootApplication
public class Running {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Running.class).web(true).run(args);
    }
}
