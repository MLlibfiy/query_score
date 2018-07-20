package com.shujia.service.impl;

import com.shujia.bean.User;
import com.shujia.dao.UserDao;
import com.shujia.dao.impl.UserDaoImpl;
import com.shujia.service.UserService;
import com.shujia.util.MD5Util;

import java.util.List;


/**
 * 业务层实现类
 */
public class UserServiceImpl implements UserService {

    //初始化数据访问层
    private UserDao userDao = new UserDaoImpl();


    @Override
    public boolean login(User user) {


        //登录之前对密码加密处理
        user.setPassword(MD5Util.getMD5(user.getPassword()));

        //根据用户名密码查询
        List<User> users = userDao.query(user.getUsername(), user.getPassword());

        //判断是否查询到用户
        return !users.isEmpty() && users.get(0)!=null;
    }

    /**
     * 用户注册
     * @param user 用户
     * @return
     */
    @Override
    public boolean register(User user) {


        //判断用户名是否存在
        List<User> users = userDao.query(user.getUsername());

        if (!users.isEmpty()){
            //用户名已存在
            return false;
        }

        //对密码做加密处理
        user.setPassword(MD5Util.getMD5(user.getPassword()));

        //注册用户
        int insert = userDao.insert(user);

        if(insert>0){
            return true;
        }

        return false;
    }


}
