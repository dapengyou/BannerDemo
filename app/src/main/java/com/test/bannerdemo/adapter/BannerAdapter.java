package com.test.bannerdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.test.bannerdemo.bean.LiveHouseListBean;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter{
    private boolean isSingleLoop = true;
    private ArrayList<LiveHouseListBean> banners;

    public BannerAdapter(){

    }
    @Override
    public int getCount() {
        if (!isSingleLoop && banners.size() == 1) {
            return 1;
        }
        return Short.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
