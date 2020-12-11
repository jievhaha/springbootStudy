package com.jievhaha.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class LoginController {
    @PostMapping("/user/login")
    public String login(@RequestParam("username")String username, @RequestParam("password")String password, Map<String,Object> maps, HttpSession session){
        if(!StringUtils.isEmpty(username) && "111111".equals(password)){
            session.setAttribute("loginUser",username);
            //防重复提交，重定向
            return "redirect:/main.html";
        }
        maps.put("msg","用户名或者密码错误，请重新登录。");
        return "login";
    }
}
