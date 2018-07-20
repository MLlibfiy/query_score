package com.shujia;

import com.google.gson.Gson;
import com.shujia.bean.Message;
import org.junit.Test;

import java.util.HashMap;

public class TestLogin {


    @Test
    public void login(){
        String url = "http://localhost:8080/login";
        HashMap<String, String> map = new HashMap<>();
        map.put("username","李四");
        map.put("password","123");

        String s = HttpUtil.doPost(url, map);
        Gson gson = new Gson();
        Message message = gson.fromJson(s, Message.class);
        System.out.println(message);

    }

    /**
     * 访问百度
     */

    @Test
    public void testBaidu(){
        System.out.println("===================");
        String s = HttpUtil.doGet("http://www.baidu.com");
        System.out.println(s);
    }
}
