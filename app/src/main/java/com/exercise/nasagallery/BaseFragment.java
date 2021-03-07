package com.exercise.nasagallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class BaseFragment extends Fragment {

    private ProgressDialog progressDialog = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void redirectTo(Class myClass) {
        Intent i = new Intent(getActivity(), myClass);
        startActivity(i);
    }

    protected void redirectTo(Class myClass, Bundle args) {
        Intent i = new Intent(getActivity(), myClass);
        if (args != null)
            i.putExtras(args);
        startActivity(i);
    }

    protected void redirectTo(Class myClass, Bundle args, int flag) {
        Intent i = new Intent(getActivity(), myClass);
        i.setFlags(flag);
        if (args != null)
            i.putExtras(args);
        startActivity(i);
    }

    protected void showSnackBarShort(View view, String msg) {

        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark))
                .setActionTextColor(getResources().getColor(R.color.white))
                .show();
    }

    protected void showProgress(boolean show, String msg) {
        if (show) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getActivity());
            }

            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }
}
