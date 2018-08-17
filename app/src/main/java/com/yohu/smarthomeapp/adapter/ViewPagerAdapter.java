package com.yohu.smarthomeapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;


public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    //轮播需要的图片
    public ArrayList<ImageView> imgs;

    public ViewPagerAdapter(Context context, ArrayList<ImageView> imgs) {
        this.context = context;
        this.imgs = imgs;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;

    }
}
