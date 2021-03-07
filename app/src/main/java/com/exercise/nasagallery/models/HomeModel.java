package com.exercise.nasagallery.models;

import android.content.Context;

import com.exercise.nasagallery.R;
import com.exercise.nasagallery.common.Exclude;
import com.exercise.nasagallery.common.Utils;
import com.exercise.nasagallery.datamodels.ImageModel;
import com.exercise.nasagallery.interfaces.HomeContract;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeModel implements HomeContract.imagesListModel {

    private static String TAG = HomeModel.class.getSimpleName();

    @Override
    public void getImagesList(onFinish onFinish, Context context) {

        String resultJson = Utils.readJsonStringFromAssetFile(context, "imagesDataJson.json");
        if (resultJson != null && !resultJson.isEmpty()) {

            try {

                Exclude ex = new Exclude();
                Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
                Type type = new TypeToken<List<ImageModel>>() {
                }.getType();
                ArrayList<ImageModel> list = gson.fromJson(resultJson, type);

                if (list != null && list.size() > 0) {

                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setTimeInMs(Utils.getCalendarFromYmdDate(list.get(i).getDate()).getTimeInMillis());
                    }

                    Collections.sort(list, Collections.reverseOrder());
                    onFinish.onSuccess(list);

                } else {
                    onFinish.onFailed(context.getResources().getString(R.string.json_error));
                }

            } catch (Exception e) {

                Utils.showLogDefault(TAG, "Exception :" + e.getMessage());
                onFinish.onFailed(context.getResources().getString(R.string.json_error));
            }

        } else {
            onFinish.onFailed(context.getResources().getString(R.string.no_pics));
        }
    }
}
