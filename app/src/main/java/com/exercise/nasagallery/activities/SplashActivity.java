package com.exercise.nasagallery.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.exercise.nasagallery.BaseActivity;
import com.exercise.nasagallery.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                redirectTo(MainActivity.class);
                finish();
            }
        }, 3000);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }
}