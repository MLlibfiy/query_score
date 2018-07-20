package com.shujia.service.impl;

import com.shujia.bean.User;
import com.shujia.dao.UserDao;
import com.shujia.dao.impl.UserDaoImpl;
import com.shujia.service.UserService;

import java.util.List;


/**
 * 业务层实现类
 */
public class UserServiceImpl implements UserService {

    //初始化数据访问层
    private UserDao userDao = new UserDaoImpl();


    @Override
    public boolean login(User user) {

        //根据用户名密码查询
        List<User> users = userDao.query(user.getUsername(), user.getPassword());

        //判断是否查询到用户
        return !users.isEmpty() && users.get(0)!=null;
    }
}
