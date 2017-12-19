package com.yc.cn.ycbaseadapterlib.first;

/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2016/5/9
 * 描    述：定义一个接口，判断返回数据类型
 * 修订历史：
 * ================================================
 */
public interface MultiTypeSupport<T> {
    int getLayoutId(T item, int position);
}
