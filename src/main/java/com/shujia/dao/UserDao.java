package com.shujia.dao;

import com.shujia.bean.User;

import java.util.List;

/**
 * 用户数据访问层
 *
 */
public interface UserDao {

    /**
     * 查询
     *
     * 根据用户名和密码查询用户
     */

    public List<User> query(String username, String password);


    /**
     *根据用户名查询用户
     */
    public List<User> query(String username);


    /**
     * 增加
     *
     * 插入用户
     */

    public int insert(User user);



}
