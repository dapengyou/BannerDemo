package com.test.bannerdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.bannerdemo.R;
import com.test.bannerdemo.bean.FirstAdvertisingBean;

import java.util.List;


public class MusicPartyAdapter extends BaseQuickAdapter<FirstAdvertisingBean, BaseViewHolder> {


    public MusicPartyAdapter(List<FirstAdvertisingBean> data) {
        super(R.layout.item_first_advertising, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FirstAdvertisingBean item) {

        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_date, item.getTime());
    }
}
