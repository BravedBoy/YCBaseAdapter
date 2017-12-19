package com.yc.cn.ycbaseadapter.five;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yc.cn.ycbaseadapter.R;
import com.yc.cn.ycbaseadapter.first.RecycleViewItemLine;
import com.yc.cn.ycbaseadapterlib.second.adapter.SimpleRvAdapter;
import com.yc.cn.ycbaseadapterlib.second.inter.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/11/9.
 * 作者：PC
 */

public class FiveActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private SimpleRvAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initRecycleView();
    }



    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SimpleRvAdapter();
        recyclerView.setAdapter(adapter);
        adapter.showLoading();
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.hideLoading();
                List<FiveBean> fiveBeen = mockMoreData();
                adapter.setData(getCells(fiveBeen));
            }
        },2000);
        RecycleViewItemLine line = new RecycleViewItemLine(this,LinearLayoutManager.HORIZONTAL,2, Color.GRAY);
        recyclerView.addItemDecoration(line);
    }

    public static List<FiveBean> mockMoreData(){
        List<FiveBean> entries = new ArrayList<>();
        FiveBean entry;
        for(int i=0;i<6;i++){
            entry = new FiveBean();
            if(i==0){
                entry.setLocation(0);               //图片
                FiveBean.Banner banner = new FiveBean.Banner();
                banner.setTitle("图片");
                entry.setBanner(banner);
            }else if (i==1){
                entry.setLocation(1);               //文本
                FiveBean.Top top = new FiveBean.Top();
                top.setTitle("文本");
                entry.setTop(top);
            }else if(i==2){
                entry.setLocation(2);               //图片
                FiveBean.FirstList firstList = new FiveBean.FirstList();
                firstList.setTitle("图片");
                entry.setFirstList(firstList);
            }else if(i==3){
                entry.setLocation(3);               //专辑
                FiveBean.Inv inv = new FiveBean.Inv();
                inv.setTitle("专辑");
                entry.setInv(inv);
            }else if(i==4){
                entry.setLocation(4);               //会议
                FiveBean.SecondList secondList = new FiveBean.SecondList();
                secondList.setTitle("会议");
                entry.setSecondList(secondList);
            }else if(i==5){
                entry.setLocation(5);               //底部
                FiveBean.Footer footer = new FiveBean.Footer();
                footer.setTitle("底部");
                entry.setFooter(footer);
            }
            entries.add(entry);
        }
        return entries;
    }


    protected List<Cell> getCells(List<FiveBean> entries){
        //根据实体生成Cell
        List<Cell> cells = new ArrayList<>();
        for (int i=0;i<entries.size();i++){
            FiveBean entry = entries.get(i);
            if(entry.getLocation() == 0){
                cells.add(new BannerCell(entry.getBanner()));
            }else if(entry.getLocation() == 1){
                cells.add(new TopCell(entry.getTop()));
            }else if(entry.getLocation() == 2){
                cells.add(new FirstCell(entry.getFirstList()));
            }else if(entry.getLocation() == 3){
                cells.add(new InvCell(entry.getInv()));
            }else if(entry.getLocation() == 4){
                cells.add(new SecondCell(entry.getSecondList()));
            }else if(entry.getLocation() == 5){
                cells.add(new FootCell(entry.getFooter()));
            }
        }
        return cells;
    }

}
