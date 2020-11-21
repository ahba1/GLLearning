package com.ahba1.geographylearningapp.holder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

public class DownloadTaskViewHolder extends BaseViewHolder {

    private int taskId;

    public DownloadTaskViewHolder(View view){
        super(view);
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
