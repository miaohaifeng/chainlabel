package com.happy.video.controller;

import com.happy.video.pojo.SysUser;
import com.happy.video.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhonglq on 2019/1/10.
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserServiceImpl userService;

    @RequestMapping("index")
    public String index() {
        return "hello user";
    }

    @RequestMapping("/queryUserById")
    public SysUser selectUserById(int id) {
        System.out.println("id:" + id);
        SysUser user = userService.selectByPrimaryKey(id);
        System.out.println(user.toString());
        return user;
    }

}
