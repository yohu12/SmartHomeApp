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
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageViewFragment extends Fragment {

    private ArrayList<String> imgurl = new ArrayList<>();
    private FragmentImageViewBinding binding;

    public ImageViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        for (int i=0;i<20;i++) {
            imgurl.add("https://picsum.photos/1600/900/?image=" + new Random().nextInt(1000));
        }
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_image_view, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        init();
        binding.setList(imgurl);
        return view;
    }

    private void init() {

//        sliderLayout.setPresetTransformer(1);
//        sliderLayout.setCustomAnimation(new DescriptionAnimation());
//        sliderLayout.setDuration(5000);
//        sliderLayout.setCustomIndicator(indicator);

    }
}
