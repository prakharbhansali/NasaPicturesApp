package com.exercise.nasagallery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exercise.nasagallery.BaseFragment;
import com.exercise.nasagallery.R;
import com.exercise.nasagallery.common.AppConstants;
import com.exercise.nasagallery.common.Utils;
import com.exercise.nasagallery.datamodels.ImageModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ImageDetailsFragment extends BaseFragment {

    private static String TAG = ImageDetailsFragment.class.getSimpleName();

    private View myMainView = null;
    private FrameLayout progressBarLayout;
    private ImageView ivImage1;
    private TextView tvCopyright, tvTitle, tvDate, idContent;
    private LinearLayout llDateLayout;

    private ImageModel imageModel = null;

    public ImageDetailsFragment() {
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
        if (myMainView == null) {
            myMainView = inflater.inflate(R.layout.fragment_image_details, container, false);
            initView(myMainView);
        }

        return myMainView;
    }

    private void getDataFromBundle() {

        if (getArguments() != null) {
            if (getArguments().containsKey(AppConstants.TAG_IMAGE_MODEL)) {
                imageModel = getArguments().getParcelable(AppConstants.TAG_IMAGE_MODEL);
            }
        }
    }

    private void initView(View view) {

        progressBarLayout = view.findViewById(R.id.progressBarLayout);
        ivImage1 = view.findViewById(R.id.ivImage1);

        tvCopyright = view.findViewById(R.id.tvCopyright);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvDate = view.findViewById(R.id.tvDate);
        idContent = view.findViewById(R.id.idContent);
        llDateLayout = view.findViewById(R.id.llDateLayout);

        if (imageModel != null) {

            Utils.setGlideImage(getContext(), imageModel.getHdurl(), ivImage1, R.drawable.default_image, progressBarLayout);

            if (imageModel.getCopyright() != null && !imageModel.getCopyright().isEmpty()) {
                tvCopyright.setText(imageModel.getCopyright());
                tvCopyright.setVisibility(View.VISIBLE);
            }

            if (imageModel.getTitle() != null && !imageModel.getTitle().isEmpty()) {
                tvTitle.setText(imageModel.getTitle());
                tvTitle.setVisibility(View.VISIBLE);
            }

            if (imageModel.getExplanation() != null && !imageModel.getExplanation().isEmpty()) {
                idContent.setText(imageModel.getExplanation());
                idContent.setVisibility(View.VISIBLE);
            }

            if (imageModel.getDate() != null && !imageModel.getDate().isEmpty()) {
                Calendar cal = Utils.getCalendarFromYmdDate(imageModel.getDate());
                tvDate.setText(new SimpleDateFormat(AppConstants.DATE_FORMAT_DD_MMM_YYYY, Locale.US).format(cal.getTime()));
                llDateLayout.setVisibility(View.VISIBLE);
            }
        }
    }
}