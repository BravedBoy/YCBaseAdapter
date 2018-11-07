package com.yc.cn.ycbaseadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yc.cn.ycbaseadapter.first.FirstActivity;
import com.yc.cn.ycbaseadapter.five.FiveActivity;
import com.yc.cn.ycbaseadapter.four.FourActivity;
import com.yc.cn.ycbaseadapter.second.SecondActivity;
import com.yc.cn.ycbaseadapter.third.ThirdActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_1).setOnClickListener(this);
        findViewById(R.id.tv_2).setOnClickListener(this);
        findViewById(R.id.tv_3).setOnClickListener(this);
        findViewById(R.id.tv_4).setOnClickListener(this);
        findViewById(R.id.tv_5).setOnClickListener(this);
        findViewById(R.id.tv_6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_1:
                startActivity(new Intent(this, FirstActivity.class));
                break;
            case R.id.tv_2:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.tv_3:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
            case R.id.tv_4:
                startActivity(new Intent(this, FourActivity.class));
                break;
            case R.id.tv_5:
                startActivity(new Intent(this, FiveActivity.class));
                break;
            case R.id.tv_6:
                test1();
                break;
        }
    }


    private final Object obj1 = new Object();
    private final Object obj2 = new Object();
    private void test1() {
        new Thread(){
            @Override
            public void run() {
                synchronized (obj1){
                    try {
                        System.out.println("yc---Thread1 obj1");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (obj2){
                        System.out.println("yc---Thread1 obj2");
                    }
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                synchronized (obj2){
                    System.out.println("yc---Thread2 obj2");
                    synchronized (obj1){
                        System.out.println("yc---Thread2 obj1");
                    }
                }
            }
        }.start();
    }


}
