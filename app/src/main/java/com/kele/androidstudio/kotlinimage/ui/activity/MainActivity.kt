package com.kele.androidstudio.kotlinimage.ui.activity

import android.os.Bundle
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.ui.presenter.MainPersenter
import com.kele.androidstudio.kotlinimage.ui.view.MainView

class MainActivity : BaseActivity() {
    val TAG = "MainActivity"


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        var view = MainView<MainActivity>(this)
        var presenter = MainPersenter<MainView<MainActivity>>(view)
        view.setPersenter(presenter)

    }

    override fun initData() {

    }


}
