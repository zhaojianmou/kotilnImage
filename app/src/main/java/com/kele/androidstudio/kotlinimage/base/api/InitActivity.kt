package com.kele.androidstudio.kotlinimage.base.api

abstract class InitActivity : com.zhy.autolayout.AutoLayoutActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        initView(savedInstanceState)
        initData()
    }

    abstract fun initView(savedInstanceState: android.os.Bundle?)

    abstract fun initData()

    abstract fun getCurrFragment(): InitFragment?

    abstract fun getFragmentById(): Int

}