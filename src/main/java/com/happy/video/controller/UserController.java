package com.happy.video.controller;

import com.happy.video.pojo.User;
import com.happy.video.service.impl.UserServiceImpl;
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
    private UserServiceImpl userService;

    @RequestMapping("index")
    public String index() {
        return "hello user";
    }

    @RequestMapping("/queryUserById")
    public User selectUserById(int id) {
        System.out.println("id:" + id);
        User user = userService.selectByPrimaryKey(id);
        System.out.println(user.toString());
        return user;
    }

}
