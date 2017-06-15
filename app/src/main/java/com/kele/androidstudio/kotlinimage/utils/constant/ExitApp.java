package com.kele.androidstudio.kotlinimage.utils.constant;

import android.widget.Toast;

import com.kele.androidstudio.kotlinimage.base.BaseApplication;

public class ExitApp {
    private static long mExitTime;


    public static boolean exitDouble() {
        long current = System.currentTimeMillis();
        if ((current - mExitTime) > 2000) {
            ToastUtils.showShort(BaseApplication.application, "");
            mExitTime = current;
            return false;
        } else {
            return true;
        }
    }

    public static void exitApp() {
        //先关掉所有的activity，再杀死应用
        //双击退出
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);


    }


}
