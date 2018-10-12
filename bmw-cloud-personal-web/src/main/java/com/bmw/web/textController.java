package com.bmw.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2018/10/8.
 */
@Controller
@RequestMapping("text/text_bmw")
public class textController {
    /**
     * 测试
     * @author jmy
     * 2018年05月24日
     * @throws
     */
    @RequestMapping(value="neighborhoodList/{jsonParams}",method = RequestMethod.GET)
    public String commodityList(@PathVariable("jsonParams") String jsonParams) {
        return "/text/text_bmw/index.html";
//        return "123123";
    }
}
