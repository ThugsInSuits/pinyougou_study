package com.pinyougou.manager.controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    /***
     * 获取用户登录名字
     * @return
     */
    @GetMapping("/name")
    public Map showName(){
        //得到登陆人账号
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map=new HashMap<>();
        map.put("loginName", name);
        return map;
    }
}
