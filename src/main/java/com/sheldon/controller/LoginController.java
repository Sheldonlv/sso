package com.sheldon.controller;

import com.sheldon.pojo.User;
import com.sheldon.service.UserService;
import com.sheldon.util.ResultData;
import com.sheldon.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
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

    @Autowired
    UserService userService;

    // 登录页面跳转
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登录处理
     * 1.检测用户账户信息是否正确
     * 2.生成一个令牌 ticket，并将其与用户信息存储到 redis 中
     * 3.将 ticket 返回到客户端（浏览器）
     * @param id
     * @param passwd
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultData logins(@RequestParam String id, @RequestParam String passwd){
        // 数据库获取账号密码
        User user = userService.login(id, passwd);
        // 验证账号
        if (user != null){
            // 设置 ticket
            String ticket = DigestUtils.md5DigestAsHex(id.getBytes());
            // 将 ticket 信息存储到缓存中
            userService.addTicket(ticket, user);
            // 返回 ticket
            return ResultUtil.success(ticket);
        }
        return ResultUtil.error("该账号不存在");
    }

    /**
     * 验证账户登录状态
     * @param ticket
     * @return
     */
    @RequestMapping(value = "isLogin", method = RequestMethod.POST)
    public ResultData isLogin(@RequestParam String ticket){
        if (userService.getUserByTicket(ticket) != null){
            return ResultUtil.success("账户已登录", null);
        }
        return ResultUtil.error("账户未登录");
    }

    /**
     * 退出登录状态（由子系统发起）
     * @param ticket
     * @return
     */
    public ResultData loginOut(@RequestParam String ticket){
        if (userService.getUserByTicket(ticket) == null){
            return ResultUtil.success("账户不存在", null);
        }
        if (userService.delTicket(ticket)){
            return ResultUtil.success("账户已退出登录状态", null);
        }
        return ResultUtil.error("退出登录失败");
    }
}
