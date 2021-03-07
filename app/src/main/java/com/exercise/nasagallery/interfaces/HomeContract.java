package com.exercise.nasagallery.interfaces;

import android.content.Context;

import com.exercise.nasagallery.datamodels.ImageModel;

import java.util.ArrayList;

public interface HomeContract {

    interface imagesListModel {

        interface onFinish {

            void onSuccess(ArrayList<ImageModel> list);

            void onFailed(String msg);
        }

        void getImagesList(onFinish onFinish, Context context);
    }

    interface presenter {

        void onPresenterDestroy();

        void requestImagesList(Context context);
    }

    interface myView {

        void showProgress();

        void hideProgress();

        void showImagesList(ArrayList<ImageModel> list);

        void showErrorMsg(String msg);
    }
}
