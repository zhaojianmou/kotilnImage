package com.kele.androidstudio.kotlinimage.base.api

import android.app.Service
import android.content.Intent
import android.os.IBinder

abstract class InitService : Service() {

    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented")
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}