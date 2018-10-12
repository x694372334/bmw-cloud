package com.bmw.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/10/8.
 */
@RestController
@RequestMapping("property/neighborhood")
public class textController {

    /**
     * 测试
     * @author jmy
     * 2018年05月24日
     * @throws
     */
    @RequestMapping(value="neighborhoodList/{jsonParams}",method = RequestMethod.GET)
    public String commodityList(@PathVariable("jsonParams") String jsonParams) {
//        return "/property/neighborhood/123.html";
        return "123123";
    }
}
