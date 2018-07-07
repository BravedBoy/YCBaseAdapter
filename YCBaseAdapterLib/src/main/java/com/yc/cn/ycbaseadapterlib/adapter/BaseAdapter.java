package com.yc.cn.ycbaseadapterlib.adapter;

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
    private OnItemClickListener mOnItemClickListener;
    //默认可以回收
    private boolean isRecycle = true;

    public BaseAdapter() {
        //默认可以回收
        this.isRecycle = true;
    }

    /**
     * 构造方法
     * @param layoutId      布局
     * @param context       上下文
     */
    public BaseAdapter(Context context , int layoutId) {
        this.layoutId = layoutId;
        this.context = context;
        data = new ArrayList<>();
        //默认可以回收
        this.isRecycle = true;
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
        //Default value is true
        if (!isRecycle) {
            viewHolder.setIsRecyclable(false);
        }
        setListener(viewHolder);
        return viewHolder;
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


    /*-------------------------------------操作方法-----------------------------------------------*/



    /**
     * 设置点击事件和长按事件
     * @param viewHolder        viewHolder
     */
    private void setListener(final BaseViewHolder viewHolder) {
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
     * 设置数据T，并且刷新页面
     */
    public boolean setData(T t) {
        if (t == null) {
            return false;
        }
        if (data.contains(t)) {
            return false;
        }
        boolean b = data.add(t);
        notifyItemInserted(data.size() - 1);
        return b;
    }

    /**
     * 在索引position处添加数据t并且刷新
     * @param position                  position
     * @param t                         t
     * @return
     */
    public boolean setData(int position, T t) {
        if (t == null){
            return false;
        }
        if (position < 0 || position > data.size()){
            return false;
        }
        if (data.contains(t)){
            return false;
        }
        data.add(position, t);
        notifyItemInserted(position);
        return true;
    }

    /**
     * 在索引position处添加数据list集合并且刷新
     * @param position                  position
     * @param list                      list
     * @return
     */
    public boolean setData(int position, List<T> list) {
        if (list == null) {
            return false;
        }
        if (data.contains(list)){
            return false;
        }
        data.addAll(position, list);
        notifyItemRangeInserted(position, list.size());
        return true;
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
    public void clearAll(){
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
        notifyItemRemoved(index);
    }


    /**
     * 移除数据
     */
    public void remove(int position){
        if (position < 0 || position >= data.size()) {
            return ;
        }
        data.remove(position);
        notifyItemRemoved(position);
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
     * 更新数据
     * @param position           索引
     * @return
     */
    public boolean updateItem(int position) {
        if (position < 0 || position >= data.size()) {
            return false;
        }
        notifyItemChanged(position);
        return true;
    }

    /**
     * 更新数据
     * @param t                 t
     * @return
     */
    public boolean updateItem(T t) {
        if (t == null) {
            return false;
        }
        int index = data.indexOf(t);
        if (index >= 0) {
            data.set(index, t);
            notifyItemChanged(index);
            return true;
        }
        return false;
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
