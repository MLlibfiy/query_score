package com.shujia.Controller;

import com.shujia.bean.User;
import com.shujia.service.UserService;
import com.shujia.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * （控制层）
 *
 */

@RestController
public class UserController {


    //初始化业务层
    private UserService userService = new UserServiceImpl();


    @PostMapping("/login")
    public String login(User user){

        boolean login = userService.login(user);

        if (login){
            return "登录成功";
        }else {

            return "用户名密码错误";
        }
    }
}
