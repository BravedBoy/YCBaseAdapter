package com.yc.cn.ycbaseadapter.second;

import android.content.Context;

import com.yc.cn.ycbaseadapter.R;



public class SecondAdapter extends BaseAdapter<SecondBean> {

    private BaseViewHolder.onItemCommonClickListener commonClickListener;

    public SecondAdapter(Context context) {
        super(context,R.layout.item_first);

    }

    public SecondAdapter(Context context, BaseViewHolder.onItemCommonClickListener commonClickListener) {
        super(context, R.layout.item_first);
        this.commonClickListener = commonClickListener;
    }

    @Override
    protected void bindData(BaseViewHolder holder, SecondBean s) {
        holder.setText(R.id.tv_title,s.getTitle());
        //TextView view = holder.getView(R.id.tv_title);
        //view.setText(s);
        holder.setCommonClickListener(commonClickListener);
    }

}
