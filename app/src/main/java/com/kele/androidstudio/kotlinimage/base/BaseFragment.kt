package com.kele.androidstudio.kotlinimage.base

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.kele.androidstudio.kotlinimage.base.api.InitApplication
import com.kele.androidstudio.kotlinimage.base.api.InitFragment


open class BaseFragment : InitFragment() {


    override fun getLayoutId(): Int {
        return 0
    }

    //TODO 添加Fragment进入退出动画
    override fun initView(view: View?, savedInstanceState: Bundle?) {

    }


    override fun initData() {

    }


    override fun onBackPressed() {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onDestroy() {
        super.onDestroy()
        //检查内存泄漏
        InitApplication.getRefWatcher(activity).watch(activity)

    }


}
