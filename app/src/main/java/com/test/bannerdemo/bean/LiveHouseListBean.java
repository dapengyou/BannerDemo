package com.test.bannerdemo.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class LiveHouseListBean implements Serializable, MultiItemEntity {

    public static final int BIG = 1;
    public static final int SMALL = 2;


    private String backgroundImgUrl;//(string,optional):背景图 ,
    private String description;//(string, optional):描述 ,
    private List<String> labels;//(string,optional):标签 ,
    private String name;//(string, optional): 趴间名称 ,
    private int popular;// (integer, optional): 人气 ,
    private int pv;//(integer, optional): 点击量 ,
    private int sort;//(integer, optional): 排序（值越大越靠前） ,
    private String thumbImgUrl;// (string, optional): 趴间封面图 ,

    private int type = SMALL;//用来区分类列表也加载大小类型


    public String getBackgroundImgUrl() {
        return backgroundImgUrl;
    }

    public void setBackgroundImgUrl(String backgroundImgUrl) {
        this.backgroundImgUrl = backgroundImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getLabels() {
        return labels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getThumbImgUrl() {
        return thumbImgUrl;
    }

    public void setThumbImgUrl(String thumbImgUrl) {
        this.thumbImgUrl = thumbImgUrl;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
