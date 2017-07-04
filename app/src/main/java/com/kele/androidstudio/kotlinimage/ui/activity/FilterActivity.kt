package com.kele.androidstudio.kotlinimage.ui.activity

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.View.OnClickListener
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.image.filter.GPUImageUtil
import jp.co.cyberagent.android.gpuimage.GPUImage
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : BaseActivity(), OnClickListener {


//    var gpuImage: GPUImage? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_filter
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initData() {
        super.initData()

        var path: String = Environment.getExternalStorageDirectory().absolutePath + "/UCDownloads/11.jpg"

        val into = Glide.with(this).load(path).into(imageView)

        seekBar.setOnSeekBarChangeListener(SeekBarImpl())
        button.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        var gpuImage = GPUImage(this)
        var bt = (imageView.drawable as BitmapDrawable).bitmap
        gpuImage.setImage(bt)
        gpuImage.setFilter(GPUImageUtil.getFilter(5))
        imageView.setImageBitmap(gpuImage.getBitmapWithFilterApplied())
    }

    class SeekBarImpl : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }

    }


}

