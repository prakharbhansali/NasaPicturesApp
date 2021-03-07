package com.exercise.nasagallery.presenters;

import android.content.Context;

import com.exercise.nasagallery.datamodels.ImageModel;
import com.exercise.nasagallery.interfaces.HomeContract;
import com.exercise.nasagallery.models.HomeModel;

import java.util.ArrayList;

public class HomePresenter implements HomeContract.presenter, HomeContract.imagesListModel.onFinish {

    private HomeContract.myView myView;
    private HomeContract.imagesListModel imagesListModel;

    public HomePresenter(HomeContract.myView myView) {
        this.myView = myView;
        imagesListModel = new HomeModel();
    }

    @Override
    public void onPresenterDestroy() {

        myView = null;
    }

    @Override
    public void requestImagesList(Context context) {

        if (myView != null) {
            myView.showProgress();
        }

        imagesListModel.getImagesList(this, context);
    }

    @Override
    public void onSuccess(ArrayList<ImageModel> list) {

        if (myView != null) {
            myView.hideProgress();
            myView.showImagesList(list);
        }
    }

    @Override
    public void onFailed(String msg) {

        if (myView != null) {
            myView.hideProgress();
            myView.showErrorMsg(msg);
        }
    }
}
