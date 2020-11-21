package com.ahba1.geographylearningapp.activity.progress;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ahba1.geographylearningapp.adapter.ProgressAdapter;
import com.ahba1.geographylearningapp.databinding.ActivityProgressBinding;
import com.liulishuo.filedownloader.BaseDownloadTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProgressActivity extends AppCompatActivity implements ProgressContract.View {

    private ProgressContract.Presenter presenter;

    private ActivityProgressBinding binding;

    public ProgressActivity(){
        this.presenter = new ProgressPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProgressBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();

        presenter.start();
    }

    private void init(){
        ProgressAdapter adapter = new ProgressAdapter();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        binding.rv.setLayoutManager(manager);
        binding.rv.setAdapter(adapter);
    }

    @Override
    public void newTask(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        ProgressAdapter adapter = (ProgressAdapter) binding.rv.getAdapter();
        adapter.addData(task);
        Log.v("new task", task.getFilename());
    }

    @Override
    public void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        ProgressAdapter adapter = (ProgressAdapter) binding.rv.getAdapter();
        adapter.updateProgress(task.getId(), soFarBytes);
    }

    @Override
    public void complete(BaseDownloadTask task) {
        ProgressAdapter adapter = (ProgressAdapter) binding.rv.getAdapter();
        Log.v("complete", task.getFilename());
    }

    @Override
    public void pause(BaseDownloadTask task) {

    }

    @Override
    public void error(BaseDownloadTask task, Throwable e) {

    }

    @Override
    public void setPresenter(ProgressContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
