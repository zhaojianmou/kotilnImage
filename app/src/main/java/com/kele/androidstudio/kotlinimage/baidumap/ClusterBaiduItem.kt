package com.kele.androidstudio.kotlinimage.baidumap

import com.baidu.mapapi.clusterutil.clustering.ClusterItem
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.utils.BitmapTool

class ClusterBaiduItem : ClusterItem {
    //经纬度
    var mPosition: LatLng? = null
    var path: String? = null

    constructor(latLng: LatLng, path: String) {
        mPosition = latLng
        this.path = path
    }

    override fun getPosition(): LatLng? {
        return mPosition
    }

    override fun getBitmapDescriptor(): BitmapDescriptor {
        if (path != null) {
//            var ro: RequestOptions = RequestOptions()
//            ro.centerCrop()
            var bitmap = BitmapTool.decodeSampledBitmapFromFile(path, 80, 80, 1)
//                    Glide.with(InitApplication.application).asBitmap().load("file://" + path).apply(ro).into(80, 80).get()
            return BitmapDescriptorFactory.fromBitmap(bitmap)
//            BitmapDescriptorFactory.fromPath(path)
        }
        return BitmapDescriptorFactory
                .fromResource(R.mipmap.default_map_icon)
    }
}