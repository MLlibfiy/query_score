package com.shujia.dao.impl;

import com.shujia.bean.Student_score;
import com.shujia.dao.StudentDao;
import com.shujia.util.DBUtils;

import java.util.List;

/**
 * 数据访问层
 *
 */
public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student_score> queryScore(String name) {

        //拼接sql
        String sql = "select a.id,a.`name`,a.clazz,c.`name` as cource,b.score from " +
                "student as a join score as b on a.id=b.student_id " +
                "join cource as c on b.cource_id=c.id  " +
                "where a.name=?";

        List<Student_score> select = DBUtils.select(sql, Student_score.class, name);

        return select;
    }
}
