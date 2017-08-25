package com.kele.androidstudio.kotlinimage.utils

import android.R
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.kele.androidstudio.kotlinimage.base.api.InitApplication

class GlideUtils {

    companion object {
        val context = InitApplication.application
        fun clearMemory() {
            Glide.get(context).clearMemory()
        }

        fun clearDiskCache() {
            Glide.get(context).clearDiskCache()
        }

        fun loadImage(url: String, imageView: ImageView) {
            val options = RequestOptions()
            options.override(imageView.width, imageView.height)
            options.placeholder(ColorDrawable(R.color.black))
            Glide.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade()).apply(options).into(imageView)
        }


    }


}