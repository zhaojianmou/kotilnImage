package com.kele.androidstudio.kotlinimage.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.ui.presenter.SplashPersenter
import com.kele.androidstudio.kotlinimage.ui.view.SplashView

class SplashActivity : BaseActivity() {
    //github WoWoViewPager

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }


    override fun initView(savedInstanceState: Bundle?) {
        var view = SplashView<SplashActivity>(t = this)
        var persenter = SplashPersenter<SplashView<SplashActivity>>(view)
        view.setPersenter(persenter)


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initData() {
        startActivity(Intent(this, MainActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        finish()
    }


}
