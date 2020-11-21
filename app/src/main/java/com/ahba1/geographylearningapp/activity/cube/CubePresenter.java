package com.ahba1.geographylearningapp.activity.cube;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.ahba1.geographylearningapp.BuildConfig;
import com.ahba1.geographylearningapp.module.ApplicationAPI;
import com.ahba1.geographylearningapp.module.http.APIManager;
import com.ahba1.geographylearningapp.module.http.model.CatalogInfo;
import com.ahba1.geographylearningapp.module.rxjava2.BaseObserver;
import com.ahba1.geographylearningapp.module.rxjava2.SchedulersCompat;
import com.ahba1.geographylearningapp.tools.HttpClient;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

import androidx.core.content.FileProvider;

class CubePresenter implements CubeContract.Presenter{

    private CubeContract.View view;

    CubePresenter(CubeContract.View view){
        this.view=view;
    }

    @Override
    public void start() {
        FileDownloader.setup(view.getContext());
    }

    @Override
    public void getFileList(int ancestor) {

        String token = ApplicationAPI.getLoginToken();

        APIManager.getInstance()
                .getDownloadService()
                .getDownloadList(HttpClient.formatToken(token), ancestor)
                .compose(SchedulersCompat.applyIoSchedulers())
                .subscribe(new BaseObserver<CatalogInfo>(view) {
                    @Override
                    public void success(CatalogInfo o) {
                        view.showChoseWindow(o.getFile());
                    }

                    @Override
                    public void error(Throwable e) {

                    }
                });
    }

    @Override
    public void download(String path) {
        FileDownloader.getImpl()
                .create(HttpClient.BASE+path)
                .setPath(view.getContext().getFilesDir()+path)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        Toast.makeText(view.getContext(), "开始下载", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        Log.v("TAG", task.getFilename());
                        Toast.makeText(view.getContext(), task.getFilename()+"下载成功", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {

                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                }).start();
    }

    @Override
    public void openFile(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.v("FileIntentMsg", BuildConfig.APPLICATION_ID + ".fileProvider");
            Uri uri = FileProvider.getUriForFile(view.getContext(), BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else {
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        }
        view.getContext().startActivity(intent);
    }
}
