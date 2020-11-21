package com.ahba1.geographylearningapp.activity.progress;

import com.ahba1.geographylearningapp.module.ApplicationAPI;
import com.ahba1.geographylearningapp.module.http.APIManager;
import com.ahba1.geographylearningapp.module.http.model.CatalogInfo;
import com.ahba1.geographylearningapp.module.http.model.CatalogItem;
import com.ahba1.geographylearningapp.module.http.model.FileInfo;
import com.ahba1.geographylearningapp.module.rxjava2.BaseObserver;
import com.ahba1.geographylearningapp.module.rxjava2.SchedulersCompat;
import com.ahba1.geographylearningapp.tools.HttpClient;
import com.ahba1.geographylearningapp.tools.SpUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

class ProgressPresenter implements ProgressContract.Presenter {

    private ProgressContract.View view;
    private FileDownloadListener queueTarget;
    private FileDownloadQueueSet queueSet;
    private List<BaseDownloadTask> wholeTask = new LinkedList<>();

    ProgressPresenter(ProgressContract.View view){
        this.view = view;
    }

    @Override
    public void pause(BaseDownloadTask task) {
        task.pause();
    }

    @Override
    public List<BaseDownloadTask> addTask(List<String> uris) {
        String filesRoot = view.getContext().getFilesDir().getAbsolutePath();
        List<BaseDownloadTask> tasks = new LinkedList<>();
        for (String uri:uris){
            BaseDownloadTask task = FileDownloader.getImpl().create(HttpClient.BASE+uri).setPath(filesRoot+uri);
            Object o = SpUtils.get(view.getContext(), Integer.toString(task.getId()), task);
            if (o instanceof BaseDownloadTask){
                task = (BaseDownloadTask) o;
            }
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public void start() {
        FileDownloader.setup(view.getContext());

        queueTarget = new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                view.newTask(task, soFarBytes, totalBytes);
            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                view.progress(task, soFarBytes, totalBytes);
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                SpUtils.put(view.getContext(), Integer.toString(task.getId()), task);
                view.complete(task);
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                view.pause(task);
            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
                view.error(task, e);
            }

            @Override
            protected void warn(BaseDownloadTask task) {

            }
        };

        queueSet = new FileDownloadQueueSet(queueTarget);
        queueSet.disableCallbackProgressTimes();
        queueSet.setAutoRetryTimes(1);
        getDownloadList(14);
        //SpUtils.setCheckTag(view.getContext());
    }

    private void getDownloadList(@Nullable Integer ancestor){
        String token = ApplicationAPI.getLoginToken();
        APIManager.getInstance()
                .getDownloadService()
                .getDownloadList(HttpClient.formatToken(token), ancestor)
                .compose(SchedulersCompat.applyIoSchedulers())
                .filter(new Predicate<CatalogInfo>() {
                    @Override
                    public boolean test(@NonNull CatalogInfo catalogInfo) throws Exception {
                        if (catalogInfo.getFile()!=null&&catalogInfo.getFile().size()!=0){
                            return true;
                        }
                        else{
                            for (CatalogItem item:catalogInfo.getDictionary()){
                                getDownloadList(item.getId());
                            }
                        }
                        return false;
                    }
                })
                .map(new Function<CatalogInfo, List<FileInfo>>() {
                    @Override
                    public List<FileInfo> apply(@NonNull CatalogInfo catalogInfo) throws Exception {
                        return catalogInfo.getFile();
                    }
                })
                .subscribe(new BaseObserver<List<FileInfo>>(view) {
                    @Override
                    public void success(List<FileInfo> o) {
                        for (FileInfo info:o){
                            FileDownloader.getImpl()
                                    .create(HttpClient.BASE+info.getPath())
                                    .setPath(view.getContext().getFilesDir()+info.getPath())
                                    .setCallbackProgressTimes(1)
                                    .setListener(queueTarget)
                                    .asInQueueTask()
                                    .enqueue();
                        }
                        FileDownloader.getImpl().start(queueTarget, true);
                    }

                    @Override
                    public void error(Throwable e) {

                    }
                });
    }
}
