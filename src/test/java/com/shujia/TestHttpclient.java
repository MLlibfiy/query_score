package com.shujia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shujia.bean.Student_score;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TestHttpclient {

    /**
     * 模拟浏览器发送请求，发送get请求
     *
     * @throws IOException
     */

    @Test
    public void testGet() throws IOException {
        HttpClient client = new DefaultHttpClient();
        //发送get请求
        HttpGet request = new HttpGet("http://localhost:8080/query?name=单乐蕊");
        HttpResponse response = client.execute(request);
        /**请求发送成功，并得到响应**/
        //response.getStatusLine().getStatusCode()  获取状态码  200表示请求成功，404，表示网页不存在，500表示服务器错误
        if (response.getStatusLine().getStatusCode() == 200) {
            /**读取服务器返回过来的json字符串数据**/
            String strResult = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            //将json数组转换成java里面的集合对象
            ArrayList<Student_score> list = gson.fromJson(strResult,new TypeToken<ArrayList<Student_score>>(){}.getType());
            for (Student_score student_score : list) {
                System.out.println(student_score);
            }
        }
    }

    @Test
    public void testPost(){

        String url="http://localhost:8080/login";

        HashMap<String, String> params = new HashMap<>();
        params.put("username","李四");
        params.put("password","123");

        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));

                //System.out.println(name +"-"+value);
            }
            request.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));

            //发送请求，得到相应
            HttpResponse response = client.execute(request);
            //获取响应码
            int code = response.getStatusLine().getStatusCode();
            if(code == 200){	//请求成功

                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));

                //可变长度字符串
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();

                System.out.println(sb.toString());
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
