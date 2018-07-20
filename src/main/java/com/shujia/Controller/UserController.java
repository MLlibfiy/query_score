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


    @PostMapping("/register")
    public String register(String username,String password,String newPassword){

        //判断两次密码是否相同
        if (!password.equals(newPassword)){
            return "两次密码输入不一致";
        }

        //注册
        boolean register = userService.register(new User(username, password));

        if (register){
            return "注册成功";
        }else {
            return "用户已存在";
        }
    }
}
