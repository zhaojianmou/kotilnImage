package com.kele.androidstudio.kotlinimage.ui.base

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.kele.androidstudio.kotlinimage.ui.activity.*

class UIManager {
    companion object {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun toMain(activity: Activity) {
            activity.startActivity(Intent(activity, MainActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun toBrowse(activity: Activity) {
            toBrowse(activity, null)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun toBrowse(activity: Activity, bundle: Bundle?) {
            var intent = Intent(activity, BrowseActivity::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun toFilter(activity: Activity) {
            toFilter(activity, null)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun toFilter(activity: Activity, bundle: Bundle?) {
            var intent = Intent(activity, FilterActivity::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun toCamera(activity: Activity) {
            activity.startActivity(Intent(activity, CameraActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun toMap(activity: Activity) {
            toMap(activity, null)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun toMap(activity: Activity, bundle: Bundle?) {
            var intent = Intent(activity, MapActivity::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun toAbout(activity: Activity) {
            activity.startActivity(Intent(activity, AboutActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }


    }
}


