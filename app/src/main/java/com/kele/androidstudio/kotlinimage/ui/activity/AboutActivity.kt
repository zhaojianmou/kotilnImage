package com.kele.androidstudio.kotlinimage.ui.activity

import android.os.Bundle

import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import kotlinx.android.synthetic.main.title_bar_main.*

class AboutActivity : BaseActivity() {


    override fun getLayoutId(): Int {
        return R.layout.activity_about
    }

    override fun titleBar() {
        super.titleBar()
        titleTv.setText(R.string.about)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

    }


}
