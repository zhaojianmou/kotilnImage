package com.kele.androidstudio.kotlinimage.base

import android.content.Context
import com.kele.androidstudio.kotlinimage.base.api.InitAppComponent
import com.kele.androidstudio.kotlinimage.base.api.InitApplication

class AppComponent : InitAppComponent {


    companion object {
        var appComponent: AppComponent? = null;

        fun getInstance(): InitAppComponent {
            if (appComponent == null) {
                appComponent = AppComponent()
            }
            return appComponent as AppComponent
        }
    }

    private constructor()


    override fun context(): Context {
        return InitApplication.application
    }

    override fun getActivityManager() {


    }


}
