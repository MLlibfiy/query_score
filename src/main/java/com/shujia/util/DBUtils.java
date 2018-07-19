package com.shujia.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 通过反射加泛型实现的封装
 *
 *
 */

public class DBUtils {

    private static  String driver = "";
    private static  String url = "";
    private static  String user = "";
    private static  String password = "";

    static{
        try {
            //创建Properties对象
            Properties properties = new Properties();
            //通过类加载器加载properties文件
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("DButils.properties"));
            //得到属性
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("username");
            password = properties.getProperty("password");
            //加载驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return con;

    }
    public static void closeAll(ResultSet rs , Statement stmt,Connection con){
        try {
            if(rs!=null)rs.close();

            if(stmt!=null)stmt.close();

            if(con!=null)con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询
     * @param sql
     * @param c  持久化对象类型
     *
     * @return
     */

    public static  <T> List<T> select(String sql, Class<T> c, Object... parames) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<T>();
        try{
            con = getConnection();//得到连接
            stmt = con.prepareStatement(sql);//得到预执行sql语句
            for (int i = 0; i < parames.length; i++) {
                stmt.setObject(i+1, parames[i]);//传递参数
            }
            rs = stmt.executeQuery();//得到返回结果
            //得到所有的成员变量
            Field [] fields = c.getDeclaredFields();
            while(rs.next()){
                //得到泛型实例
                T t = c.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    //得到成员变量设值的方法字符串
                    String methodStr = "set"+fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
                    //得到成员变量设值的方法
                    @SuppressWarnings("unchecked")
                    Method method = c.getMethod(methodStr, (Class<Object>)fields[i].getGenericType());
                    //调用方法
                    Object Column = null;
                    try {
                        //解决空字段抛异常问题
                        Column = rs.getObject(fields[i].getName());
                        method.invoke(t,Column);
                    } catch (Exception e) {
                        System.out.println(fields[i].getName()+"字段在数据库中不存在");
                        //e.printStackTrace();
                    }
                }
                list.add(t);
            }
        }catch(SQLException e){
            e.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            closeAll(rs, stmt, con);//关闭连接
        }
        return list;
    }
    /**
     * 增加，删除，修改
     * @param sql
     * @param obj
     * @return
     */
    public static  int update(String sql,Object...parames){
        //得到连接
        Connection con = null;
        PreparedStatement stmt  = null;
        int result = 0 ;
        //创建预编译sql语句
        try {
            con = getConnection();

            stmt= con.prepareStatement(sql);

            for (int i = 0; i < parames.length; i++) {
                stmt.setObject(i+1, parames[i]);
            }
            //提示执行
            result = stmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            //关闭连接
            closeAll(null, stmt, con);
        }


        return result;

    }
}
