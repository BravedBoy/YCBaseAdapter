package com.yc.cn.ycbaseadapter.second;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yc.cn.ycbaseadapter.R;
import com.yc.cn.ycbaseadapter.first.RecycleViewItemLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/11/9.
 * 作者：PC
 */

public class SecondActivity extends AppCompatActivity implements BaseViewHolder.onItemCommonClickListener{

    private List<SecondBean> list;
    private RecyclerView recyclerView;
    private SecondAdapter adapter;

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
            SecondBean bean = new SecondBean();
            bean.setTitle("这个是假数据"+a);
            list.add(bean);
        }
    }


    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SecondAdapter(this,this);
        recyclerView.setAdapter(adapter);
        adapter.setData(list);
        RecycleViewItemLine line = new RecycleViewItemLine(this,LinearLayoutManager.HORIZONTAL,2, Color.GRAY);
        recyclerView.addItemDecoration(line);
    }


    @Override
    public void onItemClickListener(int position) {
        List<SecondBean> data = adapter.getData();
        Toast.makeText(this,data.get(position).getTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClickListener(int position) {
        Toast.makeText(this,"长按",Toast.LENGTH_SHORT).show();
    }
}
