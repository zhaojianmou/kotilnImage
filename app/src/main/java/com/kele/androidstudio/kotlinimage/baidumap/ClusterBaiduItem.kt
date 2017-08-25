package com.kele.androidstudio.kotlinimage.baidumap

import com.baidu.mapapi.clusterutil.clustering.ClusterItem
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.utils.BitmapTool

public class ClusterBaiduItem : ClusterItem {
    //经纬度
    var mPosition: LatLng? = null
    var mPath: String? = null

    constructor(latLng: LatLng, path: String) {
        mPosition = latLng
        mPath = path
    }

    override fun getPosition(): LatLng? {
        return mPosition
    }

    override fun getBitmapDescriptor(): BitmapDescriptor {
        if (mPath != null) {
//            var ro: RequestOptions = RequestOptions()
//            ro.centerCrop()
            var bitmap = BitmapTool.decodeSampledBitmapFromFile(mPath, 80, 80, 1)
//                    Glide.with(InitApplication.application).asBitmap().load("file://" + path).apply(ro).into(80, 80).get()
            return BitmapDescriptorFactory.fromBitmap(bitmap)
//            BitmapDescriptorFactory.fromPath(path)
        }
        return BitmapDescriptorFactory
                .fromResource(R.mipmap.default_map_icon)
    }

    override fun hashCode(): Int {
        return if (mPath != null) {
            mPath!!.hashCode()
        } else {
            super.hashCode()
        }
    }

    override fun equals(obj: Any?): Boolean {
        if (obj == null)
            return false
        if (javaClass != obj.javaClass)
            return false
        if (mPath != null) {
            val tmp = obj as ClusterBaiduItem?
            return mPath == tmp!!.mPath && mPosition == tmp!!.mPosition
        } else {
            return super.equals(obj)
        }
    }

}