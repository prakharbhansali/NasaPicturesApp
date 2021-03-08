package com.exercise.nasagallery.activities;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentTransaction;

import com.exercise.nasagallery.BaseActivity;
import com.exercise.nasagallery.R;
import com.exercise.nasagallery.fragments.HomeFragment;
import com.exercise.nasagallery.fragments.ImageFragment;

public class MainActivity extends BaseActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    private RelativeLayout rlMain;
    private FrameLayout myFrame;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {

        rlMain = findViewById(R.id.rlMain);
        myFrame = findViewById(R.id.myFrame);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(myFrame.getId(), new HomeFragment()).addToBackStack(HomeFragment.class.getSimpleName()).commit();
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().findFragmentById(myFrame.getId()) instanceof HomeFragment) {
            finish();
        } else if (getSupportFragmentManager().findFragmentById(myFrame.getId()) instanceof ImageFragment) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}