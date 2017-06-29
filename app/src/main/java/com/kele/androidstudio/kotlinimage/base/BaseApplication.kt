package com.kele.androidstudio.kotlinimage.base

import com.facebook.drawee.backends.pipeline.Fresco
import com.kele.androidstudio.kotlinimage.BuildConfig
import com.kele.androidstudio.kotlinimage.base.api.InitApplication
import com.kele.androidstudio.kotlinimage.constant.AppConstant
import com.kele.androidstudio.kotlinimage.image.ImageManager
import com.kele.androidstudio.kotlinimage.utils.constant.ExitApp
import com.kele.androidstudio.kotlinimage.utils.constant.LeakCanaryUtils
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import com.kele.androidstudio.kotlinimage.utils.Utils


open class BaseApplication : InitApplication() {
    //动画组件


    override fun initAfter() {
        application = this
        //TODO 动态权限处理


    }


    override fun initData() {
        performance()
        //加载工具包
//        AppUtils.init(this)
        ImageManager.init(this)
        Fresco.initialize(this)


    }

    override fun initBefore() {


    }

    override fun performance() {
        LeakCanaryUtils.checkLeakCanary()

        //bugly异常信息统计
        // 设置是否为上报进程
        val strategy = UserStrategy(applicationContext)
        strategy.isUploadProcess = (applicationContext.packageName == null || applicationContext.packageName.equals(Utils.getProcessName(android.os.Process.myPid())))
        CrashReport.initCrashReport(applicationContext, AppConstant.BUGLY_APP_ID, BuildConfig.DEBUG, strategy)
    }


    override fun clearAppMemory() {

    }


    override fun exitApp() {
        ExitApp.exitApp()
    }


}