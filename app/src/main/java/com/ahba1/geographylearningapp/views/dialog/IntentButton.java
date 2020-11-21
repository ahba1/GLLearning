package com.ahba1.geographylearningapp.views.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ahba1.geographylearningapp.MyApplication;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class IntentButton extends AppCompatButton {
    private final static String TAG="IntentButton";

    private String intentUri;

    private Context context;

    public IntentButton(Context context, @Nullable AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        this.context=context;
    }

    public IntentButton(Context context, @Nullable AttributeSet attrs){
        this(context,attrs,0);
    }

    public IntentButton(Context context){
        this(context,null);
    }

    public void bind(String relativePath){
        this.intentUri=MyApplication.getInstance().getCacheRootDir()+relativePath;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,intentUri);
            }
        });
    }
}
