package com.ahba1.geographylearningapp.activity.cube;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ahba1.geographylearningapp.activity.progress.ProgressActivity;
import com.ahba1.geographylearningapp.databinding.ActivityCubeBinding;
import com.ahba1.geographylearningapp.module.http.model.FileInfo;
import com.ahba1.geographylearningapp.tools.RouterIndex;
import com.ahba1.geographylearningapp.views.cube.CubeView;
import com.github.mzule.activityrouter.annotation.Router;

import java.io.File;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

@Router(RouterIndex.Cube)
public class CubeActivity extends AppCompatActivity implements CubeContract.View{
    private CubeContract.Presenter presenter;

    private final static String TAG = CubeActivity.class.getName();

    private ActivityCubeBinding binding;

    public CubeActivity(){
        this.presenter=new CubePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCubeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        presenter.start();
    }

    private void init(){
        int[] indexMapper = {14, 9, 27, 15, 10,
                                6, 4, 5, 3, 2,
                                21, 18, 20, 19, 17};
        binding.cube.setIndexMapper(indexMapper);
        binding.cube.setOnClickListener(new CubeView.OnClickListener() {
            @Override
            public void onClick(int index) {
                //构造一个dialogView，从SP中加载数据名称，展示，并为每一个button绑定二级id
                Log.v("TAG", index+"");
                presenter.getFileList(index);
            }
        });
    }

    @Override
    public Context getContext(){
        return this;
    }

    @Override
    public void setPresenter(CubeContract.Presenter presenter){
        this.presenter=presenter;
    }


    @Override
    public void showChoseDialog(String name, String path){
        AlertDialog.Builder builder=new AlertDialog.Builder(CubeActivity.this);
        builder.setTitle("检测到手机上"+name+"文件未下载，是否下载？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //启动下载界面
                presenter.download(path);
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void showChoseWindow(List<FileInfo> fileInfoList) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] names = new String[fileInfoList.size()];
        for (int i = 0 ;i < fileInfoList.size(); i++){
            names[i] = fileInfoList.get(i).getName();
        }
        builder.setItems(names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openFile(fileInfoList.get(which).getPath());
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private void openFile(String path) {
        File file = new File(getFilesDir() + path);
        if (!file.exists()){
            showChoseDialog(file.getName(), path);
        }else{
            presenter.openFile(file);
        }
    }
}
