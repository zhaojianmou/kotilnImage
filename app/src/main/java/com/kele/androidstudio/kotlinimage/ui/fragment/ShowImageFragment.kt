package com.kele.androidstudio.kotlinimage.ui.fragment

import android.os.Bundle
import android.view.View
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseFragment
import com.kele.androidstudio.kotlinimage.ui.presenter.ShowImagePersenter
import com.kele.androidstudio.kotlinimage.ui.view.ShowImageView

class ShowImageFragment : BaseFragment() {

    val TAG = "ShowImageFragment"

    companion object {
        fun getInstance(): ShowImageFragment {
            var fragment = ShowImageFragment()
            return fragment
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_showimage
    }


    override fun initView(view: View?, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        var view = ShowImageView<ShowImageFragment>(this)
        var presenter = ShowImagePersenter<ShowImageView<ShowImageFragment>>(view)
        view.setPersenter(presenter)

    }

    override fun initData() {
        super.initData()
    }


}
