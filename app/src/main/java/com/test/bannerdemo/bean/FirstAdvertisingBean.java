package com.test.bannerdemo.bean;

import java.io.Serializable;

/**
 * Created by lady_zhou on 2018/4/4.
 */

public class FirstAdvertisingBean implements Serializable {
    private String title;//标题
    private String time;//日期

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
