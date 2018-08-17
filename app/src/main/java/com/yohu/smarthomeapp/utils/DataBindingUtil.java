package com.yohu.smarthomeapp.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DataBindingUtil {

    // 下载一个图片 设置到ImageView
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        if (url == null) {
//            view.setImageResource(R.mipmap.ic_launcher);
        } else {
            int drawableId = view.getResources().getIdentifier(url,"drawable",view.getContext().getPackageName());
            Glide.with(view.getContext()).load(drawableId).into(view);
        }
    }

}
