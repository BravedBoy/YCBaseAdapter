package com.yc.cn.ycbaseadapterlib.first;

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
 * 创建日期：2016/5/9
 * 描    述：RecycleView的adapter抽取类
 * 修订历史：
 * ================================================
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private int layoutId;
    private List<T> data;
    protected MultiTypeSupport<T> multiTypeSupport;
    protected OnItemClickListener mOnItemClickListener;


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

    /**
     * 创建一个ViewHolder
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (multiTypeSupport != null) {
            layoutId = viewType;
        }
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);
        setListener(viewHolder,viewType);
        return viewHolder;
    }

    /**
     * 设置点击事件和长按事件
     * @param viewHolder        viewHolder
     * @param viewType          类型
     */
    private void setListener(final BaseViewHolder viewHolder, int viewType) {
        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder , position);
                }
            }
        });

        viewHolder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemClickListener.onItemLongClick(v, viewHolder, position);
                }
                return false;
            }
        });
    }

    /**
     * 绑定数据，创建抽象方法，让子类实现
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(data!=null && data.size()>0){
            bindData(holder, data.get(position));
        }
    }

    /**
     * 获取类型
     */
    @Override
    public int getItemViewType(int position) {
        if (multiTypeSupport != null) {
            return multiTypeSupport.getLayoutId(data.get(position), position);
        }
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


    /**
     * 回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder,  int position);
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder,  int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}
