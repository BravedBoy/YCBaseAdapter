package com.yc.cn.ycbaseadapter.five;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yc.cn.ycbaseadapter.R;
import com.yc.cn.ycbaseadapterlib.second.cell.BaseRvCell;
import com.yc.cn.ycbaseadapterlib.second.viewHolder.BaseRvViewHolder;

/**
 * Created by PC on 2017/11/9.
 * 作者：PC
 */
public class BannerCell extends BaseRvCell<FiveBean.Banner> {

    public BannerCell(FiveBean.Banner entry) {
        super(entry);
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner,null));
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        //绑定数据
    }
}
