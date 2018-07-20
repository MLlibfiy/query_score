package com.shujia.Controller;

import com.shujia.bean.Message;
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
    public Message login(User user){

        boolean login = userService.login(user);

        if (login){
            //spring boot会自动将对象转换成json字符串
            return new Message("success","登录成功");
        }else {

            return  new Message("error","用户名密码错误");
        }
    }


    @PostMapping("/register")
    public Message register(String username,String password,String newPassword){

        //判断两次密码是否相同
        if (!password.equals(newPassword)){

            return  new Message("error","两次密码输入不一致");
        }

        //注册
        boolean register = userService.register(new User(username, password));

        if (register){
            return new Message("success","注册成功");
        }else {
            return new Message("error","用户名已存在");
        }
    }
}
