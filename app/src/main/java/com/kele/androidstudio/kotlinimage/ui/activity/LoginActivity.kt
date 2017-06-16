package com.kele.androidstudio.kotlinimage.ui.activity

import android.os.Bundle
import android.widget.Toast


import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.AppComponent
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.base.BaseApplication
import com.kele.androidstudio.kotlinimage.ui.presenter.LoginPersenter
import com.kele.androidstudio.kotlinimage.ui.view.LoginView
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.tencent.bugly.crashreport.CrashReport
import okhttp3.OkHttpClient


class LoginActivity : BaseActivity() {


    override fun initView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login)
        var view: LoginView = LoginView(this)
        var persenter: LoginPersenter = LoginPersenter(view)
        view.setPersenter(persenter)

    }

    override fun initData() {
        //test
//        AppComponent.getInstance().context()
//        OkHttpClient.Builder()
//                .addNetworkInterceptor(StethoInterceptor())
//                .build()
        if (!isStethoPresent()) {
            Toast.makeText(
                    this,
                    "Stetho missing in %s build!",
                    Toast.LENGTH_LONG)
                    .show()
        }

    }

    private fun isStethoPresent(): Boolean {
        try {
            Class.forName("com.facebook.stetho.Stetho")
            return true
        } catch (e: ClassNotFoundException) {
            return false
        }

    }

}


