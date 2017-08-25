package com.kele.androidstudio.kotlinimage.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.constant.UIConstant
import com.kele.androidstudio.kotlinimage.ui.fragment.ListMapFragment
import com.kele.androidstudio.kotlinimage.ui.fragment.LocalMapFragment
import com.kele.androidstudio.kotlinimage.ui.fragment.TrackMapFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.functions.Consumer

class MapActivity : BaseActivity() {

    var path = ""
    var type = UIConstant.TYPE_IMAGE


    override fun getLayoutId(): Int {
        return R.layout.activity_map
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        if (intent != null && intent.extras != null) {
            path = intent.extras.getString(UIConstant.IMAGE_PATH, "")
            type = intent.getStringExtra(UIConstant.MAP_TYPE)
        }
    }

    override fun initData() {
        super.initData()
        RxPermissions(this).request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(Consumer<Boolean> { granted: Boolean ->
            if (granted) {
                addFragment()
            } else {

            }
        })
    }

    fun addFragment() {
        var beginTransaction = supportFragmentManager.beginTransaction()
        if (type.equals(UIConstant.TYPE_IMAGE)) {
            //地图上单个图片展示
            beginTransaction.replace(R.id.frameLayout, LocalMapFragment.getInstance(path))
        } else if (type.equals(UIConstant.TYPE_LIST)) {
            beginTransaction.replace(R.id.frameLayout, ListMapFragment.getInstance())
        } else {
            //地图上多个图片展示
            beginTransaction.replace(R.id.frameLayout, TrackMapFragment.getInstance())
        }
        beginTransaction.commitAllowingStateLoss()


    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}
