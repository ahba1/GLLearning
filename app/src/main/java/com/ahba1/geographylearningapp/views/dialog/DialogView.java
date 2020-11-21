package com.ahba1.geographylearningapp.views.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahba1.geographylearningapp.MyApplication;
import com.ahba1.geographylearningapp.tools.SizeUtil;

import java.util.ArrayList;


public class DialogView extends Activity {
    private final static int DIALOG_WINDOW_SIZE=300;
    private final static int CHILD_VIEW_WIDTH=200;

    private final static String TITLE="title";
    private final static String BUTTONS="childButtonsName";
    private final static String ROOT="root";

    private ArrayList<String> fileNames;
    private String title;
    private String root;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        setContentView(initView(bundle.getStringArrayList(BUTTONS),bundle.getString(TITLE),bundle.getString(ROOT)));
    }

    private View initView(ArrayList<String> files,String title,String root){
        this.fileNames=files;
        this.title=title;
        this.root=root;

        int pxSize=SizeUtil.dp2px(DIALOG_WINDOW_SIZE,this);
        int pxWidth=SizeUtil.dp2px(CHILD_VIEW_WIDTH,this);

        LinearLayout linearLayout=new LinearLayout(this);
        LinearLayout.LayoutParams linerLayoutParams=new LinearLayout.LayoutParams(pxSize,pxSize);
        linerLayoutParams.gravity=Gravity.CENTER;
        linerLayoutParams.height=pxSize;
        linerLayoutParams.width=pxSize;
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linerLayoutParams);

        TextView textView=new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(title);

        LinearLayout.LayoutParams textViewParams=new LinearLayout.LayoutParams(pxWidth,0,1.0f);
        linearLayout.addView(textView,textViewParams);

        for(final String file:files){
            Button button=new Button(this);
            button.setText(file);
            button.setTextSize(SizeUtil.dp2px(4f,this));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            LinearLayout.LayoutParams buttonParams=new LinearLayout.LayoutParams(pxWidth,0,1.0f);
            linearLayout.addView(button,buttonParams);
        }

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击内部无法退出弹窗
            }
        });

        return linearLayout;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //点击外部退出弹窗
        finish();
        return true;
    }
    public static void showDialog(Context context,String title,ArrayList<String> files,String root){
        Intent intent=new Intent(context,DialogView.class);
        Bundle bundle=new Bundle();
        bundle.putString(TITLE,title);
        bundle.putString(ROOT,root);
        bundle.putStringArrayList(BUTTONS,files);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
