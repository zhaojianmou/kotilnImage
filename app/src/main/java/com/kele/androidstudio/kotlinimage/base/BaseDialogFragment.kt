package com.kele.androidstudio.kotlinimage.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import com.kele.androidstudio.kotlinimage.base.api.InitDialogFragment


class BaseDialogFragment : InitDialogFragment() {

    override fun initData() {

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onKeyDown(dialogInterface: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }


}
