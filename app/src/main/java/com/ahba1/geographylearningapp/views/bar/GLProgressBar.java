package com.ahba1.geographylearningapp.views.bar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.ahba1.geographylearningapp.R;

public class GLProgressBar {

    private Dialog dialog;

    private Context context;

    private ProgressBar pb;

    public GLProgressBar(Context context){
        this.context = context;
        dialog = new Dialog(context, R.style.dialog);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }

    public void initDialog(){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_dialog, null);
        dialog.setContentView(view);
        pb = (ProgressBar)dialog.findViewById(R.id.progressBar1);
        pb.setIndeterminate(false);
        pb.setMax(100);
        pb.setProgress(0);
        dialog.show();
    }

    public void setProgress(int progressValue) {
        pb.setProgress(progressValue);
    }

    public void colseDialog() {
        dialog.dismiss();
    }

    public boolean isShowing() {
        if (dialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }
}
