package com.yohu.smarthomeapp.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.yohu.smarthomeapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageViewFragment extends Fragment {

    private SliderLayout sliderLayout;
    private PagerIndicator indicator;
    private int[] imgurl = new int[]{R.drawable.cloud,
            R.drawable.f, R.drawable.rain, R.drawable.overcast};

    public ImageViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_image_view, container, false);
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        indicator = (PagerIndicator) view.findViewById(R.id.ind_pager);
        init();
        return view;
    }
    private void init() {

        for (int url : imgurl) {
            TextSliderView customSliderView = new TextSliderView(getActivity());
            customSliderView
                    .image(url)
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(customSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(10000);
        sliderLayout.setCustomIndicator(indicator);
        sliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Fade);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
