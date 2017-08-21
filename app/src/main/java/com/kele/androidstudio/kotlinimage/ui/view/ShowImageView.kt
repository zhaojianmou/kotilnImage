package com.kele.androidstudio.kotlinimage.ui.view

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kele.androidstudio.kotlinimage.constant.UIConstant
import com.kele.androidstudio.kotlinimage.image.ImageManager
import com.kele.androidstudio.kotlinimage.ui.adapter.ShowImageAdapter
import com.kele.androidstudio.kotlinimage.ui.base.UIManager
import com.kele.androidstudio.kotlinimage.ui.contract.ShowImageContract
import kotlinx.android.synthetic.main.activity_showimage.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_bar_main.*

class ShowImageView<T> : BaseViewImpl<T>, ShowImageContract.View, ShowImageAdapter.OnItemClickListener {

    var adapter: ShowImageAdapter? = null

    constructor(t: T) {
        init(t)
    }


    override fun titleBar() {

    }


    override fun initView() {
        val list = ImageManager.getImageProvide().images

        getActivity()!!.recyclerView.layoutManager = LinearLayoutManager(getActivity())
        getActivity()!!.recyclerView.setHasFixedSize(true)
        adapter = ShowImageAdapter(getActivity() as Activity, list, this)
        getActivity()!!.recyclerView.adapter = adapter

    }

    override fun initUpdateView() {

    }

    override fun UpdateView(any: Any?) {

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onItemClick(view: View?, position: Int) {
        if (view != null) {
            var type = getActivity()!!.rightTv.text.toString()
            var path = adapter!!.getPath(position)
            var bundler = Bundle()
            if (UIConstant.BROWSE_LOCATION.equals(type)) {
                bundler.putString(UIConstant.IMAGE_PATH, path as String)
                UIManager.toMap(getActivity()!!, bundler)
            } else {
                bundler.putString(UIConstant.IMAGE_PATH, "file://" + path)
                UIManager.toBrowse(getActivity()!!, bundler)
            }
        }
    }

}