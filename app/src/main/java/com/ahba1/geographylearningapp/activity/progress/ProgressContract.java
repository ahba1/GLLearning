package com.ahba1.geographylearningapp.activity.progress;

import com.ahba1.geographylearningapp.mvp.BasePresenter;
import com.ahba1.geographylearningapp.mvp.BaseView;
import com.liulishuo.filedownloader.BaseDownloadTask;

import java.util.List;

public class ProgressContract {

    interface View extends BaseView<ProgressContract.Presenter>{

        void newTask(BaseDownloadTask task, int soFarBytes, int totalBytes);

        void progress(BaseDownloadTask task, int soFarBytes, int totalBytes);

        void complete(BaseDownloadTask task);

        void pause(BaseDownloadTask task);

        void error(BaseDownloadTask task, Throwable e);
    }

    interface Presenter extends BasePresenter{

        void pause(BaseDownloadTask task);

        List<BaseDownloadTask> addTask(List<String> uris);

    }
}
