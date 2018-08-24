package com.yohu.smarthomeapp.utils;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public class DataBindingUtil {

    // 下载一个图片 设置到ImageView
    @BindingAdapter({"imageSource"})
    public static void loadImageSource(ImageView view, String sourceName) {
        if (sourceName == null) {
//            view.setImageResource(R.mipmap.ic_launcher);
        } else {
            int drawableId = view.getResources().getIdentifier(sourceName, "drawable", view.getContext().getPackageName());
            Glide.with(view.getContext()).load(drawableId).into(view);
        }
    }

    // 下载一个图片 设置到ImageView
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String sourceName) {
        if (sourceName == null) {
//            view.setImageResource(R.mipmap.ic_launcher);
        } else {
            int drawableId = view.getResources().getIdentifier(sourceName, "drawable", view.getContext().getPackageName());
            Glide.with(view.getContext()).load(drawableId).into(view);
        }
    }

    // 下载一个图片 SliderLayout
    @BindingAdapter({"loadImageSliderSourceByInt"})
    public static void loadImageSliderSourceByInt(final SliderLayout sliderLayout, List<Integer> usrList) {
        for (Integer url : usrList) {
            TextSliderView customSliderView = new TextSliderView(sliderLayout.getContext());
            customSliderView
                    .image(url)
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(customSliderView);
        }
        sliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("loadImageSliderSource", "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("loadImageSliderSource", "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("loadImageSliderSource", "onPageScrollStateChanged");
                if (state == 2) {
                    sliderLayout.setPresetTransformer(new Random().nextInt(16));
                }
            }
        });
    }

    @BindingAdapter({"loadImageSliderSourceString"})
    public static void loadImageSliderSourceString(final SliderLayout sliderLayout, List<String> usrList) {
        if (usrList == null) return;
        for (String urlStr : usrList) {
            TextSliderView customSliderView = new TextSliderView(sliderLayout.getContext());
            customSliderView
                    .image(urlStr)
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(customSliderView);
        }
        sliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 2) {
                    sliderLayout.setPresetTransformer(new Random().nextInt(16));
                }
            }
        });
    }

    @BindingAdapter({"setTransform"})
    public static void setTransform(SliderLayout sliderLayout, String transformerName) {
        for (SliderLayout.Transformer t : SliderLayout.Transformer.values()) {
            if (t.equals(transformerName)) {
                sliderLayout.setPresetTransformer(t);
            }
        }
    }
}
