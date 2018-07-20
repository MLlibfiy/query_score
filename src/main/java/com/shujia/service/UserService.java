package com.shujia.service;

import com.shujia.bean.User;

/**
 * 业务层
 *
 */
public interface UserService {

    /**
     * 用户登录的方法
     *
     * @param user 用户
     * @return
     */
    public boolean login(User user);

}
