package com.exercise.nasagallery.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.exercise.nasagallery.R;

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {

    public CustomTextView(@NonNull Context context) {
        super(context);
    }

    public CustomTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setCustomFont(context, attrs);
    }

    public CustomTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        try {

            TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
            String font = a.getString(R.styleable.CustomTextView_textViewFontFamily);

            Typeface tf = Typeface.createFromAsset(ctx.getAssets(), font);
            setTypeface(tf);

            a.recycle();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

