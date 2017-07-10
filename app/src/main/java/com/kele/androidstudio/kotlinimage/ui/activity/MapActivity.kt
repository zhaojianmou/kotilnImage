package com.kele.androidstudio.kotlinimage.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.ui.fragment.LocalMapFragment

class MapActivity : BaseActivity() {

    var path = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_map
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initData() {
        super.initData()
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 5)
        } else {
            addFragment()
        }
    }

    fun addFragment() {
//        path = "/storage/emulated/0/DCIM/IMG_6424.jpg"
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, LocalMapFragment.getInstance(path)).commitAllowingStateLoss()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.size != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            addFragment()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


}
