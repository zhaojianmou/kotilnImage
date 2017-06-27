package com.kele.androidstudio.kotlinimage.utils.constant;


import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.facebook.stetho.Stetho;
import com.kele.androidstudio.kotlinimage.BuildConfig;
import com.kele.androidstudio.kotlinimage.base.api.InitApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.lang.reflect.Field;

public class LeakCanaryUtils {
    /**
     * 添加崩溃日志、添加web调试工具、添加内存泄漏
     */
    public static void checkLeakCanary() {
        //奔溃日志
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(InitApplication.application);
        if (BuildConfig.DEBUG) {
            //LeakCanary
            InitApplication.application.setRefWatcher(LeakCanary.install(InitApplication.application));
            //Stetho web调试工具
            Stetho.initializeWithDefaults(InitApplication.application);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //android 调试工具
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        //会造成屏幕闪烁，不过一般的设备可能没有这个功能。
                        .penaltyFlashScreen()
                        //记录到 dropbox 系统日志目录中（/data/system/dropbox）
                        //命令 adb shell dumpsys dropbox dataappstrictmode  --print   /   adb shell dumpsys dropbox --print >>log.java
//                    .penaltyDropBox()
                        //打出log提示
                        .penaltyLog()
                        .build());
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
//                    .penaltyDropBox()
                        .build());
            }
        } else {
            InitApplication.application.setRefWatcher(RefWatcher.DISABLED);
        }

    }


    /**
     * 解决InputMethodManager内存泄露现象
     */
    public static void fixInputMethodManagerLeak(Context destContext) {
        //有待研究
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f;
        Object obj_get;
        for (String param : arr) {
            try {
                f = imm.getClass().getDeclaredField(param);
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get
                            .getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        /*if (QLog.isColorLevel()) {
                            QLog.d(ReflecterHelper.class.getSimpleName(), QLog.CLR, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
                        }*/
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


}
