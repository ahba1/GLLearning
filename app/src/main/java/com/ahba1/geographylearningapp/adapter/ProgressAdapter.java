package com.ahba1.geographylearningapp.adapter;

import com.ahba1.geographylearningapp.R;
import com.ahba1.geographylearningapp.holder.BaseDownloadTaskHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.liulishuo.filedownloader.BaseDownloadTask;

import java.util.HashMap;
import java.util.Map;

public class ProgressAdapter extends BaseQuickAdapter<BaseDownloadTask, BaseDownloadTaskHolder> {

    private Map<Integer, BaseDownloadTaskHolder> holders;

    public ProgressAdapter(){
        super(R.layout.item_download);
        holders = new HashMap<>();
    }


    @Override
    protected void convert(BaseDownloadTaskHolder helper, BaseDownloadTask item) {
        holders.put(item.getId(), helper);
        helper.setText(R.id.item_download_name, item.getFilename());

        NumberProgressBar bar = (NumberProgressBar) helper.getView(R.id.item_download_progress);
        //bar.setProgress(item.getSmallFileSoFarBytes()/item.getSmallFileTotalBytes());
    }

    public void updateProgress(int id, int soFarBytes){
        BaseDownloadTaskHolder holder = holders.get(id);
        NumberProgressBar bar = (NumberProgressBar) holder.getView(R.id.item_download_progress);
        holder.setText(R.id.item_download_size, soFarBytes+"");
    }

    @Override
    public void remove(int position) {
        super.remove(position);
    }
}
