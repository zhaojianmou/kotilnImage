package com.kele.androidstudio.kotlinimage.utils

import android.os.Build
import com.kele.androidstudio.kotlinimage.base.BaseApplication

class MediaUtils() {
    //android系统 在4.4版本之后对图片视频这块的api做了大的调整，这块要做分别处理


    fun getPhoto() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
//                BaseApplication.application.getSystemService()

        }


    }


}