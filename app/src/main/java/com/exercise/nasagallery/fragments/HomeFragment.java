package com.exercise.nasagallery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exercise.nasagallery.BaseFragment;
import com.exercise.nasagallery.R;
import com.exercise.nasagallery.adapters.ImageListAdapter;
import com.exercise.nasagallery.common.SpacesItemDecoration;
import com.exercise.nasagallery.datamodels.ImageModel;
import com.exercise.nasagallery.interfaces.HomeContract;
import com.exercise.nasagallery.presenters.HomePresenter;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment implements HomeContract.myView {

    private static String TAG = HomeFragment.class.getSimpleName();

    private View myView = null;
    private RecyclerView rvImagesList;
    private ProgressBar progressBar;
    private TextView tvErrorMsg;
    private GridLayoutManager gridLayoutManager;
    private ImageListAdapter imageListAdapter;
    private ArrayList<ImageModel> imagesList = null;

    private HomePresenter homePresenter;

    public HomeFragment() {
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

        init();
        if (myView == null) {
            myView = inflater.inflate(R.layout.fragment_home, container, false);
            initView(myView);
        }

        return myView;
    }

    private void init() {

        homePresenter = new HomePresenter(this);
    }

    private void initView(View view) {

        rvImagesList = view.findViewById(R.id.rvImagesList);
        progressBar = view.findViewById(R.id.progressBar);
        tvErrorMsg = view.findViewById(R.id.tvErrorMsg);

        initList();
        homePresenter.requestImagesList(getContext());
    }

    private void initList() {

        imagesList = new ArrayList<>();

        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvImagesList.setLayoutManager(gridLayoutManager);

        imageListAdapter = new ImageListAdapter(getContext(), imagesList);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen._5sdp);

        rvImagesList.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        rvImagesList.setAdapter(imageListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homePresenter.onPresenterDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showImagesList(ArrayList<ImageModel> list) {

        if (imagesList == null) {
            imagesList = new ArrayList<>();
        }

        imagesList.clear();
        imagesList.addAll(list);

        imageListAdapter.notifyDataSetChanged();

        progressBar.setVisibility(View.GONE);
        rvImagesList.setVisibility(View.VISIBLE);
        tvErrorMsg.setVisibility(View.GONE);
        tvErrorMsg.setText("");
    }

    @Override
    public void showErrorMsg(String msg) {

        progressBar.setVisibility(View.GONE);
        rvImagesList.setVisibility(View.GONE);
        tvErrorMsg.setVisibility(View.VISIBLE);
        tvErrorMsg.setText(msg);
    }
}