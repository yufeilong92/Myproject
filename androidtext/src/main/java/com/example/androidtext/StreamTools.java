package com.example.androidtext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/6/14.
 */
public class StreamTools {
    //        [1]把inputStream 转换成String

    public static String ReadStream(InputStream in) throws IOException {
//        [2]定义一个内存输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int len = -1;
        byte[] buffer = new byte[1024];//kb
        while ((len =in.read(buffer))!= -1) {
            baos.write(buffer, 0, len);
        }
        //        [3]构造方法
        in.close();
        String content=new String(baos.toByteArray());
        return content;
    }
}
