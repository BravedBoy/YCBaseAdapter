package com.yc.cn.ycbaseadapterlib.second.inter;

import android.view.ViewGroup;

import com.yc.cn.ycbaseadapterlib.second.viewHolder.BaseRvViewHolder;


/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/6/9
 * 描    述：定义一个独立的组件,它负责每个item的视图绑定、数据绑定和逻辑处理
 * 修订历史：
 * ================================================
 */
public interface Cell {
    /**
     * 回收资源
     */
    void releaseResource();

    /**
     * 获取viewType
     */
    int getItemType();

    /**
     * 创建ViewHolder
     */
    BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 数据绑定
     */
    void onBindViewHolder(BaseRvViewHolder holder, int position);
}
