package com.yc.cn.ycbaseadapterlib.second.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.yc.cn.ycbaseadapterlib.R;
import com.yc.cn.ycbaseadapterlib.second.adapter.SimpleRvAdapter;
import com.yc.cn.ycbaseadapterlib.second.viewHolder.BaseRvViewHolder;



public class ErrorCell extends BaseStateCell {

    public ErrorCell(Object o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return SimpleRvAdapter.ERROR_TYPE;
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {

    }

    @Override
    protected View getDefaultView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.rv_error_layout,null);
    }
}
