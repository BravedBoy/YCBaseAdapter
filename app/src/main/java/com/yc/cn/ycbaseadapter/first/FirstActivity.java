package com.yc.cn.ycbaseadapter.first;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yc.cn.ycbaseadapter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/11/9.
 * 作者：PC
 */

public class FirstActivity extends AppCompatActivity {

    private List<FirstBean> list;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initData();
        initRecycleView();
    }

    private void initData() {
        list = new ArrayList<>();
        for(int a=0 ; a<20 ; a++){
            FirstBean bean = new FirstBean();
            bean.setTitle("这个是假数据"+a);
            list.add(bean);
        }
    }


    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirstAdapter adapter = new FirstAdapter(list, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        RecycleViewItemLine line = new RecycleViewItemLine(this,LinearLayoutManager.HORIZONTAL,2, Color.GRAY);
        recyclerView.addItemDecoration(line);
    }


}
