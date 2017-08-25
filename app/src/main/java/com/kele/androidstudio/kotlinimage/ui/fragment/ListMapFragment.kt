package com.kele.androidstudio.kotlinimage.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.baidu.mapapi.clusterutil.MarkerManager
import com.baidu.mapapi.clusterutil.clustering.ClusterManager
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.baidumap.ClusterBaiduItem
import com.kele.androidstudio.kotlinimage.base.BaseFragment
import com.kele.androidstudio.kotlinimage.image.ImageManager
import com.kele.androidstudio.kotlinimage.image.utils.ExifUtils
import com.kele.androidstudio.kotlinimage.utils.GlideUtils
import com.kele.androidstudio.kotlinimage.utils.baidu.LocationUtils
import kotlinx.android.synthetic.main.activity_localmap.*
import java.util.ArrayList

class ListMapFragment : BaseFragment(), BaiduMap.OnMapLoadedCallback {

    var mBaiduMap: BaiduMap? = null
    var textureMapView: TextureMapView? = null
    var mMapStatus: MapStatus? = null

    var mMarkerManager: MarkerManager? = null
    var mClusterManager: ClusterManager<ClusterBaiduItem>? = null

    companion object {
        val PATH = "path"
        fun getInstance(): ListMapFragment {
            return ListMapFragment()
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_localmap
    }

    override fun initView(view: View?, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        textureMapView = activity.textureMapView
        mBaiduMap = textureMapView!!.map

        // 比例尺控件
        textureMapView!!.showScaleControl(true)
        // 缩放控件
        textureMapView!!.showZoomControls(false)
        // 百度地图LoGo -> 正式版切记不能这么做，本人只是觉得logo丑了
        textureMapView!!.removeViewAt(1)
        //不倾斜
        mBaiduMap!!.uiSettings.isOverlookingGesturesEnabled = false
        //不旋转
        mBaiduMap!!.uiSettings.isRotateGesturesEnabled = false
        mBaiduMap!!.setMaxAndMinZoomLevel(21f, 4f)

        mMapStatus = MapStatus.Builder().target(LatLng(39.914935, 116.403119)).zoom(8f).build()
        mBaiduMap!!.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus))

        //图标管理器
//        mMarkerManager = MarkerManager(mBaiduMap)
        //聚合与渲染管理器
        mClusterManager = ClusterManager<ClusterBaiduItem>(activity, mBaiduMap)

        mBaiduMap!!.setOnMapStatusChangeListener(mClusterManager)
        // 设置maker点击时的响应
        mBaiduMap!!.setOnMarkerClickListener(mClusterManager)

        mBaiduMap!!.setOnMapLoadedCallback(this)

        mBaiduMap!!.setMyLocationEnabled(true)

        addData()
        mClusterManager!!.setOnClusterClickListener(ClusterManager.OnClusterClickListener<ClusterBaiduItem> { cluster ->
            Toast.makeText(activity, "有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT).show()
            return@OnClusterClickListener false
        })
        mClusterManager!!.setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<ClusterBaiduItem> {
            Toast.makeText(activity, "点击单个Item", Toast.LENGTH_SHORT).show()
            return@OnClusterItemClickListener false
        })


//        if (!TextUtils.isEmpty(imagePath) && ExifUtils.isGpsExist(imagePath)) {
//            var location = ExifUtils.getPhotoLocation(imagePath)
//            var latLng = LocationUtils.gpsToBd09ll(LatLng(location.latitude, location.longitude))
//            setMapTag(latLng, imagePath)
//        }
    }

    fun addData() {
        val items = ArrayList<ClusterBaiduItem>()

        val list = ImageManager.getImageProvide().gpsImages
        if (list != null && list.size > 0) {

            var count = 0
            for (path: String in list) {
                count++
                if (count > 10) {
                    break
                }
                var location = ExifUtils.getPhotoLocation(path)
                var latLng = LocationUtils.gpsToBd09ll(LatLng(location.latitude, location.longitude))
                var item = ClusterBaiduItem(latLng, path)
                items.add(item)
            }
        }

        // 添加Marker点
//        val llA = LatLng(39.963175, 116.400244)
//        val llB = LatLng(39.942821, 116.369199)
//        val llC = LatLng(39.939723, 116.425541)
//        val llD = LatLng(39.906965, 116.401394)
//        val llE = LatLng(39.956965, 116.331394)
//        val llF = LatLng(39.886965, 116.441394)
//        val llG = LatLng(39.996965, 116.411394)
//
//        items.add(ClusterBaiduItem(llA))
//        items.add(ClusterBaiduItem(llB))
//        items.add(ClusterBaiduItem(llC))
//        items.add(ClusterBaiduItem(llD))
//        items.add(ClusterBaiduItem(llE))
//        items.add(ClusterBaiduItem(llF))
//        items.add(ClusterBaiduItem(llG))


        mClusterManager!!.addItems(items)
    }

    override fun onMapLoaded() {
        mMapStatus = MapStatus.Builder().zoom(9f).build()
        mBaiduMap!!.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mMapStatus))
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
        GlideUtils.clearMemory()
    }


    override fun onResume() {
        super.onResume()
        textureMapView!!.onResume()

    }

    override fun onPause() {
        super.onPause()
        textureMapView!!.onPause()
    }

    override fun onDestroy() {
        GlideUtils.clearMemory()
        textureMapView!!.onDestroy()
        super.onDestroy()
    }


}