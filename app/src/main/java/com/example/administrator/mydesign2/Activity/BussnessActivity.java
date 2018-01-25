package com.example.administrator.mydesign2.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.mydesign2.R;

public class BussnessActivity extends AppCompatActivity {
private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussness);
        btn1=(Button)findViewById(R.id.btn1);
        //给btn1绑定事件点击
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //给tn1添加点击响应事件
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                Intent intent=new Intent( BussnessActivity.this,BuapplicationActivity.class);
                //启动
                startActivity(intent);

            }
        });
    }
}
