package com.kele.androidstudio.kotlinimage.base.api

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class InitFragment : android.support.v4.app.Fragment() {
    var mInflater: LayoutInflater? = null

    override fun onAttach(context: android.content.Context?) {
        super.onAttach(context)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mInflater = inflater
        if (inflater != null && getLayoutId() != 0) {
            return inflater.inflate(getLayoutId(), container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
        initData()
    }


    override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    abstract fun getLayoutId(): Int

    abstract fun initData()

    abstract fun initView(view: View?, savedInstanceState: android.os.Bundle?)


    /**
     * 给fragment添加返回键
     */
    abstract fun onBackPressed()

    /**
     * 给fragment添加物理键
     */
    abstract fun onKeyDown(keyCode: Int, event: android.view.KeyEvent?): Boolean


}
