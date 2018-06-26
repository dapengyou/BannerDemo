package com.test.bannerdemo.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.bannerdemo.R;
import com.test.bannerdemo.bean.LiveHouseListBean;
import com.test.bannerdemo.utils.GlideUtils;

import java.util.List;

public class BannerView extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private int position;
    private long mScrollInterval = 2 * 1000;      //循环间隙
    private OnBannerItemClickListener mOnBannerItemClickListener;

    private boolean isSingleLoop = true;    //是否单图循环

    private boolean isEnableLoop = true;  //是否循环

    private Context mContext;
    private TextView mPartyName;
    private TextView mPartyPopular;
    private ImageView mImgBg;
    private ImageView mViewpagerBg; //viewpager的背景图
    private ViewPager mBannerPager;
    private CircleIndicator mCircleIndicator;
    private List<LiveHouseListBean> banners;
    private BannerAdapter adapter;


    public BannerView(Context context) {
        super(context);
        mContext = context;
        setUpView();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        setUpView();

    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        setUpView();

    }

    private void setUpView() {
        LayoutInflater.from(mContext).inflate(R.layout.widget_banner_view, this);

        mBannerPager = findViewById(R.id.vp_content);
        mCircleIndicator = findViewById(R.id.circleIndicator);
        mImgBg = findViewById(R.id.iv_bg);

        mBannerPager.addOnPageChangeListener(this);
    }


    /**
     * 设置单张图片不可以轮播
     */
    public void disableSingleLoop() {
        isSingleLoop = false;
    }


    /**
     * 不设置轮播
     */
    public void disableEnableLoop() {
        isEnableLoop = false;
    }

    /**
     * 设置轮播时间
     *
     * @param seconds
     */
    public void setScrollInterval(long seconds) {
        mScrollInterval = seconds * 1000;

    }


    public void setUpData(List<LiveHouseListBean> banners, OnBannerItemClickListener listener) {
        this.banners = banners;
        this.mOnBannerItemClickListener = listener;

        initViewPager();

        if (banners != null && banners.size() > 0) {
            adapter = new BannerAdapter();
            mBannerPager.setAdapter(adapter);
            if (!isSingleLoop && banners.size() == 1) {

            } else {
                int half = Short.MAX_VALUE / 2;
                if (banners != null && banners.size() > 0) {
                    half = half - half % banners.size();
                }
                mBannerPager.setCurrentItem(half);//设置当前选择的页面

                mCircleIndicator.initData(banners.size(), 0);
                mCircleIndicator.setCurrentPage(0);

            }
        }

    }

    /**
     * 设置viewpager的宽度与间距
     */
    private void initViewPager() {
        mBannerPager.setOffscreenPageLimit(3);//设置预加载页面个数
        final int pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 9.0f / 10.0f);
        ViewGroup.LayoutParams lp = mBannerPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        mBannerPager.setLayoutParams(lp);
        mBannerPager.setPageMargin(30);
        mBannerPager.setPageTransformer(true, new AlphaTransformer());

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isSingleLoop && banners.size() == 1) {
                mBannerPager.setCurrentItem(0, true);
            } else {
                if (position < Short.MAX_VALUE - 1) {
                    mBannerPager.setCurrentItem(position + 1, true);
                }
            }
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        handler.removeMessages(0);
        this.position = position;
        if (!isSingleLoop && banners.size() == 1) {

        } else {
            int index = position % banners.size();
            //设置指示器位置
            mCircleIndicator.setCurrentPage(index);

            //设置大背景图片
            GlideUtils.blurImagePlay(mContext, mImgBg, banners.get(index).getThumbImgUrl());

            if (isEnableLoop) {
                handler.sendEmptyMessageDelayed(0, mScrollInterval);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void onStart() {
        handler.removeMessages(0);
        if (isEnableLoop) {
            handler.sendEmptyMessageDelayed(0, mScrollInterval);
        }
    }

    public void onStop() {
        handler.removeMessages(0);
    }

    /**
     * viewpager里面的内容控件初始化
     *
     * @param view
     */
    private void initView(View view) {
        mPartyPopular = view.findViewById(R.id.tv_party_popular);
        mPartyName = view.findViewById(R.id.tv_party_name);
        mViewpagerBg = view.findViewById(R.id.iv_bg);
    }


    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (!isSingleLoop && banners.size() == 1) {
                return 1;
            }
            return Short.MAX_VALUE;

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 实例化viewpager里面的内容
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(mContext, R.layout.item_music_header, null);
            initView(view);

            final int index = position % banners.size();
            final LiveHouseListBean bean = banners.get(index);
            if (bean != null) {
                mPartyName.setText(bean.getName());
                mPartyPopular.setText(bean.getPopular() + "");
                GlideUtils.roundCornersImagePlay(mContext, mViewpagerBg, bean.getThumbImgUrl());

            }

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnBannerItemClickListener.onBannerClick(index, bean);
                }
            });

            container.addView(view);
            return view;
        }

        /**
         * 销毁item
         *
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * banner中点击Item的接口
     */
    public interface OnBannerItemClickListener {
        void onBannerClick(int index, LiveHouseListBean banner);
    }
}
