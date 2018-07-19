package com.shujia.service;

import com.shujia.bean.Student_score;

import java.util.List;


/**
 * 学生业务层
 *
 */
public interface StudentService {

    //根据学生名称查询学生分数方法
    public List<Student_score> queryScore(String name);
}
