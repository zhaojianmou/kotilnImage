package com.kele.androidstudio.kotlinimage.base.api

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent

abstract class InitFragment : android.support.v4.app.Fragment() {

    override fun onAttach(context: android.content.Context?) {
        super.onAttach(context)

    }


    override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(savedInstanceState)
        initData()
    }

    abstract fun initData()

    abstract fun initView(savedInstanceState: android.os.Bundle?)

    /**
     * 给fragment添加返回键
     */
    abstract fun onBackPressed()

    /**
     * 给fragment添加物理键
     */
    abstract fun onKeyDown(keyCode: Int, event: android.view.KeyEvent?): Boolean


}
