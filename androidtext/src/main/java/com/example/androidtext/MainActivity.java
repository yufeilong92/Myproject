package com.example.androidtext;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.InputStream;
import java.net.HttpURLConnection;


import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private TextView tv_result;
    private EditText et_path;
    //开辟子线程
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String content1 = (String) msg.obj;
                    tv_result.setText(content1);
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_path = (EditText) findViewById(R.id.et_path);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    //4.0以上不能这样用 开辟子线程访问网络更新UI
    public void click(View v) {
        try {        //     、

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //  [1] 得到路劲
                    String path = et_path.getText().toString();
                    System.out.println("1>>>");
                    //       [2] 既定url路劲我们要访问的

                    URL url = null;
                    try {
                        url = new URL(path);
                        //        [3] 拿到httpURLconnectiond对象，用于发送或接受数据
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        System.out.println("2>>>>" + conn);
                        //        [3.1]设置取请求方式
                        conn.setRequestMethod("GET");
                        //        [4]设置请求超时时间
                        conn.setConnectTimeout(5000);
                        //        [5]设置服务器返回的状态码
                        int code = conn.getResponseCode();

                        //        [6]code==200说明请求成功
                        System.out.println("3>>" + code);
                        if (code == 200) {
                            /**
                             * [7]获取服务器返回的数据，以流的形式返回
                             * [7把流转换成字符串，使用一个工具utils
                             */
                            InputStream in = conn.getInputStream();
                            //        [7]使用我们定义的工具类把in转换成String
                            System.out.println(">>>>");
                            String content = StreamTools.ReadStream(in);
                            //        [7.]把流里面的数据展示到Textview 上
                            Message message = new Message();
                            message.obj = content;
                            message.what = 1;
                            handler.handleMessage(message);


                            // tv_result.setText(content);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
