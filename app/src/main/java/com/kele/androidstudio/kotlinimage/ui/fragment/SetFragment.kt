package com.kele.androidstudio.kotlinimage.ui.fragment

import android.os.Bundle
import android.view.View
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseFragment

class SetFragment : BaseFragment() {

    val TAG = "SetFragment"

    companion object {
        fun getInstance(): SetFragment {
            var fragment = SetFragment()
            return fragment
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_set
    }


    override fun initView(view: View?, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)

    }

    override fun initData() {
        super.initData()
    }


}
