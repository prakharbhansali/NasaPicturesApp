package com.exercise.nasagallery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.exercise.nasagallery.BaseFragment;
import com.exercise.nasagallery.R;
import com.exercise.nasagallery.adapters.MyViewPagerAdapter;
import com.exercise.nasagallery.common.AppConstants;
import com.exercise.nasagallery.datamodels.ImageModel;

import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends BaseFragment {

    private static String TAG = ImageFragment.class.getSimpleName();

    private View myMainView = null;
    private RelativeLayout backButtonLayout;
    private ViewPager2 mainViewPager;
    private int CURR_POS = -1;
    private ArrayList<ImageModel> imagesList = null;
    private MyViewPagerAdapter myViewPagerAdapter;

    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getDataFromBundle();
        init();
        if (myMainView == null) {
            myMainView = inflater.inflate(R.layout.fragment_image, container, false);
            initView(myMainView);
        }

        return myMainView;
    }

    private void getDataFromBundle() {

        if (getArguments() != null) {
            if (getArguments().containsKey(AppConstants.TAG_LIST_ITEM_POSITION)) {
                CURR_POS = getArguments().getInt(AppConstants.TAG_LIST_ITEM_POSITION);
            }

            if (getArguments().containsKey(AppConstants.TAG_IMAGE_LIST)) {
                imagesList = getArguments().getParcelableArrayList(AppConstants.TAG_IMAGE_LIST);
            }
        }
    }

    private void init() {

        if (imagesList == null) {
            imagesList = new ArrayList<>();
        }
    }

    private void initView(View view) {

        backButtonLayout = view.findViewById(R.id.backButtonLayout);
        mainViewPager = view.findViewById(R.id.mainViewPager);

        setViewPager();
        setCurrentImageDetailFrag();

        setListeners();
    }

    private void setViewPager() {

        List<Fragment> fragmentList = new ArrayList<>();
        ImageDetailsFragment imageDetailsFragment = null;

        if (imagesList != null && imagesList.size() > 0) {

            Bundle bundle;
            for (int i = 0; i < imagesList.size(); i++) {

                bundle = new Bundle();
                bundle.putParcelable(AppConstants.TAG_IMAGE_MODEL, imagesList.get(i));
                imageDetailsFragment = new ImageDetailsFragment();
                imageDetailsFragment.setArguments(bundle);
                fragmentList.add(imageDetailsFragment);
            }

            myViewPagerAdapter = new MyViewPagerAdapter(getActivity(), fragmentList);
            mainViewPager.setAdapter(myViewPagerAdapter);
        }
    }

    private void setListeners() {

        backButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });
    }

    private void setCurrentImageDetailFrag() {

        if (imagesList != null && imagesList.size() > 0) {
            if (CURR_POS > -1) {
                mainViewPager.setCurrentItem(CURR_POS);
            }
        }
    }
}