package com.kele.androidstudio.kotlinimage.image;


import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class ImageProvide {
    public String[] projection = new String[]{
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_ID, // 直接包含该图片文件的文件夹ID，防止在不同下的文件夹重名
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME, // 直接包含该图片文件的文件夹名
            MediaStore.Images.Media.DISPLAY_NAME, // 图片文件名
            MediaStore.Images.Media.DATA, // 图片绝对路径
            "count(" + MediaStore.Images.Media._ID + ")"//统计当前文件夹下共有多少张图片
    };


    public List<String> getImages() {
        List<String> list = new ArrayList<String>();

        Cursor cursor = ImageManager.context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null, null);
        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                }

            }
            cursor.close();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }

        return list;
    }


}
