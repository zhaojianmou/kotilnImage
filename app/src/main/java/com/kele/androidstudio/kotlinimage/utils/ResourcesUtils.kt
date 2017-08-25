package com.kele.androidstudio.kotlinimage.utils

import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import com.kele.androidstudio.kotlinimage.base.api.InitApplication


class ResourcesUtils {

    companion object {
        val context = InitApplication.application

        fun getString(id: Int): String {
            return context.resources.getString(id)
        }

        fun getDrawable(id: Int): Drawable {
            return context.resources.getDrawable(id)
        }

        fun getColor(id: Int): Int {
            return context.resources.getColor(id)
        }

        fun getAssetManager(): AssetManager {
            return context.resources.assets
        }

    }

}