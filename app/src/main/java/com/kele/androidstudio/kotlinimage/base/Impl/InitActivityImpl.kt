package com.kele.androidstudio.kotlinimage.base.Impl

import android.os.Bundle
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.api.InitActivity
import com.kele.androidstudio.kotlinimage.base.api.InitFragment
import kotlinx.android.synthetic.main.title_bar_main.*

open class InitActivityImpl : InitActivity() {

    override fun initAfter() {
        //添加沉浸式状态栏
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.bg_color_413E4A)
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun titleBar() {
        if (left != null) {
            left.setOnClickListener { finish() }
        }

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {

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
        if (supportFragmentManager.fragments != null) {
            for (fragment in supportFragmentManager.fragments) {
                if (fragment != null && fragment!!.isVisible()) {
                    ft = fragment as InitFragment
                    return ft
                }
            }
        }
        return ft
    }

    override fun getFragmentById(): Int {
        return 0
    }

}
