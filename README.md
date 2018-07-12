# YCBaseAdapter
#### 目录介绍
- 1.功能说明
- 2.使用介绍
    - 2.1 初步设置recycleView
    - 2.2 设置adapter，继承BaseAdapter<T>
    - 2.3 如果是需要支持添加Header或者Footer，继承RecyclerArrayAdapter<T>
- 3.相关方法介绍
    - 3.1 设置数据方法
    - 3.2 移除，清除方法看代码
    - 3.3 添加header或者footer
- 4.遇到问题介绍
- 5.局限性


### 1.功能说明
- 1.1 封装一个通常的ViewHolder，里面用SparseArray<View>来缓存id
- 1.2 减少Adapter基础逻辑重复书写，通过泛型传参，使得代码更清晰
- 1.3 支持设置多种不同的ViewType类型布局
- 1.4 支持recyclerView添加多个header和footer
- 1.5 子类继承BaseAdapter<T>，通过实现bindData方法设置view数据


### 2.使用介绍
- 2.1 初步设置recycleView

```
recyclerView.setLayoutManager(new LinearLayoutManager(this));
adapter = new FourAdapter(this);
recyclerView.setAdapter(adapter);
adapter.setData(list);
RecycleViewItemLine line = new RecycleViewItemLine(this,LinearLayoutManager.HORIZONTAL,2, Color.GRAY);
recyclerView.addItemDecoration(line);
adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        List<FourBean> data = adapter.getData();
        Toast.makeText(FourActivity.this,data.get(position).getTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
});
```

- 2.2 设置adapter，继承BaseAdapter<T>

```
public class FourAdapter extends BaseAdapter<FourBean> implements MultiTypeSupport<FourBean>{

    private Context mContent;

    public FourAdapter(Context context) {
        super(context, R.layout.main_chat_from_msg);
        this.mContent = context;
        //这句话一点要添加
        this.multiTypeSupport = this;
    }

    /**
     * 绑定数据
     * @param holder                    holder
     * @param s                         bean
     */
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

    /**
     * 用来区分不同item
     * @param item              item
     * @param position
     * @return
     */
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
```



### 3.相关方法介绍
- 3.1 设置数据方法

```
设置数据，并且刷新
setData(List<T> list)

设置插入数据，并且只刷新插入数据
setData(T t)

在索引position处添加数据t并且刷新
setData(int position, T t)

在索引position处添加数据list集合并且刷新
setData(int position, List<T> list)
```

- 3.2 移除，清除方法看代码

```
//移除T数据，不会触发任何事情
remove(T object)

//移除索引position数据，不会触发任何事情
remove(int position)

//出发清空数据并刷新
clear()
```

- 3.3 添加header或者footer

```
//添加header
adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
    @Override
    public View onCreateView(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindView(View headerView) {

    }
});

//添加footer
adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
    @Override
    public View onCreateView(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindView(View headerView) {

    }
});
```

### 4.遇到问题介绍
- 4.1 添加多个header或者footer时候，如果header或者footer包含RecyclerView，如果避免卡顿或者焦点抢占
- 4.2 


### 5.局限性


