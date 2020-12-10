package com.jievhaha.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class WebController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @RequestMapping("/success")
    public String success(Map<String, Object> maps){
        maps.put("hello", "<h5 style='color:red'>您好</h5>");
        maps.put("users", Arrays.asList("张三", "李四", "王五", "赵柳"));
        return "success";
    }
}
