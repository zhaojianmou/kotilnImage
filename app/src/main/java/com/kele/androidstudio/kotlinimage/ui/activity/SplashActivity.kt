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
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import android.widget.Toast


class SplashActivity : BaseActivity() {
    val TAG = "SplashActivity"
    //github WoWoViewPager


    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }


    override fun initView(savedInstanceState: Bundle?) {
//        var view = SplashView<SplashActivity>(this)
//        var persenter = SplashPersenter<SplashView<SplashActivity>>(view)
//        view.setPersenter(persenter)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initData() {
        RxPermissions(this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE).subscribe(Consumer<Boolean> { granted: Boolean ->
            if (granted) {
                //同意权限
                UIManager.toMain(this)
                finish()
            } else {
                //拒绝权限
                finish()
            }
        })
    }

}

