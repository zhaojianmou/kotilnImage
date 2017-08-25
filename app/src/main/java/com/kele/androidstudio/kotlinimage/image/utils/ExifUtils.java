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

    public static Boolean isGpsExist(String imagePath) {
        Boolean isGps = false;
        try {
            ExifInterface exifInterface = new ExifInterface(imagePath);
            String latValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String lngValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            String latRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String lngRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
            if (latValue != null && latRef != null && lngValue != null && lngRef != null) {
                isGps = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isGps;
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


//    String LATITUDE = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
//    String LATITUDE_REF = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
//    String LONGITUDE = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
//    String LONGITUDE_REF = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
//
//    // your Final lat Long Values
//    Double Latitude = new Double(0);
//    Double Longitude = new Double(0);
//
//
//                if ((LATITUDE != null)
//            && (LATITUDE_REF != null)
//            && (LONGITUDE != null)
//            && (LONGITUDE_REF != null)) {
//
//        if (LATITUDE_REF.equals("N")) {
//            Latitude = convertToDegree(LATITUDE);
//        } else {
//            Latitude = 0 - convertToDegree(LATITUDE);
//        }
//
//        if (LONGITUDE_REF.equals("E")) {
//            Longitude = convertToDegree(LONGITUDE);
//        } else {
//            Longitude = 0 - convertToDegree(LONGITUDE);
//        }
//
//    } else {
//        continue;
//    }


    private static Double convertToDegree(String stringDMS) {
        Double result = null;
        String[] DMS = stringDMS.split(",", 3);

        String[] stringD = DMS[0].split("/", 2);
        Double D0 = new Double(stringD[0]);
        Double D1 = new Double(stringD[1]);
        Double FloatD = D0 / D1;

        String[] stringM = DMS[1].split("/", 2);
        Double M0 = new Double(stringM[0]);
        Double M1 = new Double(stringM[1]);
        Double FloatM = M0 / M1;

        String[] stringS = DMS[2].split("/", 2);
        Double S0 = new Double(stringS[0]);
        Double S1 = new Double(stringS[1]);
        Double FloatS = S0 / S1;

        result = new Double(FloatD + (FloatM / 60) + (FloatS / 3600));

        return result;


    }


}
