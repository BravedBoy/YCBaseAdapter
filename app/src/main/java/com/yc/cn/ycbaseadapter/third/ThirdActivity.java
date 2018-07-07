package com.yc.cn.ycbaseadapter.third;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yc.cn.ycbaseadapter.R;
import com.yc.cn.ycbaseadapter.first.RecycleViewItemLine;
import com.yc.cn.ycbaseadapterlib.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/11/9.
 * 作者：PC
 */

public class ThirdActivity extends AppCompatActivity  {

    private List<ThirdBean> list;
    private RecyclerView recyclerView;
    private ThirdAdapter adapter;

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
        for(int a=0 ; a<13 ; a++){
            ThirdBean bean = new ThirdBean();
            if(a==3 || a==8 || a==10 || a==12){
                bean.setTitle("这个是假数据"+a);
                bean.setLocation(1);
            }else {
                bean.setTitle("这个是假数据"+a);
                bean.setLocation(2);
            }
            list.add(bean);
        }
    }


    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ThirdAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setData(list);
        RecycleViewItemLine line = new RecycleViewItemLine(this,LinearLayoutManager.HORIZONTAL,2, Color.GRAY);
        recyclerView.addItemDecoration(line);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                List<ThirdBean> data = adapter.getData();
                Toast.makeText(ThirdActivity.this,data.get(position).getTitle(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
}
