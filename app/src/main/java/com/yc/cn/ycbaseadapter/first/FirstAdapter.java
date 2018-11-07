package com.yc.cn.ycbaseadapter.first;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yc.cn.ycbaseadapter.R;

import java.util.List;


public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.MyViewHolder> {

    private Context context;
    private List<FirstBean> list;
    private OnItemClickListener mItemClickListener;

    public FirstAdapter(List<FirstBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    private int a , b;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("yangchong","onCreateViewHolder"+ a++);
        View view = LayoutInflater.from(context).inflate(R.layout.item_first, parent, false);
        MyViewHolder holder = new MyViewHolder(view, mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Log.e("yangchong","onBindViewHolder"+ b++);
        if(list!=null && list.size()>0){
            holder.tv_title.setText(list.get(position).getTitle());
        }
    }


    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_title;
        private OnItemClickListener mListener;

        MyViewHolder(View view, OnItemClickListener listener) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.mListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

}
