package com.kele.androidstudio.kotlinimage.image;


import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class AudioProvide {


    public List<String> getAudio() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = ImageManager.context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null, null);
        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                }
                cursor.close();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        return list;
    }


}
