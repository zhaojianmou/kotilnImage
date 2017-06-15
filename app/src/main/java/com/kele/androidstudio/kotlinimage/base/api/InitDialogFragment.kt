package com.kele.androidstudio.kotlinimage.base.api

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.KeyEvent

abstract class InitDialogFragment : android.support.v4.app.DialogFragment(), android.content.DialogInterface.OnKeyListener {

    override fun onAttach(context: android.content.Context?) {
        super.onAttach(context)

    }


    override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(savedInstanceState)
        initData()
        dialog.setOnKeyListener(this)
    }

    abstract fun initData()

    abstract fun initView(savedInstanceState: android.os.Bundle?)

    override fun onKey(p0: android.content.DialogInterface?, p1: Int, p2: android.view.KeyEvent?): Boolean {
        return onKeyDown(p0, p1, p2)
    }

    /**
     * 给fragment添加物理键
     */
    abstract fun onKeyDown(dialogInterface: android.content.DialogInterface?, keyCode: Int, event: android.view.KeyEvent?): Boolean

}
