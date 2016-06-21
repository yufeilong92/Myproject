package com.example.myandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private Button btn_post;
    private Button btn_get;
    private EditText et_password;
    private EditText et_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_user = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_post = (Button) findViewById(R.id.btn_post);
    }
    public void click1(View v){

        try {
        String name =et_user.getText().toString().trim();
        String pwd=et_password.getText().toString().trim();

            String path="http://192.168.1.125:8080/Servlet/LoginServlet?username="+ URLEncoder.encode(name,"utf-8")+"" +
                    "password="+URLEncoder.encode(pwd,"utf-8");
            //[☆]获取HttpClient实例
            DefaultHttpClient client = new DefaultHttpClient();


        } catch (Exception e) {
                e.printStackTrace();
        }

    }
    public void click2(View v){

    }


  
}
