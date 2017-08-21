package com.kele.androidstudio.kotlinimage.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.constant.UIConstant
import com.kele.androidstudio.kotlinimage.ui.fragment.LocalMapFragment
import com.kele.androidstudio.kotlinimage.ui.fragment.TrackMapFragment

class MapActivity : BaseActivity() {

    var path = ""
    var type = UIConstant.TYPE_IMAGE


    override fun getLayoutId(): Int {
        return R.layout.activity_map
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        if (intent != null && intent.extras != null) {
            path = intent.extras.getString(UIConstant.IMAGE_PATH)
//            type = intent.getStringExtra(UIConstant.MAP_TYPE)
        }
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
        var beginTransaction = supportFragmentManager.beginTransaction()
        if (type.equals(UIConstant.TYPE_IMAGE)) {
            beginTransaction.replace(R.id.frameLayout, LocalMapFragment.getInstance(path))
        } else {
            beginTransaction.replace(R.id.frameLayout, TrackMapFragment.getInstance())
        }
        beginTransaction.commitAllowingStateLoss()


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.size != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            addFragment()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}
