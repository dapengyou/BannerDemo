package com.test.bannerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.bannerdemo.adapter.MusicPartyAdapter;
import com.test.bannerdemo.bean.LiveHouseListBean;
import com.test.bannerdemo.widget.BannerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BannerView.OnBannerItemClickListener {
    private RecyclerView mRecyclerView;
    private MusicPartyAdapter mMusicPartyAdapter;

    private BannerView mBannerView;

    private List<LiveHouseListBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_content);

        initData();

        initBanner();
    }

    private void initBanner() {
        mBannerView.disableSingleLoop();
//        mBannerView.disableEnableLoop();//不设为轮播图，手动滚动

        mList = MockerData.initList();
        mBannerView.setScrollInterval(5);//设置轮播间隔为5秒

        mBannerView.setUpData(mList, MainActivity.this);

    }

    /**
     * recycleView 添加headerview
     */
    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMusicPartyAdapter = new MusicPartyAdapter(MockerData.getDatas(10));
        mMusicPartyAdapter.addHeaderView(initHeaderView());//今日趴精选

        mRecyclerView.setAdapter(mMusicPartyAdapter);
    }

    /**
     * 添加今日趴精选
     *
     * @return
     */
    private View initHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.layout_header_banner, (ViewGroup) mRecyclerView.getParent(), false);
        mBannerView = view.findViewById(R.id.bannerview);
        return view;
    }

    @Override
    public void onBannerClick(int index, LiveHouseListBean banner) {
        Toast.makeText(this, "进入趴间" + index, Toast.LENGTH_SHORT).show();
    }
}
