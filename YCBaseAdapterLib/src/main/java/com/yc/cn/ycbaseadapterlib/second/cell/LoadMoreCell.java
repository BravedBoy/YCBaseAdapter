package com.yc.cn.ycbaseadapterlib.second.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.yc.cn.ycbaseadapterlib.R;
import com.yc.cn.ycbaseadapterlib.second.adapter.SimpleRvAdapter;
import com.yc.cn.ycbaseadapterlib.second.viewHolder.BaseRvViewHolder;



public class LoadMoreCell extends BaseStateCell {

    public static final int mDefaultHeight = 80;    //dp
    public LoadMoreCell(Object o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return SimpleRvAdapter.LOAD_MORE_TYPE;
    }


    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {

    }

    @Override
    protected View getDefaultView(Context context) {
        // 设置LoadMore View显示的默认高度
        setHeight(dpToPx(context,mDefaultHeight));
        return LayoutInflater.from(context).inflate(R.layout.rv_load_more_layout,null);
    }


    private int dpToPx(Context context, float dip) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        return (int) (dip * SCALE + 0.5f);
    }

}
