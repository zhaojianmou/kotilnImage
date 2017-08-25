package com.kele.androidstudio.kotlinimage.base

import android.app.ActivityManager
import android.content.Intent
import android.graphics.Color
import android.view.KeyEvent
import com.kele.androidstudio.kotlinimage.base.Impl.InitActivityImpl
import com.kele.androidstudio.kotlinimage.base.api.InitApplication
import com.kele.androidstudio.kotlinimage.utils.constant.LeakCanaryUtils
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.kele.androidstudio.kotlinimage.utils.StatusBarUtils
import android.view.ViewGroup
import com.kele.androidstudio.kotlinimage.R


open class BaseActivity : InitActivityImpl() {


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

    protected fun addFragment(i: Int, fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(i, fragment, fragment.javaClass.simpleName)
        beginTransaction.setTransition(0)
        beginTransaction.addToBackStack(fragment.javaClass.simpleName)
        try {
            beginTransaction.commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
        }
    }

    protected fun addFragment(i: Int, fragment: Fragment, z: Boolean) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(i, fragment, fragment.javaClass.name)
        if (z) {
            beginTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        try {
            beginTransaction.commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
        }
    }

    protected fun replaceFragment(i: Int, fragment: Fragment, z: Boolean) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(i, fragment, fragment.javaClass.simpleName)
        beginTransaction.setTransition(0)
        if (z) {
            beginTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        try {
            beginTransaction.commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
        }
    }


}
