package com.ahba1.geographylearningapp.activity.cube;

import com.ahba1.geographylearningapp.module.http.model.FileInfo;
import com.ahba1.geographylearningapp.mvp.BasePresenter;
import com.ahba1.geographylearningapp.mvp.BaseView;

import java.io.File;
import java.util.List;


class CubeContract {
    interface View extends BaseView<Presenter>{
        void showChoseDialog(String name, String path);

        void showChoseWindow(List<FileInfo> fileInfoList);
    }
    interface Presenter extends BasePresenter{
        default void start(){

        }

        void getFileList(int ancestor);

        void download(String path);

        void openFile(File file);
    }
}
