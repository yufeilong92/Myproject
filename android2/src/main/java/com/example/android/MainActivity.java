package com.example.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button btn ,btn1,btn2,btn3,btn4,btn5;
    private ButtonLinstener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listener=new ButtonLinstener();
        FindView();
        SetClickListtner();
    }

    private void FindView() {
        btn= (Button) findViewById(R.id.btn);
        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        btn3= (Button) findViewById(R.id.btn3);
        btn4= (Button) findViewById(R.id.btn4);
        btn5= (Button) findViewById(R.id.btn5);
    }
    private void SetClickListtner(){
        btn.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
    }

    private class ButtonLinstener  implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn:
                    Intent intent= new Intent(MainActivity.this,IndexActivtiy.class);
                    startActivity(intent);
                    Log.e("进程查询", "跳转IndexAvtivity" );
                break;
                case R.id.btn1:
                    Intent intent1=new Intent(MainActivity.this,IndexActivtiy.class);
                    startActivity(intent1);
                    break;
            }
        }
    }
}
