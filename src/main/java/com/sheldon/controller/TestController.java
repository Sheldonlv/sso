package com.sheldon.controller;

import com.sheldon.util.ResultData;
import com.sheldon.util.ResultUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Sheldon on 2019/8/24.
 * Project Name: sso.
 * Package Name: com.sheldon.controller.
 */
@Controller
@RequestMapping("sso")
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    /**
     * 系统登录
     * @param name
     * @param passwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResultData login(String name, String passwd, HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("ticket"));
//        System.out.println(session.getId());
        if (StringUtils.isEmpty(name)){
            return ResultUtil.error("账号为空");
        }
        if (StringUtils.isEmpty(passwd)){
            return ResultUtil.error("密码为空");
        }
        return ResultUtil.success("yes");
    }

    @ResponseBody
    @RequestMapping(value = "/test1")
    public String test(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("ticket","123");
        // 设置cookie
        Cookie cookie = new Cookie("token","zxcv");
        response.addCookie(cookie);
        return "123";
    }

    @ResponseBody
    @RequestMapping(value = "/test2")
    public String test2(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("ticket","456");
        return "456";
    }

    @RequestMapping(value = "/redirect")
    public String redirect(){
        return "redirect:http://localhost:8000/";
    }


}
