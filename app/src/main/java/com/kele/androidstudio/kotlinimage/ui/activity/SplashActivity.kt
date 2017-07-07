package com.kele.androidstudio.kotlinimage.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.ui.base.UIManager
import com.kele.androidstudio.kotlinimage.ui.presenter.SplashPersenter
import com.kele.androidstudio.kotlinimage.ui.view.SplashView

class SplashActivity : BaseActivity() {
    val TAG = "SplashActivity"
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
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 5)
        } else {
            UIManager.splashToMap(this)
        }

//        finish()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            UIManager.splashToMap(this)
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }


}
