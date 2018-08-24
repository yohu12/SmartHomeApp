package com.yohu.smarthomeapp.fragment;


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yohu.smarthomeapp.R;
import com.yohu.smarthomeapp.databinding.FragmentImageViewBinding;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageViewFragment extends Fragment {

//    private SliderLayout sliderLayout;
//    private PagerIndicator indicator;
    private ArrayList<Integer> imgurl = new ArrayList<>();

    public ImageViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imgurl.add(R.drawable.cloud);
        imgurl.add(R.drawable.f);
        imgurl.add(R.drawable.rain);
        imgurl.add(R.drawable.overcast);
        FragmentImageViewBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_image_view, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        binding.setList(imgurl);
        binding.setTransform("ZoomIn");
        init();

        return view;
    }
    private void init() {

//        sliderLayout.setPresetTransformer(1);
//        sliderLayout.setCustomAnimation(new DescriptionAnimation());
//        sliderLayout.setDuration(5000);
//        sliderLayout.setCustomIndicator(indicator);

    }
}
