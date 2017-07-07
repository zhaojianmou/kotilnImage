package com.kele.androidstudio.kotlinimage.utils.baidu;


import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.kele.androidstudio.kotlinimage.R;

public class LocationUtils {


    public static void locationMap(BaiduMap mBaiduMap, LatLng location) {
        if (mBaiduMap == null || location == null) {
            return;
        }

        //  开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(0)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.latitude)
                .longitude(location.longitude).build();
        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//        String path = "/storage/emulated/0/DCIM/Camera/20170222_152004.jpg";
//        File file = new File(path);
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
//                .fromFile(path);
        //      fromResource(R.mipmap.ic_launcher);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);
        //动画
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        // 当不需要定位图层时关闭定位图层
        //  mBaiduMap.setMyLocationEnabled(false);
    }

    public static void locationCloseMap(BaiduMap mBaiduMap) {
        if (mBaiduMap != null && mBaiduMap.isMyLocationEnabled()) {
            // 当不需要定位图层时关闭定位图层
            mBaiduMap.setMyLocationEnabled(false);
        }

    }

    public static LatLng gpsToBd09ll(LatLng sourceLatLng) {
        // 将GPS设备采集的原始GPS坐标转换成百度坐标
        CoordinateConverter converter = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.GPS);
        // sourceLatLng待转换坐标
        converter.coord(sourceLatLng);
        return converter.convert();
    }

    public static LatLng mapToBd09ll(LatLng sourceLatLng) {
        // 将google地图、soso地图、aliyun地图、mapabc地图和amap地图// 所用坐标转换成百度坐标
        CoordinateConverter converter = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.COMMON);
        // sourceLatLng待转换坐标
        converter.coord(sourceLatLng);
        return converter.convert();
    }


}
