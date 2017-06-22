package com.kele.androidstudio.kotlinimage.base.api

import android.view.LayoutInflater

abstract class InitActivity : com.zhy.autolayout.AutoLayoutActivity() {
    var mInflater: LayoutInflater? = null

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        mInflater = layoutInflater
        initAfter()
        if (getLayoutId() != 0) {
            setContentView(getLayoutId())
        }
        titleBar()
        initView(savedInstanceState)
        initData()
    }

    abstract fun initAfter()

    abstract fun getLayoutId(): Int

    abstract fun titleBar()

    abstract fun initView(savedInstanceState: android.os.Bundle?)

    abstract fun initData()

    abstract fun getCurrFragment(): InitFragment?

    abstract fun getFragmentById(): Int

}