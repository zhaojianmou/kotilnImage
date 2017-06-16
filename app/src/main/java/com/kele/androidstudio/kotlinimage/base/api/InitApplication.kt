package com.kele.androidstudio.kotlinimage.base.api

import android.support.multidex.MultiDexApplication


abstract class InitApplication : MultiDexApplication() {


    lateinit var refWatcher: com.squareup.leakcanary.RefWatcher

    companion object {
        lateinit var application: com.kele.androidstudio.kotlinimage.base.api.InitApplication
        fun getRefWatcher(context: android.content.Context): com.squareup.leakcanary.RefWatcher {
            val application = context.applicationContext as com.kele.androidstudio.kotlinimage.base.api.InitApplication
            return application.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        initAfter()
        initData()
        initBefore()
    }

    abstract fun initAfter()

    abstract fun initData()

    abstract fun initBefore()

    abstract fun performance()

    abstract fun clearAppMemory()

    abstract fun exitApp()

}
