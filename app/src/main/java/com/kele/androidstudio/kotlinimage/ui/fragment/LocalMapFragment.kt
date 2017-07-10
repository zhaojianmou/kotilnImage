package com.kele.androidstudio.kotlinimage.ui.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.model.LatLng
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseFragment
import com.kele.androidstudio.kotlinimage.image.utils.ExifUtils
import com.kele.androidstudio.kotlinimage.utils.baidu.LocationUtils
import kotlinx.android.synthetic.main.activity_localmap.*

/**
 * 图片gps定位
 *
 */
class LocalMapFragment : BaseFragment() {

    var imagePath = ""
    var mBaiduMap: BaiduMap? = null


    companion object {
        val PATH = "path"
        fun getInstance(path: String): LocalMapFragment {
            var bundle = Bundle()
            bundle.putString(PATH, path)
            var fragment = LocalMapFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_localmap
    }

    override fun initView(view: View?, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        if (TextUtils.isEmpty(imagePath)) {
            imagePath = arguments.getString(PATH)
        }
        mBaiduMap = activity.mapView.map
        if (!TextUtils.isEmpty(imagePath) && ExifUtils.isGpsExist(imagePath)) {
            var location = ExifUtils.getPhotoLocation(imagePath)
            var latLng = LocationUtils.gpsToBd09ll(LatLng(location.latitude, location.longitude))
            Glide.with(activity).asBitmap().load("file://" + imagePath).into(object : SimpleTarget<Bitmap>(80, 80) {
                override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                    LocationUtils.locationMap(mBaiduMap, latLng, resource)
                }
            })
        }
    }


    override fun onResume() {
        super.onResume()
        activity.mapView.onResume()

    }

    override fun onPause() {
        super.onPause()
        activity.mapView.onPause()
    }

    override fun onDestroy() {
        LocationUtils.locationCloseMap(mBaiduMap)
        activity.mapView.onDestroy()
        super.onDestroy()

    }
}
