package com.shujia.service.impl;

import com.shujia.bean.Student_score;
import com.shujia.dao.StudentDao;
import com.shujia.dao.impl.StudentDaoImpl;
import com.shujia.service.StudentService;

import java.util.List;


/**
 * 服务层
 *
 */

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student_score> queryScore(String name) {

        List<Student_score> student_scores = studentDao.queryScore(name);

        return student_scores;
    }
}
