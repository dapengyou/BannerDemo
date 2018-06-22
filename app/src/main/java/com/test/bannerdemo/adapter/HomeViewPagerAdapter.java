package com.test.bannerdemo.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.bannerdemo.R;
import com.test.bannerdemo.bean.LiveHouseListBean;
import com.test.bannerdemo.utils.GlideUtils;
import com.test.bannerdemo.widget.CircleImageView;

import java.util.List;


/**
 * Created by wanghongjia on 2018/5/12.
 */

public class HomeViewPagerAdapter extends AbstractViewPagerAdapter implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private CircleImageView mIvUserImg;
    private TextView mPartyName;
    private TextView mPartyPopular;
    private TextView mPartyTag;
    private ImageView mImgBg;

    private List<LiveHouseListBean> mDatas;
    private OnPageSelectListener listener;

    private OnPageViewClickListener mPageViewClickListener;


    public HomeViewPagerAdapter(Context context, List<LiveHouseListBean> data) {
        super(data);
        this.mContext = context;
        this.mDatas = data;
    }

    @Override
    public View newView(final int position) {
        View view = View.inflate(mContext, R.layout.item_music_header, null);
        initView(view);
        initViewData(position);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPageViewClickListener != null) {
                    mPageViewClickListener.onViewClick(position);
                }
            }
        });
        return view;
    }

    private void initView(View view) {
        mPartyTag = view.findViewById(R.id.tv_party_tag);
        mPartyPopular = view.findViewById(R.id.tv_party_popular);
        mPartyName = view.findViewById(R.id.tv_party_name);
        mIvUserImg = view.findViewById(R.id.iv_user_img);
        mImgBg = view.findViewById(R.id.iv_bg);
    }

    private void initViewData(int position) {
        LiveHouseListBean bean = mDatas.get(position);
        if (bean != null) {
            mPartyName.setText(bean.getName());
            mPartyPopular.setText(bean.getPopular() + "");
//            if (bean.getLabels() != null && bean.getLabels().size() > 0) {
//                StringBuilder sb = new StringBuilder();
//                for (String tag : bean.getLabels()) {
//                    sb.append("#").append(tag);
//                }
//                mPartyTag.setText(sb.toString());
//
//            }

//            if (bean.getUser() != null) {
//                GlideUtils.display(mContext, mIvUserImg, bean.getUser().getPhotoUrl());
//            }
            GlideUtils.roundCornersImagePlay(mContext, mImgBg, bean.getThumbImgUrl());

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (listener != null) {
            listener.select(position);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnPageSelectListener {
        void select(int positon);
    }

    public interface OnPageViewClickListener {
        void onViewClick(int positon);
    }

    public void setOnPageSelectListener(OnPageSelectListener listener) {
        this.listener = listener;
    }

    public void setOnPageViewClickListener(OnPageViewClickListener listener) {
        this.mPageViewClickListener = listener;
    }


    public List<LiveHouseListBean> getmDatas() {
        return mDatas;
    }
}