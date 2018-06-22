package com.test.bannerdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.test.bannerdemo.adapter.HomeViewPagerAdapter;
import com.test.bannerdemo.adapter.MusicPartyAdapter;
import com.test.bannerdemo.bean.FirstAdvertisingBean;
import com.test.bannerdemo.bean.LiveHouseListBean;
import com.test.bannerdemo.utils.GlideUtils;
import com.test.bannerdemo.widget.AlphaTransformer;
import com.test.bannerdemo.widget.CircleImageView;
import com.test.bannerdemo.widget.CircleIndicator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements HomeViewPagerAdapter.OnPageViewClickListener, HomeViewPagerAdapter.OnPageSelectListener {
    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;
    private RelativeLayout mViewPagerContainer;
    private CircleIndicator mCircleIndicator;
    private ImageView mViewpagerBg;
    private MusicPartyAdapter mMusicPartyAdapter;
    private HomeViewPagerAdapter mHomeViewPagerAdapter;

    private List<LiveHouseListBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_content);

        mList = initList();

        initData();
        initHeaderData(mList);

    }

    private List<LiveHouseListBean> initList() {
        List<LiveHouseListBean> listBeans = new ArrayList<>();

        LiveHouseListBean liveHouseListBean = new LiveHouseListBean();
        liveHouseListBean.setName("ztt");
        liveHouseListBean.setThumbImgUrl("http://dev-proj.obs.cn-north-1.myhwclouds.com/file/2018051213/39f23446-1e28-4b35-be35-e5a5dbc72361.png");
        liveHouseListBean.setBackgroundImgUrl("http://dev-proj.obs.cn-north-1.myhwclouds.com/file/2018051213/39f23446-1e28-4b35-be35-e5a5dbc72361.png");
        listBeans.add(liveHouseListBean);

        LiveHouseListBean liveHouseListBean1 = new LiveHouseListBean();
        liveHouseListBean1.setName("mmp");
        liveHouseListBean1.setThumbImgUrl("http://dev-proj.obs.cn-north-1.myhwclouds.com/file/2018051213/4fee0414-9747-4728-9830-5c191e3dfa0b.png");
        liveHouseListBean1.setBackgroundImgUrl("http://dev-proj.obs.cn-north-1.myhwclouds.com/file/2018051213/4fee0414-9747-4728-9830-5c191e3dfa0b.png");
        listBeans.add(liveHouseListBean1);

        LiveHouseListBean liveHouseListBean2 = new LiveHouseListBean();
        liveHouseListBean2.setName("sss");
        liveHouseListBean2.setThumbImgUrl("http://dev-proj.obs.cn-north-1.myhwclouds.com/file/2018051213/5c2f3193-f586-4abc-a63d-72180ffaf5ab.png");
        liveHouseListBean2.setBackgroundImgUrl("http://dev-proj.obs.cn-north-1.myhwclouds.com/file/2018051213/5c2f3193-f586-4abc-a63d-72180ffaf5ab.png");
        listBeans.add(liveHouseListBean2);

        return listBeans;
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMusicPartyAdapter = new MusicPartyAdapter(getDatas(10));
        mMusicPartyAdapter.addHeaderView(initHeaderView());//今日趴精选

        mRecyclerView.setAdapter(mMusicPartyAdapter);
    }

    /**
     * 添加今日趴精选
     *
     * @return
     */
    private View initHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.layout_music_header_banner, (ViewGroup) mRecyclerView.getParent(), false);
        mViewPager = view.findViewById(R.id.vp_content);
        mViewPagerContainer = view.findViewById(R.id.viewPagerContainer);
        mCircleIndicator = view.findViewById(R.id.circleIndicator);
        mViewpagerBg = view.findViewById(R.id.iv_bg);
        mCircleIndicator.setCurrentPage(0);
        initViewPager();
        return view;
    }


    private void initViewPager() {
        mViewPager.setOffscreenPageLimit(3);
        final int pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 9.0f / 10.0f);
        ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        mViewPager.setLayoutParams(lp);
        mViewPager.setPageMargin(30);
        mViewPager.setPageTransformer(true, new AlphaTransformer());

        mViewPagerContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mViewPager.dispatchTouchEvent(motionEvent);
            }
        });

    }

    private void initHeaderData(List<LiveHouseListBean> datas) {
        if (datas != null && datas.size() > 0) {
            GlideUtils.blurImagePlay(this, mViewpagerBg, datas.get(0).getThumbImgUrl());
        }
        mCircleIndicator.initData(datas.size(), 0);
        mCircleIndicator.setCurrentPage(0);

        mHomeViewPagerAdapter = new HomeViewPagerAdapter(this, datas);
        mHomeViewPagerAdapter.setOnPageSelectListener(this);
        mHomeViewPagerAdapter.setOnPageViewClickListener(this);

        mViewPager.addOnPageChangeListener(mHomeViewPagerAdapter);
        mViewPager.setAdapter(mHomeViewPagerAdapter);
        mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void select(int positon) {
        mCircleIndicator.setCurrentPage(positon);
        GlideUtils.blurImagePlay(this, mViewpagerBg, mHomeViewPagerAdapter.getmDatas().get(positon).getThumbImgUrl());

    }

    @Override
    public void onViewClick(int positon) {
        Toast.makeText(this, "进入趴间", Toast.LENGTH_SHORT).show();
    }

    /**
     * 虚拟数据
     *
     * @param count
     * @return
     */
    public static List<FirstAdvertisingBean> getDatas(int count) {
        List<FirstAdvertisingBean> datas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < count; i++) {
            FirstAdvertisingBean firstAdvertisingBean = new FirstAdvertisingBean();
            firstAdvertisingBean.setTitle("标题" + i);
            firstAdvertisingBean.setTime(sdf.format(getRndTime()));
            datas.add(firstAdvertisingBean);
        }
        return datas;
    }

    private static Random random = new Random();

    private static long getRndTime() {
        return System.currentTimeMillis() - random.nextInt(10) * 24 * 60 * 60 * 1000;
    }
}
