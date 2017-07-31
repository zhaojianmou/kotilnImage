package com.kele.androidstudio.kotlinimage.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseFragment
import com.kele.androidstudio.kotlinimage.image.utils.ExifUtils
import com.kele.androidstudio.kotlinimage.utils.baidu.LocationUtils
import kotlinx.android.synthetic.main.activity_localmap.*

class ListMapFragment : BaseFragment() {

    var imagePath = ""
    var mBaiduMap: BaiduMap? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_localmap
    }

    override fun initView(view: View?, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        mBaiduMap = activity.textureMapView.map
        if (!TextUtils.isEmpty(imagePath) && ExifUtils.isGpsExist(imagePath)) {
            var location = ExifUtils.getPhotoLocation(imagePath)
            var latLng = LocationUtils.gpsToBd09ll(LatLng(location.latitude, location.longitude))
            setMapTag(latLng, imagePath)
        }
    }

    fun setMapTag(latLng: LatLng, path: String) {
        Glide.with(activity).asBitmap().load("file://" + path).into(object : SimpleTarget<Bitmap>(80, 80) {
            override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                val bitmap = BitmapDescriptorFactory.fromBitmap(resource)
                val ooA = MarkerOptions().position(latLng).icon(bitmap)
                        .zIndex(9)
                ooA.animateType(MarkerOptions.MarkerAnimateType.drop)
                ooA.draggable(true)
                val builder = MapStatus.Builder()
                // 定位
                builder.target(latLng)
                mBaiduMap!!.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))
                mBaiduMap!!.addOverlay(ooA)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Glide.get(activity).clearMemory()
    }


    override fun onResume() {
        super.onResume()
        activity.textureMapView.onResume()

    }

    override fun onPause() {
        super.onPause()
        activity.textureMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Glide.get(activity).clearMemory()
        activity.textureMapView.onDestroy()

    }


}