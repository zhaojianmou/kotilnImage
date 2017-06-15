package com.kele.androidstudio.kotlinimage.base

import android.content.Intent
import android.view.KeyEvent
import com.kele.androidstudio.kotlinimage.base.api.InitActivity
import com.kele.androidstudio.kotlinimage.base.api.InitApplication
import com.kele.androidstudio.kotlinimage.base.api.InitFragment
import com.kele.androidstudio.kotlinimage.utils.constant.LeakCanaryUtils


abstract class BaseActivity : InitActivity() {

    override fun onStart() {
        super.onStart()
        //TODO 添加Activity进入退出动画
    }

    override fun onResume() {
        super.onResume()
        //网络请求
        //耗时操作

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
        //关闭网络请求
        //关闭耗时操作

        //检查内存泄漏
        InitApplication.getRefWatcher(this).watch(this)
        LeakCanaryUtils.fixInputMethodManagerLeak(this)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (getCurrFragment() != null) {
            getCurrFragment()!!.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (getCurrFragment() != null) {
            return getCurrFragment()!!.onKeyDown(keyCode, event = null)
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (supportFragmentManager.fragments != null && supportFragmentManager.fragments.size > 0) {
            for (fragment in supportFragmentManager.fragments) {
                fragment?.onActivityResult(requestCode, resultCode, data)
            }
        }
    }


//    override fun getCurrFragment(): InitFragment? {
//        var ft: InitFragment? = null
//        //TODO 优化getCurrFragment（）
//        if (supportFragmentManager != null && getFragmentById() != 0) {
//            var ftInit: InitFragment? = supportFragmentManager.findFragmentById(getFragmentById()) as InitFragment
//            if (ftInit != null && ftInit.isVisible) {
//                ft = ftInit
//                return ft
//            }
//        }
//        return ft
//    }

    override fun getCurrFragment(): InitFragment? {
        var ft: InitFragment? = null
        for (fragment in supportFragmentManager.fragments) {
            if (fragment != null && fragment!!.isVisible()) {
                ft = fragment as InitFragment
                return ft
            }
        }
        return ft
    }


    override fun getFragmentById(): Int {
        return 0
    }


}
