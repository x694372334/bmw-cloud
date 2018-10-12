package com.bmw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jinmy on 2018/10/7
 * project:Bmw站Pc后台端
 */
@RestController
@SpringBootApplication
public class PersonalRun {
    public static void main(String[] args) {
        SpringApplication.run(PersonalRun.class, args);
    }
}
