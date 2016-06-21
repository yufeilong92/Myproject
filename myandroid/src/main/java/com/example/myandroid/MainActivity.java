package com.example.myandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText et_user;
    private EditText et_password;
    private Button btn_post, btn_get;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//       找到关心的控件
        et_user = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_post = (Button) findViewById(R.id.btn_post);
    }

    public void click1(View v) {
        //[☆]获取用户信息和密码
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    String name = et_user.getText().toString().trim();
                    String pwd = et_password.getText().toString().trim();
                    //0[☆]//[☆]//[☆]//[☆]和GET方式提交数据区别设置请求方式Post设置请求体
                    //[☆]小细节如果提交要对name和pwd进行一个urlencode编码
                    String  data="username="+ URLEncoder.encode(name,"utf-8")+"&password="+URLEncoder.encode(pwd,"utf-8")+"";
                    //[☆]定义get方式提交
                   System.out.println("name:"+name+"pwd:"+pwd);
                    //[☆]//[☆]//[☆]//[☆]和GET方式提交数据区别路径不同
                    String path = "http://192.168.1.125:8080/Servlet/LoginServlet";
                    System.out.println(path);
                    //[☆]获取路径
                    URL url = new URL(path);
                    //[☆]h获取HttpURLConnection对象，用于发送解说数据
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //[☆]设置请求头
                    //1[☆]//[☆]//[☆]//[☆]
                    conn.setRequestMethod("POST");

                    //[☆]设置时间
                    conn.setConnectTimeout(5000);
                    //2[☆]//[☆]//[☆]//[☆]和GET方式提交数据区别设置请求方式Post设置请2求头信息
                    conn.setRequestProperty("Content-type","application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length",data.length()+"");
                    //4[☆]//[☆]//[☆]//[☆]和GET方式提交数据区别设置请求方式Post以流的形式提交到服务器
                    conn.setDoOutput(true);//设置标记允许输出
                    conn.getOutputStream().write(data.getBytes());//请求体以流的形式写到服务器
                    //[☆]获取浏览器响应
                    int code = conn.getResponseCode();
                    //[☆]判断其值
                    if (code == 200) {
                        //[☆]获取服务器返回数据，以流形式返回，把流转换字符串
                        InputStream in = conn.getInputStream();
                        //[☆]把inputStream转换String
                        String content=StreamTools.ReadStream(in);
                        //[☆]把服务器返回数据显示到Toast上，不能再子线程显示
                        showToast(content);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void click2(View v) {

    }
    //[☆]封装toast方法，该toast方法放到主线程
    public void  showToast(final  String content){
        //[☆]无论你在哪里到用该方法，都会在主线程


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),content,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
