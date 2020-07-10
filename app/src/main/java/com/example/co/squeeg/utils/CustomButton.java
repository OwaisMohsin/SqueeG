package com.example.co.squeeg.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.example.co.squeeg.R;

public class CustomButton extends AppCompatButton {

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public CustomButton(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs!=null) {
             TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView);
             String fontName = a.getString(R.styleable.CustomTextView_fontName);
             if (fontName!=null) {
                 Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName + ".ttf");
                 setTypeface(myTypeface);
             }
             a.recycle();
        }
    }

}