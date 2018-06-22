package com.test.bannerdemo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class HomeCategoryBean implements MultiItemEntity {
    public static final int TITLE = 1;
    public static final int CONTENT = 2;
    public static final int AD = 3;


    private int itemType;
    private String title;
    private int id;

    private List<LiveHouseListBean> data;

    public HomeCategoryBean(int itemType, int id , String title) {
        this.itemType = itemType;
        this.id = id;
        this.title = title;
    }

    public HomeCategoryBean(int itemType, List<LiveHouseListBean> data) {
        this.itemType = itemType;
        this.data = data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setData(List<LiveHouseListBean> data) {
        this.data = data;
    }

    public List<LiveHouseListBean> getData() {
        return data;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
