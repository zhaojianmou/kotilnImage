package com.kele.androidstudio.kotlinimage.ui.fragment

import android.os.Bundle
import android.view.View
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseFragment

class TrackMapFragment : BaseFragment() {


    companion object {
        fun getInstance(): LocalMapFragment {
            var fragment = LocalMapFragment()
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_trackmap
    }


    override fun initView(view: View?, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)

    }


}
