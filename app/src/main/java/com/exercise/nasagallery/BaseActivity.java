package com.exercise.nasagallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
    }

    protected abstract int getLayoutResourceId();

    protected void redirectTo(Class myClass) {
        Intent i = new Intent(this, myClass);
        startActivity(i);
    }

    protected void redirectTo(Class myClass, Bundle args) {
        Intent i = new Intent(this, myClass);
        if (args != null)
            i.putExtras(args);
        startActivity(i);
    }

    protected void redirectTo(Class myClass, Bundle args, int flag) {
        Intent i = new Intent(this, myClass);
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
                progressDialog = new ProgressDialog(this);
            }
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }
}
