package com.yc.cn.ycbaseadapter.third;

import android.content.Context;

import com.yc.cn.ycbaseadapter.R;
import com.yc.cn.ycbaseadapterlib.first.BaseAdapter;
import com.yc.cn.ycbaseadapterlib.first.BaseViewHolder;
import com.yc.cn.ycbaseadapterlib.first.MultiTypeSupport;


public class ThirdAdapter extends BaseAdapter<ThirdBean> implements MultiTypeSupport<ThirdBean>{


    public ThirdAdapter(Context context) {
        super(context, R.layout.main_chat_from_msg);
        //这句话一点要添加
        this.multiTypeSupport = this;
    }

    @Override
    protected void bindData(BaseViewHolder holder, ThirdBean s) {
        holder.setText(R.id.tv_title,s.getTitle());
        //TextView view = holder.getView(R.id.tv_title);
        //view.setText(s);
    }

    @Override
    public int getLayoutId(ThirdBean item, int position) {
        if (item.getLocation()==1) {
            return R.layout.main_chat_from_msg;
        }
        return R.layout.main_chat_send_msg;
    }
}
