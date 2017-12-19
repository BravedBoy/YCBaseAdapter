package com.yc.cn.ycbaseadapter.second;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2016/3/9
 * 描    述：RecycleView的adapter抽取类
 * 修订历史：
 * ================================================
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private int layoutId;
    private List<T> data;

    /**
     * 构造方法
     * @param layoutId      布局
     * @param context       上下文
     */
    public BaseAdapter(Context context , int layoutId) {
        this.layoutId = layoutId;
        this.context = context;
        data = new ArrayList<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(data!=null && data.size()>0){
            bindData(holder, data.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return data==null ? 0 : data.size();
    }


    /**
     * 当子类adapter继承此BaseAdapter时，需要子类实现的绑定数据方法
     */
    protected abstract void bindData(BaseViewHolder holder, T t);

    /**
     * 设置数据，并且刷新页面
     */
    public void setData(List<T> list){
        data.clear();
        if(list==null || list.size()==0){
            return;
        }
        data.addAll(list);
        notifyItemRangeChanged(data.size()-list.size(),data.size());
        notifyDataSetChanged();
    }

    /**
     * 获取数据
     */
    public List<T> getData(){
        return data;
    }

    /**
     * 清理所有数据，并且刷新页面
     */
    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    /**
     * 移除数据
     */
    public void remove(T t){
        if(data.size()==0){
            return;
        }
        int index = data.indexOf(t);
        remove(index);
    }


    /**
     * 移除数据
     */
    public void remove(int index){
        if(data.size()==0){
            return;
        }
        data.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * 移除数据
     */
    public void remove(int start,int count){
        if(data.size()==0){
            return;
        }
        if((start +count) > data.size()){
            return;
        }
        data.subList(start,start+count).clear();
        notifyItemRangeRemoved(start,count);
    }


}
