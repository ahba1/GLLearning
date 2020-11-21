package com.ahba1.geographylearningapp.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.ResponseBody;

public class FileUtils {

    public FileUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean openFile(Context context, String root, int secondId, int id){
        String key = SpUtils.formatKey(secondId, id);
        if (SpUtils.contains(context, key)){
            String value = SpUtils.get(context, key, null);
            if(!TextUtils.isEmpty(value)){
                String path = SpUtils.parsePath(value);
                String extension = getTypeInfo(SpUtils.parseType(value));
                String absolutePath = root+path+extension;
                File file = new File(absolutePath);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri uri = Uri.fromFile(file);
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
                context.startActivity(intent);
                return true;
            }
            return false;
        }else {
            return false;
        }
    }

    public static String getTypeInfo(int type){
        if (type==4){
            return ".ppt";
        }
        return "";
    }

    public static void write(String path, InputStream is){

    }

    public static void writeFileToDisk(ResponseBody responseBody, File file) {
        long totalByte = responseBody.contentLength();
        long downloadByte = 0;
        File directory = new File(file.getParent());
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                Log.v("TAG", "创建成功");
            }
        }
        downloadByte = file.length();
        byte[] buffer = new byte[1024 * 4];
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(downloadByte);
            InputStream is = responseBody.byteStream();
            is.skip(downloadByte);
            int len;
            while ((len=is.read(buffer))!=-1) {

                randomAccessFile.write(buffer);
                downloadByte += len;

            }
            randomAccessFile.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void writeFileToDis(InputStream is, File file){
        Log.v("file", file.getAbsolutePath());
        FileOutputStream fos = null;

        try {
            if (!file.getParentFile().exists()){
                Log.v("file", "createParentFile");
                file.getParentFile().mkdirs();
            }
            if (!file.exists()){
                Log.v("file", "createFile");
                Log.v("file", file.createNewFile()+"");
            }

            fos = new FileOutputStream(file);
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            byte[] b = new byte[1024*4];

            int len = 0;
            while ((len = is.read(b))!=-1){
                raf.write(b);
            }
            is.close();
            fos.close();
            raf.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
