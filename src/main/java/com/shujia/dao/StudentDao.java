package com.shujia.dao;


import com.shujia.bean.Student_score;

import java.util.List;

/**
 * 访问学生数据的接口
 *
 */
public interface StudentDao {


    //根据学生名称查询学生分数方法
    public List<Student_score> queryScore(String name);
}
