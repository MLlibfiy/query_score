package com.shujia.dao.impl;

import com.shujia.bean.User;
import com.shujia.dao.UserDao;
import com.shujia.util.DBUtils;
import sun.security.pkcs11.Secmod;

import java.util.List;

/**
 * 数据访问层
 */
public class UserDaoImpl implements UserDao {

    /**
     * 更具用户名密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public List<User> query(String username, String password) {
        String sql = "select name as username,password as password from user where name=? and password=?";
        List<User> users = DBUtils.select(sql, User.class, username, password);
        return users;
    }
    /**
     * 更具用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    @Override
    public List<User> query(String username) {
        String sql = "select name as username,password as password from user where name=?";
        List<User> users = DBUtils.select(sql, User.class, username);
        return users;
    }

    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    @Override
    public int insert(User user) {
        String sql ="insert into user(name,password) values(?,?)";
        int update = DBUtils.update(sql, user.getUsername(), user.getPassword());
        return update;
    }
}
