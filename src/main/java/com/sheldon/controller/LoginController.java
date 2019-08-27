package com.sheldon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Sheldon on 2019/8/26.
 * Project Name: sso.
 * Package Name: com.sheldon.controller.
 */

@Controller
public class LoginController {

    // 登录页面跳转
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    // 登录处理
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String logins(@RequestParam String id, @RequestParam String passwd){
        return null;
    }
}
