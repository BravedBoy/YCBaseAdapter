package com.yc.cn.ycbaseadapter.second;

import android.content.Context;

import com.yc.cn.ycbaseadapter.R;
import com.yc.cn.ycbaseadapterlib.adapter.BaseAdapter;
import com.yc.cn.ycbaseadapterlib.adapter.BaseViewHolder;


public class SecondAdapter extends BaseAdapter<SecondBean> {

    public SecondAdapter(Context context) {
        super(context,R.layout.item_first);
    }


    @Override
    protected void bindData(BaseViewHolder holder, SecondBean s) {
        holder.setText(R.id.tv_title,s.getTitle());
        //TextView view = holder.getView(R.id.tv_title);
        //view.setText(s);
    }
}
