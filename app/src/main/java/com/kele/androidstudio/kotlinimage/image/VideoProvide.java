package com.kele.androidstudio.kotlinimage.image;


import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class VideoProvide {

    public List<String> getVideo() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = ImageManager.context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null, null);
        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA)));
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
