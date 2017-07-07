package com.kele.androidstudio.kotlinimage.image.utils;


import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;

public class ExifUtils {

    // TAG_DATETIME时间日期
    // TAG_FLASH闪光灯
    // TAG_GPS_LATITUDE纬度
    // TAG_GPS_LATITUDE_REF纬度参考
    // TAG_GPS_LONGITUDE经度
    // TAG_GPS_LONGITUDE_REF经度参考
    // TAG_IMAGE_LENGTH图片长
    // TAG_IMAGE_WIDTH图片宽
    // TAG_MAKE设备制造商
    // TAG_MODEL设备型号
    // TAG_ORIENTATION方向
    // TAG_WHITE_BALANCE白平衡


    public static Location getPhotoLocation(String imagePath) {
        float output1 = 0;
        float output2 = 0;
        Location location;

        try {
            ExifInterface exifInterface = new ExifInterface(imagePath);
            String datetime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME); // 拍摄时间
            String deviceName = exifInterface.getAttribute(ExifInterface.TAG_MAKE); // 设备品牌
            String deviceModel = exifInterface.getAttribute(ExifInterface.TAG_MODEL); // 设备型号
            String latValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String lngValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            String latRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String lngRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
            if (latValue != null && latRef != null && lngValue != null && lngRef != null) {
                try {
                    output1 = convertRationalLatLonToFloat(latValue, latRef);
                    output2 = convertRationalLatLonToFloat(lngValue, lngRef);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(output1);
        location.setLongitude(output2);
        return location;
    }


    private static float convertRationalLatLonToFloat(String rationalString, String ref) {

        String[] parts = rationalString.split(",");

        String[] pair;
        pair = parts[0].split("/");
        double degrees = Double.parseDouble(pair[0].trim())
                / Double.parseDouble(pair[1].trim());

        pair = parts[1].split("/");
        double minutes = Double.parseDouble(pair[0].trim())
                / Double.parseDouble(pair[1].trim());

        pair = parts[2].split("/");
        double seconds = Double.parseDouble(pair[0].trim())
                / Double.parseDouble(pair[1].trim());

        double result = degrees + (minutes / 60.0) + (seconds / 3600.0);
        if ((ref.equals("S") || ref.equals("W"))) {
            return (float) -result;
        }
        return (float) result;
    }


}
