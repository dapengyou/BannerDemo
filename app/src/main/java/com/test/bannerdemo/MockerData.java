package com.test.bannerdemo;

import com.test.bannerdemo.bean.FirstAdvertisingBean;
import com.test.bannerdemo.bean.LiveHouseListBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockerData {
    private static Random random = new Random();

    private static long getRndTime() {
        return System.currentTimeMillis() - random.nextInt(10) * 24 * 60 * 60 * 1000;
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

    public static List<LiveHouseListBean> initList() {
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
}
