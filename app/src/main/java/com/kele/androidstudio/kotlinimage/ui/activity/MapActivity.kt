package com.kele.androidstudio.kotlinimage.ui.activity

import android.os.Bundle
import android.view.View
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.model.LatLng
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.image.ImageManager
import com.kele.androidstudio.kotlinimage.image.utils.ExifUtils
import com.kele.androidstudio.kotlinimage.utils.baidu.LocationUtils
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : BaseActivity(), View.OnClickListener {


    var mMap: BaiduMap? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_map
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        button.setOnClickListener(this)

    }

    override fun initData() {
        super.initData()
        mMap = mapView.map


    }


    override fun onClick(v: View?) {
        val list = ImageManager.getImageProvide().images
        var str = "/storage/emulated/0/DCIM/IMG_6424.jpg"
        var location = ExifUtils.getPhotoLocation(str)
        //  var latLng = LatLng(40.05688536780656, 116.30814181679612)
        var latLng = LocationUtils.mapToBd09ll(LatLng(location.latitude, location.longitude))
        var latLng1 = LocationUtils.gpsToBd09ll(LatLng(location.latitude, location.longitude))
        LocationUtils.locationMap(mMap, latLng)
    }


    override fun onDestroy() {
        super.onDestroy()
        LocationUtils.locationCloseMap(mMap)
    }


}
