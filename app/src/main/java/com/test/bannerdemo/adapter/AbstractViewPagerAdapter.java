package com.test.bannerdemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wanghongjia on 2017/4/19.
 */

public abstract class AbstractViewPagerAdapter<T> extends PagerAdapter {
    protected List<T> mData;
    protected SparseArray<View> mViews;

    public AbstractViewPagerAdapter(List<T> data) {
        mData = data;
        mViews = new SparseArray<View>(data.size());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        if (view == null) {
            Log.d("adapter","加载1");
            view = newView(position);
            mViews.put(position, view);
        }
        Log.d("adapter","加载2");

        container.addView(view);
        return view;
    }

    public abstract View newView(int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    public T getItem(int position) {
        return mData.get(position);
    }
}
