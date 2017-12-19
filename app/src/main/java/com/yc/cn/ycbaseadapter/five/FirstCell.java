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
public class FirstCell extends BaseRvCell<FiveBean.FirstList> {

    public FirstCell(FiveBean.FirstList entry) {
        super(entry);
    }

    @Override
    public int getItemType() {
        return 2;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,null));
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {

    }
}
