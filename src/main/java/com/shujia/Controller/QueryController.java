package com.shujia.Controller;


import com.shujia.bean.Student_score;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询分数控制器
 *
 */

@RestController
public class QueryController {
    //[学号，姓名，班级，科目，分数]
    @GetMapping("/query")
    public List<Student_score> queryScore(String name){
        //拼接sql
        String sql = "select a.id,a.`name`,a.clazz,c.`name` as name1,b.score from " +
                "student as a join score as b on a.id=b.student_id " +
                "join cource as c on b.cource_id=c.id  " +
                "where a.name=?";

        //加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Student_score> lists = new ArrayList<>();
        Connection con =null;
        PreparedStatement stat = null;
        try {
            //创建连接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root", "123");

            //创建预编译sql执行器
            stat = con.prepareStatement(sql);
            //设置参数
            stat.setString(1,name);
            //执行sql
            ResultSet resultSet = stat.executeQuery();
            //遍历结果，构建自定义对象
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name1 = resultSet.getString("name");
                String clazz = resultSet.getString("clazz");
                String coreceName = resultSet.getString("name1");
                int score = resultSet.getInt("score");

                Student_score student_score = new Student_score(id, name1, clazz, coreceName, score);
                lists.add(student_score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            if (con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stat!=null){
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        //spring boot自动把集合转换成json字符串返回
        return lists;
    }
}
