package com.yc.cn.ycbaseadapter.five;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yc.cn.ycbaseadapter.R;
import com.yc.cn.ycbaseadapterlib.itemType.BaseMViewHolder;
import com.yc.cn.ycbaseadapterlib.itemType.RecyclerArrayAdapter;


public class FiveSecondAdapter extends RecyclerArrayAdapter<Bean> {


    private boolean isEdit;
    public FiveSecondAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseMViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonViewHolder(parent);
    }

    public void setNotifyAll() {
        notifyDataSetChanged();
    }

    public class PersonViewHolder extends BaseMViewHolder<Bean> {

        CheckBox checkBox;
        TextView tv_state;

        PersonViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_five_one);
            checkBox = getView(R.id.cb);
            tv_state = getView(R.id.tv_state);
        }

        @Override
        public void setData(final Bean beans){
            checkBox.setChecked(beans.isState());
            if(isEdit){
                tv_state.setVisibility(View.VISIBLE);
            }else {
                tv_state.setVisibility(View.GONE);
            }
        }
    }

    public void setNotifyEdit(boolean isEdit) {
        this.isEdit = isEdit;
        notifyDataSetChanged();
    }

}
