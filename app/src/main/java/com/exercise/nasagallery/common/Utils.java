package com.exercise.nasagallery.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private static String TAG = Utils.class.getSimpleName();

    private static boolean SHOW_LOGCAT = true;

    public static boolean haveNetworkConnection(Context mContext) {

        boolean isConnected;
        try {

            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null && activeNetwork.isConnected();

        } catch (Exception e) {

            isConnected = false;
        }
        return isConnected;
    }

    public static void showToast(Context mContext, String message) {

        if (mContext != null && message != null && !message.isEmpty()) {

            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void setGlideImage(Context mContext, String path, ImageView imageView, Integer defImage) {

        if (mContext != null && path != null && !path.isEmpty() && imageView != null) {

            Glide.with(mContext)
                    .load(path)
                    .override(300, 300)
                    .placeholder(defImage)
                    .error(defImage)
                    .into(imageView);

        } else {

            if (imageView != null) {

                imageView.setImageResource(defImage);
            }
        }
    }

    public static void showLog(String tag, String message, int type) {

        if (SHOW_LOGCAT) {

            switch (type) {

                case AppConstants.LOG_TYPE_DEBUG:
                    Log.d(tag, message);
                    break;

                case AppConstants.LOG_TYPE_ERROR:
                    Log.e(tag, message);
                    break;

                case AppConstants.LOG_TYPE_INFO:
                    Log.i(tag, message);
                    break;

                case AppConstants.LOG_TYPE_VERBOSE:
                    Log.v(tag, message);
                    break;

                case AppConstants.LOG_TYPE_ASSERT:
                    Log.w(tag, message);
                    break;

                default:
                    System.out.println(message);
                    break;
            }
        }
    }

    public static void showLogDefault(String tag, String message) {

        showLog(tag, message, AppConstants.LOG_TYPE_ERROR);
    }

    public static void showFragment(FragmentActivity activity, int container, Fragment fragment, String name, int FRAG_POLICY) {

        try {

            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

            if (FRAG_POLICY == AppConstants.ADD_FRAGMENT) {

                if (name != null)
                    fragmentTransaction.add(container, fragment).addToBackStack(name).commit();
                else
                    fragmentTransaction.add(container, fragment).commit();

            } else if (FRAG_POLICY == AppConstants.REPLACE_FRAGMENT) {

                if (name != null)
                    fragmentTransaction.replace(container, fragment).addToBackStack(name).commit();
                else
                    fragmentTransaction.replace(container, fragment).commit();

            } else {
                // nothing
            }

        } catch (Exception e) {

            showLogDefault(TAG, e.getMessage());
        }
    }

    public static Calendar getCalendarFromYmdDate(String str) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD, Locale.US);
        try {
            Date date = format1.parse(str);
            if (date != null)
                calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
