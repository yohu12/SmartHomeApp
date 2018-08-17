package com.yohu.smarthomeapp.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DataBindingUtil {

    // 下载一个图片 设置到ImageView
    @BindingAdapter({"imageSource"})
    public static void loadImageSource(ImageView view, String sourceName) {
        if (sourceName == null) {
//            view.setImageResource(R.mipmap.ic_launcher);
        } else {
            int drawableId = view.getResources().getIdentifier(sourceName,"drawable",view.getContext().getPackageName());
            Glide.with(view.getContext()).load(drawableId).into(view);
        }
    }
    // 下载一个图片 设置到ImageView
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String sourceName) {
        if (sourceName == null) {
//            view.setImageResource(R.mipmap.ic_launcher);
        } else {
            int drawableId = view.getResources().getIdentifier(sourceName,"drawable",view.getContext().getPackageName());
            Glide.with(view.getContext()).load(drawableId).into(view);
        }
    }

}
