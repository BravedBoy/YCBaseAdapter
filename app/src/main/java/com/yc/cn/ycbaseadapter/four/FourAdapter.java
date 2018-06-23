package com.yc.cn.ycbaseadapter.four;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.yc.cn.ycbaseadapter.R;
import com.yc.cn.ycbaseadapterlib.BaseAdapter;
import com.yc.cn.ycbaseadapterlib.BaseViewHolder;
import com.yc.cn.ycbaseadapterlib.MultiTypeSupport;


public class FourAdapter extends BaseAdapter<FourBean> implements MultiTypeSupport<FourBean>{

    private Context mContent;

    public FourAdapter(Context context) {
        super(context, R.layout.main_chat_from_msg);
        this.mContent = context;
        //这句话一点要添加
        this.multiTypeSupport = this;
    }

    @Override
    protected void bindData(BaseViewHolder holder, FourBean s) {
        int location = s.getLocation();
        switch (location){
            case 1:     //处理头部布局逻辑
                holder.setText(R.id.tv_title,s.getTitle());
                holder.setOnClickListener(R.id.chat_from_icon, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContent,"字控件",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 2:     //文本逻辑处理
                holder.setText(R.id.tv_title,s.getTitle());
                break;
            case 3:     //图片逻辑处理
                holder.setText(R.id.tv_title,s.getTitle());
                break;
            case 4:     //处理底部布局逻辑
                holder.setText(R.id.tv_title,s.getTitle());
                break;
        }
    }

    @Override
    public int getLayoutId(FourBean item, int position) {
        if (item.getLocation()==1) {
            return R.layout.main_chat_from_msg;
        }else if(item.getLocation()==2){
            return R.layout.item_first;
        } else if(item.getLocation()==4){
            return R.layout.view_footer;
        }
        return R.layout.main_chat_send_msg;
    }
}
