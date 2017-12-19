package com.yc.cn.ycbaseadapterlib.second.cell;

import com.yc.cn.ycbaseadapterlib.second.inter.Cell;


/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/6/9
 * 描    述：定义一个独立组件的基类，子类实现该基类，并且传递数据
 *          由于数据类型未知，因此定义为泛型
 * 修订历史：
 * ================================================
 */
public  abstract class BaseRvCell<T> implements Cell {

    public T mData;         //泛型数据
    public BaseRvCell(T t){
        mData = t;
    }

    @Override
    public void releaseResource() {
        // do nothing
        // 如果有需要回收的资源，子类自己实现
    }
}
