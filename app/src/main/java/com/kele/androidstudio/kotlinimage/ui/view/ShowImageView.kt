package com.kele.androidstudio.kotlinimage.ui.view

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.jack.commonlibrary.utils.LogUtils
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.constant.UIConstant
import com.kele.androidstudio.kotlinimage.image.ImageManager
import com.kele.androidstudio.kotlinimage.image.utils.ExifUtils
import com.kele.androidstudio.kotlinimage.ui.adapter.ShowImageAdapter
import com.kele.androidstudio.kotlinimage.ui.base.UIManager
import com.kele.androidstudio.kotlinimage.ui.contract.ShowImageContract
import com.kele.androidstudio.kotlinimage.utils.ResourcesUtils
import com.kele.androidstudio.kotlinimage.utils.constant.ToastUtils
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

        getActivity()!!.recyclerView.layoutManager = LinearLayoutManager(getActivity()) as RecyclerView.LayoutManager?
        getActivity()!!.recyclerView.setHasFixedSize(true)
        adapter = ShowImageAdapter(getActivity() as Activity, list, this)
        getActivity()!!.recyclerView.adapter = adapter
        LogUtils.e(this.javaClass.name, "adapter")
        getActivity()!!.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_DRAGGING -> Glide.with(getActivity()).resumeRequests()
                    RecyclerView.SCROLL_STATE_SETTLING -> Glide.with(getActivity()).pauseRequests()
                    RecyclerView.SCROLL_STATE_IDLE -> Glide.with(getActivity()).resumeRequests()
                }
            }
        })

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
                if (ExifUtils.isGpsExist(path as String)) {
                    bundler.putString(UIConstant.IMAGE_PATH, path as String)
                    bundler.putString(UIConstant.MAP_TYPE, UIConstant.TYPE_IMAGE)
                    UIManager.toMap(getActivity()!!, bundler)
                } else {
                    ToastUtils.showShort(getActivity(), ResourcesUtils.getString(R.string.image_localtion_info_no))
                }
            } else if (UIConstant.BROWSE_SHOW.equals(type)) {
                bundler.putString(UIConstant.IMAGE_PATH, "file://" + path)
                UIManager.toBrowse(getActivity()!!, bundler)
            }
        }
    }

}