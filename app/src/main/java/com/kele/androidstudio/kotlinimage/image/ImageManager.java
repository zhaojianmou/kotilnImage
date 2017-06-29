package com.kele.androidstudio.kotlinimage.image;


import android.content.Context;
import android.os.Build;


public class ImageManager {
    public static Context context;
    public static final int SELECT_PIC = 10;
    private static ImageProvide mImageProvide;
    private static VideoProvide mVideoProvide;
    private static AudioProvide mAudioProvide;

    public static boolean isKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static void init(Context mContext) {
        context = mContext;
    }

    public static ImageProvide getImageProvide() {
        if (mImageProvide == null) {
            mImageProvide = new ImageProvide();
        }
        return mImageProvide;
    }

    public static VideoProvide getVideoProvide() {
        if (mVideoProvide == null) {
            mVideoProvide = new VideoProvide();
        }
        return mVideoProvide;
    }

    public static AudioProvide getAudioProvide() {
        if (mAudioProvide == null) {
            mAudioProvide = new AudioProvide();
        }
        return mAudioProvide;
    }
}
