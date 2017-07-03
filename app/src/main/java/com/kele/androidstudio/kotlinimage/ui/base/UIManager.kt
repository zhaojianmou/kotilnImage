package com.kele.androidstudio.kotlinimage.ui.base

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import com.kele.androidstudio.kotlinimage.ui.activity.FilterActivity
import com.kele.androidstudio.kotlinimage.ui.activity.MainActivity

class UIManager {
    companion object {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun splashToMain(activity: Activity) {
            activity.startActivity(Intent(activity, MainActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun splashToFilter(activity: Activity) {
            activity.startActivity(Intent(activity, FilterActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }


    }
}


