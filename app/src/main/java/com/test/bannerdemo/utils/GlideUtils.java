package com.test.bannerdemo.utils;


import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.test.bannerdemo.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by wanghongia on 2016/4/20.
 */
public class GlideUtils {


    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context).load(url).into(imageView);
    }

    public static void display(Context context, ImageView imageView, Uri uri) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context).load(uri).placeholder(R.mipmap.icon_music_default)
                .error(R.mipmap.icon_music_default).into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context).load(url).placeholder(placeholder)
                .error(error).into(imageView);
    }

    //加载圆形头像专用
    public static void circleImagePlay(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context).load(url).transform(new CircleCrop())
                .placeholder(R.mipmap.icon_music_default).into(imageView);
    }

    public static void circleImagePlay(Context context, ImageView imageView, Uri uri) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context).load(uri).transform(new CircleCrop())
                .placeholder(R.mipmap.icon_music_default).into(imageView);
    }


    //加载高斯模糊
    public static void blurImagePlay(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context).load(url).transform(new BlurTransformation(20))
                .placeholder(R.mipmap.icon_music_default).into(imageView);
    }

    //加载高斯模糊
    public static void roundCornersImagePlay(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context).load(url).transform(new RoundedCornersTransformation(16, 0))
                .placeholder(R.mipmap.icon_music_default).into(imageView);
    }

    //加载高斯模糊
    public static void roundCornersImagePlay(Context context, ImageView imageView, Uri uri) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context).load(uri).transform(new RoundedCornersTransformation(16, 0))
                .placeholder(R.mipmap.icon_music_default).into(imageView);
    }


}
