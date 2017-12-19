package com.yc.cn.ycbaseadapterlib.second.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.yc.cn.ycbaseadapterlib.second.cell.BaseRvCell;
import com.yc.cn.ycbaseadapterlib.second.viewHolder.BaseRvViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2016/5/9
 * 描    述：RecycleView的adapter抽取类
 * 修订历史：
 * ================================================
 */
public abstract class BaseRvAdapter<C extends BaseRvCell> extends RecyclerView.Adapter<BaseRvViewHolder> {

    private List<C> data;

    /**
     * 构造方法
     */
    public BaseRvAdapter() {
        data = new ArrayList<>();
    }


    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for(int i=0;i<getItemCount();i++){
            if(viewType == data.get(i).getItemType()){
                return data.get(i).onCreateViewHolder(parent,viewType);
            }
        }
        throw new RuntimeException("wrong viewType");
    }



    /**
     * 绑定数据
     */
    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        if(data!=null && data.size()>0){
            data.get(position).onBindViewHolder(holder,position);
        }
    }

    /**
     * 获取类型
     */
    @Override
    public int getItemViewType(int position) {
        if(data!=null && data.size()>0){
            return data.get(position).getItemType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return data==null ? 0 : data.size();
    }

    /**
     * 销毁时释放资源
     */
    @Override
    public void onViewDetachedFromWindow(BaseRvViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //释放资源
        int position = holder.getAdapterPosition();
        //越界检查
        if(position<0 || position>=data.size()){
            return;
        }
        data.get(position).releaseResource();
    }



    /**
     * 设置数据，并且刷新页面
     */
    public void setData(List<C> list){
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
    public List<C> getData(){
        return data;
    }

    public void add(C cell){
        data.add(cell);
        int index = data.indexOf(cell);
        notifyItemChanged(index);
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
    public void remove(C c){
        if(data.size()==0){
            return;
        }
        int index = data.indexOf(c);
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
