package com.kele.androidstudio.kotlinimage.utils.constant;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;

//跟App相关的辅助类
public class AppUtils
{
	/**
	 * 可以放在Application继承类中更好
	 */
	public static Context context;
	
	
	/**
	 * 获取应用程序名称
	 */
	public static String getAppName(Context context)
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取应用程序版本名称信息]
	 * 
	 * @param context
	 * @return 当前应用的版本名称
	 */
	public static String getVersionName(Context context)
	{
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 获取设备码IMEI
	 */
	public static String getIMEI() {
		// 获取普通机型的主机编号
		TelephonyManager telManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String IMEI = telManager.getDeviceId();
		// 如果获取不到，就用另外一种方法获取
		if (IMEI == null) {
			IMEI = android.provider.Settings.System.getString(
					context.getContentResolver(), "android_id");
		}
		return IMEI;
	}
	

}
