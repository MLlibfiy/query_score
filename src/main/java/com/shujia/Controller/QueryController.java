package com.shujia.Controller;


import com.shujia.bean.Student_score;
import com.shujia.service.StudentService;
import com.shujia.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询分数控制器
 *
 * 控制层
 *
 */

@RestController
public class QueryController {

    private StudentService studentService = new StudentServiceImpl();


    //[学号，姓名，班级，科目，分数]
    @GetMapping("/query")
    public List<Student_score> queryScore(String name){

        List<Student_score> student_scores = studentService.queryScore(name);

        return student_scores;
    }
}
